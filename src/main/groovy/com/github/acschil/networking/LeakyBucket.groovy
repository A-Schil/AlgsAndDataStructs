package com.github.acschil.networking

import com.github.acschil.networking.exceptions.RateLimitExceededException

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class LeakyBucket {
    private int bufferCapacity
    private int elementsInBuffer
    private ArrayDeque<String> buffer
    private int eventsPerSecond
    private ScheduledExecutorService executor
    private final Object eventLock
    private List destination

    /**
     * Processes events at a steady rate, queueing them up if the input rate exceeds the output rate.
     *
     * Events are just string in this case. They get forwarded to another list at a steady rate. In a less contrived
     * implementation, this could be used to rate limit requests per second in a reverse proxy, where each
     * client/service-provider pairing would have its own bucket.
     */
    LeakyBucket(int bufferCapacity, int eventsPerSecond, LinkedList<String> destination) {
        this.eventsPerSecond = eventsPerSecond
        this.bufferCapacity = bufferCapacity
        this.buffer = new ArrayDeque<>(bufferCapacity)
        this.elementsInBuffer = 0
        this.destination = destination
        this.eventLock = new Object()
        Runnable eventProcessor = new Runnable() {
            void run() {
                synchronized (eventLock) {
                    String event = buffer.poll()
                    if (event) {
                        destination.add(event)
                        elementsInBuffer--
                        println "[${System.currentTimeMillis()}] forwarded event: ${event}"
                    }
                }
            }
        }

        this.executor = Executors.newScheduledThreadPool(1)
        this.executor.scheduleAtFixedRate(eventProcessor, 0, (int) (1000 / eventsPerSecond), TimeUnit.MILLISECONDS)
    }

    void makeRequest(String request) {
        synchronized (eventLock) {
            if (elementsInBuffer < bufferCapacity) {
                buffer.addLast(request)
                elementsInBuffer++
            } else {
                // tail-drop
                throw new RateLimitExceededException()
            }
        }
    }

}

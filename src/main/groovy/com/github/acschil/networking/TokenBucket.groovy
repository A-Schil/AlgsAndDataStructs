package com.github.acschil.networking

import com.github.acschil.networking.exceptions.RateLimitExceededException

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class TokenBucket {
    private int maxTokens
    private int numTokens
    private int tokensPerSecond
    private final Object tokenLock
    private ScheduledExecutorService executor
    private List destination

    /**
     * Processes events if there is a token to do so, else discards event.
     *
     * Events are just string in this case. They get forwarded to another list if there is a token to do so.
     * In a less contrived implementation, this could be used to rate limit requests per second in a reverse proxy,
     * where each client/service-provider pairing would have its own bucket.
     */
    TokenBucket(int maxTokens, int tokensPerSecond, LinkedList<String> destination) {
        this.maxTokens = maxTokens
        this.numTokens = 0
        this.tokensPerSecond = tokensPerSecond
        this.destination = destination
        this.tokenLock = new Object()
        Runnable tokenGenerator = new Runnable() {
            void run() {
                synchronized (tokenLock) {
                    if (numTokens < maxTokens) {
                        numTokens++
                    }
                }
            }
        }

        if (tokensPerSecond) {
            this.executor = Executors.newScheduledThreadPool(1)
            this.executor.scheduleAtFixedRate(tokenGenerator, 0, (int) (1000 / tokensPerSecond), TimeUnit.MILLISECONDS)
        }
    }

    void makeRequest(String request) {
        synchronized (tokenLock) {
            if (numTokens > 0) {
                destination.add(request)
                numTokens--
                println "[${System.currentTimeMillis()}] forwarded event: ${request}"
            } else {
                // tail-drop
                throw new RateLimitExceededException()
            }
        }
    }
}

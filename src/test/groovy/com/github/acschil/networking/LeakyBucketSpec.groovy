package com.github.acschil.networking

import com.github.acschil.networking.exceptions.RateLimitExceededException
import spock.lang.Specification

class LeakyBucketSpec extends Specification {
    def "input rate is less than output rate"() {
        setup:
        LinkedList<String> destination = new LinkedList<>()
        LeakyBucket leakyBucket = new LeakyBucket(20, 50, destination)
        int eventsSent = 0

        when:
        while (eventsSent < 20) {
            leakyBucket.makeRequest(String.valueOf(eventsSent))
            eventsSent++
        }
        sleep(1000)

        then:
        destination.size() == 20

        cleanup:
        leakyBucket.executor.shutdown()
    }

    def "short burst - input rate exceeds output rate"() {
        setup:
        LinkedList<String> destination = new LinkedList<>()
        LeakyBucket leakyBucket = new LeakyBucket(20, 5, destination)
        int eventsSent = 0

        when:
        while (eventsSent <= 20) {
            leakyBucket.makeRequest(String.valueOf(eventsSent))
            eventsSent++
        }

        sleep(1000)

        then:
        destination.containsAll(["0", "1", "2", "3", "4"])
        destination.size() < 10

        cleanup:
        leakyBucket.executor.shutdown()
    }

    def "extended burst - bucket overflows and tail dropping occurs"() {
        setup:
        LinkedList<String> destination = new LinkedList<>()
        LeakyBucket leakyBucket = new LeakyBucket(10, 10, destination)
        int eventsSent = 0

        when:
        while (eventsSent <= 50) {
            leakyBucket.makeRequest(String.valueOf(eventsSent))
            eventsSent++
        }

        then:
        thrown(RateLimitExceededException)

        when:
        sleep(1000)

        then:
        destination.size() >= 10
        println(destination)

        cleanup:
        leakyBucket.executor.shutdown()
    }
}

package com.github.acschil.networking

import com.github.acschil.networking.exceptions.RateLimitExceededException
import spock.lang.Specification

class TokenBucketSpec extends Specification {
    def "can handle burst if sufficient tokens built up"() {
        setup:
        LinkedList<String> destination = new LinkedList<>()
        TokenBucket tokenBucket = new TokenBucket(20, 50, destination)
        int eventsSent = 0

        when:
        sleep(1000)

        then:
        tokenBucket.numTokens == 20

        when:
        while (eventsSent < 20) {
            tokenBucket.makeRequest(String.valueOf(eventsSent))
            eventsSent++
        }

        then:
        destination.size() == 20
    }

    def "rejects requests if insufficient tokens"() {
        setup:
        LinkedList<String> destination = new LinkedList<>()
        TokenBucket tokenBucket = new TokenBucket(20, 0, destination)

        when:
            tokenBucket.makeRequest("event 1")


        then:
        thrown(RateLimitExceededException)
    }

    def "extended burst - tokens exhausted"() {
        setup:
        LinkedList<String> destination = new LinkedList<>()
        TokenBucket tokenBucket = new TokenBucket(10, 10, destination)
        int eventsSent = 0

        when:
        sleep(1000)
        while (eventsSent < 50) {
            tokenBucket.makeRequest(String.valueOf(eventsSent))
            eventsSent++
        }

        then:
        thrown(RateLimitExceededException)
        destination.containsAll(["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"])
    }
}

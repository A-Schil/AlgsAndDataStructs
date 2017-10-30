package com.github.acschil.sorting

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.ForkJoinPool

class ThreadedQuickSortSpec extends Specification {

    @Unroll
    def "correctly sorts Integer"() {
        setup:
        ForkJoinPool pool = new ForkJoinPool()
        ThreadedQuickSort<Integer> quicksort = new ThreadedQuickSort<Integer>(input as List<Integer>)


        when:
        pool.invoke(quicksort)

        then:
        input == expect

        where:
        input                                          || expect
        []                                             || []
        [1, 2, 3]                                      || [1, 2, 3]
        [1, 5, 6, 9, 2]                                || [1, 2, 5, 6, 9]
        [5]                                            || [5]
        [1, 2, 2, 2, 2, 3]                             || [1, 2, 2, 2, 2, 3]
        [2.0, 4.8, 3.995, 3.99999, 1, 9.2357, 3.14159] || [1, 2.0, 3.14159, 3.995, 3.99999, 4.8, 9.2357]
        [1, -1, 0]                                     || [-1, 0, 1]
    }

    @Unroll
    def "correctly sorts String"() {
        setup:
        ForkJoinPool pool = new ForkJoinPool()
        ThreadedQuickSort<String> quicksort = new ThreadedQuickSort<String>(input as List<String>)

        when:
        pool.invoke(quicksort)

        then:
        input == expect

        where:
        input                   || expect
        ["A", "C", "D", "B"]    || ["A", "B", "C", "D"]
        ["A", "B", "C"]         || ["A", "B", "C"]
        ["B", "a", "A", "b"]    || ["A", "B", "a", "b"]
        ["dog", "cat", "doggo"] || ["cat", "dog", "doggo"]
    }

    @Ignore
    def "sorts faster than non-threaded version on large input"() {
        setup:
        List<Double> quicksortedList = new LinkedList<>()
        List<Double> threadedQuicksortedList = new LinkedList<>()
        (1..10_000).count {
            Double num = Math.random()
            quicksortedList.add(num)
            threadedQuicksortedList.add(num)
        }

        when:
        long startOfQuicksort = System.currentTimeMillis()
        Quicksort.sort(quicksortedList)
        long endOfQuicksort = System.currentTimeMillis()
        long elapsedTimeQuicksorting = endOfQuicksort - startOfQuicksort
        println "Quicksort: ${elapsedTimeQuicksorting / 1000}s"

        ForkJoinPool pool = new ForkJoinPool()
        ThreadedQuickSort<Double> threadedQuicksort = new ThreadedQuickSort<Double>(threadedQuicksortedList)
        long startOfThreadedQuicksort = System.currentTimeMillis()
        pool.invoke(threadedQuicksort)
        long endOfThreadedQuicksort = System.currentTimeMillis()
        long elapsedTimeQuicksortingWithThreads = endOfThreadedQuicksort - startOfThreadedQuicksort
        println "Threaded Quicksort: ${elapsedTimeQuicksortingWithThreads / 1000}s"

        then:
        quicksortedList == threadedQuicksort.data
        elapsedTimeQuicksortingWithThreads < elapsedTimeQuicksorting
    }
}


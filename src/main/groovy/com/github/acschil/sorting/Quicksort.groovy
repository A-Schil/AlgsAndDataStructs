package com.github.acschil.sorting

class Quicksort {

    static <T extends Comparable<? super T>> void sort(List<T> list) {
        quicksort(list, 0, list.size() - 1)
    }

    private static <T extends Comparable<? super T>> void quicksort(List<T> list,
                                                                    int startingIndex,
                                                                    int endingIndex) {
        if (startingIndex < endingIndex) {
            int pivotIndex = partition(list, startingIndex, endingIndex)
            quicksort(list, startingIndex, pivotIndex - 1)
            quicksort(list, pivotIndex + 1, endingIndex)
        }
    }

    protected static <T extends Comparable<? super T>> int partition(List<T> list,
                                                                   int startingIndex,
                                                                   int endingIndex) {
        T pivotElement = list[endingIndex]
        int pivotIndex = startingIndex - 1
        (startingIndex..(endingIndex - 1)).each{
            if ((list[it] <=> pivotElement) <= 0) {
                pivotIndex++
                SortingUtilities.exchange(list, pivotIndex, it)
            }
        }
        SortingUtilities.exchange(list, pivotIndex + 1, endingIndex)
        return pivotIndex + 1
    }
}
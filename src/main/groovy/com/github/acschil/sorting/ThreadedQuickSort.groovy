package com.github.acschil.sorting

import java.util.concurrent.RecursiveAction

class ThreadedQuickSort<T extends Comparable> extends RecursiveAction {
    List<T> data
    int startingIndex
    int endingIndex

    ThreadedQuickSort(List<T> list) {
        this(list, 0, list.size() - 1)

    }

    private ThreadedQuickSort(List<T> list, int startingIndex, int endingIndex) {
        this.data = list
        this.startingIndex = startingIndex
        this.endingIndex = endingIndex
    }

    @Override
    protected void compute() {
        if (startingIndex < endingIndex) {
            int pivotIndex = Quicksort.partition(data, startingIndex, endingIndex)

            invokeAll(new ThreadedQuickSort(data, startingIndex, pivotIndex-1),
                    new ThreadedQuickSort(data, pivotIndex+1, endingIndex))
        }
    }
}

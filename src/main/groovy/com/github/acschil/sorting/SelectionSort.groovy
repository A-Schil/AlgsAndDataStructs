package com.github.acschil.sorting

class SelectionSort {
    static <T extends Comparable<? super T>> void sort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T smallestSeenSoFar = list[i]
            int indexOfSmallestSeenSoFar = i
            for (int j = i + 1; j < list.size(); j++) {
                T candidate = list[j]
                if ((candidate <=> smallestSeenSoFar) < 0) {
                    smallestSeenSoFar = candidate
                    indexOfSmallestSeenSoFar = j
                }
            }
            if (indexOfSmallestSeenSoFar != i) {
                SortingUtilities.exchange(list, i, indexOfSmallestSeenSoFar)
            }
        }
    }
}

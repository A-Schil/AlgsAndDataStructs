package com.github.acschil.sorting

class InsertionSort {
    static <T extends Comparable<? super T>> void sort(List<T> list) {
        int progressIndex = 0
        while (progressIndex < list.size()) {
            int indexOfElementBeingSorted = progressIndex
            int indexToCompareAgainst = progressIndex - 1
            while (indexToCompareAgainst >= 0 &&
                    list[indexToCompareAgainst].compareTo(list[indexOfElementBeingSorted]) > 0) {
                SortingUtilities.exchange(list, indexToCompareAgainst, indexOfElementBeingSorted)
                indexToCompareAgainst--
                indexOfElementBeingSorted--
            }
            progressIndex++
        }
    }
}

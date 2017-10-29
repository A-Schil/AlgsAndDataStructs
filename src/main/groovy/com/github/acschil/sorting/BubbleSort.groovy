package com.github.acschil.sorting

class BubbleSort {
    static <T extends Comparable<? super T>> void sort(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if ((list[j] <=> list[j + 1]) > 0) {
                    SortingUtilities.exchange(list, j, j+1)
                }
            }
        }
    }
}

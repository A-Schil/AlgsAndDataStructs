package com.github.acschil.sorting

class SortingUtilities {
    static <T extends Comparable<? super T>> void exchange(List<T> list, int index1, int index2) {
        if (index1 >= list.size() || index2 >= list.size() || index1 < 0 || index2 < 0) {
            throw new IndexOutOfBoundsException()
        }
        T tmp = list[index1]
        list[index1] = list[index2]
        list[index2] = tmp
    }
}

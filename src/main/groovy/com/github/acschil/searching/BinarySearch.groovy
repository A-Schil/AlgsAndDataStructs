package com.github.acschil.searching

class BinarySearch<T> {
    static int find(List<T> list, T elementToFind) {
        int lowerIndex = 0
        int upperIndex = list.size() - 1

        while (lowerIndex <= upperIndex) {
            int midpoint = lowerIndex + Math.ceil((upperIndex - lowerIndex) / 2)
            T candidate = list[midpoint]
            if (candidate == elementToFind) {
                return midpoint
            } else if (elementToFind < candidate) {
                upperIndex = midpoint - 1
            } else {
                lowerIndex = midpoint + 1
            }
        }

        return -1
    }
}

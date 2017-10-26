package com.github.aschil.sorting

class MergeSort {
    static <T extends Comparable<? super T>> void sort(List<T> list) {
        mergeSort(list, 0, list.size() - 1)
    }

    static <T extends Comparable<? super T>> void mergeSort(List<T> list, int startingIndex, int endingIndex) {
        if (startingIndex < endingIndex) {
            int splitIndex = Math.floor((startingIndex + endingIndex) / 2)
            mergeSort(list, startingIndex, splitIndex)
            mergeSort(list, splitIndex + 1, endingIndex)
            merge(list, startingIndex, splitIndex, endingIndex)
        }
    }

    static <T extends Comparable<? super T>> void merge(List<T> list, int startingIndex, int splitIndex, int endingIndex) {
        int lengthOfLeftSubList = splitIndex - startingIndex + 1
        int lengthOfRightSubList = endingIndex - splitIndex

        T[] leftSubList = new T[lengthOfLeftSubList + 1]
        (0..(lengthOfLeftSubList - 1)).each {
            leftSubList[it] = list[startingIndex + it]
        }
        T[] rightSubList = new T[lengthOfRightSubList + 1]
        (0..(lengthOfRightSubList - 1)).each {
            rightSubList[it] = list[splitIndex + 1 + it]
        }

        int leftIndex = 0
        int rightIndex = 0
        (startingIndex..endingIndex).each {
            if (leftIndex > lengthOfLeftSubList - 1) {
                list[it] = rightSubList[rightIndex]
                rightIndex++
            } else if (rightIndex > lengthOfRightSubList - 1 ||
                    leftSubList[leftIndex].compareTo(rightSubList[rightIndex]) <= 0) {
                list[it] = leftSubList[leftIndex]
                leftIndex++
            } else {
                list[it] = rightSubList[rightIndex]
                rightIndex++
            }
        }
    }
}

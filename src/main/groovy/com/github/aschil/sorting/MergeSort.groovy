package com.github.aschil.sorting

class MergeSort {
    static void sort(List<Integer> list) {
        mergeSort(list, 0, list.size() - 1)
    }

    static void mergeSort(List<Integer> list, int startingIndex, int endingIndex) {
        if (startingIndex < endingIndex) {
            int splitIndex = Math.floor((startingIndex + endingIndex) / 2)
            mergeSort(list, startingIndex, splitIndex)
            mergeSort(list, splitIndex + 1, endingIndex)
            merge(list, startingIndex, splitIndex, endingIndex)
        }
    }

    static void merge(List<Integer> list, int startingIndex, int splitIndex, int endingIndex) {
        int lengthOfLeftSubList = splitIndex - startingIndex + 1 // 3
        int lengthOfRightSubList = endingIndex - splitIndex  // 2

        int[] leftSubList = new int[lengthOfLeftSubList + 1]
        (0..(lengthOfLeftSubList - 1)).each {
            leftSubList[it] = list[startingIndex + it]
        }
        int[] rightSubList = new int[lengthOfRightSubList + 1]
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
                    leftSubList[leftIndex] <= rightSubList[rightIndex]) {
                list[it] = leftSubList[leftIndex]
                leftIndex++
            } else {
                list[it] = rightSubList[rightIndex]
                rightIndex++
            }
        }
    }
}

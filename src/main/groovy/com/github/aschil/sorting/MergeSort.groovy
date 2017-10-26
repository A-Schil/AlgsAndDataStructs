package com.github.aschil.sorting

class MergeSort {
    static void sort(List<Integer> list) {
        int[] array = new int[list.size()]
        list.eachWithIndex { it, idx -> array[idx] = it }
        mergeSort(array, 0, list.size() - 1)
        (0..(list.size() - 1)).each {
            list[it] = array[it]
        }
    }

    static void mergeSort(int[] array, int startingIndex, int endingIndex) {
        if (startingIndex < endingIndex) {
            int splitIndex = Math.floor((startingIndex + endingIndex) / 2)
            mergeSort(array, startingIndex, splitIndex)
            mergeSort(array, splitIndex + 1, endingIndex)
            merge(array, startingIndex, splitIndex, endingIndex)
        }
    }

    static void merge(int[] array, int startingIndex, int splitIndex, int endingIndex) {
        int lengthOfLeftSubArray = splitIndex - startingIndex + 1 // 3
        int lengthOfRightSubArray = endingIndex - splitIndex  // 2
        // TODO optimize to not copy
        int[] leftSubArray = new int[lengthOfLeftSubArray + 1]
        (0..(lengthOfLeftSubArray - 1)).each {
            leftSubArray[it] = array[startingIndex + it]
        }
        int[] rightSubArray = new int[lengthOfRightSubArray + 1]
        (0..(lengthOfRightSubArray - 1)).each {
            rightSubArray[it] = array[splitIndex + 1 + it]
        }
        leftSubArray[lengthOfLeftSubArray] = Integer.MAX_VALUE
        rightSubArray[lengthOfRightSubArray] = Integer.MAX_VALUE
        int leftIndex = 0
        int rightIndex = 0
        (startingIndex..endingIndex).each {
            if(leftSubArray[leftIndex] <= rightSubArray[rightIndex]) {
                array[it] = leftSubArray[leftIndex]
                leftIndex++
            } else {
                array[it] = rightSubArray[rightIndex]
                rightIndex++
            }
        }

    }
}

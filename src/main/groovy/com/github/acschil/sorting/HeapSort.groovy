package com.github.acschil.sorting

class HeapSort {

    static void sort(List<Integer> list) {
        int heapSizeIndex = list.size()
        list.add(list.size()) // heap size counter

        buildMaxHeap(list)
        ((list.size() - 2)..1).each {
            exchange(list, 0, it)
            list[heapSizeIndex]--
            maxHeapify(list, 0)
        }

        list.remove(list.size() - 1) // remove heap size counter
    }

    private static void buildMaxHeap(List<Integer> list) {
        (Math.floorDiv((list.size() - 2), 2)..0).each {
            maxHeapify(list, it)
        }
    }

    private static void maxHeapify(List<Integer> list, int parentIndex) {
        int leftChildIndex = leftChild(parentIndex)
        int rightChildIndex = rightChild(parentIndex)
        int indexOfLargest
        if (leftChildIndex < list[list.size() - 1] && list[leftChildIndex] > list[parentIndex]) {
            indexOfLargest = leftChildIndex
        } else {
            indexOfLargest = parentIndex
        }
        if (rightChildIndex < list[list.size() - 1] && list[rightChildIndex] > list[indexOfLargest]) {
            indexOfLargest = rightChildIndex
        }
        if (indexOfLargest != parentIndex) {
            exchange(list, parentIndex, indexOfLargest)
            maxHeapify(list, indexOfLargest)
        }
    }

    private static int parentIndex(int index) {
        return Math.floorDiv(index - 1, 2)
    }

    private static int leftChild(int index) {
        return 2 * index + 1
    }

    private static int rightChild(int index) {
        return 2 * index + 2
    }

    private static void exchange(List<Integer> list, int index1, int index2) {
        int tmp = list[index1]
        list[index1] = list[index2]
        list[index2] = tmp
    }
}

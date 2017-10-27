package com.github.acschil.sorting

class HeapSort {

    static <T extends Comparable<? super T>> void sort(List<T> list) {
        int heapSize = list.size()
        if (heapSize > 2) {
            buildMaxHeap(list)
            ((list.size() - 1)..1).each {
                exchange(list, 0, it)
                heapSize--
                maxHeapify(list, heapSize, 0)
            }
        }
    }

    private static <T extends Comparable<? super T>> void buildMaxHeap(List<T> list) {
        (Math.floorDiv((list.size() - 2), 2)..0).each {
            maxHeapify(list, list.size(), it)
        }
    }

    private static <T extends Comparable<? super T>> void maxHeapify(List<T> list, int heapSize, int parentIndex) {
        int leftChildIndex = leftChild(parentIndex)
        int rightChildIndex = rightChild(parentIndex)
        int indexOfLargest
        if (leftChildIndex < heapSize && (list[leftChildIndex] <=> list[parentIndex]) > 0) {
            indexOfLargest = leftChildIndex
        } else {
            indexOfLargest = parentIndex
        }
        if (rightChildIndex < heapSize && (list[rightChildIndex] <=> list[indexOfLargest]) > 0) {
            indexOfLargest = rightChildIndex
        }
        if (indexOfLargest != parentIndex) {
            exchange(list, parentIndex, indexOfLargest)
            maxHeapify(list, heapSize, indexOfLargest)
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

    private static <T extends Comparable<? super T>> void exchange(List<T> list, int index1, int index2) {
        T tmp = list[index1]
        list[index1] = list[index2]
        list[index2] = tmp
    }
}

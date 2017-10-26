class Quicksort {

    static <T extends Comparable<? super T>> void sort(List<T> list) {
        quicksort(list, 0, list.size() - 1)
    }

    private static <T extends Comparable<? super T>> void quicksort(List<T> list,
                                                                    int startingIndex,
                                                                    int endingIndex) {
        if (startingIndex < endingIndex) {
            int pivotIndex = partition(list, startingIndex, endingIndex)
            quicksort(list, startingIndex, pivotIndex - 1)
            quicksort(list, pivotIndex + 1, endingIndex)
        }
    }

    private static <T extends Comparable<? super T>> int partition(List<T> list,
                                                                   int startingIndex,
                                                                   int endingIndex) {
        T pivotElement = list[endingIndex]
        int pivotIndex = startingIndex - 1
        (startingIndex..(endingIndex - 1)).each{
            if (list[it].compareTo(pivotElement) <= 0) {
                pivotIndex++
                exchange(list, pivotIndex, it)
            }
        }
        exchange(list, pivotIndex + 1, endingIndex)
        return pivotIndex + 1
    }

    private static <T extends Comparable<? super T>> void exchange(List<T> list, int index1, int index2) {
        T tmp = list[index1]
        list[index1] = list[index2]
        list[index2] = tmp
    }

}
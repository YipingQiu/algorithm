package xyz.qiuyiping.algorithm.sort;

public class BubbleSort implements SortInterface{
    @Override
    public int[] sort(int[] array) {
        for(int i = 0; i < array.length - 1; i ++) {
            for(int k = 0; k < array.length - i - 1; k ++) {
                if(array[k] > array[k + 1]) {
                    int temp = array[k + 1];
                    array[k + 1] = array[k];
                    array[k] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] noSortArray = {2, 5, 6, 8, 3, 5, 8, 9, 10, 44, 4, 7};
        SortInterface sort = new BubbleSort();
        int[] sortArray = sort.sort(noSortArray);
        for(int num : sortArray) {
            System.out.print(num + " ");
        }
    }
}

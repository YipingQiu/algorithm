package xyz.qiuyiping.algorithm.sort;

public class SelectionSort implements SortInterface{
    @Override
    public int[] sort(int[] array) {
        for(int i = 0; i < array.length/2; i ++) {
            int minPosition = i;
            int maxPosition = i;
            for(int k = i; k < array.length - i; k ++) {
                if(array[minPosition] > array[k]) {
                    minPosition = k;
                }
                if(array[maxPosition] < array[k]) {
                    maxPosition = k;
                }
            }
            System.out.println("排序前, i:" + i);
            System.out.println(minPosition + " " + maxPosition);
            for(int num : array) {
                System.out.print(num + " ");
            }
            System.out.println();
            int temp = array[minPosition];
            array[minPosition] = array[i];
            array[i] = temp;
            if(i == maxPosition) {
                maxPosition = minPosition;
            }
            temp = array[array.length - 1 - i];
            array[array.length - 1 - i] = array[maxPosition];
            array[maxPosition] = temp;
//            int midleValueOne = array[i];
//            int midleValueTwo = array[array.length - 1 - i];
//            int minValue = array[minPosition];
//            int maxValue = array[maxPosition];
//            System.out.println(midleValueOne + " " + midleValueTwo + " " + minValue + " " + maxValue );
//            array[i] = minValue;
//            array[array.length - 1 - i] = maxValue;
//            array[minPosition] = midleValueOne;
//            array[maxPosition] = midleValueTwo;

//            array[maxPosition] = temp;

//            int tempMin = array[i];
//            array[i] = array[minPosition];
//            array[minPosition] = tempMin;
//            for(int num : array) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
//            int tempMax = array[array.length - 1 - i];
//            array[array.length - 1 - i] = array[maxPosition];
//            array[maxPosition] = tempMax;
//            int temp = array[i];
//            array[i] = array[minPosition];
//            array[minPosition] = temp;
//            temp = array[array.length - 1 - i];
//            array[array.length - 1 - i] = array[maxPosition];
//            array[maxPosition] = temp;
            for(int num : array) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        return array;
    }

    public static void main(String[] args) {
        int[] noSortArray = {2, 5, 6, 8, 3, 5, 8, 9, 10, 44, 4, 70};
//        int[] noSortArray = {5, 6, 8, 3, 5, 8, 9, 10, 44, 4};
//        int[] noSortArray = {8, 5, 8, 6};


        SortInterface sort = new SelectionSort();
        int[] sortArray = sort.sort(noSortArray);
        for(int num : sortArray) {
            System.out.print(num + " ");
        }
    }
}

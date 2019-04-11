package xyz.qiuyiping.algorithm.sort;

public class NewMakeCaffe {

    public static void main(String[] args) {
        float[] makeCaffeTimes = {3, 4, 5};
        int drinkCoffePeopleNum = 49;
        float washCupTime = 2.0f;
        float volatilizeTime = 3f;

        float time = minTime(makeCaffeTimes, drinkCoffePeopleNum, washCupTime, volatilizeTime);
    }

    /**
     *
     * @param makeTimes 每个咖啡机制作咖啡的时间
     * @param n 喝咖啡的人数
     * @param x 清洗机清洗所需的时间
     * @param y 挥发所需要的时间
     * @return  最小所需要的时间
     */
    private static float minTime(float[] makeTimes, int n, float x, float y) {
        int m = makeTimes.length;
        float[] makeFinishTime = new float[m];
        int[] makeNum = new int[m];
        float[] tempFinishTime = new float[m];
        float[] theAllFinishTime = new float[n];
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                tempFinishTime[j] = makeFinishTime[j] + makeTimes[j];
            }
            int minIndex = minIndexOfArray(tempFinishTime);
            makeFinishTime[minIndex] += makeTimes[minIndex];
            makeNum[minIndex] ++;
            theAllFinishTime[i] = makeFinishTime[minIndex];
        }
        float minTime = 0;
        float washRecord = 0;
        for(int i = 0; i < n; i ++) {
            float time = Math.max(theAllFinishTime[i], washRecord) + x;
            if(time < theAllFinishTime[i] + y) {
                washRecord = time;
                System.out.print("机洗 ");
            } else {
                System.out.print("挥发 ");
            }
            if(i == n -1) {
                minTime = (time < theAllFinishTime[i] + y) ? time : (theAllFinishTime[i] + y);
            }
        }
        System.out.println();
        for(int i = 0; i < m; i ++) {
            System.out.println("第" + i + "台咖啡机制作了" + makeNum[i] + "杯咖啡，用时：" + makeFinishTime[i]);
        }
        System.out.println("最短时间：" + minTime);
        return minTime;
    }

    private static int minIndexOfArray(float[] array) {
        int index = 0;
        for(int i = 1; i < array.length;i ++) {
            if(array[i] < array[0]) {
                index = i;
            }
        }
        return index;
    }


}

package xyz.qiuyiping.algorithm.sort;

import java.util.*;

public class MakeCoffe {

    public static void main(String[] args) {
        int caffeMachineNum = 3;
        float[] makeCaffeTimes = {3, 4, 5};
        int drinkCoffePeopleNum = 3;
        float washCupTime = 2.0f;
        float volatilizeTime = 10f;

        float time = minTime(caffeMachineNum, makeCaffeTimes, drinkCoffePeopleNum, washCupTime, volatilizeTime);
    }

    /**
     *
     * @param m 咖啡机数量
     * @param makeTimes 每个咖啡机制作咖啡的时间
     * @param n 喝咖啡的人数
     * @param x 清洗机清洗所需的时间
     * @param y 挥发所需要的时间
     * @return  最小所需要的时间
     */
    private static float minTime(int m, float[] makeTimes, int n, float x, float y) {
        //校验参数:1、m>0; 2、m等于makeTimes的长度;3、所有数据均需大于0（省略了）
        if(m < 1 || makeTimes == null || m != makeTimes.length) {
            throw new IllegalArgumentException("参数不合法");
        }

        float[] makeSpeeds = new float[m];  //makeSpeeds：每个咖啡机制作一杯咖啡的速度
        float makeTimeMin = makeTimes[0];   //makeTimeMin：所有咖啡机中做咖啡时间最短的咖啡机
        //计算每个咖啡机制作的速度；遍历取得做的最快的咖啡机的时间
        for(int i = 0; i < m; i ++) {
            makeSpeeds[i] = 1 / makeTimes[i];
            makeTimeMin = Math.min(makeTimeMin, makeTimes[i]);
        }
        float sumSpeed = 0; //sumSpeed：所有咖啡机制作咖啡的总速度
        //计算所有咖啡机制作咖啡的总速度
        for(float speed : makeSpeeds) {
            sumSpeed += speed;
        }
        float[] rates = new float[m];   //rates：每个咖啡机分配比
        //按速度计算每个咖啡机占总速度的比率，后面将会按照此比率分配制作咖啡的杯数
        for(int i = 0; i < m; i ++) {
            rates[i] = makeSpeeds[i] / sumSpeed;
        }
        int[] makeCaffes = new int[m];  //makeCaffes：每个咖啡机制作咖啡的杯数
        float[] quotients = new float[m];   //quotients：余数（小数）（每个咖啡机按比例分配后去掉整数部分后的小数）
        //按比例计算每个咖啡机制作的杯数（向下取整），并计算取整后的余数存于quotients
        for(int i = 0; i < m; i ++) {
            float allocate = n * rates[i];
            makeCaffes[i] = (int) allocate;
            quotients[i] = allocate - makeCaffes[i];
        }
        int allocatedNum = 0;   //allocatedNum：按整数分配的数量和
        //计算按整数分配的数量和
        for(int allocate : makeCaffes) {
            allocatedNum += allocate;
        }
        int noAllocateNum = n - allocatedNum;   //noAllocateNum：未分配的数量
        //如果还有未分配的，对未分配的按照余数从大到小进行分配，按照余数排序，从大到小，每个咖啡机分配一个制作任务
        if(noAllocateNum > 0) {
            //排序quotients，记录下标
            TreeMap<Float, Integer> sort = new TreeMap<>();
            for(int i = 0; i < m; i ++) {
                sort.put(quotients[i], i);
            }
            //按照余数进行分配
            List<Integer> indexs = new ArrayList<>(sort.values());
            for(int i = 0; i < noAllocateNum; i ++) {
                makeCaffes[indexs.get(m - 1 - i)] ++;
            }
        }
        float minMakeTime = 0;  //最小制作时间
        //根据分配的数量，计算每个咖啡机所需要的总时间，取最长时间，即为最小制作时间
        for(int i = 0; i < m; i ++) {
            minMakeTime = Math.max(minMakeTime, makeCaffes[i] * makeTimes[i]);
        }
        System.out.println("最短时间为： " + minMakeTime);
        System.out.println("分配策略为： ");
        for(int i = 0; i < m; i ++) {
            System.out.println("第" + i + "台机器，做一杯的时间： " + makeTimes[i] + " ,制作数量为： " + makeCaffes[i] + ", 需要时间： " + makeTimes[i] * makeCaffes[i]);
        }

        float[] makeCaffeFinishTimes = new float[n];    //做完每杯咖啡结束的时刻
        int k = 0;  //循环n个人时的临时计数器
        //计算制作完每杯咖啡时的时刻
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < makeCaffes[i]; j ++) {
                makeCaffeFinishTimes[k ++] = (j + 1) * makeTimes[i];
            }
        }
        //对做完的时刻按从小到大的顺序进行排序
        Arrays.sort(makeCaffeFinishTimes);
        for(float time : makeCaffeFinishTimes) {
            System.out.print(time + ", ");
        }
        System.out.println();

        float minTime = 0;  //喝完咖啡+清洗完杯子需要的最短时间

        float washFinishTime = 0;   //咖啡机结束工作时的时刻
        //每杯咖啡制作完后，判断何种制作制作方式，清洗机清洗还是直接挥发
        for(int i = 0; i < n; i ++) {
            //下面两行代码，根据清洗机的可用时刻计算如果使用清洗机的方式时，结束的时刻
            float maxTime = Math.max(washFinishTime, makeCaffeFinishTimes[i]);  //如果清洗机空闲，则使用咖啡机结束制作的时间，否则使用清洗机结束的时间
            float ifUseMachine = maxTime + x;   //如果使用清洗机，则清洗完时间
            float ifUseVolatilize = makeCaffeFinishTimes[i] + y;    //如果使用挥发，挥发完的时间
            //判断两种方式的时间，如果使用清洗机，则重新计算咖啡机结束时刻
            if(ifUseMachine < ifUseVolatilize) {
                washFinishTime = ifUseMachine;
                System.out.print("机洗，");
            } else {
                System.out.print("挥发，");
            }
            //如果是最后一个，取两种方式的最小时间即为喝完咖啡+清洗完杯子需要的最短时间
            if(i == n - 1) {
                minTime = Math.min(ifUseMachine, ifUseVolatilize);
            }
        }
        System.out.println();
        System.out.println("最小时间为：" + minTime);
        return minTime;
    }

}

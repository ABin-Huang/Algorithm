package cn.hb.algorithm.maxvalue;

import java.util.Arrays;

/**
 * @author abin
 * @date 2024/10/8 20:47
 * @description
 * 1.分组算法，找最大最小值，将数组分为k组，求每组最大值和最小值
 * 2.分治算法，首先将数组一分为二为L1,L2，递归的在L1，L2中寻找max1,min1,max2,min2,即可得出结果
 */

public class Grouping {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findMaxMin(new int[]{1, 2, 2, 2, 6, 7, 8, 34, 3, 3, 3, 3, 5, 6, 7})));
        System.out.println(Arrays.toString(findMaxMin(new int[]{2})));
    }

    public static int[] findMaxMin(int[] arr) {
        int len = arr.length;
        int[] res = {-0x3f3f3f3f, 0x3f3f3f3f};
        int[] maxArr = new int[len / 2];
        int[] minArr = new int[len / 2];


        int curIndex = 0;
        for (int i = 0; i < len - 1; i = i + 2) {
            if (arr[i] < arr[i + 1]) {
                minArr[curIndex] = arr[i];
                maxArr[curIndex] = arr[i + 1];
            } else {
                minArr[curIndex] = arr[i + 1];
                maxArr[curIndex] = arr[i];
            }
            curIndex++;
        }

        traverseArray(maxArr, minArr, res, arr);

        return res;
    }

    /**
     * Traverse array.
     *
     * @param maxArr the max arr
     * @param minArr the min arr
     * @param res    the res
     * @param arr    the arr
     */
    public static void traverseArray(int[] maxArr, int[] minArr, int[] res, int[] arr) {
        boolean isEven = arr.length % 2 == 0;

        // 遍历两个数组
        for (int i = 0; i < maxArr.length; i++) {
            if (res[0] < maxArr[i]) {
                res[0] = maxArr[i];
            }
        }

        for (int i = 0; i < minArr.length; i++) {
            if (res[1] > minArr[i]) {
                res[1] = minArr[i];
            }
        }


        // 如果是奇数,需要多比较最后一个元素
        if (!isEven) {
            if (res[0] < arr[arr.length - 1]) {
                res[0] = arr[arr.length - 1];
            }
            if (res[1] > arr[arr.length - 1]) {
                res[1] = arr[arr.length - 1];
            }
        }
    }
}


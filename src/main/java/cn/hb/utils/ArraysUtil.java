package cn.hb.utils;

/**
 * @author hb
 * @data 2024/9/23
 * @descript 数组工具类
 */
public class ArraysUtil {
    /**
     * 交换位置
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

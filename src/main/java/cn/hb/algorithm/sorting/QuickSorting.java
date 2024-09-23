package cn.hb.algorithm.sorting;

import java.util.Arrays;

import static cn.hb.utils.RandomUtil.RANDOM;

/**
 * 原始快速排序--通过递归方式.
 *
 * @author abin
 * @date 2024 /9/22 19:13
 * @description
 */
public class QuickSorting {
    /**
     * 统计选择划分点次数
     */
    private static int rounds = 1;

    public static void main(String[] args) {
        int[] arr = {880900,918767,427094,223627,665730,924322};
        System.out.println(Arrays.toString(arr));
        quickSorting(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序.
     *
     * @param arr the arr
     */
    public static void quickSorting(int[] arr, int left, int right) {

        // 如果遍历完则结束
        if (left >= right) {
            System.out.println("结束：" +Arrays.toString(arr));
            return;
        }

        // 选择划分点
        int dividePoint = RANDOM.nextInt(left, right);
        System.out.println("第" +rounds++ +"次选择的位置是" +dividePoint +"值：" +arr[dividePoint]);

        // 将划分点与最后一个值交换位置
        swap(arr, right, dividePoint);
        dividePoint = left - 1;

        // 从left开始比较一直到right，如果当前值小于等于arr[dividePoint]，则交换位置。 随机点位置通过计算多少个元素小于划分点的值
        for (int j = left; j < right; j++) {
            if (arr[j] <= arr[right]) {
                swap(arr, ++dividePoint, j);
                System.out.print("dividePoint的位置：" +dividePoint +"值：" +arr[dividePoint] +"--");
                System.out.println(Arrays.toString(arr));
            }
        }

        // 交换位置是为了保证dividePoint左边的值小于dividePoint右边的值
        swap(arr, ++dividePoint, right);
        System.out.println("end: " +Arrays.toString(arr));
        quickSorting(arr, left, dividePoint - 1);
        quickSorting(arr, dividePoint + 1, right);

    }

    /**
     * 快速排序--使用双指针
     *
     * @param arr   arr
     * @param left  左边
     * @param right 正确
     */
    public static void quickSortingTwo(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int pivot = arr[left];
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            if (i <j) {}
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

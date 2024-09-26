package cn.hb.algorithm.sorting;

import java.util.Arrays;

import static cn.hb.utils.ArraysUtil.swap;
import static cn.hb.utils.RandomUtil.RANDOM;

/**
 * 原始快速排序--通过递归方式.
 *
 * @author abin
 * @date 2024 /9/22 19:13
 * @description ### 工作原理
 *
 * 快速排序使用分治法（Divide and Conquer）来对数组进行排序。其基本步骤如下：
 *
 * 1. **选择基准值**：从数组中选择一个元素作为基准值（pivot）。
 * 2. **分区操作**：将数组分为两部分，一部分的所有元素都小于基准值，另一部分的所有元素都大于基准值。
 * 3. **递归排序**：对这两部分分别递归地进行快速排序。
 *
 * ### 时间复杂度
 *
 * - 最好情况：(O(nlog n))
 * - 平均情况：(O(nlog n))
 * - 最坏情况：(O(n^2))
 */
public class QuickSorting {
    /**
     * 统计选择划分点次数
     */
    private static int rounds = 1;

    public static void main(String[] args) {
        int[] arr2 = {1,2,2,2,6,7,8,34,3,3,3,3,5,6,7,1,2,4,988};
        int[] arr = {880900,918767,427094,223627,665730,924322};
        System.out.println(Arrays.toString(arr2));
        quickSorting(arr2, 0, arr2.length - 1);

        int[] arr1 = {880900,918767,427094,223627,665730,924322};
        quickSortingTwo(arr2, 0, arr2.length - 1);
        //System.out.println(Arrays.toString(arr2));
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
        // 基本情况：当子数组长度小于等于1时直接返回
        if (left >= right) {
            return;
        }

        // 初始化指针和基准值
        int i = left;
        int j = right;
        int pivot = arr[left];

        // 双指针交换元素
        while (i < j) {
            // 从右向左找到小于基准值的元素
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            // 从左向右找到大于基准值的元素
            while (i < j && arr[i] <= pivot) {
                i++;
            }

            // 如果 i < j，则交换 arr[i] 和 arr[j]
            if (i < j) {
                swap(arr, i, j);
            }
        }

        // 将基准值放到正确位置
        arr[left] = arr[i];
        arr[i] = pivot;

        // 递归调用快速排序
        quickSortingTwo(arr, left, i - 1);
        quickSortingTwo(arr, i + 1, right);
    }
}

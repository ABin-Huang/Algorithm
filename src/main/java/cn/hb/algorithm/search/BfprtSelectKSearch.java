package cn.hb.algorithm.search;


import java.util.Arrays;

import static cn.hb.utils.ArraysUtil.swap;

/**
 * @author hb
 * @data 2024/9/24
 * @descript BFPRT算法,找第k大元素
 *
 *1. 基本概念
 *  ~枢纽（Pivot）：枢纽是一个特殊的元素，用来将数组分成两部分。
 *  ~划分（Partition）：根据枢纽将数组分成两部分，一部分小于枢纽，另一部分大于枢纽。
 *  ~递归：通过递归的方式不断缩小查找范围，直到找到第k大的元素
 *
 * 2. BFPRT 算法的步骤
 *  1分组：将数组分成每组 5 个元素的小数组。
 *  2找中位数：对每个小数组进行排序，并找到每个小数组的中位数。
 *  3递归找中位数的中位数：将所有中位数组成一个新的数组，递归地找到这些中位数的中位数作为枢纽。
 *  4划分：使用枢纽将数组划分为两部分，一部分小于枢纽，另一部分大于枢纽。
 *  5递归选择：根据枢纽的位置和k的值，决定继续在左边还是右边递归查找。
 *
 * 例子
 *   有一个数组 [7, 10, 4, 3, 20, 15, 18, 12, 9, 11, 6, 5]，我们需要找到第 7 大的元素。
 *
 *   1. 分组
 *   将数组分成每组 5 个元素的小数组：
 *   [7, 10, 4, 3, 20]
 *   [15, 18, 12, 9, 11]
 *   [6, 5]
 *
 *   2. 找中位数
 *   对每个小数组进行排序，并找到每个小数组的中位数：
 *   [3, 4, 7, 10, 20] -> 中位数: 7
 *   [9, 11, 12, 15, 18] -> 中位数: 12
 *   [5, 6] -> 中位数: 5
 *
 *   3. 递归找中位数的中位数
 *   将这些中位数 [7, 12, 5] 组成一个新的数组，并找到它们的中位数：
 *   [5, 7, 12] -> 中位数: 7
 *
 *   4. 划分
 *   使用枢纽 7 将原数组划分为两部分：
 *   小于 7 的部分：[3, 4, 5, 6]
 *   大于 7 的部分：[10, 20, 15, 18, 12, 9, 11]
 *
 *   5. 递归选择
 *   枢纽 7 在数组中的位置是 3（从 0 开始计数）。我们需要找第 7 大的元素，所以需要在大于 7 的部分继续查找第 3 大的元素（因为 7 已经是第 4 大的元素了）。
 *
 *   重复上述过程：
 *
 *   对 [10, 20, 15, 18, 12, 9, 11] 进行分组、找中位数、递归找中位数的中位数、划分等操作。
 *   最终，我们会找到第 7 大的元素。
 */
public class BfprtSelectKSearch {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,5,4,3,32,2,1,45,657,9};
        selectK(arr, 0, arr.length - 1, 3);
        System.out.println(Arrays.toString(arr));
    }

    public static int bfprtSelectKSearch(int L[], int left, int right, int k) {
        return 1;
    }

    /**
     * put the k-th element at L[k] (0 indexed)
     */
    public static void selectK(int L[], int left, int right, int k) {
        while (true) {
            if (left == right) return;
            int pivot_index = pivot(L, left, right);
            pivot_index = partition(L, left, right, pivot_index, k);
            if (k == pivot_index)
                return;
            else if (k < pivot_index)
                right = pivot_index - 1;
            else
                left = pivot_index + 1;
        }
    }

    /**
     * actual median of medians algo
     */
    public static int pivot(int L[], int left, int right) {
        if (right - left < 5) return partition5(L, left, right);

        // 遍历数据
        for (int i = left; i <= right; i += 5) {
            // 防止数组越界，获取这组数据第五个元素的位置
            int sub_right = Math.min(i + 4, right);

            // 获取五个数中位数
            int median5 = partition5(L, i, sub_right);
            // 交换中位数到当前这组数据的最左侧
            swap(L, median5, left + (i - left) / 5);
        }

        // approximate median index 近似中位数位置
        // 我们已经找到了每个子数组的中位数，这些中位数的数量大约是原数组长度的 1/5。
        //从中位数中再选择一个近似的中位数时，选择 1/10 有助于找到一个更接近真正的中位数的位置。
        int mid = (right - left) / 10 + left + 1;
        selectK(L, left, left + (right - left) / 5, mid);
        return mid;
    }

    /**
     * three way partition: ---[=]=[=]+++
     */
    public static int partition(int L[], int left, int right, int pivot_index, int k) {
        int pivot_value = L[pivot_index];
        swap(L, pivot_index, right);
        // ---[=]=[=]+++
        int store_index = left;
        for (int i = left; i < right; ++i) {
            if (L[i] < pivot_value) {
                swap(L, store_index++, i);
            }
        }
        int store_index_eq = store_index;
        for (int i = store_index; i < right; ++i) {
            if (L[i] == pivot_value) {
                swap(L, store_index_eq++, i);
            }
        }
        swap(L, right, store_index_eq);
        if (k < store_index)
            return store_index;
        else if (k < store_index_eq)
            return k;
        else
            return store_index_eq;
    }

    /**
     * <= 5 elements, insertion sort, and pick the middle as partition index
     * 使用插入排序，找中位数
     */
    public static int partition5(int L[], int left, int right) {
        for (int i = left + 1; i <= right; ++i) {
            int j = i;
            while (j > left && L[j - 1] > L[j]) {
                swap(L, j - 1, j);
                --j;
            }
        }
        return (left + right) / 2;  // return middle index (the median index)
    }
}

package cn.hb.algorithm.search;

import java.util.Arrays;

import static cn.hb.utils.ArraysUtil.swap;
import static cn.hb.utils.RandomUtil.RANDOM;

/**
 * @author hb
 * @data 2024/9/25
 * @descript LazySelect 基于快速选择（Quickselect）算法,在大数据集中找到第 k 小元素（或中位数）的算法
 *
 * 基本思想：
 * 1. 随机选择基准元素
 * 2. 将数组分为小于或等于基准元素和大于基准元素的两个子数组
 * 3. 如果k正好等于基准元素位置，则返回。否则如果k小于该位置，则在较小的半部分递归地应用该算法；如果kk大于该位置，则在较大的半部分递归地应用该算法
 * 直持续到找到第kk大的元素为止。
 */
public class LazySelect {

    public static void main(String[] args) {
        int[] arr = {1,2,2,2,6,7,8,34,3,3,3,3,5,6,7,1,2,4,988};
        System.out.println(arr.length);
        System.out.println(lazySelect(arr, 0, arr.length - 1, 17));
    }

    public static int lazySelect(int[] arr, int left, int right, int k){
        if (left > right || k <= 0 || k > arr.length) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        int pivotIndex;
        while (left < right) {
            // 可以优化随机基准数
            pivotIndex = RANDOM.nextInt(left, right);
            int curIndex = divide(arr, left, right, pivotIndex);
            if (curIndex == k - 1) {
                System.out.println("answer: " +arr[k - 1]);
                return arr[curIndex];
            } else if (curIndex < k - 1) {
                left = curIndex + 1;
            } else {
                right = curIndex - 1;
            }
        }
        System.out.println("answer: " +arr[left]);
        return arr[left];
    }

    /**
     * 划分
     *
     * @param arr   arr
     * @param left  左边
     * @param right 正确
     * @return int
     */
    public static int divide(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, right, pivotIndex);
        System.out.println("pivotIndex: "+pivotIndex);
        pivotIndex = left - 1;
        // 从left开始比较一直到right，如果当前值小于等于arr[dividePoint]，则交换位置。 随机点位置通过计算多少个元素小于划分点的值
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivotValue) {
                swap(arr, ++pivotIndex, j);
                System.out.print("dividePoint的位置：" +pivotIndex +"值：" +arr[pivotIndex] +"--");
                System.out.println(Arrays.toString(arr));
            }
        }
        // 交换位置是为了保证dividePoint左边的值小于dividePoint右边的值
        swap(arr, ++pivotIndex, right);
        return pivotIndex;
    }
}

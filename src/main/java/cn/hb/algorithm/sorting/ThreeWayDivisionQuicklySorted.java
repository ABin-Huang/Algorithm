package cn.hb.algorithm.sorting;

import java.util.Arrays;

import static cn.hb.utils.ArraysUtil.swap;
import static cn.hb.utils.RandomUtil.RANDOM;

/**
 * @author hb
 * @data 2024/9/23
 * @descript 三路划分快速排序
 *  将数据分成3份，小于划分点的元素，大于划分点的元素，等于划分点的元素。每次只递归大于和小于划分点值的数据
 *  递归调用的次数会减少，减少了不必要的比较和交换
 */
public class ThreeWayDivisionQuicklySorted {

    public static void main(String[] args) {
        int[] arr = {1,2,2,2,6,7,8,34,3,3,3,3,5,6,7,1,2,4,988};
        threeWayDivisionQuicklySorted(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 三路划分快速排序
     *
     * @param arr   arr
     * @param left  左边
     * @param right 右边
     */
    public static void threeWayDivisionQuicklySorted(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        // 从 left 到 right 之间随机选择一个索引作为划分点，并获取该位置的值 pointValue
        int pointIndex = RANDOM.nextInt(left, right);

        // 初始化三个指针：lo 指向当前处理区间的左边界，hi 指向右边界，pointIndex 用于遍历数组
        int pointValue = arr[pointIndex], lo = left, hi = right;
        pointIndex = left;
        while (pointIndex <= hi) {
            // 等于pointValue
            if (arr[pointIndex] == pointValue) {
                // 将等于划分点的元素单独分出来，不用继续拆分点，减少了不必要的比较和交换
                pointIndex++;
            } else if (arr[pointIndex] < pointValue) {
                // 小于pointValue
                swap(arr, pointIndex++, lo++);
            } else {
                // 大于pointValue
                swap(arr, pointIndex, hi--);
            }
        }
       threeWayDivisionQuicklySorted(arr, left, lo - 1);
       threeWayDivisionQuicklySorted(arr, hi + 1, right);
   }
}

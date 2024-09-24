package cn.hb.algorithm.sorting;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


/**
 * @author hb
 * @data 2024/9/24
 * @descript
 */

//@RunWith(PowerMockRunner.class)
public class QuickSortingTest {

    @DisplayName("测试快速排序")
    @Test
    public void testQuickSorting() {
        QuickSorting.quickSorting(new int[]{0}, 0, 0);
    }

    @DisplayName("测试快速排序2")
    @Test
    public void testQuickSortingTwo() {
        int[] arr = {0,2,3,6,1,3,5};
        QuickSorting.quickSortingTwo(arr, 0, arr.length - 1);
        // assert Arrays.equals(arr, new int[]{0,1,2,3,3,5,6}); // 断言两个数组相等
        Assert.assertArrayEquals("数组应该相等", arr, new int[]{0,1,2,3,3,5,6});
    }
}

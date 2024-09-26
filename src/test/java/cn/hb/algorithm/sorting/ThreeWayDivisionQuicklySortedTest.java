package cn.hb.algorithm.sorting;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author hb
 * @data 2024/9/25
 * @descript
 */
public class ThreeWayDivisionQuicklySortedTest {

    @Test
    @DisplayName("三路划分快速排序测试")
    public void testThreeWayDivisionQuicklySorted() {
        int[] arr = {0,2,3,6,1,3,5};
        ThreeWayDivisionQuicklySorted.threeWayDivisionQuicklySorted(arr, 0, arr.length - 1);
        Assert.assertArrayEquals("数组应该相等", arr, new int[]{0,1,2,3,3,5,6});
    }
}


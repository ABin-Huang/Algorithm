package cn.hb.algorithm.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author hb
 * @data 2024/9/24
 * @descript
 */
public class BfprtSelectKSearchTest {
    BfprtSelectKSearch bfprtSelectKSearch = new BfprtSelectKSearch();

    @Test
    public void testMain() {
        BfprtSelectKSearch.main(new String[]{"args"});
    }

    @Test
    public void testBfprtSelectKSearch() {
        int result = BfprtSelectKSearch.bfprtSelectKSearch(new int[]{0}, 0, 0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testSelect() {
        bfprtSelectKSearch.select(new int[]{0}, 0, 0, 0);
    }



    @Test
    public void testPartition() {
        int result = bfprtSelectKSearch.partition(new int[]{0}, 0, 0, 0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testPartition5() {
        int result = bfprtSelectKSearch.partition5(new int[]{0}, 0, 0);
        Assertions.assertEquals(0, result);
    }
}


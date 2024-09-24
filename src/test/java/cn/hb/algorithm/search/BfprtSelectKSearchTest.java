package cn.hb.algorithm.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * @author hb
 * @data 2024/9/24
 * @descript
 */
public class BfprtSelectKSearchTest {
    @Mock
    BfprtSelectKSearch bfprtSelectKSearch;

    @Test
    public void testMain() {
        BfprtSelectKSearch.main(new String[]{"args"});
    }

    @Test
    public void testBfprtSelectKSearch() {
        int result = BfprtSelectKSearch.bfprtSelectKSearch(new int[]{0}, 0, 0, 0);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testSelect() {
        bfprtSelectKSearch.selectK(new int[]{0}, 0, 0, 0);
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


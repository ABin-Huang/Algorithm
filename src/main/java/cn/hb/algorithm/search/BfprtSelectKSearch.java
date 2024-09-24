package cn.hb.algorithm.search;


import static cn.hb.utils.ArraysUtil.swap;

/**
 * @author hb
 * @data 2024/9/24
 * @descript BFPRT算法,找第k大元素
 */
public class BfprtSelectKSearch {
    public static void main(String[] args) {

    }

    public static int bfprtSelectKSearch(int L[], int left, int right, int k) {
        return 1;
    }

    /**
     * put the k-th element at L[k] (0 indexed)
     */
    void select(int L[], int left, int right, int k) {
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
    int pivot(int L[], int left, int right) {
        if (right - left < 5) return partition5(L, left, right);
        for (int i = left; i <= right; i += 5) {
            int sub_right = Math.min(i + 4, right);
            int median5 = partition5(L, i, sub_right);
            swap(L, median5, left + (i - left) / 5);
        }

        // approximate median index
        int mid = (right - left) / 10 + left + 1;
        select(L, left, left + (right - left) / 5, mid);
        return mid;
    }

    /**
     * three way partition: ---[=]=[=]+++
     */
    int partition(int L[], int left, int right, int pivot_index, int k) {
        return 1;
    }

    /*
     * <= 5 elements, insertion sort, and pick the middle as partition index
     */
    int partition5(int L[], int left, int right) {
        return 1;
    }
}

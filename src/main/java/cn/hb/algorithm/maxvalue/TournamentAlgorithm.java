package cn.hb.algorithm.maxvalue;

import java.util.Arrays;

/**
 * @author abin
 * @date 2024/10/9 22:13
 * @description
 */

public class TournamentAlgorithm {

    public static int buildTournamentTree(int[] arr, int[] tree, int index, int n) {
        if (index == n) {
            return arr[index - n];
        } else if (index > n) {
            return arr[index - n - 1];
        }
//        System.out.println(index);
        int left = buildTournamentTree(arr, tree, index * 2 + 1, n);
        int right = buildTournamentTree(arr, tree, index * 2 + 2, n);
        tree[index] = Math.max(left, right);
//        System.out.println(left + ", " + right);

        return tree[index];
    }

    // 查找第二大值
    public static int findSecondMax(int[] a, int[] tree) {
        int secondmax = -0x3f3f3f3f;
        //我们将从直接输给最大值的数中找到第二大的数
        for (int i = 0; i < a.length - 1; ) {
            if (tree[i] == tree[2 * i + 1]) {
                if (secondmax < tree[2 * i + 2]) {
                    secondmax = tree[2 * i + 2];
                }
                i = i * 2 + 1;
            } else {
                if (secondmax < tree[2 * i + 1]) {
                    secondmax = tree[2 * i + 1];
                }
                i = i * 2 + 2;
            }
            System.out.println(secondmax +" index: " +i);
        }
        return secondmax;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 12, 34, 123};
        int[] tree = new int[arr.length * 2];
        for (int i = tree.length - 1; i >= arr.length; i--)
            tree[i] = arr[i - arr.length];
        int max = buildTournamentTree(arr, tree, 0, arr.length);
        System.out.println(max);
        System.out.println(Arrays.toString(tree));

        // 查找第二大值
        int second_max = findSecondMax(arr, tree);
        System.out.println("第二大值: " + second_max);
    }
}

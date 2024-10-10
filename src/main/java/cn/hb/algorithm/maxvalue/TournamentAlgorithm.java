package cn.hb.algorithm.maxvalue;

import java.util.Arrays;

/**
 * @author abin
 * @date 2024/10/9 22:13
 * @description
 */

public class TournamentAlgorithm {

    public static void main(String[] args) {
        int[] numbers = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        Pair<Integer, Integer> result = findTwoLargest(numbers);
        System.out.println("The largest number is: " + result.first);
        System.out.println("The second largest number is: " + result.second);
    }

    // 找出数组中最大的两个数
    public static Pair<Integer, Integer> findTwoLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        // 构建虚拟的满二叉树
        int n = arr.length;
        // 满二叉树的大小
        int treeSize = 2 * n - 1;
        int[] tournamentTree = new int[treeSize];

        // 将原始数据复制到二叉树的叶节点
        System.arraycopy(arr, 0, tournamentTree, n - 1, n);

        // 填充非叶节点
        for (int i = n - 2; i >= 0; i--) {
            tournamentTree[i] = Math.max(tournamentTree[2 * i + 1], tournamentTree[2 * i + 2]);
        }

        System.out.println(Arrays.toString(tournamentTree));

        // 最大值位于根节点
        int max = tournamentTree[0];
        int secondMax = Integer.MIN_VALUE;

        // 回溯查找第二大值
        int index = 0;
        while (index < n - 1) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            // 如果左子节点是当前节点的最大贡献者
            if (tournamentTree[leftChild] == max) {
                System.out.println(secondMax +":" +index);
                secondMax = Math.max(secondMax, tournamentTree[rightChild]);
                index = leftChild;
            } else { // 否则右子节点是最大贡献者
                System.out.println(secondMax +":" +index);
                secondMax = Math.max(secondMax, tournamentTree[leftChild]);
                index = rightChild;
            }
        }
        System.out.println(secondMax +":" +index);

        return new Pair<>(max, secondMax);
    }

    // 简单的Pair类用于返回两个值
    public record Pair<T1, T2>(T1 first, T2 second) {
    }
}

package cn.hb.algorithm.divide;

/**
 * @author abin
 * @date 2024/9/25 22:08
 * @description  Fibonacci数列 使用分治法结合矩阵得到结果 O(n) = O(8logn)
 * |Fn+1 Fn| |1 1|
 * |Fn Fn-1| |1 0|
 */

import java.util.Scanner;

public class FibonacciMatrix {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = input.nextInt();
        input.close();

        long result = fibonacci(n);
        System.out.println("The " + n + "th Fibonacci number is: " + result);
    }

    public static long fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        long[][] a = {{1, 1}, {1, 0}};
        long[][] resultMatrix = matrixPower(a, n - 1);

        return resultMatrix[0][0];
    }

    public static long[][] matrixMultiply(long[][] A, long[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        long[][] result = new long[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    public static long[][] matrixPower(long[][] A, int n) {
        if (n == 1) {
            return A;
        } else if (n % 2 == 0) {
            long[][] root = matrixPower(A, n / 2);
            return matrixMultiply(root, root);
        } else {
            long[][] root = matrixPower(A, n / 2);
            return matrixMultiply(matrixMultiply(root, root), A);
        }
    }
}

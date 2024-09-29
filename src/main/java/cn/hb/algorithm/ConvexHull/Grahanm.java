package cn.hb.algorithm.ConvexHull;


import java.util.Arrays;


/**
 * @author hb
 * @data 2024/9/27
 * @descript 凸包问题
 *
 * 目标：找到一个点集的最小凸多边形，使得该多边形包含所有的点
 * Grahanm's Scan算法基本思想
 *  1，选择P中y坐标最小的点为起始点p0，若有多个这样的点则进一步选取其中x坐标最小的点为p0;
 *
 *  2，设<p1,p2,……,pm>是P中剩余的点，对其按逆时针方向相对p0的极角进行从小到大排序，若有多个点有相同的极角，则离p0点近的排在前面；
 *
 *  3，设排序后的点的顺序为p1,p2,……,pm，分别计算向量p0p1与p1p2的叉积，二维矢量的叉积a × b = IaI•IbIsinθ，θ为从a到b的夹角，
 *  如果b在a的逆时针方向，则sinθ为正，叉积也就为正，在我们逆时针遍历的情况下，如果b在a的顺时针方向，则p1点必定不是凸包上的点，反之则是在凸包上的点。
 *
 */
public class Grahanm {
    public static void main(String[] args) {
        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 0),
                new Point(1, -1), new Point(0, -2), new Point(-1, -1),
                new Point(-2, 0), new Point(-1, 1)};

        // 坐标点个数
        int n = points.length;

        // 记录凸包坐标点，index对应point的下标
        int[] convexs = new int[n];
        int total = getConvexHull(points, convexs, n);
        System.out.println(total);
        for (int i = 0; i < total; i++) {
            System.out.println(points[convexs[i]]);
        }
    }

    public static int getConvexHull(Point[] points, int[] convex, int n) {
        // 得到最左边的点 优化排序算法，快速排序
        // x小的在前面，如果相同，y小的在前面
        Arrays.sort(points, (p1, p2) -> {
            if (p1.x != p2.x) {
                return Integer.compare(p1.x, p2.x);
            } else {
                return Integer.compare(p1.y, p2.y);
            }
        });
        int total = 0;

        // 下凸包
        // 1.将第一个点（即排序后的第一个点）和第二个点加入 convex 数组中。
        // 2.如果向量叉积>=0，满足逆时针方向，将当前点加入 convex 数组
        //   否则移除 convex 数组中的最后一个点，再次计算前一个点向量，直到满足向量叉积>=0或构成凸包点大于1个
        for (int i = 0; i < n; i++) {
            while (total >= 2 && calcCrossProduct(points, convex, total, i) <= 0) {
                total--;
            }
            convex[total++] = i;
        }

        int temp = total;

        // 上凸包
        // 从倒数第二个点开始,反向遍历剩余的点，并检查当前点是否满足逆时针方向。如果不满足，则移除 convex 数组中的最后一个点，直到满足条件为止。
        for (int i = n - 2; i >= 0; i--) {

            while (total > temp && calcCrossProduct(points, convex, total, i) <= 0) {
                total--;
            }
            convex[total++] = i;
        }

        // 移除重复的最后一个点
        if (total > 1 && convex[0] == convex[total - 1]) {
            total--;
        }
        return total;
    }

    /**
     * 计算向量积
     *
     * @param points 点集合
     * @param convex 凸面
     * @param n      当前凸点数
     * @param m      遍历的点下标
     * @return double
     */
    public static double calcCrossProduct(Point[] points, int[] convex, int n, int m) {
        Point p0p1 = points[convex[n - 1]].del(points[convex[n - 2]]);
        Point p0p2 = points[m].del(points[convex[n - 1]]);
        return p0p1.det(p0p2);
    }

    /**
     * 点
     *
     * @author 24232
     * @date 2024/09/27
     */
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double det(Point point) {
            return this.x * point.y - this.y * point.x;
        }

        public Point del(Point p) {
            return new Point(this.x - p.x, this.y - p.y);
        }

        double dist(Point p) {
            return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}

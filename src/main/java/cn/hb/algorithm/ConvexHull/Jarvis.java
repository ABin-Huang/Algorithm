package cn.hb.algorithm.ConvexHull;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abin
 * @date 2024/9/29 22:34
 * @description 凸包问题 时间复杂度：O(nh) h为凸包中点数，n为点数
 * 基本思想：
 * 1.找到所有点中最左边最低的那个点作为起点。
 * 2.
 */

public class Jarvis {

    public static void main(String[] args) {
        List<Point> points = Lists.newArrayList();

        // 获得最左边且y最小的点
        Point startPoint = findLeftPoint(points);

        Point curPoint = startPoint;

        List<Point> hull = new ArrayList<>(points.size());

        do {
            // 当前点必定是凸点
            hull.add(curPoint);

            // 随机获取下一个点
            Point nextPoint = points.get(0);

            for (Point point : points) {
                // 找到第三个不同的点
                if (point == curPoint || point == startPoint) {
                    continue;
                }
                // 如果第三个点与curPoint的边在右边，则point为凸点
                double cp = crossProduct(curPoint, nextPoint, point);
                if (cp > 0) {
                    nextPoint = point;
                } else if (cp == 0 && distance(curPoint, point) > distance(curPoint, nextPoint)) {
                    // 同一条边比较长度
                    nextPoint = point;
                }
            }
            curPoint = nextPoint;
        } while (startPoint != curPoint); // 表示绕到了起点，已经围成一圈
    }

    private static double crossProduct(Point curPoint, Point nextPoint, Point point) {
        return 0;
    }

    private static Point findLeftPoint(List<Point> points) {
        Point leftmostLowest = points.get(0);
        for (Point point : points) {
            if (point.x < leftmostLowest.x || (point.x == leftmostLowest.x && point.y < leftmostLowest.y)) {
                leftmostLowest = point;
            }
        }
        return leftmostLowest;
    }

    // 计算两点之间的距离
    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    static class Point {
        int x;
        int y;
    }

}

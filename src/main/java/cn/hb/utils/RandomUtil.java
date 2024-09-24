package cn.hb.utils;

import java.util.Random;

/**
 * @author hb
 * @data 2024/9/23
 * @descript 随机数工具类
 */
public class RandomUtil {
    /**
     * 生成随机数
     */
    public static final Random RANDOM = new Random();

    public static int randomInt() {
        return RANDOM.nextInt();
    }

    /**
     * 在范围区间生成随机整数
     *
     * @param start 开始
     * @param end   结束
     * @return int
     */
    public static int randomInt(int start, int end) {
        return RANDOM.nextInt(start, end);
    }
}

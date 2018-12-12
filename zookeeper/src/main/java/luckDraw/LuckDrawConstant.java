package luckDraw;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/19 0019上午 11:52
 */

public class LuckDrawConstant {
    public static final double THE_FIRST_PRIZE_PERCENT = 5;
    public static final double THE_SECOND_PRIZE_PERCENT = 10;
    public static final double THE_THIRD_PRIZE_PERCENT = 15;
    public static final double THE_FOURTH_PRIZE_PERCENT = 20;
    public static final double THE_FIFTH_PRIZE_PERCENT = 50;
    public static final List<Double> luckPercentList = new ArrayList<>();
    static {
        luckPercentList.add(THE_FIRST_PRIZE_PERCENT);
        luckPercentList.add(THE_SECOND_PRIZE_PERCENT);
        luckPercentList.add(THE_THIRD_PRIZE_PERCENT);
        luckPercentList.add(THE_FOURTH_PRIZE_PERCENT);
        luckPercentList.add(THE_FIFTH_PRIZE_PERCENT);
    }
}

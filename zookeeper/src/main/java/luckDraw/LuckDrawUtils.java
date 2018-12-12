package luckDraw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/19 0019上午 11:26
 */

public class LuckDrawUtils {

    /**
     * 构造抽奖概率区间集合
     *
     * @param list
     */
    public static List<LuckRegion> handleLuckPersentList(List<Double> list) {
        List<LuckRegion> luckPersentList = new ArrayList<>();
        if (list.size() == 0) {
            throw new IllegalArgumentException("抽奖集合不能为空！");
        }
        double minElement = 0d;
        double maxElement = 0d;
        LuckRegion luckPercent = null;
        for (Double d : list) {
            minElement = maxElement;
            maxElement = maxElement + d;
            luckPercent = new LuckRegion(minElement/100d, maxElement/100d);
            luckPersentList.add(luckPercent);
        }
        return luckPersentList;
    }



        /**
         * 进行抽奖操作
         * 返回：奖品的概率list集合中的下标
         */
        public static int randomColunmIndex(List<LuckRegion> luckPersentList) {
            int index = -1;
            Random r = new Random();
            double d = r.nextDouble();  //生成0-1间的随机数
            if (d == 0d) {
                d = r.nextDouble();     //防止生成0.0
            }
            int size = luckPersentList.size();
            for (int i = 0; i < size; i++) {
                LuckRegion cl = luckPersentList.get(i);
                if (cl.isContain(d)) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                throw new IllegalArgumentException("概率集合设置不合理！");
            }
            return index;


        }




}

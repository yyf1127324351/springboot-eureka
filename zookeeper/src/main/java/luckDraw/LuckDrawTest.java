package luckDraw;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zookeeper.ZookeeperApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_DOWN;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/19 0019上午 09:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ZookeeperApplication.class)
public class LuckDrawTest {

    @Test
    public void test(){

        Long remainTime = DateUtils.todayRemainingSconds() - 1;
        System.out.println(remainTime);


//        List list = null;
//        Integer i = list.size();
//        System.out.println(i);
//
//        BigDecimal b = new BigDecimal(0.049).setScale(3,ROUND_DOWN);
//        double d = b.doubleValue();
//        System.out.println(d);





//        List<Double> luckPercentList = LuckDrawConstant.luckPercentList;
//
//        List<LuckRegion> luckRegionList = LuckDrawUtils.handleLuckPersentList(luckPercentList);
//        System.out.println(luckRegionList);
//
//
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int i = 0 ;i<1000;i++){
//            Integer index  = LuckDrawUtils.randomColunmIndex(luckRegionList);
//            if (map.containsKey(index)){
//                map.put(index,map.get(index)+1);
//            }else{
//                map.put(index,1);
//            }
//        }
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
//            int index = entry.getKey();
//            int time  = entry.getValue();
//            System.out.println( index+1 +"等奖:"+time+"次");
//        }
//
//
    }


}

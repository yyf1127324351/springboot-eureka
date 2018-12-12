package luckDraw;

/**
 * 类描述：抽奖区间实体类
 * 创建人：yyf
 * 创建时间：2018/7/19 0019下午 02:39
 */

public class LuckRegion {
    private double minElement;
    private double maxElement;

    public LuckRegion(double minElement, double maxElement) {
        if (minElement > maxElement) {
            throw new IllegalArgumentException("区间不合理，minElement不能大于maxElement！");
        }
        this.minElement = minElement;
        this.maxElement = maxElement;
    }

    /**
     * 判断当前集合是否包含特定元素
     *
     * @param element
     */
    public boolean isContain(double element) {
        boolean flag = false;
        if (element > minElement && element <= maxElement) {
            flag = true;
        }
        return flag;
    }

    public double getMinElement() {
        return minElement;
    }

    public void setMinElement(double minElement) {
        this.minElement = minElement;
    }

    public double getMaxElement() {
        return maxElement;
    }

    public void setMaxElement(double maxElement) {
        this.maxElement = maxElement;
    }
}

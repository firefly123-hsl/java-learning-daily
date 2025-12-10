package entity.seat;

/**
 * 票价策略场景枚举（用于切换策略）
 */

public enum PriceStrategyScene {
    NORMAL("普通时段"),
    HOLIDAY("节假日"),
    DISCOUNT("限时折扣"),
    GOLDEN_HOUR("黄金场次"); // 可扩展

    private final String desc;

    PriceStrategyScene(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
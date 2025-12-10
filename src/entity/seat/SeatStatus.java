package entity.seat;

/**
 * 座位状态枚举（可选/已锁定/已售出）
 */
public enum SeatStatus {
    AVAILABLE("可选"),
    LOCKED("已锁定"),
    SOLD("已售出");

    private final String desc;

    SeatStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
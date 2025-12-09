package entity.order;

import characters.user.User;
import entity.seat.Seat;
import entity.session.Session;
import system.CinemaSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Order 的建造者，用于构建复杂订单对象
 * 符合报告中“建造者模式-复杂对象的装配大师”要求
 */
public class OrderBuilder {
    // 与 Order 对应的字段（不包含状态字段）
    private String orderId;
    private String userId;
    private String sessionId;
    private List<Seat> seatList = new ArrayList<>();
    private double totalAmount;
    private LocalDateTime createTime = LocalDateTime.now();

    public OrderBuilder user(User user) {
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("用户不能为空");
        }
        this.userId = user.getUserId();
        return this;
    }

    public OrderBuilder session(Session session) {
        if (session == null || session.getSessionId() == null) {
            throw new IllegalArgumentException("场次不能为空");
        }
        this.sessionId = session.getSessionId();
        return this;
    }

    public OrderBuilder seats(List<Seat> seats) {
        if (seats == null || seats.isEmpty()) {
            throw new IllegalArgumentException("座位列表不能为空");
        }
        this.seatList = new ArrayList<>(seats);
        return this;
    }

    /**
     * 构建 Order 对象
     * - 生成订单ID
     * - 计算总金额（依赖 CinemaSystem 获取场次价格）
     * - 校验完整性
     */
    public Order build() {
        if (userId == null || sessionId == null || seatList.isEmpty()) {
            throw new IllegalStateException("必须设置用户、场次和座位");
        }

        // 1. 生成唯一订单ID（简单方案）
        this.orderId = "ORD" + System.currentTimeMillis() + userId.substring(0, Math.min(4, userId.length()));

        // 2. 计算总金额
        CinemaSystem system = CinemaSystem.getInstance();
        Session session = system.getSessionById(sessionId);
        if (session == null) {
            throw new IllegalStateException("场次不存在: " + sessionId);
        }

        this.totalAmount = seatList.stream()
                .mapToDouble(seat -> {
                    if (seat == null) return 0;
                    return seat.isVip() ? session.getVipPrice() : session.getRegularPrice();
                })
                .sum();

        if (totalAmount <= 0) {
            throw new IllegalStateException("订单金额必须大于0");
        }

        // 3. 创建 Order（调用私有构造器）
        return new Order(this);
    }
}
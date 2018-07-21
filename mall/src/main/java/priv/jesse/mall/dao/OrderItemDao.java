package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.jesse.mall.entity.OrderItem;

import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
    /**
     * 根据订单Id查询
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(int orderId);
}

package priv.jesse.mall.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.OrderItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrderService {

    /**
     * 订单状态 1:未付款 2:等待发货 3:等待收货 4:订单完成
     */
    int STATE_NO_PAY = 1;
    int STATE_WAITE_SEND = 2;
    int STATE_WAIT_RECEIVE = 3;
    int STATE_COMPLETE = 4;


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Order findById(int id);

    /**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    Page<Order> findAll(Pageable pageable);

    /**
     * 按条件查询
     *
     * @param example
     * @return
     */
    List<Order> findAllExample(Example<Order> example);

    /**
     * 更新
     *
     * @param order
     * @return
     */
    void update(Order order);

    /**
     * 创建
     *
     * @param order
     * @return
     */
    int create(Order order);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    void delById(int id);

    /**
     * 查询订单的订单项
     *
     * @param orderId
     * @return
     */
    List<OrderItem> findItems(int orderId);

    /**
     * 更改订单状态
     *
     * @param id
     * @param status
     */
    void updateStatus(int id, int status);

    /**
     * 查找用户的订单列表
     *
     * @param request
     * @return
     */
    List<Order> findUserOrder(HttpServletRequest request);

    /**
     * 支付
     * @param orderId
     */
    void pay(int orderId);

    /**
     * 提交订单
     * @param name
     * @param phone
     * @param addr
     * @param request
     * @param response
     */
    void submit(String name, String phone, String addr, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 确认收货
     * @param orderId
     */
    void receive(int orderId);
}

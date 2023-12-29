package ivan.nppsystem.reposictory;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import ivan.nppsystem.model.Order;
import ivan.nppsystem.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void saveOrderOk() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(10.0);
        product.setQuantity(5.0);
        entityManager.persist(product);

        Order order = new Order();
        order.setProduct(product);
        order.setValue(50.0);
        order.setDeliverDate(LocalDate.now());
        order.setAddress("TestAddress");

        orderRepository.save(order);

        List<Order> all = orderRepository.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0)).isEqualTo(order);
    }

    @Test
    public void updateOrderOk() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(10.0);
        product.setQuantity(5.0);
        entityManager.persist(product);

        Order order = new Order();
        order.setProduct(product);
        order.setValue(50.0);
        order.setDeliverDate(LocalDate.now());
        order.setAddress("TestAddress");
        orderRepository.save(order);

        order.setValue(60.0);
        orderRepository.save(order);

        Order updatedOrder = orderRepository.findById(order.getId()).orElse(null);
        assertThat(updatedOrder).isNotNull();
        assertThat(updatedOrder.getValue()).isEqualTo(60.0);
    }

    @Test
    public void deleteOrderOk() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(10.0);
        product.setQuantity(5.0);
        entityManager.persist(product);

        Order order = new Order();
        order.setProduct(product);
        order.setValue(50.0);
        order.setDeliverDate(LocalDate.now());
        order.setAddress("TestAddress");
        orderRepository.save(order);

        orderRepository.delete(order);

        assertThat(orderRepository.findById(order.getId())).isEmpty();
    }

    @Test
    public void findOrderByIdOk() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(10.0);
        product.setQuantity(5.0);
        entityManager.persist(product);

        Order order = new Order();
        order.setProduct(product);
        order.setValue(50.0);
        order.setDeliverDate(LocalDate.now());
        order.setAddress("TestAddress");
        orderRepository.save(order);

        Order foundOrder = orderRepository.findById(order.getId()).orElse(null);

        assertThat(foundOrder).isNotNull();
        assertThat(foundOrder).isEqualTo(order);
    }
}

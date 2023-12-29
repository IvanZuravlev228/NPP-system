package ivan.nppsystem.reposictory;

import ivan.nppsystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

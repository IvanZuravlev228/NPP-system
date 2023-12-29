package ivan.nppsystem.reposictory;

import ivan.nppsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

package ivan.nppsystem.reposictory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
class ProductRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveProductOk() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(10.0);
        product.setQuantity(5.0);

        productRepository.save(product);

        List<Product> all = productRepository.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0)).isEqualTo(product);
    }

    @Test
    public void updateProductOk() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(10.0);
        product.setQuantity(5.0);

        productRepository.save(product);

        product.setPrice(20.0);
        productRepository.save(product);

        Product updatedProduct = productRepository.findById(product.getId()).orElse(null);
        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.getPrice()).isEqualTo(20.0);
    }

    @Test
    public void deleteProductOk() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(10.0);
        product.setQuantity(5.0);

        productRepository.save(product);

        productRepository.delete(product);

        assertThat(productRepository.findById(product.getId())).isEmpty();
    }

    @Test
    public void findProductByIdOk() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(10.0);
        product.setQuantity(5.0);

        productRepository.save(product);

        Product foundProduct = productRepository.findById(product.getId()).orElse(null);

        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct).isEqualTo(product);
    }
}

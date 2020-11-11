import com.codecool.shop.dao.JdbcImplementation.ProductDaoJdbc;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.memoryImplementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDaoMemTest {

    ProductDao productDataStore;
    Supplier supplier;
    Supplier supplier1;
    ProductCategory category;
    ProductCategory category1;
    Product product;
    Product product1;


    @BeforeEach
    void beforeSetup() throws SQLException {
        productDataStore = ProductDaoMem.getInstance();

        supplier = new Supplier("BWM", "BMW (Bayerische Motoren-Werke) is a German manufacturer of motorcycles and automobiles, founded in 1917 and headquartered in München, Germany.");
        supplier1 = new Supplier("BWM", "BMW (Bayerische Motoren-Werke) is a German manufacturer of motorcycles and automobiles, founded in 1917 and headquartered in München, Germany.");

        category = new ProductCategory("Cars", "Transport", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        category1 = new ProductCategory("Cars", "Transport", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");

        product = new Product("BMW 5 Series", 57050, "EUR", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", category, supplier);
        product1 = new Product("BMW 6 Series", 63850, "EUR", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", category, supplier);

        productDataStore.add(product);
    }

    @AfterEach
    void afterSetup(){
        productDataStore.remove(1);
    }


    @Test
    @Order(1)
    void addProductToMemory() {
        int expectedSize = 1;
        int actualSize = productDataStore.getAll().size();

        assertEquals(expectedSize, actualSize);
    }


    @Test
    @Order(2)
    void findByIdFromMemory() {
        Product actualProduct = productDataStore.find(1);
        Product expectedProduct = product;

        assertEquals(expectedProduct, actualProduct);
    }


    @Test
    @Order(3)
    void removeProductFromMemoryById()  {
        productDataStore.remove(1);

        int expectedSize = 0;
        int actualSize = productDataStore.getAll().size();

        assertEquals(expectedSize, actualSize);
    }



    @Test
    @Order(4)
    void getProductBySupplier_correctSupplier() {

        List<Product> productList = productDataStore.getBy(supplier);

        int expectedSize = 1;
        int actualSize = productList.size();

        assertEquals(expectedSize, actualSize);
    }


    @Test
    @Order(5)
    void getProductBySupplier_incorrectSupplier() {

        List<Product> productList = productDataStore.getBy(supplier1);

        int expectedSize = 0;
        int actualSize = productList.size();

        assertEquals(expectedSize, actualSize);
    }


    @Test
    @Order(6)
    void getProductByCategory_correctCategory() {
        List<Product> productList = productDataStore.getBy(category);

        int expectedSize = 1;
        int actualSize = productList.size();

        assertEquals(expectedSize, actualSize);
    }


    @Test
    @Order(7)
    void getProductByCategory_incorrectCategory() {
        List<Product> productList = productDataStore.getBy(category1);

        int expectedSize = 0;
        int actualSize = productList.size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(8)
    void getAllProductsFromMemory() throws SQLException {
        productDataStore.add(product1);

        int expectedSize = 2;
        int actualSize = productDataStore.getAll().size();

        productDataStore.remove(2);

        assertEquals(expectedSize, actualSize);
    }

}

import com.codecool.shop.config.DatabaseManager;
import com.codecool.shop.dao.JdbcImplementation.CartDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.ProductDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategorySupplierProductDaoJdbcTest {
    public CategorySupplierProductDaoJdbcTest() throws SQLException {
    }

    ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(DatabaseManager.connect());
    SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc(DatabaseManager.connect());
    ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc(DatabaseManager.connect());

    Supplier supplier = new Supplier("Supplier", "description");
    Supplier supplier1 = new Supplier("Supplier1", "description1");
    ProductCategory category = new ProductCategory("Category", "department", "description");
    ProductCategory category1 = new ProductCategory("Category1", "department1", "description1");
    Product product = new Product("Product", 500, "USD", "description", category, supplier);


    @Test
    void addSupplierToDatabase(){
        supplierDaoJdbc.add(supplier);

        int expectedSize = 1;
        int actualSize = supplierDaoJdbc.getAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void addProductCategoryToDatabase(){
        productCategoryDaoJdbc.add(category);

        int expectedSize = 1;
        int actualSize = productCategoryDaoJdbc.getAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void addProductToDatabase(){
        productDaoJdbc.add(product);

        int expectedSize = 1;
        int actualSize = productDaoJdbc.getAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void removeProductFromDatabase(){
        productDaoJdbc.remove(1);

        int expectedSize = 0;
        int actualSize = productDaoJdbc.getAll().size();

        assertEquals(expectedSize, actualSize);

    }

    @Test
    void removeSupplierFromDatabase(){
        supplierDaoJdbc.remove(1);

        int expectedSize = 0;
        int actualSize = supplierDaoJdbc.getAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void removeCategoryFromDatabase(){
        productCategoryDaoJdbc.remove(1);

        int expectedSize = 0;
        int actualSize = productCategoryDaoJdbc.getAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findSupplierInDatabase(){
        supplierDaoJdbc.add(supplier1);

        Supplier actualSupplier = supplierDaoJdbc.find(2);
        Supplier expectedSupplier = supplier1;

        assertEquals(expectedSupplier, actualSupplier);

    }

    @Test
    void findCategoryInDatabase() throws SQLException {
        productCategoryDaoJdbc.add(category1);

        ProductCategory actualCategory = productCategoryDaoJdbc.find(2);
        ProductCategory expectedCategory = category1;

        assertEquals(expectedCategory, actualCategory);
    }


}

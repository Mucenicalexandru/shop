//import com.codecool.shop.config.DatabaseManager;
//import com.codecool.shop.dao.JdbcImplementation.ProductCategoryDaoJdbc;
//import com.codecool.shop.dao.JdbcImplementation.ProductDaoJdbc;
//import com.codecool.shop.dao.JdbcImplementation.SupplierDaoJdbc;
//import com.codecool.shop.dao.ProductDao;
//import com.codecool.shop.dao.memoryImplementation.ProductDaoMem;
//import com.codecool.shop.model.Product;
//import com.codecool.shop.model.ProductCategory;
//import com.codecool.shop.model.Supplier;
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class CategorySupplierProductDaoJdbcTest {
//    public CategorySupplierProductDaoJdbcTest() throws SQLException {
//    }
//
//    ProductDao productDao = new ProductDaoJdbc(DatabaseManager.connect());
//    ProductDao productDao = new ProductDaoMem(DatabaseManager.connect());
//    SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc(DatabaseManager.connect());
//    ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc(DatabaseManager.connect());
//
//    Supplier supplier = new Supplier("Supplier", "description");
//    Supplier supplier1 = new Supplier("Supplier1", "description1");
//    ProductCategory category = new ProductCategory("Category", "department", "description");
//    ProductCategory category1 = new ProductCategory("Category1", "department1", "description1");
//    Product product = new Product("Product", 500, "USD", "description", category, supplier);
//
//    //TODO clear database after each test
//
//    @Test
//    void addSupplierToDatabase(){
//        supplierDaoJdbc.add(supplier);
//
//        int expectedSize = 1;
//        int actualSize = supplierDaoJdbc.getAll().size();
//
//        assertEquals(expectedSize, actualSize);
//    }
//
//
//    @Test
//    void addProductCategoryToDatabase(){
//        productCategoryDaoJdbc.add(category);
//
//        int expectedSize = 1;
//        int actualSize = productCategoryDaoJdbc.getAll().size();
//
//        assertEquals(expectedSize, actualSize);
//    }
//
//
//    @Test
//    void addProductToDatabase(){
//        productDaoJdbc.add(product);
//
//        int expectedSize = 1;
//        int actualSize = productDaoJdbc.getAll().size();
//
//        assertEquals(expectedSize, actualSize);
//    }
//
//
//    @Test
//    void removeProductFromDatabase(){
//        productDaoJdbc.remove(1);
//
//        int expectedSize = 0;
//        int actualSize = productDaoJdbc.getAll().size();
//
//        assertEquals(expectedSize, actualSize);
//
//    }
//
//
//    @Test
//    void removeSupplierFromDatabase(){
//        supplierDaoJdbc.remove(1);
//
//        int expectedSize = 0;
//        int actualSize = supplierDaoJdbc.getAll().size();
//
//        assertEquals(expectedSize, actualSize);
//    }
//
//
//    @Test
//    void removeCategoryFromDatabase(){
//        productCategoryDaoJdbc.remove(1);
//
//        int expectedSize = 0;
//        int actualSize = productCategoryDaoJdbc.getAll().size();
//
//        assertEquals(expectedSize, actualSize);
//    }
//
//
//    @Test
//    void getAllSuppliersFromDatabase(){
//        supplierDaoJdbc.add(supplier);
//        supplierDaoJdbc.add(supplier1);
//
//        int expectedSize = 2;
//        int actualSize = supplierDaoJdbc.getAll().size();
//
//        assertEquals(expectedSize, actualSize);
//
//    }
//
//
//    @Test
//    void getAllCategoriesFromDatabase(){
//        productCategoryDaoJdbc.add(category);
//        productCategoryDaoJdbc.add(category1);
//
//        int expectedSize = 2;
//        int actualSize = productCategoryDaoJdbc.getAll().size();
//
//        assertEquals(expectedSize, actualSize);
//
//    }
//
//
//    @Test
//    void findSupplierInDatabase(){
//
//        Supplier actualSupplier = supplierDaoJdbc.find(0);
//        Supplier expectedSupplier = supplier1;
//
//        System.out.println(actualSupplier);
//
//        assertEquals(expectedSupplier, actualSupplier);
//
//    }
//
//
//    @Test
//    void findCategoryInDatabase() throws SQLException {
//
//        ProductCategory actualCategory = productCategoryDaoJdbc.find(0);
//        ProductCategory expectedCategory = category1;
//
//        assertEquals(expectedCategory, actualCategory);
//    }
//
//
//
//}

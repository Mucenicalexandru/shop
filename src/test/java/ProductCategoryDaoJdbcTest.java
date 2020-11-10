

import com.codecool.shop.config.DatabaseManager;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCategoryDaoJdbcTest {

    DatabaseManager databaseManager;

    @BeforeEach
    void setUp(){
        databaseManager = new DatabaseManager();
        try {
            databaseManager.testSetup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @Test
    void addProductToDatabase() throws SQLException {
        Supplier audi = new Supplier("Audi", "Audi AG is a German automobile manufacturer that designs, engineers, produces, markets and distributes luxury vehicles.");
        ProductCategory cars = new ProductCategory("Cars", "Transport", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        Product product = new Product("Porsche Cayenne Turbo Coupe 4.0", 171900, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, audi);

        databaseManager.addProduct(product, audi, cars);


    }




}

import com.codecool.shop.dao.JdbcImplementation.UserDaoJdbc;
import com.codecool.shop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * After completing the test please use the following command in PostgreSQL to clear the data from the table :
 * TRUNCATE registered_users CASCADE;
 */
public class UserDaoJdbcTest {

    UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
    User user1;
    User user2;
    public UserDaoJdbcTest() throws IOException, SQLException {
    }

    @BeforeEach
    void setUp(){
    user1 = new User("TestFirstName1",
            "testLastName1",
            "testCountry1",
            "testAddress1",
            "testPostcode1",
            "testTown1",
            "testPhone1",
            "testEmail1",
            "testPassword1"
            );
        user2 = new User("TestFirstName2",
                "testLastName2",
                "testCountry2",
                "testAddress2",
                "testPostcode2",
                "testTown2",
                "testPhone2",
                "testEmail2",
                "testPassword2"
        );
    }


    @Test
    void addUser_test(){
        userDaoJdbc.add(user1);
        userDaoJdbc.add(user2);

        int expectedSize = 2;
        int actualSize =  userDaoJdbc.getAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findUserByEmail_test() {
        User user = userDaoJdbc.findByEmail("testEmail1");

        String expectedFirstName = "TestFirstName1";
        String actualFirstName = user.getFirstName();

        assertEquals(expectedFirstName, actualFirstName);
    }


    @Test
    void getAllUsers_test(){
        List<User> listOfUsers = userDaoJdbc.getAll();

        List<String> expectedFirstNames = new ArrayList<>();
        List<String> actualFirstNames = new ArrayList<>();

        expectedFirstNames.add("TestFirstName1");
        expectedFirstNames.add("TestFirstName2");

        listOfUsers.forEach(user -> {
            actualFirstNames.add(user.getFirstName());
        });

        assertEquals(expectedFirstNames, actualFirstNames);
    }

}

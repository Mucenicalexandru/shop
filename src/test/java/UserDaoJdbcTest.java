import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.JdbcImplementation.UserDaoJdbc;
import com.codecool.shop.model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserDaoJdbcTest {
    public UserDaoJdbcTest() throws SQLException {
    }

    User user = new User("Mucenic", "Alexandru", "Romania", "Berlin 15", 900001, "Constanta", 14979657, "alex@gmail.com", "1234");
    UserDaoJdbc userDaoJdbc = new UserDaoJdbc(Connector.connect());


    @Test
    void addUserToDatabaseAndFindUserByEmail(){
        userDaoJdbc.add(user);

        String actualUser = userDaoJdbc.findByEmail("alex@gmail.com").getFirstName();
        String expectedUser = "Mucenic";

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void removeUserFromDatabase(){
        userDaoJdbc.remove(1);

        int actualSize = userDaoJdbc.getAll().size();
        int expectedSize = 0;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void getAllUsers(){
        userDaoJdbc.add(user);
        userDaoJdbc.add(user);
        userDaoJdbc.add(user);
        userDaoJdbc.add(user);
        userDaoJdbc.add(user);

        int actualSize = userDaoJdbc.getAll().size();
        int expectedSize = 5;

        assertEquals(expectedSize, actualSize);
    }


}

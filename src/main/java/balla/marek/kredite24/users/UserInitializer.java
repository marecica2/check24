package balla.marek.kredite24.users;

import balla.marek.kredite24.utils.CSVReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

@Component
@Transactional
public class UserInitializer {

    @PersistenceContext
    EntityManager em;

    public void init() throws IOException {
        List<User> users = initializeUsers();
        new HashSet<>(users).forEach(em::persist);
    }

    public List<User> initializeUsers() throws IOException {
        InputStream csvStream = new ClassPathResource("books-views.csv").getInputStream();
        CSVReader<User> csvReader = new CSVReader<>();
        return csvReader.readCSV(csvStream, row -> new User(row.get("user")));
    }
}

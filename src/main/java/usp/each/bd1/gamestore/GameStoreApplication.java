package usp.each.bd1.gamestore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import usp.each.bd1.gamestore.data.repository.EmployeeRepository;

@SpringBootApplication
public class GameStoreApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GameStoreApplication.class, args);
    }

}

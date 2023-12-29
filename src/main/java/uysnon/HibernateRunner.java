package uysnon;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.BlockingQueue;

public class HibernateRunner {
    @SneakyThrows
    public static void main(String[] args) {
//        BlockingQueue<Connection> pool = null;
//        Connection connection = pool.take();
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "pass"
        );

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // sessionFactory объект должен быть одним на все приложение
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            System.out.println("OK");
        }
    }


}

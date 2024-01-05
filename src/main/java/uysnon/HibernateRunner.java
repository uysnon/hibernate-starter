package uysnon;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import uysnon.model.Role;
import uysnon.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

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
        configuration.addAnnotatedClass(User.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        // sessionFactory объект должен быть одним на все приложение
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("oleg1")
                    .firstname("oleg")
                    .lastname("ivanov")
                    .role(Role.ADMIN)
                    .birthDate(LocalDate.of(1999, 9, 2))
                    .age(24)
                    .build();

            session.persist(user);

           session.getTransaction().commit();
        }
    }


}

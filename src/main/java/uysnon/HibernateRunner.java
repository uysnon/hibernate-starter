package uysnon;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import uysnon.model.BirthDate;
import uysnon.model.Role;
import uysnon.model.User;
import uysnon.model.converter.BirthDateConverter;

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

        configuration.addAttributeConverter(BirthDateConverter.class, true  );

        // sessionFactory объект должен быть одним на все приложение
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("oleg14")
                    .firstname("oleg")
                    .lastname("ivanov")
                    .role(Role.ADMIN)
                    .birthDate(new BirthDate(LocalDate.of(1999, 9, 2)))
                    .info("""
                            {
                                "name": "oleg",
                                "age": 21
                            }
                            """)
                    .build();

          User user1 =  session.get(User.class, "oleg1");



           session.getTransaction().commit();
        }
    }


}

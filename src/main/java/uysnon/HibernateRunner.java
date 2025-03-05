package uysnon;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import uysnon.model.Company;
import uysnon.model.PersonalInfo;
import uysnon.model.User;
import uysnon.util.HibernateUtil;

@Slf4j
public class HibernateRunner {
    @SneakyThrows
    public static void main(String[] args) {
        Company company = Company.builder()
                .name("Mailru")
                .build();
        User user = User.builder()
                .username("ivan6@gmail.com")
                .company(company)
                .personalInfo(PersonalInfo.builder()
                        .firstname("Ivan")
                        .lastname("Vasichkin")
                        .build())
                .build();
        log.info("User entity is in transient state, object: {}", user);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();
                session1.persist(user);
                session1.getTransaction().commit();
            }
        }
    }


}

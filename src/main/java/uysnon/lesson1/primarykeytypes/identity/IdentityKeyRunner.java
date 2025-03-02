package uysnon.lesson1.primarykeytypes.identity;

import uysnon.util.HibernateUtil;

public class IdentityKeyRunner {
    /*
    CREATE TABLE identity_key_table (
            id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
            description TEXT NOT NULL
    );
     */

    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                var transaction = session.beginTransaction();
                IdentityKeyEntity identityKeyEntity = new IdentityKeyEntity();
                identityKeyEntity.setDescription(
                        """
                                Insert записи в БД произойдет
                                до коммита транзакции, т.к. для добавления 
                                сущности в 1-st level cache нужен заполненный ключ.
                                А получить его селектом из бд не получится
                                """
                );
                session.persist(identityKeyEntity); // тут произойдет insert
                transaction.commit();
            }
        }

    }
}

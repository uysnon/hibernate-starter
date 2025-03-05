package uysnon.lesson1.primarykeytypes.uuid;

import uysnon.util.HibernateUtil;

import java.util.UUID;

public class UUIDKeyRunner {
    /*
    CREATE TABLE uuid_key_table (
            id UUID PRIMARY KEY,
            description TEXT NOT NULL
    );
     */

    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                var transaction = session.beginTransaction();
                UUIDKeyEntity entity1 = new UUIDKeyEntity();
                entity1.setDescription(
                        """
                                Insert записи в БД произойдет
                                во время коммита транзакции.
                                
                                Первичный ключ будет сгенерирован через
                                java.util.UUID.randomUUID
                                
                                Доп. обращений к БД не будет
                                """
                );
                session.persist(entity1); // вызов java.util.UUID.randomUUID
                transaction.commit(); // insert
            }
        }
    }
}

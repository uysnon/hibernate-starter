package uysnon.lesson1.primarykeytypes.dbsequence;

import uysnon.util.HibernateUtil;

public class SequenceKeyRunner {
    /*
    CREATE TABLE sequence_key_table (
            id INTEGER PRIMARY KEY,
            description TEXT NOT NULL
    );
    CREATE SEQUENCE seq_sequence_key_table START WITH 1 INCREMENT BY 2 owned by sequence_key_table.id;
     */

    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (var session = sessionFactory.openSession()) {
                var transaction = session.beginTransaction();
                SequenceKeyEntity entity1 = new SequenceKeyEntity();
                entity1.setDescription(
                        """
                                Insert записи в БД произойдет
                                во время коммита транзакции.
                                Но перед этим будет вызвана функция nextval(..)
                                у последовательности для того чтобы обогатить 
                                ключом сущность.
                                """
                );
                var entity2 = SequenceKeyEntity.builder().description("2").build();
                var entity3 = SequenceKeyEntity.builder().description("3").build();

                session.persist(entity1); // тут произойдет обращение к последовательности
                // хибер зарезервирует 2 id-шника, т.к. allocationSize = 2
                session.persist(entity2); // нет sql запросов
                session.persist(entity3); // select nexval

                transaction.commit(); // 3 insert выражения
            }
        }

    }
}

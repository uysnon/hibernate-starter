package uysnon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Cleanup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uysnon.model.*;
import uysnon.util.HibernateUtil;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class HibernateRunnerTest {
    @BeforeAll
    public static void init() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var user1 = User.builder().id(1L).username("Vanya").build();
        var user2 = User.builder().id(2L).username("Oleg").build();
        var user3 = User.builder().id(3L).username("Maksim").build();
        var user4 = User.builder().id(4L).username("Vladislav").build();
        var company1 = Company.builder().id(1).name("Mail.ru").build();
        var company2 = Company.builder().id(2).name("Yandex").build();
        var company3 = Company.builder().id(3).name("School").build();
        var chat = Chat.builder().id(1L).name("Kittens-chat").build();

        session.createMutationQuery("delete from UserChat").executeUpdate();
        session.createMutationQuery("delete from Chat").executeUpdate();
        session.createMutationQuery("delete from User").executeUpdate();
        session.createMutationQuery("delete from Company").executeUpdate();

        Stream.of(user1, user2, user3, user4, company1, company2, company3, chat)
                .forEach(session::persist);
        session.getTransaction().commit();
    }

    @Test
    void checkGetUser() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Chat chat = session.get(Chat.class, 1L);
        User user = session.get(User.class, 4L);
        session.getTransaction().commit();
    }

    @Test
    void checkManyToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Chat chat = session.get(Chat.class, 1L);
        User user = session.get(User.class, 4L);
        UserChat userChat = UserChat.builder()
                .createdAt(LocalDateTime.now())
                .createdBy("alex")
                .build();
        userChat.setChat(chat);
        userChat.setUser(user);
        session.persist(userChat);
        session.getTransaction().commit();
    }

    @Test
    void checkChatCreation() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Chat chat = Chat.builder()
                .id(101L)
                .name("teachers-chat")
                .build();
        session.persist(chat);
        session.getTransaction().commit();
    }

    @Test
    void checkReflectionApi() {
        User user = User.builder()
                .username("oleg1999")
                .personalInfo(PersonalInfo.builder()
                        .firstname("oleg")
                        .lastname("olegov")
                        .birthDate(new BirthDate(LocalDate.of(1999, 9, 2)))
                        .build())
                .build();

        String sql = """
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;

        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] declaredFields = user.getClass().getDeclaredFields();
        String columnNames = Arrays.stream(declaredFields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())
                )
                .collect(Collectors.joining(", "));

        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        System.out.println(String.format(sql, tableName, columnNames, columnValues));
    }

}
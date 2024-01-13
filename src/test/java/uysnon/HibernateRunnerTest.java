package uysnon;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import uysnon.model.*;
import uysnon.model.util.HibernateUtil;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

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
package uysnon;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;
import uysnon.model.User;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

    @Test
    void checkReflectionApi() {
        User user = User.builder()
                .username("oleg1999")
                .firstname("oleg")
                .lastname("ivanov")
                .birthDate(LocalDate.of(1999, 9, 2))
                .age(24)
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
                .map(field  -> "?")
                .collect(Collectors.joining(", "));

        System.out.println(String.format(sql, tableName, columnNames, columnValues));
    }

}
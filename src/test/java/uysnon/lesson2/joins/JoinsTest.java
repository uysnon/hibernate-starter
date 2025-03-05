package uysnon.lesson2.joins;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uysnon.lesson2.joins.entity.Cat;
import uysnon.lesson2.joins.entity.Home;
import uysnon.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JoinsTest {
    static List<Integer> catsIds = new ArrayList<>();
    static List<Integer> homeIds = new ArrayList<>();

    @BeforeAll
    public static void init() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        session.createMutationQuery("delete from Cat").executeUpdate();
        session.createMutationQuery("delete from Home").executeUpdate();

        var home1 = Home.builder().address("Mocsow, 1").areaInMeters(129).build();
        var home2 = Home.builder().address("St.Petersburg, 23/2").areaInMeters(35).build();
        var home3 = Home.builder().address("Kazan, 23/3").areaInMeters(35).build();
        var cat1 = Cat.builder().name("Masha").home(home1).color("Black").build();
        var cat2 = Cat.builder().name("Vika").home(home1).color("Orange").build();
        var cat3 = Cat.builder().name("Zina").home(home1).color("White").build();
        var cat4 = Cat.builder().name("Venik").home(home2).color("Grey").build();
        var cat5 = Cat.builder().name("Rizhik").home(home3).color("Rizhiy").build();

        Stream.of(
                        home1
                        , home2
                        , home3
                ).peek(session::persist)
                .forEach(e -> homeIds.add(e.getId()));

        Stream.of(
                        cat1
                        , cat2
                        , cat3
                        , cat4
                        , cat5

                ).peek(session::persist)
                .forEach(e -> catsIds.add(e.getId()));


        session.getTransaction().commit();
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            Find all cats
            """)
    public void getAllCats() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        // будет ли тут какая-то проблема?
        var searchResult = session.createSelectionQuery("from Cat", Cat.class);

        List<Cat> cats = searchResult.getResultList();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(cats));
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            Find 1 cat
            """)
    public void getOneCat() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        var catId = catsIds.get(0);

        // будет ли тут какая-то проблема?
        var cat = session.get(Cat.class, catId);
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writeValueAsString(cat));
    }
}
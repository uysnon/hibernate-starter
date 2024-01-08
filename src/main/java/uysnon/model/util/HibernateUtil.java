package uysnon.model.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import uysnon.model.User;
import uysnon.model.converter.BirthDateConverter;

@UtilityClass
public class HibernateUtil {

    public SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(BirthDateConverter.class);
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
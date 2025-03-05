package uysnon.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import uysnon.lesson1.primarykeytypes.dbsequence.SequenceKeyEntity;
import uysnon.lesson1.primarykeytypes.identity.IdentityKeyEntity;
import uysnon.lesson1.primarykeytypes.uuid.UUIDKeyEntity;
import uysnon.model.User;
import uysnon.model.converter.BirthDateConverter;

@UtilityClass
public class HibernateUtil {

    public SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(IdentityKeyEntity.class);
        configuration.addAnnotatedClass(SequenceKeyEntity.class);
        configuration.addAnnotatedClass(UUIDKeyEntity.class);
        configuration.addAttributeConverter(BirthDateConverter.class);
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
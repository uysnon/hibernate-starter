package uysnon.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import uysnon.model.BirthDate;

import java.sql.Date;
import java.util.Optional;

@Converter(autoApply = true)
public class BirthDateConverter implements AttributeConverter<BirthDate, Date> {
    @Override
    public Date convertToDatabaseColumn(BirthDate attribute) {
        return Optional.ofNullable(attribute)
                .map(a -> Date.valueOf(a.birthDate()))
                .orElse(null);
    }

    @Override
    public BirthDate convertToEntityAttribute(Date dbData) {
        return Optional.ofNullable(dbData)
                .map(d -> new BirthDate(dbData.toLocalDate()))
                .orElse(null);
    }
}

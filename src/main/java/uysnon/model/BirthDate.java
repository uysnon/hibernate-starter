package uysnon.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record BirthDate (LocalDate birthDate) {

    long getAge() {
        return ChronoUnit.YEARS.between(LocalDate.now(), birthDate);
    }
}

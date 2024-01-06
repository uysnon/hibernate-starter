package uysnon.model;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import uysnon.model.converter.BirthDateConverter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    private String username;
    private String firstname;
    private String lastname;
    @Convert(converter = BirthDateConverter.class)
    @Column(name = "birth_date")
    private BirthDate birthDate;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Type(JsonBinaryType.class)
    private String info;
}

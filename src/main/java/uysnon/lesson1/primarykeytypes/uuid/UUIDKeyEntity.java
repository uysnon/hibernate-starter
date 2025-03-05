package uysnon.lesson1.primarykeytypes.uuid;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Table(name = "uuid_key_table")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UUIDKeyEntity {
    @Id
    @UuidGenerator
    private UUID id;
    private String description;
}

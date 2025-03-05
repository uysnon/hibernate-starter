package uysnon.lesson1.primarykeytypes.identity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "identity_key_table")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentityKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
}

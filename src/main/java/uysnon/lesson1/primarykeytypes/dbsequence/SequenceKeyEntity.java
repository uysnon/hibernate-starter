package uysnon.lesson1.primarykeytypes.dbsequence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "sequence_key_table")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name = "any_name", // используется только внутри java
        sequenceName = "seq_sequence_key_table",
        allocationSize = 2 // сколько будующих id будет "кешировать" hibernate,
        // по умолчанию 50
        // но кажется что операция не такая дорогая по вызову nextval у последоватлеьности
        // поэтому чтобы не было "дыр" в id лучше указать единичку
        // должно сочетаться с инкрементом на уровне БД
)
public class SequenceKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "any_name")
    private Long id;
    private String description;
}

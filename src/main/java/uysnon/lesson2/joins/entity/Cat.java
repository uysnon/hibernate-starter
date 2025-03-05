package uysnon.lesson2.joins.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "cat")
public class Cat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "age")
    private Integer age;
    @Column(name = "name")
    private String name;
    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "home_id")
    private Home home;
}

package uysnon.lesson2.joins.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "home")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Home {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "area_in_meters")
    private Integer areaInMeters;
}

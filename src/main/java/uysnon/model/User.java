package uysnon.model;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // по сути просто метаинформация для программиста
    // + может использоваться при генерации ddl
    private String username;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded // опционально, без этой аннотации тоже можно, но так более читабельно
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Type(JsonBinaryType.class)
    private String info;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "company_id")
    @ToString.Exclude
    private Company company;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "users_chat",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private List<Chat> chats = new ArrayList<>();

    public void addChat(Chat chat){
        chats.add(chat);
        chat.getUsers().add(this);
    }
}

package balla.marek.kredite24.bookstore.book;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Book implements Serializable {

    @Id
    @NaturalId
    @NonNull
    private String id;

    @NonNull
    private String name;

    @NonNull
    @Column(length = 1000)
    private String details;

    @NonNull
    private BigDecimal price;

    @NonNull
    @Column(length = 1000)
    private String image;
}

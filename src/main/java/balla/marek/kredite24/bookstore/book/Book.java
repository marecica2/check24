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
public class Book implements Serializable {

    @Id
    @Getter
    @Setter
    @NaturalId
    @NonNull
    private String id;

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    @NonNull
    @Column(length = 1000)
    private String details;

    @Getter
    @Setter
    @NonNull
    private BigDecimal price;

    @Getter
    @Setter
    @NonNull
    @Column(length = 1000)
    private String image;
}

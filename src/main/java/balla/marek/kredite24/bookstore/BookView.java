package balla.marek.kredite24.bookstore;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class BookView {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Getter
    @Setter
    @NonNull
    private String user;

    @Getter
    @Setter
    @NonNull
    @ManyToMany
    private List<Book> book = new ArrayList<>();
}

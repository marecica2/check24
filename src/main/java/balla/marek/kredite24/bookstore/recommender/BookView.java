package balla.marek.kredite24.bookstore.recommender;

import balla.marek.kredite24.bookstore.book.Book;
import balla.marek.kredite24.users.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookView implements Serializable {

    @Getter
    @Setter
    @EmbeddedId
    @NonNull
    private BookViewId id;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookViewId implements Serializable {
        @Getter
        @Setter
        @ManyToOne(cascade = CascadeType.DETACH)
        private Book book;

        @Getter
        @Setter
        @ManyToOne(cascade = CascadeType.DETACH)
        private User user;
    }
}

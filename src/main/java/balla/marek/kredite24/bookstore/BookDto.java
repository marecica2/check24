package balla.marek.kredite24.bookstore;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {

    @Getter
    @Setter
    @NonNull
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String details;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private String image;
}

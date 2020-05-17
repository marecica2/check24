package balla.marek.kredite24.users;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class User implements Serializable {

    @Id
    @Getter
    @Setter
    @NaturalId
    @NonNull
    private String email;
}

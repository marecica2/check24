package balla.marek.kredite24;

import balla.marek.kredite24.bookstore.book.BookInitializer;
import balla.marek.kredite24.users.UserInitializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.transaction.Transactional;
import java.io.IOException;

@Configuration
@ConditionalOnProperty(
        name = "kredite24.initialization.enable",
        havingValue = "true",
        matchIfMissing = true)
public class StartupConfiguration {

    private final UserInitializer userInitializer;

    private final BookInitializer bookInitializer;

    public StartupConfiguration(UserInitializer userInitializer,
                                BookInitializer bookInitializer
    ) {
        this.userInitializer = userInitializer;
        this.bookInitializer = bookInitializer;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initializeDb() throws IOException {
        userInitializer.init();
        bookInitializer.init();
    }
}

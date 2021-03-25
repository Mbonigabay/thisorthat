package rw.thisorthat.thisorthat.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {
    @Autowired
    UserSeeder userSeeder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        userSeeder.seedUsersTable();
    }

}
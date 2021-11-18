package pl.smile.SmileApp.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Admin {
    private final String ADMIN_EMAIL = "admin@gmail.com";
    private final String ADMIN_PW = "admin123";

}

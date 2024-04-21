package store.backendpojectfinal.Web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/vetements/**")  // Adjust the mapping pattern according to your API endpoints
                .allowedOrigins("http://localhost:4200")  // Allow requests from Angular app
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific HTTP methods
                .allowCredentials(true);  // Allow credentials (cookies, authorization headers)
    }
}
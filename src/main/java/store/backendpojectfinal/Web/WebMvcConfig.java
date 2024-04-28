package store.backendpojectfinal.Web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Adjust the mapping pattern according to your API endpoints
                .allowedOrigins("http://localhost:4200/")  // Allow requests from Angular app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin")
                .exposedHeaders("Authorization", "Content-Length", "X-Foo", "X-Bar")
                .allowCredentials(true)
                .maxAge(3600);  // Cache pre-flight response for 1 hour
    }
}
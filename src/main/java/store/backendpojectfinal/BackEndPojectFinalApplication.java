package store.backendpojectfinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import store.backendpojectfinal.service.StoreService;

@SpringBootApplication
public class BackEndPojectFinalApplication  implements CommandLineRunner {

    @Autowired
    private StoreService storeService;

    public static void main(String[] args) {
        SpringApplication.run(BackEndPojectFinalApplication.class, args);
    }


    public void run(String... args) throws Exception {
        storeService.initVetements();
    }
}

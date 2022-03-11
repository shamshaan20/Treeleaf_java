
import com.treeleaf.demo.Manufactures;
import com.treeleaf.demo.Models;
import Repositries.ManufactureRepo;
import Repositries.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MappingApplication implements CommandLineRunner {

    @Autowired
    ManufactureRepo manufacturesRepo;

    @Autowired
    ModelRepo modelRepo;

    public static void main(String[] args) {
        SpringApplication.run(MappingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Manufactures data=new Manufactures(1,"Honda");

        // Inserting the record in the Manufactures table.
        manufacturesRepo.save(data);

        // Now try to mapped above record with multiple models
        Models model1=new Models(1,"AYZ",data);
        Models model2=new Models(2,"ZET",data);
        modelRepo.save(model1);
        modelRepo.save(model2);

    }
}

package Repositries;

import com.treeleaf.demo.Manufactures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepo extends JpaRepository<Manufactures,Integer> {
}

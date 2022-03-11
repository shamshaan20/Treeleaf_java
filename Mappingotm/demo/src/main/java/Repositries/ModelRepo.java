package Repositries;

import com.treeleaf.demo.Models;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<Models, Integer> {
}

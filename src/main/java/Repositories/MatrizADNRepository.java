package Repositories;

import Entities.MatrizADN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrizADNRepository extends JpaRepository<MatrizADN, Long> {
}

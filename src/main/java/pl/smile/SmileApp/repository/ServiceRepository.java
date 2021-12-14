package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.DentalService;

@Repository
public interface ServiceRepository extends JpaRepository<DentalService, Long> {
}

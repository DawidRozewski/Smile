package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smile.SmileApp.entity.DentalTreatment;


public interface DentalTreatmentRepository extends JpaRepository<DentalTreatment, Long> {
}

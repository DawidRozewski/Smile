package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smile.SmileApp.entity.Services;

public interface ServicesRepository extends JpaRepository<Services, Long> {
}

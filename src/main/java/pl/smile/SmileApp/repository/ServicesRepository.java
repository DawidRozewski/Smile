package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.Services;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
}

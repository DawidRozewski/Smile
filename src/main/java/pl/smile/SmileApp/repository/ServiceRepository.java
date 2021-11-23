package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}

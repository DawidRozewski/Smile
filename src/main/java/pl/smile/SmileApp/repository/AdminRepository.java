package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findByUsername(String username);
}

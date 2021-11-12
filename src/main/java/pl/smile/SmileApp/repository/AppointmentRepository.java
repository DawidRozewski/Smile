package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smile.SmileApp.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

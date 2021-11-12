package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smile.SmileApp.entity.AppointmentSlot;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {
}

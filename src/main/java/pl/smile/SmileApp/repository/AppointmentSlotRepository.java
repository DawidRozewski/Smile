package pl.smile.SmileApp.repository;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.AppointmentSlot;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {
}

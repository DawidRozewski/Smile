package pl.smile.SmileApp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smile.SmileApp.entity.DentalTreatment;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.DentalTreatmentRepository;
import pl.smile.SmileApp.service.DentalTreatmentService;

import java.util.List;

@AllArgsConstructor
@Service
public class DentalTreatmentServiceImpl implements DentalTreatmentService {

    private final DentalTreatmentRepository dentalTreatmentRepository;

    @Override
    public void delete(long id) {
        DentalTreatment dentalTreatment = getDentalTreatment(id);
        dentalTreatmentRepository.delete(dentalTreatment);
    }

    @Override
    public DentalTreatment getById(long id) {
        return getDentalTreatment(id);
    }

    private DentalTreatment getDentalTreatment(long id) {
        return dentalTreatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Dental Treatment", id));
    }

    @Override
    public DentalTreatment save(DentalTreatment dentalTreatment) {
       return dentalTreatmentRepository.save(dentalTreatment);
    }

    @Override
    public List<DentalTreatment> getAll() {
        return dentalTreatmentRepository.findAll();
    }
}

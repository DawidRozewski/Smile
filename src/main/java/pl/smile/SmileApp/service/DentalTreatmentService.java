package pl.smile.SmileApp.service;

import pl.smile.SmileApp.entity.DentalTreatment;

import java.util.List;

public interface DentalTreatmentService {
    DentalTreatment getById(long id);
    DentalTreatment save(DentalTreatment dentalTreatment);
    void delete(long id);
    List<DentalTreatment> getAll();

}

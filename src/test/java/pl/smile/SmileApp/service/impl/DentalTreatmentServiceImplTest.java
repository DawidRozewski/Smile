package pl.smile.SmileApp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.smile.SmileApp.entity.DentalTreatment;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.DentalTreatmentRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static pl.smile.SmileApp.service.impl.ServiceHelper.createDentalTreatment;

@ExtendWith(MockitoExtension.class)
class DentalTreatmentServiceImplTest {

    public static final long DENTAL_TREATMENT_ID = 1L;
    public static final long NON_EXISTING_ID = 99L;
    @Mock
    private DentalTreatmentRepository dentalTreatmentRepository;

    @InjectMocks
    private DentalTreatmentServiceImpl dentalTreatmentService;

    private DentalTreatment dentalTreatment;

    @BeforeEach
    void setUp() {
        dentalTreatment = createDentalTreatment();
    }

    @Test
    public void givenDentalTreatmentId_whenDelete_thenRemoveDentalTreatmentObject() {
        // given
        given(dentalTreatmentRepository.findById(DENTAL_TREATMENT_ID)).willReturn(Optional.of(dentalTreatment));
        willDoNothing().given(dentalTreatmentRepository).delete(dentalTreatment);
        // when
        dentalTreatmentService.delete(DENTAL_TREATMENT_ID);
        // then
        verify(dentalTreatmentRepository, times(1)).delete(dentalTreatment);
    }

    @Test
    public void givenDentalTreatmentId_whenGetById_thenReturnDentalTreatmentObject() {
        // given
        given(dentalTreatmentRepository.findById(DENTAL_TREATMENT_ID)).willReturn(Optional.of(dentalTreatment));
        // when
        DentalTreatment dentalTreatmentById = dentalTreatmentService.getById(DENTAL_TREATMENT_ID);
        // then
        assertThat(dentalTreatmentById).isNotNull();
        assertThat(dentalTreatmentById.getId()).isEqualTo(DENTAL_TREATMENT_ID);
    }

    @Test
    public void givenNonExistingId_whenDelete_thenThrowError() {
        //given
        given(dentalTreatmentRepository.findById(NON_EXISTING_ID)).willReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> dentalTreatmentService.delete(NON_EXISTING_ID))
                .isInstanceOf(ResourceNotFound.class)
                .hasMessage("Dental Treatment with id: 99 not found.");

    }

    @Test
    public void givenDentalTreatmentObject_whenSave_thenReturnSavedObject() {
        // given
        given(dentalTreatmentRepository.save(dentalTreatment)).willReturn(dentalTreatment);
        // when
        DentalTreatment savedDentalTreatment = dentalTreatmentService.save(dentalTreatment);
        // then
        assertThat(savedDentalTreatment).isNotNull();
        assertThat(savedDentalTreatment.getId()).isEqualTo(DENTAL_TREATMENT_ID);
    }

    @Test
    public void givenDentalTreatmentList_whenGetAll_thenReturnDentalTreatmentList() {
        // given
        DentalTreatment dentalTreatment1 = DentalTreatment.builder()
                .id(DENTAL_TREATMENT_ID)
                .amount(100)
                .description("Description test")
                .RTG("Yes")
                .build();
        given(dentalTreatmentRepository.findAll()).willReturn(Arrays.asList(dentalTreatment, dentalTreatment1));
        // when
        List<DentalTreatment> dentalTreatmentList = dentalTreatmentService.getAll();
        // then
        assertThat(dentalTreatmentList).isNotNull();
        assertThat(dentalTreatmentList.size()).isEqualTo(2);
    }


}
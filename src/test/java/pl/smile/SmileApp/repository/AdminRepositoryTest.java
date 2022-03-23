package pl.smile.SmileApp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.smile.SmileApp.entity.Admin;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void givenUsername_whenFindByUsername_thenFindAdminObject() {
        // given
        Admin admin = new Admin(1L, "admin", "admin");
        adminRepository.save(admin);
        // when
        Admin savedAdmin = adminRepository.findByUsername("admin");
        // then
        assertThat(savedAdmin).isNotNull();
        assertThat(savedAdmin.getUsername()).isEqualTo(admin.getUsername());
    }
}
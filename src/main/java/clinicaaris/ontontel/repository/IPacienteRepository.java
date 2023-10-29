package clinicaaris.ontontel.repository;


import clinicaaris.ontontel.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
}

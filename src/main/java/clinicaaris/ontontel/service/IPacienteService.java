package clinicaaris.ontontel.service;

import clinicaaris.ontontel.model.DTO.PacienteDTO;

import java.util.Collection;

public interface IPacienteService {
    public PacienteDTO crearPaciente(PacienteDTO paciente);
    PacienteDTO buscarPaciente(Long id);
    public PacienteDTO editPaciente(PacienteDTO paciente);
    public boolean eliminarPaciente(Long id);
    Collection<PacienteDTO> listarPaciente();
}

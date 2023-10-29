package clinicaaris.ontontel.service;

import clinicaaris.ontontel.model.DTO.TurnoDTO;

import java.util.Collection;

public interface ITurnoService {
    public TurnoDTO crearTurno(TurnoDTO turno);
    TurnoDTO buscarTurno(Long id);
    public TurnoDTO editTurno(TurnoDTO turno);
    public boolean eliminarTurno(Long turno);
    Collection<TurnoDTO> listaTurnos();


}

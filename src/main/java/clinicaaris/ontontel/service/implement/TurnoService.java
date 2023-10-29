package clinicaaris.ontontel.service.implement;


import clinicaaris.ontontel.model.DTO.TurnoDTO;
import clinicaaris.ontontel.model.Turno;
import clinicaaris.ontontel.repository.ITurnoRepository;
import clinicaaris.ontontel.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TurnoService implements ITurnoService {
    private static final Logger logger = Logger.getLogger(TurnoService.class);
    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public TurnoDTO crearTurno(TurnoDTO turno) {
        turno.setId(0L);
        logger.info("Se creo el turno: "+turno);
        Turno turnoGuardar=turnoRepository.save(mapper.convertValue(turno,Turno.class));
        turno.setId(turno.getId());
        logger.info("Se creo el turno: " + turnoGuardar);
        return mapper.convertValue(turnoGuardar, TurnoDTO.class);
    }

    @Override
    public TurnoDTO buscarTurno(Long id) {
        return null;
    }

    @Override
    public TurnoDTO editTurno(TurnoDTO turno) {
        return null;
    }

    @Override
    public boolean eliminarTurno(Long turno) {
        return false;
    }

    @Override
    public Collection<TurnoDTO> listaTurnos() {
        return null;
    }
}

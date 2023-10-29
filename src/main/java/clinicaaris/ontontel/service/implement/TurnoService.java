package clinicaaris.ontontel.service.implement;


import clinicaaris.ontontel.model.DTO.TurnoDTO;
import clinicaaris.ontontel.model.Turno;
import clinicaaris.ontontel.repository.ITurnoRepository;
import clinicaaris.ontontel.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        logger.info("Buscando turno por id: " + id);
        Optional<Turno> optionalTurno=turnoRepository.findById(id);
        if (optionalTurno.isPresent()){
            logger.info("Turno con id: " + id + " encontrado");
            return mapper.convertValue(optionalTurno, TurnoDTO.class);
        }
        logger.info("Turno con id: " + id + " no encontrado");
        return null;
    }

    @Override
    public TurnoDTO editTurno(TurnoDTO turno) {
        logger.info("Editar Turno: "+ turno);
        if (buscarTurno(turno.getId()) != null){
            Turno turnoGua=turnoRepository.save(mapper.convertValue(turno,Turno.class));
            logger.info("Se edito el Turno: "+ turnoGua);
            return mapper.convertValue(turnoGua,TurnoDTO.class);
        }
        return null;
    }

    @Override
    public boolean eliminarTurno(Long id) {
        logger.info("Eliminando turno con id: " + id);
        if (buscarTurno(id) != null){
            turnoRepository.deleteById(id);
            logger.info("Turno con id: " + id + " eliminado.");
            return true;


        }
        return false;
    }

    @Override
    public Collection<TurnoDTO> listaTurnos() {
        logger.info("Creando lista de turnos");
        List<Turno> turnoLista = turnoRepository.findAll();
        Set<TurnoDTO> turnosDTO = new HashSet<>();
        for (Turno turno:turnoLista){
            turnosDTO.add(mapper.convertValue(turno, TurnoDTO.class));
        }
        return null;
    }
}

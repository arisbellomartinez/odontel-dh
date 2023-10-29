package clinicaaris.ontontel.service.implement;

import clinicaaris.ontontel.model.DTO.PacienteDTO;
import clinicaaris.ontontel.model.Paciente;
import clinicaaris.ontontel.repository.IPacienteRepository;
import clinicaaris.ontontel.service.IPacienteService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements IPacienteService {
    private static final Logger logger = Logger.getLogger(PacienteService.class);
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public PacienteDTO crearPaciente(PacienteDTO paciente){
        paciente.setId(0L);
        logger.info("Se creo el paciente: "+paciente);
        Paciente pacienteGuardar=pacienteRepository.save(mapper.convertValue(paciente,
                Paciente.class));
        paciente.setId(paciente.getId());
        logger.info("Creacion completa: "+pacienteGuardar);
        return mapper.convertValue(pacienteGuardar,PacienteDTO.class);
    }

    @Override
    public PacienteDTO buscarPaciente(Long id){
        logger.info("Busqueda de paciente con id: "+ id);
        Optional<Paciente> o = pacienteRepository.findById(id);
        if (o.isPresent()){
            logger.info("Paciente con id indentificador: "+id+" Se encontro");
            return mapper.convertValue(o,PacienteDTO.class);
        }
        logger.info("Paciente con id proporcionado no fue encontrado");
        return null;
    }

    @Override
    public PacienteDTO editPaciente(PacienteDTO paciente) {
        logger.info("Editar paciente: "+paciente);
        if (buscarPaciente(paciente.getId()) != null){
            Paciente aEdit=pacienteRepository.save(mapper.convertValue(paciente,
                    Paciente.class));
            logger.info("Se edito el paciente: "+aEdit);
            return mapper.convertValue(aEdit,PacienteDTO.class);
        }

        return null;
    }

    @Override
    public boolean eliminarPaciente(Long id) {
        logger.info("Eliminar paciente con id: "+id);
        if (buscarPaciente(id)!=null){
            pacienteRepository.deleteById(id);
            logger.info("Se Elimino paciente con id: "+id);
            return true;
        }

        return false;
    }

    @Override
    public Collection<PacienteDTO> listarPaciente() {
        logger.info("Creando listado de peacientes");
        List<Paciente> pacienteList=pacienteRepository.findAll();
        Set<PacienteDTO> pacienteDTOS=new HashSet<>();
        for (Paciente p:pacienteList){
            pacienteDTOS.add(mapper.convertValue(p,PacienteDTO.class));
        }
        logger.info("Se creo la lista de pacientes");
        return pacienteDTOS;
    }




}

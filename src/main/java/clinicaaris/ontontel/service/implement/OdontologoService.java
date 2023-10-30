package clinicaaris.ontontel.service.implement;


import clinicaaris.ontontel.model.DTO.OdontologoDTO;
import clinicaaris.ontontel.model.Odontologo;
import clinicaaris.ontontel.repository.IOdontologoRepository;
import clinicaaris.ontontel.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements IOdontologoService {
    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    @Autowired
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public OdontologoDTO crearOdontologo(OdontologoDTO odon) {
        odon.setId(0L);
        logger.info("Se esta creando el odontologo: " + odon);
        Odontologo odontologoGuar = odontologoRepository.save(mapper.convertValue(odon, Odontologo.class));
        odon.setId(odon.getId());
        logger.info("Se creo correctamente el odontologo: " + odontologoGuar);
        return mapper.convertValue(odontologoGuar, OdontologoDTO.class);
    }

    @Override
    public OdontologoDTO buscarOdontologo(Long id) {
        logger.info("Buscando el odontologo por id: " + id);
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            logger.info("Se encontro el odontologo con id: " + id);
            return mapper.convertValue(odontologo, OdontologoDTO.class);
        }
        logger.info("No se pudo encontrar el odontologo con id: " + id);
        return null;
    }

    @Override
    public OdontologoDTO editOdontologo(OdontologoDTO odon) {
        logger.info("Se editara el odontologo con id: "+ odon);
        if(buscarOdontologo(odon.getId()) != null) {
            Odontologo guar = odontologoRepository.save(mapper.convertValue(odon, Odontologo.class));
            logger.info("Se edito el odontologo: "+ guar);
            return mapper.convertValue(guar,OdontologoDTO.class);
        }
        return null;
    }

    @Override
    public boolean eliminarOdontologo(Long id) {
        logger.info("Se eliminara odontologo con id: " + id);
        if(buscarOdontologo(id) !=null){
            odontologoRepository.deleteById(id);
            logger.info("Se elimino el odontologo con id: " + id);
            return true;
        }
        return false;
    }

    @Override
    public Collection<OdontologoDTO> listarOdontologos() {
        logger.info("Creando lista de odontologos");
        List<Odontologo> odonList = odontologoRepository.findAll();
        Set<OdontologoDTO> odontologosDTO = new HashSet<>();
        for (Odontologo odontolgo:odonList) {
            odontologosDTO.add(mapper.convertValue(odontolgo, OdontologoDTO.class));
        }
        logger.info("Se creo la lista de odontologos");
        return odontologosDTO;
    }
    }


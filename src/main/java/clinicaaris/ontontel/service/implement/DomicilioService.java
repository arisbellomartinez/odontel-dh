package clinicaaris.ontontel.service.implement;


import clinicaaris.ontontel.model.DTO.DomicilioDTO;
import clinicaaris.ontontel.model.Domicilio;
import clinicaaris.ontontel.repository.IDomicilioRepository;
import clinicaaris.ontontel.service.IDomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DomicilioService implements IDomicilioService {
    private static final Logger logger = Logger.getLogger(DomicilioService.class);
    @Autowired
    private IDomicilioRepository domicilioRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public DomicilioDTO crearDomicilio(DomicilioDTO domi) {
        domi.setId(0L);
        logger.info("Esta creando el domicilio: " + domi);
        Domicilio domiGuar = domicilioRepository.save(mapper.convertValue(domi,
                Domicilio.class));
        domi.setId(domi.getId());
        logger.info("El domicilio fue creado: " + domiGuar);
        return mapper.convertValue(domiGuar, DomicilioDTO.class);
    }

    @Override
    public DomicilioDTO buscarDomicilio(Long id) {
        logger.info("Buscando el domicilio con id: " + id);
        Optional<Domicilio> optionalDomicilio = domicilioRepository.findById(id);
        if(optionalDomicilio.isPresent()){
            logger.info("Se encontro el domicilio con id: " + id);
            return mapper.convertValue(optionalDomicilio, DomicilioDTO.class);
        }
        logger.info("No se encontro el domicilio con id: " + id);
        return null;
    }

    @Override
    public DomicilioDTO editDomicilio(DomicilioDTO domicilio) {
        logger.info("Se va a editar el domicilio: "+ domicilio);
        if(buscarDomicilio(domicilio.getId()) != null) {
            Domicilio guar = domicilioRepository.save(mapper.convertValue(domicilio, Domicilio.class));
            logger.info("Se edito el domicilio: "+ guar);
            return mapper.convertValue(guar,DomicilioDTO.class);
        }
        return null;
    }

    @Override
    public boolean elimDomicilio(Long id) {
        logger.info("Se va a eliminar el domicilio con id: " + id);
        if(buscarDomicilio(id) !=null){
            domicilioRepository.deleteById(id);
            logger.info("Se elimino el domicilio con id:" + id);
            return true;
        }
        return false;
    }

    @Override
    public Collection<DomicilioDTO> listarDomicilio() {
        logger.info("Creando lista de domicilios");
        List<Domicilio> domiList = domicilioRepository.findAll();
        Set<DomicilioDTO> domiciliosDTO = new HashSet<>();
        for (Domicilio domicilio:domiList) {
            domiciliosDTO.add(mapper.convertValue(domicilio, DomicilioDTO.class));
        }
        logger.info("La lista de domicilios fue creada");
        return domiciliosDTO;
    }
}

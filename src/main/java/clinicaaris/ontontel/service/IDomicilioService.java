package clinicaaris.ontontel.service;

import clinicaaris.ontontel.model.DTO.DomicilioDTO;

import java.util.Collection;

public interface IDomicilioService {
    public DomicilioDTO crearDomicilio(DomicilioDTO domicilio);
    DomicilioDTO buscarDomicilio(Long id);
    public DomicilioDTO editDomicilio(DomicilioDTO domicilio);
    public boolean elimDomicilio(Long id);
    Collection<DomicilioDTO> listarDomicilio();

}

package clinicaaris.ontontel.service;

import clinicaaris.ontontel.model.DTO.OdontologoDTO;

import java.util.Collection;

public interface IOdontologoService {
    public OdontologoDTO crearOdontologo(OdontologoDTO odontologo);
    OdontologoDTO buscarOdontologo(Long id);
    public OdontologoDTO editOdontologo(OdontologoDTO odontologo);
    public boolean eliminarOdontologo(Long id);
    Collection<OdontologoDTO> listarOdontologos();
}

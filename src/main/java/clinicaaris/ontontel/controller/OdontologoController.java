package clinicaaris.ontontel.controller;

import clinicaaris.ontontel.model.DTO.OdontologoDTO;
import clinicaaris.ontontel.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    IOdontologoService odontologoService;

    @GetMapping("/listar")
    public Collection<OdontologoDTO> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardarOdontologo(@RequestBody OdontologoDTO odon){
        return new ResponseEntity<OdontologoDTO>(odontologoService.crearOdontologo(odon), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarOdontologo(@PathVariable Long id) {
        OdontologoDTO odontologo = odontologoService.buscarOdontologo(id);
        if(odontologo != null){
            return new  ResponseEntity<OdontologoDTO>(odontologo, HttpStatus.OK);
        }
        return new ResponseEntity<String>("El odontologo no fue encontrado",
                HttpStatus.NOT_FOUND);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOdontologo(@RequestBody OdontologoDTO odon){
        OdontologoDTO odontologoGuar = odontologoService.editOdontologo(odon);
        if(odontologoGuar != null){
            return new  ResponseEntity<OdontologoDTO>(odontologoGuar, HttpStatus.OK);
        }
        return new ResponseEntity<String>("El id proporcionado es inexistente",
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {
        if(odontologoService.eliminarOdontologo(id)) {
            return new ResponseEntity<String>("El odontologo fue eliminado",
                    HttpStatus.OK);
        }
        return new ResponseEntity<String>("El odontologo proporcionado no puede ser " +
                "encontrado", HttpStatus.NOT_FOUND);
    }
}

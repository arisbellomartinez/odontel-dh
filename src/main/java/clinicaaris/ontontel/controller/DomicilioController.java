package clinicaaris.ontontel.controller;


import clinicaaris.ontontel.model.DTO.DomicilioDTO;
import clinicaaris.ontontel.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {
    @Autowired
    IDomicilioService domicilioService;

    @GetMapping("/listar")
    public Collection<DomicilioDTO> listarDomicilios(){
        return domicilioService.listarDomicilio();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardarDomicilio(@RequestBody DomicilioDTO domi){
        return new ResponseEntity<DomicilioDTO>( domicilioService.crearDomicilio(domi),
                HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarDomicilio(@PathVariable Long id) {
        DomicilioDTO domi = domicilioService.buscarDomicilio(id);
        if(domi != null){
            return new  ResponseEntity<DomicilioDTO>(domi, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Este Domicilio no fue  encontrado",
                HttpStatus.NOT_FOUND);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarDomicilio(@RequestBody DomicilioDTO domi){
        DomicilioDTO domiGuar = domicilioService.editDomicilio(domi);
        if(domiGuar != null){
            return new  ResponseEntity<DomicilioDTO>(domiGuar, HttpStatus.OK);
        }
        return new ResponseEntity<String>("El domicilio con esta id no existe",
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDomicilio(@PathVariable Long id) {
        if(domicilioService.elimDomicilio(id)) {
            return new ResponseEntity<String>("Se elimino este domicilio", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Este domicilio no fue encontrado",
                HttpStatus.NOT_FOUND);
    }
}

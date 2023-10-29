package clinicaaris.ontontel.controller;

import clinicaaris.ontontel.model.DTO.TurnoDTO;
import clinicaaris.ontontel.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    ITurnoService turnoService;

    @GetMapping("/listar")
    public Collection<TurnoDTO> listarTurno(){
        return turnoService.listaTurnos();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoDTO turno){
        return new ResponseEntity<TurnoDTO>(turnoService.crearTurno(turno), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarTurno(@PathVariable Long id){
        TurnoDTO turno = turnoService.buscarTurno(id);
        if(turno != null){
            return new  ResponseEntity<TurnoDTO>(turno, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Turno no se encontro", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/modificar")
    public ResponseEntity<?> modTurno(@RequestBody TurnoDTO turno){
        TurnoDTO turnoEdit = turnoService.editTurno(turno);
        if(turnoEdit != null){
            return new  ResponseEntity<TurnoDTO>(turnoEdit, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Id de turno no existente",
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id){
        if (turnoService.eliminarTurno(id)){
            return new ResponseEntity<String>("Se elimino",HttpStatus.OK);
        }
        return new ResponseEntity<String>("No se encontro este turno",
                HttpStatus.NOT_FOUND);
    }
}

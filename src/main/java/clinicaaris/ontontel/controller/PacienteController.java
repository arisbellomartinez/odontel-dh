package clinicaaris.ontontel.controller;

import clinicaaris.ontontel.model.DTO.PacienteDTO;
import clinicaaris.ontontel.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    IPacienteService pacienteService;
    @GetMapping("/listar")
    public Collection<PacienteDTO> listarPaciente(){
        return pacienteService.listarPaciente();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearPaciente(@RequestBody PacienteDTO paciente){
        return new ResponseEntity<PacienteDTO>(pacienteService.crearPaciente(paciente), HttpStatus.OK);

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPaciente(@PathVariable Long id){
        PacienteDTO paciente= pacienteService.buscarPaciente(id);
        if (paciente != null){
            return new ResponseEntity<PacienteDTO>(paciente,HttpStatus.OK);
        }
        return new ResponseEntity<String>("No se pudo encontrar al paciente",
                HttpStatus.NOT_FOUND);
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarPaciente(@RequestBody PacienteDTO paciente){
        PacienteDTO pacienteModificar=pacienteService.editPaciente(paciente);
        if (pacienteModificar != null){
            return new ResponseEntity<PacienteDTO>(pacienteModificar, HttpStatus.OK);
        }
        return new ResponseEntity<String>("ID inexistente",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id){
        if (pacienteService.eliminarPaciente(id)){
            return new ResponseEntity<String>("Se elimino el paciente",HttpStatus.OK);
        }
        return new ResponseEntity<String>("No se pudo encontrar este paciente",
                HttpStatus.NOT_FOUND);
    }
}

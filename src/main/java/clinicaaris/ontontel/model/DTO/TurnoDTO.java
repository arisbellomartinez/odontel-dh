package clinicaaris.ontontel.model.DTO;

import clinicaaris.ontontel.model.Odontologo;
import clinicaaris.ontontel.model.Paciente;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TurnoDTO implements Serializable {
    private Long id;
    private Date fecha;
    private Odontologo odontologo;
    private Paciente paciente;
}

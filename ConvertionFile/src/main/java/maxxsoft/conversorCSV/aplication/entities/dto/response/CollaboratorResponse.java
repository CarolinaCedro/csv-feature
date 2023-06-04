package maxxsoft.conversorCSV.aplication.entities.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollaboratorResponse {
    private String codigo;
    private String conta;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
}

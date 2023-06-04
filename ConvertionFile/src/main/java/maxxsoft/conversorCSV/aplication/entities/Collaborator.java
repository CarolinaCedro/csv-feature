package maxxsoft.conversorCSV.aplication.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collaborator {

    @Id
    private String codigo;
    private String conta;
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    public Collaborator(String conta, String name) {
        this.conta = conta;
        this.name = name;
    }
}

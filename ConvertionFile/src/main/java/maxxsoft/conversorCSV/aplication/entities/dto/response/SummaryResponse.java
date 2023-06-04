package maxxsoft.conversorCSV.aplication.entities.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryResponse {

    private String cod_summary;
    private String cod_collaborator;
    private String name_collaborator;
    private String cod_conta;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private String tp_despesa;
    private String tp_combustivel;
    private String tp_document;
    private String qtde;
    private String valor;
    private String tp_consolidacao;
    private String observacao;
    private String registro;

}

package maxxsoft.conversorCSV.aplication.service.conversion;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import maxxsoft.conversorCSV.aplication.entities.enums.Collum;
import maxxsoft.conversorCSV.aplication.service.conversion.jackson.HeadersCollaboratorValidsDesserializer;
import maxxsoft.conversorCSV.aplication.service.conversion.jackson.HeadersSummaryValidsDesserializer;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionCollaboratorParams {

    private String initialDate;
    private String finalDate;
    private String delimiter;

    @JsonDeserialize(using = HeadersCollaboratorValidsDesserializer.class)
    private List<Collum> headers;

}

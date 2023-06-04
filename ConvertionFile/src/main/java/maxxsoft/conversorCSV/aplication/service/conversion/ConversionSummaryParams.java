package maxxsoft.conversorCSV.aplication.service.conversion;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import maxxsoft.conversorCSV.aplication.entities.enums.Collum;
import maxxsoft.conversorCSV.aplication.service.conversion.jackson.HeadersSummaryValidsDesserializer;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionSummaryParams {

    private String initialDate;
    private String finalDate;
    private String delimiter;

    @JsonDeserialize(using = HeadersSummaryValidsDesserializer.class)
    private List<Collum> headers;

}

package maxxsoft.conversorCSV.aplication.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import maxxsoft.conversorCSV.aplication.jackson.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary {

    @Id
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

    @DBRef
    private ClassRelacionadas.Conta conta;


    @DBRef
    @JsonSerialize(using = ContatoSerializer.class)
    @JsonDeserialize(using = ContatoDeserializer.class)
    private ClassRelacionadas.Contato contato;

//    @DBRef
//    @JsonSerialize(using = PropriedadeSerializer.class)
//    @JsonDeserialize(using = PropriedadeDeserializer.class)
//    private ClassRelacionadas.Propriedade propriedade;

    @DBRef
    @JsonSerialize(using = CicloSerializer.class)
    @JsonDeserialize(using = CicloDeserializer.class)
    private ClassRelacionadas.Ciclo ciclo;

}

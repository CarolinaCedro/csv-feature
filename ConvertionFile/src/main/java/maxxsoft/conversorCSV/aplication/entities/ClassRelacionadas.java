package maxxsoft.conversorCSV.aplication.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

public class ClassRelacionadas {
    // Classe Conta

    @Document
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Conta {
        private String id;
        private String descricao;
        private String nome;


    }


    @Document
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Contato {
        private String id;
        private String descricao;
        private String nome;


    }


    @Document
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Propriedade {
        private String id;
        private String descricao;
        private String nome;

    }



    @Document
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Ciclo {
        private String id;
        private String descricao;
        private String nome;

    }

}

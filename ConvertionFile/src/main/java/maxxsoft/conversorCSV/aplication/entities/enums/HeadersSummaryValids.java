package maxxsoft.conversorCSV.aplication.entities.enums;

import java.util.Arrays;


public enum HeadersSummaryValids implements Collum {


    //name_collaborator,id_collaborator,cod_conta,date,tp_despesa,tp_combustivel,tp_document,qtde,valor,tp_consolidacao,observacao,registro

    //Passo os campos que a entidade tem staticos
    COD_SUMMARY("cod_summary", "Código..."),
    NAME_COLLABORATOR("name_collaborator", "Nome"),
    ID_COLLABORATOR("id_collaborator", "Cód. Cola"),
    COD_CONTA("cod_conta", "Cod. Conta"),
    DATE("date", "Data"),
    TP_DESPESA("tp_despesa", "Tipo pessoa"),
    TP_COMBUSTIVEL("tp_combustivel", "Tipo combustivel"),
    TP_DOCUMENT("tp_document", "documento"),
    QTDE("qtde", "qtde"),
    VALOR("valor", "Valor"),
    TP_CONSOLIDACAO("tp_consolidacao", "Tipo consolicação"),
    OBSERVACAO("observacao", "Observação"),
    REGISTRO("registro", "registro"),
    //AQUI IMPORANTE POIS ESPECIFICA A COLUNA
    CONTA("conta","Conta"),
    CICLO("ciclo","Ciclo"),
    TESTE("teste","Teste"),
    CONTATO("contato","Contato");


    private String collum;
    private String path;

//constructor
    HeadersSummaryValids(String path, String collum) {
        this.path = path;
        this.collum = collum;
    }


    public static HeadersSummaryValids from(String value) {
        return Arrays.stream(HeadersSummaryValids.values())
                .parallel()
                .filter(it -> {
                    try {
                        return it.getCollum().equalsIgnoreCase(value) || it.getPath().equalsIgnoreCase(value) || it.name().equalsIgnoreCase(value);
                    }catch (Throwable ex){
                        System.out.println(ex);
                        throw ex;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item nao encontrado"));
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getCollum() {
        return this.collum;
    }

    @Override
    public String toString() {
        return path;
    }
}

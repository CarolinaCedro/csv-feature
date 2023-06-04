package maxxsoft.conversorCSV.aplication.entities.enums;

import java.util.Arrays;


public enum HeadersCollaboratorValids implements Collum {


    //codigo,conta,name
    CONTA("conta","Conta"),
    NAME("name","Nome"),
    DATE("date","Date");


    private String collum;
    private String path;

//constructor
    HeadersCollaboratorValids(String path, String collum) {
        this.path = path;
        this.collum = collum;
    }


    public static HeadersCollaboratorValids from(String value) {
        return Arrays.stream(HeadersCollaboratorValids.values())
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

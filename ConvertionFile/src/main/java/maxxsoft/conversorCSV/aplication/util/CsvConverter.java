package maxxsoft.conversorCSV.aplication.util;

import maxxsoft.conversorCSV.aplication.entities.Result;
import maxxsoft.conversorCSV.aplication.entities.enums.Collum;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public class CsvConverter {

    public static ByteArrayResource convertToCsv(List<Result> results, List<Collum> headers, String delimiterValid) throws IOException {
        StringBuilder fileCsv = new StringBuilder();

        headers.forEach(it -> fileCsv.append(it).append(delimiterValid));

        fileCsv.append("\n");

        results.forEach(it -> {
            fileCsv.append(it.getResult()).append("\n");
            System.out.println(it.getResult());
        });

        // Cria um ByteArrayResource a partir do conteúdo do arquivo CSV
        byte[] csvBytes = fileCsv.toString().getBytes();
        ByteArrayResource resource = new ByteArrayResource(csvBytes);

        return resource;
    }


//    public static void convertToCsv(List<Result> results, List<Collum> headers, String delimiterValid) throws IOException {
//
////         String CSV_PATH = "~/Documentos/Projects";
//        String CSV_PATH = "/home/pc/Documentos/Projects/conversionCvs.csv";
//        StringBuilder fileCsv = new StringBuilder();
//
//
//        headers.forEach(it->fileCsv.append(it).append(delimiterValid));
//
//        fileCsv.append("\n");
//
//        results.forEach(it ->{
//            fileCsv.append(it.getResult()).append("\n");
//            System.out.println(it.getResult());
//        });
//
//
//        try {
//            System.out.printf("Iniciando geração csv...");
//            final File file = new File(CSV_PATH);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//
//            FileWriter fileWriter = new FileWriter(file);
//            fileWriter.append(fileCsv);
//            fileWriter.flush();
//            fileWriter.close();
//
//
//            System.out.println("Escrita csv finalizada!");
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}

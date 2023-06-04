package maxxsoft.conversorCSV.aplication.exception.errors;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

public class ApiErrors {

    @Getter
    @Setter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;

    }

    public ApiErrors(String message){
        this.errors = Collections.singletonList(message);
    }

}
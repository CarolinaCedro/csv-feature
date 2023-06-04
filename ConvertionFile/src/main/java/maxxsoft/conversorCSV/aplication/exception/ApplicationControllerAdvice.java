package maxxsoft.conversorCSV.aplication.exception;

import maxxsoft.conversorCSV.aplication.exception.errors.ApiErrors;
import maxxsoft.conversorCSV.aplication.exception.errors.DecodingExceptionEnumType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(DecodingExceptionEnumType.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleResponseDecondingException(DecodingExceptionEnumType ex){
        String mensagemError = ex.getMessage().toString();
        return new ApiErrors(mensagemError);
    }


}

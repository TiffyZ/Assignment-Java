package homework.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponseDTO {
    private String message;
    private Date date = new Date();


    public ExceptionResponseDTO(String message) {
        this.message = message;
    }
}

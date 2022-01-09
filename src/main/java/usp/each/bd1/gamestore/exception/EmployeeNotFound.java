package usp.each.bd1.gamestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Não foi achado o funcionário")
public class EmployeeNotFound extends Exception {
    public EmployeeNotFound (String msg) {
        super(msg);
    }
}

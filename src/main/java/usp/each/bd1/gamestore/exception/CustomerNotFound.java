package usp.each.bd1.gamestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Não foi achado o funcionário")
public class CustomerNotFound extends Exception {
    public CustomerNotFound(String msg) {
        super(msg);
    }
}

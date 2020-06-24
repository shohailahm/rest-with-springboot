package org.mvn.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthException  extends AuthenticationException {

    public InvalidJwtAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidJwtAuthException(String msg) {
        super(msg);
    }
}

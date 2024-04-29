package org.rest.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class CantDeleteCustomer extends RuntimeException {

    public CantDeleteCustomer(Exception exception) {
        log.error("Unable to delete customer because of: {0}", exception);
    }
}

package pl.mmarkowicz.interview.exception;


import lombok.Value;

import java.util.List;

@Value
public class InvalidQueryParameters extends RuntimeException {
    private List<String> errorCodes;
}

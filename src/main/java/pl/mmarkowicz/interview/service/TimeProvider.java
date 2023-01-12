package pl.mmarkowicz.interview.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TimeProvider {
    public LocalDate now() {
        return LocalDate.now();
    }
}

package com.example.demo.Services;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class DateService {

    public LocalDateTime getCurrentDate(){
        return LocalDateTime.now();
    }
}
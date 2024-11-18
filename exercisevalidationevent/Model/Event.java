package com.example.exercisevalidationevent.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty(message = "ID can't be empty")
    @Size(min=3,message = "ID Length  should be more than 2")
    private String ID;

    @NotEmpty(message = "description can't be empty")
    @Size(min=16,message = "description Length should be more than 15")
    private String description;

    @NotNull(message = "capacity can't be null")
    @Size(min=26,message = "size can't be null")
    @Pattern(regexp = "[\\s]*[1-9][0-9]*$",message="capacity should be positive number")


    private int capacity;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

}

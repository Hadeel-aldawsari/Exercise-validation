package com.example.exercisevalidationevent.Controller;


import com.example.exercisevalidationevent.ApiResponse.ApiResponse;
import com.example.exercisevalidationevent.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events=new ArrayList<>();

    @PostMapping("/create")
    public ResponseEntity createEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Project add successfully"));
    }


    @GetMapping("/get-all")
    public ResponseEntity displayAll(){
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @PutMapping("/update/{index}")
    public ResponseEntity update(@PathVariable int index,@RequestBody @Valid Event event,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        events.set(index,event);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("event updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity delete(@PathVariable int index){
        events.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("event removed successfully"));
    }


    @PutMapping("/change-capacity/{index}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index,@PathVariable int capacity){
        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Capacity updated successfully"));
    }


    @GetMapping("/search/{id}")
    public ResponseEntity searchByID(@PathVariable String id){
        for (Event event : events) {
            if(event.getID().equalsIgnoreCase(id))return ResponseEntity.status(HttpStatus.OK).body(event);
                        return ResponseEntity.status(HttpStatus.OK).body(event);

        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("event not found"));
    }



}

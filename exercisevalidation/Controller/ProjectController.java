package com.example.exercisevalidation.Controller;

import com.example.exercisevalidation.ApiResponse.ApiResponse;
import com.example.exercisevalidation.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project> projects=new ArrayList<>();

    @PostMapping("/create")
    public ResponseEntity createProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        projects.add(project);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Project add successfully"));
    }


    @GetMapping("/get-all")
    public ResponseEntity displayAll(){
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }


    @PutMapping("/update/{index}")
    public ResponseEntity update(@PathVariable int index,@RequestBody @Valid Project project,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        projects.set(index,project);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("project updated suussfully"));
    }


    @DeleteMapping("/delete/{index}")
    public ResponseEntity delete(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("project removed successfully"));
    }

@PutMapping("/change-status/{index}")
    public ResponseEntity changeStatus(@PathVariable int index){
        if(projects.get(index).getStatus().equalsIgnoreCase("Not Started")) {
            projects.get(index).setStatus("In Progress");
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("project status changed to In Progress"));
        }

    if(projects.get(index).getStatus().equalsIgnoreCase("In Progress")){
        projects.get(index).setStatus("Completed");
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("project status changed to Completed"));
    }
    if(projects.get(index).getStatus().equalsIgnoreCase("Completed")){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Project already completed"));
    }

    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Status changed successfully"));

    }

@GetMapping("/search/{title}")
public ResponseEntity search(@PathVariable String title){
        for(Project project:projects){
            if(project.getTitle().equalsIgnoreCase(title))
                return ResponseEntity.status(HttpStatus.OK).body(project);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("project not found"));
}

@GetMapping("search-by-company/{companyName}")
public ResponseEntity searchByCompanyName(@PathVariable String companyName) {
    ArrayList<Project> projectByCompany = new ArrayList<>();
    for (Project project : projects) {
        if (project.getCompanyName().equalsIgnoreCase(companyName))
            projectByCompany.add(project);
    }
    return ResponseEntity.status(HttpStatus.OK).body(projectByCompany);
}









}

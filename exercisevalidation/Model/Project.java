package projectSysytem.exercisevalidation.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotEmpty(message = "ID should not be empty")
    @Size(min = 3, max = 50,message = "ID lenght should be greater than 2")
    private String ID;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 9, max = 100,message="Title should be more than 8")
    private String title;

    @NotEmpty(message = "description should not be empty")
    @Size(min = 16, message = "description length should be more than 15")
    private String description;

    @NotEmpty(message = "status should not be empty")
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "Status must be 'Not Started', 'In Progress', or 'Completed'")
    private String status;

    @NotEmpty(message = "Company name should not be empty")
    @Size(min = 7, max = 150,message = "Company name length should more than 6")
    private String companyName;
}

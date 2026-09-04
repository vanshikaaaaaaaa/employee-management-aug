package employee_management.dto.response;

import employee_management.entity.Department;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String email;
    private Department department;



}
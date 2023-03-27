package homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private int id;
    private String name;
    private List<StudentDTO> students = new ArrayList<>();
}

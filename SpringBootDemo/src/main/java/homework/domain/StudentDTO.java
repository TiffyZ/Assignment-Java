package homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private int id;
    private String name;
    private List<TeacherDTO> teachers = new ArrayList<>();
}

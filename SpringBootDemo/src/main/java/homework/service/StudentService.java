package homework.service;

import homework.domain.StudentDTO;
import homework.domain.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentService {
    List<StudentDTO> findAll();
    StudentDTO findById(int id);
    List<TeacherDTO> findTeacherById(int id);
    int insert(StudentDTO student);
    void update(StudentDTO student);
}

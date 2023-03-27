package homework.repository;

import homework.domain.StudentDTO;
import homework.domain.TeacherDTO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface StudentRepository {
    List<StudentDTO> findAll();
    StudentDTO findById(int id);
    int insert(StudentDTO student);
    void update(StudentDTO student);
}

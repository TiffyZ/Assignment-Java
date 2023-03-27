package homework.repository;

import homework.domain.TeacherDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeacherRepository {
    List<TeacherDTO> findAll();
}

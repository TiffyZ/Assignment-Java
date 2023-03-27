package homework.service;

import homework.domain.StudentDTO;
import homework.domain.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    List<TeacherDTO> findAll();
}

package homework.repository;

import homework.domain.StudentDTO;
import homework.domain.TeacherDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepositoryImpl implements StudentRepository{
    private final Map<Integer, StudentDTO> cache = new HashMap<>();

    @Override
    public List<StudentDTO> findAll() {
        return cache.values().stream().toList();
    }

    @Override
    public StudentDTO findById(int id) {
        return cache.get(id);
    }

    @Override
    public int insert(StudentDTO student) {
        int id = student.getId();
        cache.put(id, student);
        return id;
    }

    @Override
    public void update(StudentDTO student) {
        cache.put(student.getId(), student);
    }
}

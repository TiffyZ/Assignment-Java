package homework.repository;

import homework.domain.TeacherDTO;
import org.springframework.stereotype.Repository;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class TeacherRepositoryImpl implements TeacherRepository {
    private final Map<Integer, TeacherDTO> cache = new HashMap<>();


    @Override
    public List<TeacherDTO> findAll() {
        return cache.values().stream().toList();
    }
}

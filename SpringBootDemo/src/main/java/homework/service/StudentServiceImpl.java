package homework.service;

import homework.domain.StudentDTO;
import homework.domain.TeacherDTO;
import homework.exception.ResourceNotFoundException;
import homework.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDTO findById(int id) {
        Optional<StudentDTO> optionalStudent = Optional.ofNullable(studentRepository.findById(id));
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        throw new ResourceNotFoundException("Student not found with id " + id);
    }

    @Override
    public List<TeacherDTO> findTeacherById(int id) {
        Optional<StudentDTO> optionalStudent = Optional.ofNullable(studentRepository.findById(id));
        if (optionalStudent.isPresent()) {
            return optionalStudent.get().getTeachers();
        }
        throw new ResourceNotFoundException("Student not found with id " + id);
    }

    @Override
    public int insert(StudentDTO student) {
        return studentRepository.insert(student);
    }

    @Override
    public void update(StudentDTO student) {
        Optional<StudentDTO> optionalStudent = Optional.ofNullable(studentRepository.findById(student.getId()));
        if (optionalStudent.isPresent()) {
            StudentDTO studentDTO = optionalStudent.get();
            studentRepository.update(student);
        }
        throw new ResourceNotFoundException("Student not found with id " + student.getId());
    }
}
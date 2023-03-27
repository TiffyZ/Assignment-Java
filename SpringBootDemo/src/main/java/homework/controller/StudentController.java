package homework.controller;

import homework.domain.StudentDTO;
import homework.exception.ExceptionResponseDTO;
import homework.exception.ResourceNotFoundException;
import homework.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/{id}/teachers")
    public ResponseEntity<?> getTeachersById(@PathVariable int id) {
        return new ResponseEntity<>(studentService.findTeacherById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> insertStudent(@RequestBody StudentDTO student) {
        return new ResponseEntity<>(studentService.insert(student), HttpStatus.CREATED);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody StudentDTO student, @PathVariable int id) {
        studentService.update(student);
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException() {
        return new ResponseEntity<>(new ExceptionResponseDTO("cannot find this resource"), HttpStatus.NOT_FOUND);
    }
}

package homework.controller;

import homework.exception.ExceptionResponseDTO;
import homework.exception.ResourceNotFoundException;
import homework.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException() {
        return new ResponseEntity<>(new ExceptionResponseDTO("cannot find this resource"), HttpStatus.NOT_FOUND);
    }
}

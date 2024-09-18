package com.msk.student.controller;

import com.msk.student.model.Student;
import com.msk.student.response.Response;
import com.msk.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Susheel Panchabhai
 * @version v1.0
 */
@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    /**
     * @param student This method takes student object as a parameter
     * @return This api method returns a String message
     * @apiNote This is a post mapping that creates a new student and saves to the database through service and repository layers
     */
    @PostMapping("/student")
    public ResponseEntity<Response> createStudent(@RequestBody Student student) {
        try {
            studentService.addStudent(student);
            return new ResponseEntity<>(new Response("Student added successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response("Failed to add Student"), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @return This api method returns either a list of students or returns a string message
     * @apiNote This is a get mapping that fetches all the students from the database through service and repository layers and returns a list of students,
     * if the students are fetched, otherwise returns a failure message
     */
    @GetMapping("/student")
    public ResponseEntity<?> findAllStudent () {
        try {
            List<Student> students = studentService.getAll();
            if (students.isEmpty()) {
                return new ResponseEntity<>(new Response("Failed to get all student"), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(students, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Response("Failed"), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id This method is taking ID of type Integer as a parameter
     * @return This api method returns either a student object or returns a string message
     * @apiNote This is a get mapping that deletes a student based on its ID from the database through service and repository layers
     * and returns a String message
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable Integer id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student == null) {
                return new ResponseEntity<>(new Response("Please enter correct Id of Student"), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Response("Failed to fetch student"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param student This method is takes student object as a parameter
     * @param id This method is taking ID of type Integer as a parameter
     * @return This api method returns a string message
     * @apiNote This is a put mapping that updates the student based on its ID from the database through
     * service and repository layers and  returns a String message
     */
    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudentById (@PathVariable Integer id, @RequestBody Student student) {

        try {
            studentService.updateStudentById(id, student);
            return new ResponseEntity<>(new Response("Student Details updated successfully!!"), HttpStatus.OK);

        } catch (Exception e ) {
            return new ResponseEntity<>(new Response("Failed to update Student"), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id This method is taking ID of type Integer as a parameter
     * @return This api method returns a string message
     * @apiNote This is a get mapping that fetches the student based on its ID from the database through service and repository layers
     * and returns a object of student,if the student is fetched by its ID, otherwise returns a failure message
     */
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Response> removeStudent(@PathVariable Integer id) {

        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                studentService.deleteStudent(id);
                return new ResponseEntity<>(new Response("Student deleted successfully!!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response("Please enter correct Id of student"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Response("Failed to delete student"), HttpStatus.NOT_FOUND);
        }
    }
}

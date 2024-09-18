package com.msk.student.service;


import com.msk.student.model.Student;
import com.msk.student.repository.StudentRepository;
import com.msk.student.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void updateStudentById(Integer id, Student student) {
        Student student1 = new Student();
        student1.setId(id);
        student1.setName(student.getName());
        student1.setLocation(student.getLocation());
        student1.setBranch(student.getBranch());
        studentRepository.save(student1);
    }
}

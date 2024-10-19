package com.subham.StudentManagementSystem.service;

import com.subham.StudentManagementSystem.model.Student;
import com.subham.StudentManagementSystem.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class StudentService {


    private StudentRepo repo;

    public StudentRepo getRepo() {
        return repo;
    }
    @Autowired
    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }

    public void addStudent(Student s) {
        repo.save(s);
    }

    public List<Student> getStudents() {
        return repo.findAll();
    }

    public Student getTopper() {
        List<Student> students = repo.findAll();
        Student topper = new Student();
        topper.setMarks(0);
        for(Student s : students){
            if(s.getMarks()>topper.getMarks())
                topper = s;
        }
        return topper;
    }

    public void updateMarks(int roll, int nmarks, Student s) {
        repo.change(roll, nmarks, s);
    }

    public void removeStudent(int rol, Student s) {
        repo.remove(rol, s);
    }
}

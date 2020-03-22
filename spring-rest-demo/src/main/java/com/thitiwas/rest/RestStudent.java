package com.thitiwas.rest;

import com.thitiwas.entity.Student;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/rest-student")
public class RestStudent {

    private final static Logger logger = Logger.getLogger(RestStudent.class);


    private List<Student> students;

    @PostConstruct
    public void loadData()
    {
        students = new ArrayList<>();

        students.add(new Student("thitiwas","nupan"));
        students.add(new Student("chalo","timkul"));
        students.add(new Student("thitiwas","nupan"));
        students.add(new Student("chalo","timkul"));
        students.add(new Student("thitiwas","nupan"));
        students.add(new Student("chalo","timkul"));
        logger.debug("load data "+Arrays.toString(students.toArray()));
    }

    @GetMapping("/list")
    public List<Student> getApiStudent()
    {
        return students;
    }

    @GetMapping("/list/{studentId}")
    public Student getStudentById(@PathVariable int studentId)
    {

        logger.debug("student id :: "+ studentId);

        if((studentId > students.size()) || studentId < 0)
        {
            throw new StudentNotFoundException("student not found "+ studentId);
        }

        return students.get(studentId);
    }
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleExection(StudentNotFoundException e)
    {
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        logger.debug("HttpStatus.NOT_FOUND.value :: > " + HttpStatus.NOT_FOUND);
        logger.debug("Integer.parseInt(String.valueOf(HttpStatus.NOT_FOUND)) :: > " + Integer.parseInt(String.valueOf(HttpStatus.NOT_FOUND)));
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp((int) System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleExection2(Exception e)
    {
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp((int) System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}

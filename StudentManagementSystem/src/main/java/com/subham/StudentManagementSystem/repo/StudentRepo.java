package com.subham.StudentManagementSystem.repo;

import com.subham.StudentManagementSystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s) {
        String query = "insert into student(rollNo, name, marks) values (?,?,?)";
        int rows = jdbc.update(query,s.getRollNo(),s.getName(),s.getMarks());//update returns the no of rows affected
        System.out.println(rows+" rows affected");
    }

    public List<Student> findAll() {
        String query = "select * from student";
        /*RowMapper<Student> mapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student s = new Student();
                s.setRollNo(rs.getInt("rollNo"));
                s.setName(rs.getString("name"));
                s.setMarks(rs.getInt("marks"));
                return s;
            }
        };*/
        return jdbc.query(query,(rs, rowNum) -> {
            Student s = new Student();
            s.setRollNo(rs.getInt("rollNo"));
            s.setName(rs.getString("name"));
            s.setMarks(rs.getInt("marks"));
            return s;
        });
    }

    public void change(int roll, int nmarks, Student s) {
        String query = "update student set marks = ? where rollNo = ?";
        int rows = jdbc.update(query, nmarks, roll);
        System.out.println(rows+" rows affected...");
    }

    public void remove(int rol, Student s) {
        String query = "delete from student where rollNo = ?";
        int rows = jdbc.update(query, rol);
        System.out.println(rows+" rows affected...");
    }
}

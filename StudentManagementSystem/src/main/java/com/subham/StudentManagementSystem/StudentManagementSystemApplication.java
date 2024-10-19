package com.subham.StudentManagementSystem;

import com.subham.StudentManagementSystem.model.Student;
import com.subham.StudentManagementSystem.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class StudentManagementSystemApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context =  SpringApplication.run(StudentManagementSystemApplication.class, args);

		Scanner sc = new Scanner(System.in);
		int choice;
		do{
			System.out.println("\n\nStudent Management System");
			System.out.println("1. Add Student");
			System.out.println("2. Fetch Student Data");
			System.out.println("3. Display Topper");
			System.out.println("4. Update Student marks");
			System.out.println("5. Remove Student");
			System.out.println("6. Exit System");
			System.out.println("Enter Choice : ");
			choice = sc.nextInt();
			//injecting the objects from the Container
			Student s = context.getBean(Student.class);
			StudentService service = context.getBean(StudentService.class);
			switch(choice){
				case 1 :
					System.out.println("\nEnter Student rollNo : ");
					int r = sc.nextInt();
					s.setRollNo(r);

					sc.nextLine();

					System.out.println("Enter Student Name : ");
					String name = sc.nextLine();
					s.setName(name);
					System.out.println("Enter Student marks : ");
					int m = sc.nextInt();
					s.setMarks(m);
					service.addStudent(s);
					System.out.println("Student added...");
					break;
				case 2 :
					List<Student> students =  service.getStudents();
					System.out.println(students);
					break;
				case 3 :
					Student topper = service.getTopper();
					System.out.println(topper);
					break;
				case 4 :
					System.out.println("Enter rollno of the Student : ");
					int roll = sc.nextInt();
					System.out.println("Enter updated marks : ");
					int nmarks = sc.nextInt();
					service.updateMarks(roll, nmarks, s);
					System.out.println("Marks Updated...");
					break;
				case 5 :
					System.out.println("Enter rollno of the Student : ");
					int rol = sc.nextInt();
					service.removeStudent(rol,s);
					break;
				case 6 :
					System.out.print("Hope You Loved our System");
					for(int i = 1; i<=3; i++){
						System.out.print(".");
						Thread.sleep(1000);
					}
					System.out.println();
					break;
				default:
					System.out.println("Not a Valid Choice!! Try Again");
			}

		}while(choice != 6);

	}
}

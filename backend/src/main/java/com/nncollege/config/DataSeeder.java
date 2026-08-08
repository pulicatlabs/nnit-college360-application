package com.nncollege.config;
import com.nncollege.common.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.util.*;
@Configuration
public class DataSeeder {
 @Bean CommandLineRunner seed(DemoRecordRepository r){return args->{if(r.count()>0)return;List<DemoRecord>x=new ArrayList<>();
 x.add(DemoRecord.builder().module("COLLEGE").name("NN Engineering College").details("Autonomous B.Tech college | Chennai | 6 departments").status("ACTIVE").build());
 x.add(DemoRecord.builder().module("STUDENT").name("Rahul Kumar").details("2026CSE001 | CSE | B.Tech | Semester 2 | CGPA 8.42").status("ACTIVE").build());
 x.add(DemoRecord.builder().module("STUDENT").name("Anita Reddy").details("2026ECE002 | ECE | B.Tech | Semester 2 | CGPA 8.91").status("ACTIVE").build());
 x.add(DemoRecord.builder().module("FACULTY").name("Dr. Suresh Kumar").details("CSE | Professor | 12 years experience").status("ACTIVE").build());
 x.add(DemoRecord.builder().module("FACULTY_TASK").name("Complete DBMS Unit 4").details("Assigned to Dr. Suresh | Priority HIGH | Progress 70%").status("IN_PROGRESS").build());
 x.add(DemoRecord.builder().module("ACADEMIC").name("CSE Semester 2 Results").details("Subjects: DBMS, Java, Maths, OS | Average SGPA 8.2").status("PUBLISHED").build());
 x.add(DemoRecord.builder().module("ATTENDANCE").name("CSE-2 Attendance").details("Overall 86% | 3 students below 75%").status("ACTIVE").build());
 x.add(DemoRecord.builder().module("EXAMINATION").name("Internal Assessment - 1").details("Results published | Average 78%").status("COMPLETED").build());
 x.add(DemoRecord.builder().module("LIBRARY").name("Clean Code").details("ISBN 9780132350884 | Issued to 2026CSE001 | Due 15-Aug-2026").status("ISSUED").build());
 x.add(DemoRecord.builder().module("FEE").name("2026CSE001 Fee").details("Total ₹1,25,000 | Paid ₹1,00,000 | Due ₹25,000").status("DUE").build());
 x.add(DemoRecord.builder().module("PLACEMENT").name("Campus Drive - ABC Technologies").details("120 eligible | 72 shortlisted | 34 offers | Highest ₹18 LPA").status("ACTIVE").build());
 x.add(DemoRecord.builder().module("DOCUMENT").name("2026CSE001 Certificates").details("Aadhaar, 10th, 12th, Transfer Certificate uploaded").status("VERIFIED").build());
 x.add(DemoRecord.builder().module("PROJECT").name("Smart Campus Project").details("Guide: Dr. Suresh | Review 2 completed | Progress 65%").status("IN_PROGRESS").build());
 x.add(DemoRecord.builder().module("INTERNSHIP").name("Summer Internship").details("78 students placed in internships").status("ACTIVE").build());
 x.add(DemoRecord.builder().module("HOSTEL").name("Block A - Boys Hostel").details("240 capacity | 218 occupied | 22 available").status("ACTIVE").build());
 x.add(DemoRecord.builder().module("TRANSPORT").name("Route 7 - Chennai").details("Bus TN-09-AB-1234 | 38 students | Driver Kumar").status("ACTIVE").build());
 x.add(DemoRecord.builder().module("TIMETABLE").name("CSE Semester 2 Timetable").details("Mon-Fri | 9:00-16:30 | Labs allocated").status("PUBLISHED").build());
 x.add(DemoRecord.builder().module("NOTIFICATION").name("Semester Exam Notification").details("Exam starts 02-Sep-2026").status("PUBLISHED").build());
 x.add(DemoRecord.builder().module("PARENT").name("Parent Portal Demo").details("Attendance, marks, fees and notifications enabled").status("ACTIVE").build());
 r.saveAll(x);};}
}

CREATE DATABASE IF NOT EXISTS college360;
USE college360;

CREATE TABLE IF NOT EXISTS colleges (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 code VARCHAR(30) NOT NULL UNIQUE,
 name VARCHAR(200) NOT NULL,
 address VARCHAR(500),
 email VARCHAR(150),
 phone VARCHAR(30),
 active BOOLEAN NOT NULL DEFAULT TRUE,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS departments (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 college_id BIGINT NOT NULL,
 code VARCHAR(30) NOT NULL,
 name VARCHAR(150) NOT NULL,
 active BOOLEAN NOT NULL DEFAULT TRUE,
 CONSTRAINT fk_department_college FOREIGN KEY (college_id) REFERENCES colleges(id),
 CONSTRAINT uk_department_college_code UNIQUE (college_id, code)
);

CREATE TABLE IF NOT EXISTS students (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 admission_no VARCHAR(40) NOT NULL UNIQUE,
 college_id BIGINT NOT NULL,
 department_id BIGINT NOT NULL,
 first_name VARCHAR(80) NOT NULL,
 last_name VARCHAR(80),
 date_of_birth DATE,
 gender VARCHAR(20),
 email VARCHAR(150),
 mobile VARCHAR(20),
 blood_group VARCHAR(10),
 address VARCHAR(500),
 city VARCHAR(100),
 state VARCHAR(100),
 postal_code VARCHAR(15),
 admission_date DATE,
 academic_year VARCHAR(20),
 current_semester INT,
 status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 CONSTRAINT fk_student_college FOREIGN KEY (college_id) REFERENCES colleges(id),
 CONSTRAINT fk_student_department FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE IF NOT EXISTS faculty (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 employee_no VARCHAR(40) NOT NULL UNIQUE,
 college_id BIGINT NOT NULL,
 department_id BIGINT NOT NULL,
 first_name VARCHAR(80) NOT NULL,
 last_name VARCHAR(80),
 email VARCHAR(150),
 mobile VARCHAR(20),
 designation VARCHAR(100),
 joining_date DATE,
 status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
 CONSTRAINT fk_faculty_college FOREIGN KEY (college_id) REFERENCES colleges(id),
 CONSTRAINT fk_faculty_department FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE IF NOT EXISTS academic_years (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 college_id BIGINT NOT NULL,
 name VARCHAR(30) NOT NULL,
 start_date DATE,
 end_date DATE,
 active BOOLEAN NOT NULL DEFAULT TRUE,
 CONSTRAINT fk_academic_year_college FOREIGN KEY (college_id) REFERENCES colleges(id)
);

CREATE TABLE IF NOT EXISTS courses (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 department_id BIGINT NOT NULL,
 code VARCHAR(30) NOT NULL,
 name VARCHAR(150) NOT NULL,
 credits DECIMAL(5,2) DEFAULT 0,
 semester INT NOT NULL,
 CONSTRAINT fk_course_department FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE IF NOT EXISTS attendance (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 student_id BIGINT NOT NULL,
 course_id BIGINT NOT NULL,
 attendance_date DATE NOT NULL,
 present BOOLEAN NOT NULL,
 CONSTRAINT fk_att_student FOREIGN KEY (student_id) REFERENCES students(id),
 CONSTRAINT fk_att_course FOREIGN KEY (course_id) REFERENCES courses(id),
 CONSTRAINT uk_attendance UNIQUE(student_id, course_id, attendance_date)
);

CREATE TABLE IF NOT EXISTS examinations (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 college_id BIGINT NOT NULL,
 name VARCHAR(150) NOT NULL,
 semester INT NOT NULL,
 exam_type VARCHAR(40) NOT NULL,
 start_date DATE,
 end_date DATE,
 status VARCHAR(30) NOT NULL DEFAULT 'DRAFT',
 CONSTRAINT fk_exam_college FOREIGN KEY (college_id) REFERENCES colleges(id)
);

CREATE TABLE IF NOT EXISTS marks (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 examination_id BIGINT NOT NULL,
 student_id BIGINT NOT NULL,
 course_id BIGINT NOT NULL,
 marks DECIMAL(6,2) NOT NULL,
 max_marks DECIMAL(6,2) NOT NULL,
 grade VARCHAR(10),
 CONSTRAINT fk_mark_exam FOREIGN KEY (examination_id) REFERENCES examinations(id),
 CONSTRAINT fk_mark_student FOREIGN KEY (student_id) REFERENCES students(id),
 CONSTRAINT fk_mark_course FOREIGN KEY (course_id) REFERENCES courses(id),
 CONSTRAINT uk_mark UNIQUE(examination_id, student_id, course_id)
);

CREATE TABLE IF NOT EXISTS faculty_tasks (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 faculty_id BIGINT NOT NULL,
 title VARCHAR(250) NOT NULL,
 description TEXT,
 priority VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
 status VARCHAR(30) NOT NULL DEFAULT 'TODO',
 progress INT NOT NULL DEFAULT 0,
 due_date DATE,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 CONSTRAINT fk_task_faculty FOREIGN KEY (faculty_id) REFERENCES faculty(id)
);

CREATE TABLE IF NOT EXISTS library_books (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 isbn VARCHAR(30) UNIQUE,
 title VARCHAR(250) NOT NULL,
 author VARCHAR(200),
 publisher VARCHAR(200),
 category VARCHAR(100),
 total_copies INT NOT NULL DEFAULT 0,
 available_copies INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS library_transactions (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 book_id BIGINT NOT NULL,
 student_id BIGINT NOT NULL,
 issue_date DATE NOT NULL,
 due_date DATE NOT NULL,
 return_date DATE,
 fine DECIMAL(10,2) DEFAULT 0,
 status VARCHAR(30) NOT NULL DEFAULT 'ISSUED',
 CONSTRAINT fk_library_book FOREIGN KEY(book_id) REFERENCES library_books(id),
 CONSTRAINT fk_library_student FOREIGN KEY(student_id) REFERENCES students(id)
);

CREATE TABLE IF NOT EXISTS fee_invoices (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 student_id BIGINT NOT NULL,
 invoice_no VARCHAR(50) NOT NULL UNIQUE,
 fee_type VARCHAR(100) NOT NULL,
 amount DECIMAL(12,2) NOT NULL,
 paid_amount DECIMAL(12,2) NOT NULL DEFAULT 0,
 due_date DATE,
 status VARCHAR(30) NOT NULL DEFAULT 'DUE',
 CONSTRAINT fk_fee_student FOREIGN KEY(student_id) REFERENCES students(id)
);

CREATE TABLE IF NOT EXISTS documents (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 student_id BIGINT NOT NULL,
 document_type VARCHAR(100) NOT NULL,
 file_name VARCHAR(255) NOT NULL,
 storage_key VARCHAR(500),
 verified BOOLEAN NOT NULL DEFAULT FALSE,
 uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT fk_document_student FOREIGN KEY(student_id) REFERENCES students(id)
);

CREATE TABLE IF NOT EXISTS projects (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 student_id BIGINT NOT NULL,
 guide_faculty_id BIGINT,
 title VARCHAR(250) NOT NULL,
 description TEXT,
 progress INT NOT NULL DEFAULT 0,
 status VARCHAR(30) NOT NULL DEFAULT 'IN_PROGRESS',
 CONSTRAINT fk_project_student FOREIGN KEY(student_id) REFERENCES students(id),
 CONSTRAINT fk_project_guide FOREIGN KEY(guide_faculty_id) REFERENCES faculty(id)
);

CREATE TABLE IF NOT EXISTS internships (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 student_id BIGINT NOT NULL,
 company_name VARCHAR(200) NOT NULL,
 role VARCHAR(150),
 start_date DATE,
 end_date DATE,
 status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
 CONSTRAINT fk_internship_student FOREIGN KEY(student_id) REFERENCES students(id)
);

CREATE TABLE IF NOT EXISTS placement_drives (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 college_id BIGINT NOT NULL,
 company_name VARCHAR(200) NOT NULL,
 role VARCHAR(150),
 package_lpa DECIMAL(8,2),
 drive_date DATE,
 status VARCHAR(30) NOT NULL DEFAULT 'PLANNED',
 CONSTRAINT fk_drive_college FOREIGN KEY(college_id) REFERENCES colleges(id)
);

CREATE TABLE IF NOT EXISTS placement_applications (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 drive_id BIGINT NOT NULL,
 student_id BIGINT NOT NULL,
 status VARCHAR(40) NOT NULL DEFAULT 'APPLIED',
 offer_package_lpa DECIMAL(8,2),
 CONSTRAINT fk_application_drive FOREIGN KEY(drive_id) REFERENCES placement_drives(id),
 CONSTRAINT fk_application_student FOREIGN KEY(student_id) REFERENCES students(id),
 CONSTRAINT uk_application UNIQUE(drive_id, student_id)
);

CREATE TABLE IF NOT EXISTS hostels (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 college_id BIGINT NOT NULL,
 name VARCHAR(150) NOT NULL,
 gender VARCHAR(20),
 capacity INT NOT NULL,
 CONSTRAINT fk_hostel_college FOREIGN KEY(college_id) REFERENCES colleges(id)
);

CREATE TABLE IF NOT EXISTS hostel_allocations (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 hostel_id BIGINT NOT NULL,
 student_id BIGINT NOT NULL,
 room_no VARCHAR(30),
 from_date DATE,
 to_date DATE,
 status VARCHAR(30) DEFAULT 'ACTIVE',
 CONSTRAINT fk_allocation_hostel FOREIGN KEY(hostel_id) REFERENCES hostels(id),
 CONSTRAINT fk_allocation_student FOREIGN KEY(student_id) REFERENCES students(id)
);

CREATE TABLE IF NOT EXISTS transport_routes (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 college_id BIGINT NOT NULL,
 route_name VARCHAR(150) NOT NULL,
 vehicle_no VARCHAR(50),
 driver_name VARCHAR(150),
 CONSTRAINT fk_route_college FOREIGN KEY(college_id) REFERENCES colleges(id)
);

CREATE TABLE IF NOT EXISTS transport_allocations (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 route_id BIGINT NOT NULL,
 student_id BIGINT NOT NULL,
 pickup_point VARCHAR(200),
 status VARCHAR(30) DEFAULT 'ACTIVE',
 CONSTRAINT fk_transport_route FOREIGN KEY(route_id) REFERENCES transport_routes(id),
 CONSTRAINT fk_transport_student FOREIGN KEY(student_id) REFERENCES students(id)
);

CREATE TABLE IF NOT EXISTS timetable_entries (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 department_id BIGINT NOT NULL,
 course_id BIGINT NOT NULL,
 faculty_id BIGINT NOT NULL,
 day_of_week VARCHAR(15) NOT NULL,
 start_time TIME NOT NULL,
 end_time TIME NOT NULL,
 room_no VARCHAR(50),
 CONSTRAINT fk_tt_department FOREIGN KEY(department_id) REFERENCES departments(id),
 CONSTRAINT fk_tt_course FOREIGN KEY(course_id) REFERENCES courses(id),
 CONSTRAINT fk_tt_faculty FOREIGN KEY(faculty_id) REFERENCES faculty(id)
);

CREATE TABLE IF NOT EXISTS notifications (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 title VARCHAR(250) NOT NULL,
 message TEXT NOT NULL,
 audience VARCHAR(50) NOT NULL,
 published_at TIMESTAMP,
 status VARCHAR(30) DEFAULT 'DRAFT'
);

CREATE TABLE IF NOT EXISTS parent_accounts (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 student_id BIGINT NOT NULL UNIQUE,
 name VARCHAR(150) NOT NULL,
 mobile VARCHAR(20),
 email VARCHAR(150),
 CONSTRAINT fk_parent_student FOREIGN KEY(student_id) REFERENCES students(id)
);

package CS.UOI.diploma_thesis_application.service;

import java.util.List;
import java.util.Optional;

import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;

public interface StudentService {

	public List<Student> findAll();
	public Student findById(int theId);
	public void saveProfile(Student theStudent);
	public void deleteById(int theId);
	public Student findByUserId(int userId);
	void createProfile(Student theStudent);
	public List<Subject> getSubjectList();
	public Subject findSubjectById(int theId);
	void applyForSubject(int studentId, int subjectId);
	boolean hasApplied(int studentId, int subjectId);

	
	
	
	//public void applyToSubject(String subject, int theId);
	//public Student retrieveProfile(String theStudent);
	//public void saveProfile(Student theStudent);
	//public List<Subject> listStudentSubjects();
}

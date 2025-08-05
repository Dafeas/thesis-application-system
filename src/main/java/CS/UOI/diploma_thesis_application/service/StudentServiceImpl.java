package CS.UOI.diploma_thesis_application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CS.UOI.diploma_thesis_application.dao.ApplicationDAO;
import CS.UOI.diploma_thesis_application.dao.StudentDAO;
import CS.UOI.diploma_thesis_application.dao.SubjectDAO;
import CS.UOI.diploma_thesis_application.model.Application;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO StudentRepository;
	
	@Autowired
	private SubjectDAO subjectRepository;
	
	@Autowired ApplicationDAO applicationRepository;
	
	@Autowired
	public StudentServiceImpl(StudentDAO theStudentRepository, SubjectDAO theSubjectRepository, ApplicationDAO theApplicationRepository) {
		StudentRepository = theStudentRepository;
		subjectRepository = theSubjectRepository;
		applicationRepository = theApplicationRepository;
	}
	
	
	@Override
	@Transactional
	public List<Student> findAll() {
		return StudentRepository.findAll();
	}
	
	
	@Override
	@Transactional
	public Student findById(int theId) {
		Student result = StudentRepository.findById(theId);
				
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find student id - " + theId);
		}
	}

	@Override
	@Transactional
	public Subject findSubjectById(int theId) {
		
		Subject result = subjectRepository.findById(theId);
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find student id - " + theId);
		}
	}
	
	@Override
	@Transactional
	public void saveProfile(Student theStudent) {
		StudentRepository.saveProfile(theStudent.getId(), theStudent.getFullName(), theStudent.getYearOfStudies(), theStudent.getCurrentAverageGrade(), theStudent.getNumberOfRemainingCourses());
	}
	
	@Override
	@Transactional
	public void createProfile(Student theStudent) {
		StudentRepository.save(theStudent);
	}
	
	@Override
	@Transactional
	public void deleteById(int theId) {
		StudentRepository.deleteById(theId);
	}
	
	@Override
	@Transactional
	public Student findByUserId(int userId) {
		return StudentRepository.findByUserId(userId);
		
	}
	
	@Override
	@Transactional
	public List<Subject> getSubjectList(){
		return subjectRepository.findAll();
	}
	
	@Override
	@Transactional
	public void applyForSubject(int studentId, int subjectId) 
	{
		Application newApplication = new Application();
		newApplication.setStudent(StudentRepository.findById(studentId));
		newApplication.setSubject(subjectRepository.findById(subjectId));
		applicationRepository.save(newApplication);
	}
	
	@Override
	@Transactional
	public boolean hasApplied(int studentId,int subjectId) 
	{
		Application existingApplication = applicationRepository.findByStudentIdAndSubjectId(studentId, subjectId);
		if(existingApplication != null) {
			return true;
		}
		return false;
	}
	

	
}
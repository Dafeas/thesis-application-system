package CS.UOI.diploma_thesis_application.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CS.UOI.diploma_thesis_application.dao.ApplicationDAO;
import CS.UOI.diploma_thesis_application.dao.ProfessorDAO;
import CS.UOI.diploma_thesis_application.dao.StudentDAO;
import CS.UOI.diploma_thesis_application.dao.SubjectDAO;
import CS.UOI.diploma_thesis_application.dao.ThesisDAO;
import CS.UOI.diploma_thesis_application.model.Application;
import CS.UOI.diploma_thesis_application.model.Professor;
import CS.UOI.diploma_thesis_application.model.Student;
import CS.UOI.diploma_thesis_application.model.Subject;
import CS.UOI.diploma_thesis_application.model.Thesis;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorDAO ProfessorRepository;
	
	@Autowired
	private SubjectDAO subjectRepository;
	
	@Autowired
	private ApplicationDAO applicationRepository;
	
	@Autowired
	private StudentDAO studentRepository;
	
	@Autowired
	private ThesisDAO thesisRepository;
	
	@Autowired
	public ProfessorServiceImpl(ProfessorDAO theProfessorRepository, SubjectDAO theSubjectRepository,ApplicationDAO theApplicationRepository,StudentDAO theStudentRepository,ThesisDAO theThesisRepository) {
		ProfessorRepository = theProfessorRepository;
		subjectRepository= theSubjectRepository;
		applicationRepository = theApplicationRepository;
		studentRepository = theStudentRepository;
		thesisRepository = theThesisRepository;
		
	}
	
	
	@Override
	@Transactional
	public List<Professor> findAll() {
		return ProfessorRepository.findAll();
	}
	
	@Override
	@Transactional
	public Professor findById(int theId) {
		Professor result = ProfessorRepository.findById(theId);
				
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
	}

	@Override
	@Transactional
	public void createProfile(Professor theProfessor) {
		ProfessorRepository.save(theProfessor);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		ProfessorRepository.deleteById(theId);
	}
	
	@Override
	@Transactional
	public Professor findByUserId(int userId) 
	{
		return ProfessorRepository.findByUserId(userId);
	}
	
	@Override
	@Transactional
	public void saveProfile(Professor theProfessor) 
	{
		ProfessorRepository.saveProfile(theProfessor.getId(), theProfessor.getFullName(), theProfessor.getSpecialty());
	}

	@Override
	@Transactional
	public List<Subject> getSubjectsByProfessorId(int professorId) {
		return subjectRepository.findByProfessorId(professorId);
	}
	
	@Override
	@Transactional
	public void deleteSubject(int subjectId) {
		subjectRepository.deleteById(subjectId);
	}
	
	
	@Override
	@Transactional
	public void addSubject(Subject theSubject) {
		subjectRepository.save(theSubject);
	
	}
	
	@Override
	@Transactional
	public void createThesis(Professor professor ,Subject theSubject) {
		Thesis thesis = new Thesis();
		thesis.setProfessor(professor);
		thesis.setSubject(theSubject);
		thesisRepository.save(thesis);
	}

	@Override
	@Transactional
	public List<Student> getStudentsBySubjectId(int subjectId) {
		
		List<Integer> studentIds = applicationRepository.findStudentIdsBySubjectId(subjectId);

		return studentRepository.findStudentsByIds(studentIds);
	
	}
	
	@Override
	@Transactional
	public void assignThesis(int studentId,int professorId,int subjectId)  {
	        
	      thesisRepository.assignStudentId(professorId, subjectId, studentId);
	    
	}
}
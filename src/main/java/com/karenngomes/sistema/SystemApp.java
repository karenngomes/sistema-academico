package com.karenngomes.sistema;

import com.karenngomes.resources.CourseResources;
import com.karenngomes.resources.DepartmentResources;
import com.karenngomes.resources.EnrollmentResources;
import com.karenngomes.resources.ProfessorResources;
import com.karenngomes.resources.SecretaryResources;
import com.karenngomes.resources.StudentResources;
import com.karenngomes.resources.SubjectResources;
import com.karenngomes.resources.UniversityResources;
import com.karenngomes.sistema.db.CourseDAO;
import com.karenngomes.sistema.db.DepartmentDAO;
import com.karenngomes.sistema.db.EnrollmentDAO;
import com.karenngomes.sistema.db.ProfessorDAO;
import com.karenngomes.sistema.db.SecretaryDAO;
import com.karenngomes.sistema.db.StudentDAO;
import com.karenngomes.sistema.db.SubjectDAO;
import com.karenngomes.sistema.db.UniversityDAO;
import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Department;
import com.karenngomes.sistema.model.Enrollment;
import com.karenngomes.sistema.model.Professor;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.model.Student;
import com.karenngomes.sistema.model.Subject;
import com.karenngomes.sistema.model.University;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SystemApp extends Application<SystemConfig> {
	
	public static void main(String[] args) throws Exception {
        new SystemApp().run(args);
    }

    @Override
    public String getName() {
        return "Sistema Academico";
    }

    @Override
    public void initialize(final Bootstrap<SystemConfig> bootstrap) {
        // nothing to do yet
    	bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final SystemConfig configuration,
                    final Environment environment) {
        // TODO: implement application
    	
    	final EnrollmentDAO enrollmentDAO = new EnrollmentDAO(hibernate.getSessionFactory());
    	final UniversityDAO universityDAO = new UniversityDAO(hibernate.getSessionFactory());
    	final DepartmentDAO departmentDAO = new DepartmentDAO(hibernate.getSessionFactory());
    	final SecretaryDAO secretaryDAO = new SecretaryDAO(hibernate.getSessionFactory());
    	final ProfessorDAO professorDAO = new ProfessorDAO(hibernate.getSessionFactory());
    	final StudentDAO studentDAO = new StudentDAO(hibernate.getSessionFactory());
    	final SubjectDAO subjectDAO = new SubjectDAO(hibernate.getSessionFactory());
    	final CourseDAO courseDAO = new CourseDAO(hibernate.getSessionFactory());
    	
    	final EnrollmentResources enrollment = new EnrollmentResources(enrollmentDAO, studentDAO, subjectDAO);
    	final UniversityResources university = new UniversityResources(universityDAO);
    	final DepartmentResources department = new DepartmentResources(departmentDAO, secretaryDAO);
    	final SecretaryResources secretary = new SecretaryResources(secretaryDAO);
    	final ProfessorResources professor = new ProfessorResources(departmentDAO, professorDAO, subjectDAO);
    	final CourseResources course = new CourseResources(courseDAO, secretaryDAO);
    	final SubjectResources subject = new SubjectResources(subjectDAO, professorDAO, courseDAO);
    	final StudentResources student = new StudentResources(studentDAO, subjectDAO);
    	
    	environment.jersey().register(enrollment);
    	environment.jersey().register(university);
    	environment.jersey().register(department);
    	environment.jersey().register(secretary);
    	environment.jersey().register(professor);
    	environment.jersey().register(subject);
    	environment.jersey().register(student);
    	environment.jersey().register(course);
    }
    
    private final HibernateBundle<SystemConfig> hibernate
    = new HibernateBundle<SystemConfig>(Enrollment.class, University.class, Student.class, Professor.class, Department.class, Secretary.class, Course.class, Subject.class) {
    	 
    	@Override
         public DataSourceFactory getDataSourceFactory(SystemConfig configuration) {
             return configuration.getDatabase();
         }
     };
}

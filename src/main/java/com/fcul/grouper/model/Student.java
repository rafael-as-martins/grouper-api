package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fcul.grouper.model.types.UserType;
import com.fcul.grouper.model.types.search.ClassSearchFieldsMapper;
import com.fcul.grouper.model.types.search.StudentSearchFieldsMapper;

@Entity
@Table(name = "student")
@NamedQueries({
		@NamedQuery(name = Student.QUERY_FIND_COURSES_BY_YEAR, query = "SELECT lc.course FROM Student s JOIN s.classes c JOIN c.lectiveCourse lc WHERE lc.id.year = :year AND s.id= :id"),
		@NamedQuery(name = Student.QUERY_FIND_PROJECTS_BY_YEAR_AND_COURSE, query = "SELECT p FROM Student s JOIN s.classes c JOIN c.lectiveCourse lc JOIN lc.projects p WHERE lc.id.year = :year AND s.id= :id AND lc.id.course = :course"),
		@NamedQuery(name = Student.QUERY_FIND_CLASSES_BY_COURSE_ID, query = "SELECT c FROM Student s JOIN s.classes c WHERE c.id.course = :courseId AND s.id = :studentId"),
		@NamedQuery(name = Student.QUERY_FIND_STUDENTS_BY_COURSE_AND_YEAR_AND_CLASS, query = "SELECT "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + " FROM Student " + StudentSearchFieldsMapper.ENTITY_ALIAS
				+ " JOIN " + StudentSearchFieldsMapper.ENTITY_ALIAS + ".classes " + ClassSearchFieldsMapper.ENTITY_ALIAS
				+ " JOIN " + ClassSearchFieldsMapper.ENTITY_ALIAS
				+ ".lectiveCourse lc WHERE lc.id.year = :year AND lc.id.course = :course AND "
				+ ClassSearchFieldsMapper.ENTITY_ALIAS + ".id.name like :" + Student.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".firstName like :" + Student.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".lastName like :" + Student.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".email like :" + Student.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".number like :" + Student.PARAM_SEARCH + " GROUP BY "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".id ORDER BY :" + Student.PARAM_ORDER_BY_FIELD + " ASC"),
		@NamedQuery(name = Student.QUERY_FIND_STUDENTS_BY_COURSE_AND_YEAR_AND_CLASS_WITH_GROUP, query = "SELECT "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + " FROM Student " + StudentSearchFieldsMapper.ENTITY_ALIAS
				+ " JOIN " + StudentSearchFieldsMapper.ENTITY_ALIAS
				+ ".classes c JOIN c.lectiveCourse lc WHERE lc.id.year = :year AND lc.id.course = :course AND "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".workgroups is not empty AND "
				+ ClassSearchFieldsMapper.ENTITY_ALIAS + ".id.name like :" + Student.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".firstName like :" + Student.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".lastName like :" + Student.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".email like :" + Student.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".number like :" + Student.PARAM_SEARCH + " GROUP BY "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".id ORDER BY :" + Student.PARAM_ORDER_BY_FIELD + " ASC"),
		@NamedQuery(name = Student.QUERY_FIND_BY_SEARCH_FILTER_AND_INSTITUITION, query = "SELECT s FROM Student s JOIN s.instituition i WHERE i.id = :"
				+ Student.PARAM_INSTITUITION + " AND s.number like :" + Student.PARAM_SEARCH + " OR s.firstName like :"
				+ Student.PARAM_SEARCH + " OR s.lastName like :" + Student.PARAM_SEARCH + " OR s.email like :"
				+ Student.PARAM_SEARCH + " OR s.degree like :" + Student.PARAM_SEARCH),
		@NamedQuery(name = Student.QUERY_COUNT_BY_SEARCH_FILTER_AND_INSTITUITION, query = "SELECT count(s) FROM Student s JOIN s.instituition i WHERE i.id = :"
				+ Student.PARAM_INSTITUITION + " AND s.number like :" + Student.PARAM_SEARCH + " OR s.firstName like :"
				+ Student.PARAM_SEARCH + " OR s.lastName like :" + Student.PARAM_SEARCH + " OR s.email like :"
				+ Student.PARAM_SEARCH + " OR s.degree like :" + Student.PARAM_SEARCH)})
public class Student implements Serializable {

	private static final long serialVersionUID = 4198560003609366531L;

	public static final String QUERY_FIND_STUDENTS_BY_COURSE_AND_YEAR_AND_CLASS = "Student.findStudentsByCourseAndYearAndClass";
	public static final String QUERY_FIND_STUDENTS_BY_COURSE_AND_YEAR_AND_CLASS_WITH_GROUP = "Student.findStudentsByCourseAndYearAndClassWithGroup";
	public static final String QUERY_FIND_PROJECTS_BY_YEAR_AND_COURSE = "Student.findProjectsByYearAndIdAndCourse";
	public static final String QUERY_FIND_COURSES_BY_YEAR = "Student.findCoursesByYear";
	public static final String QUERY_FIND_CLASSES_BY_COURSE_ID = "Student.findClassesByCourseId";
	public static final String QUERY_FIND_BY_SEARCH_FILTER_AND_INSTITUITION = "Student.findBYSearchFilterAndInstituition";
	public static final String QUERY_COUNT_BY_SEARCH_FILTER_AND_INSTITUITION = "Count.countBYSearchFilterAndInstituition";

	public static final String PARAM_SEARCH = "search";
	public static final String PARAM_COURSE = "course";
	public static final String PARAM_YEAR = "year";
	public static final String PARAM_ORDER_BY_FIELD = "orderBy";
	public static final String PARAM_STUDENT_ID = "id";
	public static final String PARAM_INSTITUITION = "instituitionId";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name", nullable = false)
	@NonNull
	@Size(max = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NonNull
	@Size(max = 50)
	private String lastName;

	@Column(name = "degree", nullable = false)
	@NonNull
	@Size(max = 255)
	private String degree;

	@Column(name = "number", nullable = false, unique = true)
	@NonNull
	private String number;

	@Column(name = "email", nullable = false, unique = true)
	@NonNull
	@Size(max = 255)
	private String email;

	@Column(name = "password", nullable = false)
	@NonNull
	@Size(max = 255)
	private String password;

	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	@NonNull
	private UserType userType;

	@ManyToOne(targetEntity = Country.class)
	@JoinColumn(insertable = true, updatable = true, name = "country", referencedColumnName = "id")
	@NonNull
	private Country countryNic;

	@Column(name = "encrypted_nic", nullable = false)
	@NonNull
	@Size(max = 255)
	private String encryptedNic;

	@Column(name = "photo_path", nullable = false)
	@NonNull
	private String photoPath;

	@ManyToOne(targetEntity = Instituition.class)
	@JoinColumn(insertable = true, updatable = true, name = "instituition", referencedColumnName = "id")
	@NonNull
	private Instituition instituition;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "student"), inverseJoinColumns = {
			@JoinColumn(name = "course"), @JoinColumn(name = "name"), @JoinColumn(name = "year") })
	private Set<Class> classes;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "student_workgroup", joinColumns = @JoinColumn(name = "student"), inverseJoinColumns = @JoinColumn(name = "workgroup"))
	private Set<Workgroup> workgroups;

	@OneToMany(mappedBy = "student")
	private Set<Message> messages;

	@OneToMany(mappedBy = "student")
	private Set<Doubt> doubts;

	@OneToMany(mappedBy = "student")
	private Set<File> files;

	public void addWorkgroup(Workgroup workgroup) {
		getWorkgroups().add(workgroup);
	}

	public void addFile(File file) {
		getFiles().add(file);
		file.setStudent(this);
	}

	public void addClass(Class c) {
		getClasses().add(c);
	}

	public void addDoubt(Doubt doubt) {
		getDoubts().add(doubt);
		doubt.setStudent(this);
	}

	public void addMessage(Message message) {
		getMessages().add(message);
		message.setStudent(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Instituition getInstituition() {
		return instituition;
	}

	public void setInstituition(Instituition instituition) {
		this.instituition = instituition;
	}

	public Set<Workgroup> getWorkgroups() {

		if (workgroups == null) {
			workgroups = new HashSet<>();
		}

		return workgroups;
	}

	public void setWorkgroups(Set<Workgroup> workgroups) {
		this.workgroups = workgroups;
	}

	public String getEncryptedNic() {
		return encryptedNic;
	}

	public void setEncryptedNic(String encryptedNic) {
		this.encryptedNic = encryptedNic;
	}

	public Country getCountryNic() {
		return countryNic;
	}

	public void setCountryNic(Country countryNic) {
		this.countryNic = countryNic;
	}

	public Set<Class> getClasses() {

		if (classes == null) {
			classes = new HashSet<>();
		}

		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	public Set<Message> getMessages() {

		if (messages == null) {
			messages = new HashSet<>();
		}

		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<Doubt> getDoubts() {

		if (doubts == null) {
			doubts = new HashSet<>();
		}

		return doubts;
	}

	public void setDoubts(Set<Doubt> doubts) {
		this.doubts = doubts;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Set<File> getFiles() {

		if (files == null) {
			files = new HashSet<File>();
		}

		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

}

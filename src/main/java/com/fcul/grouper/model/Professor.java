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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fcul.grouper.model.types.UserType;

@Entity
@Table(name = "professor")
@NamedQueries({
		@NamedQuery(name = Professor.QUERY_FIND_COURSES_BY_YEAR, query = "SELECT lc.course FROM Professor p JOIN p.lectiveCourses lc WHERE lc.id.year = :"
				+ Professor.PARAM_YEAR + " AND p.id = :" + Professor.PARAM_PROFESSOR),
		@NamedQuery(name = Professor.QUERY_FIND_PROJECTS_BY_YEAR_AND_ID_AND_COURE, query = "SELECT projects FROM Professor p JOIN p.lectiveCourses lc JOIN lc.projects projects WHERE lc.id.year = :"
				+ Professor.PARAM_YEAR + " AND p.id = :" + Professor.PARAM_PROFESSOR + " AND lc.id.course = :"
				+ Professor.PARAM_COURSE),
		@NamedQuery(name = Professor.QUERY_FIND_BY_SEARCH_FILTER_AND_INSTITUIION, query = "SELECT p FROM Professor p JOIN p.instituition i WHERE i.id = :"
				+ Professor.PARAM_INSTITUITION + " AND p.number like :" + Professor.PARAM_SEARCH
				+ " OR p.firstName like :" + Professor.PARAM_SEARCH + " OR p.lastName like :" + Professor.PARAM_SEARCH
				+ " OR p.email like :" + Professor.PARAM_SEARCH),
		@NamedQuery(name = Professor.QUERY_COUNT_BY_SEARCH_FILTER_AND_INSTITUIION, query = "SELECT count(p) FROM Professor p JOIN p.instituition i WHERE i.id = :"
				+ Professor.PARAM_INSTITUITION + " AND p.number like :" + Professor.PARAM_SEARCH
				+ " OR p.firstName like :" + Professor.PARAM_SEARCH + " OR p.lastName like :" + Professor.PARAM_SEARCH
				+ " OR p.email like :" + Professor.PARAM_SEARCH)})
public class Professor implements Serializable {

	private static final long serialVersionUID = -8814313629338277471L;

	public static final String QUERY_FIND_COURSES_BY_YEAR = "Professor.findCoursesByYear";
	public static final String QUERY_FIND_PROJECTS_BY_YEAR_AND_ID_AND_COURE = "Professor.findProjectsByYearAndIdAndCourse";
	public static final String QUERY_FIND_BY_SEARCH_FILTER_AND_INSTITUIION = "Professor.findBySearchFilterAndInstituition";
	public static final String QUERY_COUNT_BY_SEARCH_FILTER_AND_INSTITUIION = "Professor.countBySearchFilterAndInstituition";

	public static final String PARAM_YEAR = "year";
	public static final String PARAM_PROFESSOR = "professorId";
	public static final String PARAM_COURSE = "courseId";
	public static final String PARAM_SEARCH = "search";
	public static final String PARAM_INSTITUITION = "instituitionId";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name")
	@NonNull
	@Size(max = 50)
	private String firstName;

	@Column(name = "last_name")
	@NonNull
	@Size(max = 50)
	private String lastName;

	@Column(name = "number", unique = true)
	@NonNull
	@Size(max = 6)
	private String number;

	@Column(name = "email", unique = true)
	@NonNull
	@Size(max = 255)
	private String email;

	@Column(name = "password")
	@NonNull
	@Size(max = 255)
	private String password;

	@ManyToOne(targetEntity = Country.class, fetch = FetchType.LAZY)
	@JoinColumn(insertable = true, updatable = true, nullable = false, name = "country", referencedColumnName = "id")
	@NonNull
	private Country countryNic;

	@Column(name = "encrypted_nic")
	@NonNull
	@Size(max = 255)
	private String encryptedNic;

	@Column(name = "photo_path")
	private String photoPath;

	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	@NonNull
	private UserType userType;

	@ManyToOne(targetEntity = Instituition.class, fetch = FetchType.LAZY)
	@JoinColumn(insertable = true, updatable = true, nullable = false, name = "instituition", referencedColumnName = "id")
	@NonNull
	private Instituition instituition;

	@OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
	private Set<LectiveCourse> lectiveCourses;

	@OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
	private Set<Feedback> feedbacks;

	public void addLectiveCourse(LectiveCourse lectiveCourse) {
		getLectiveCourses().add(lectiveCourse);
		lectiveCourse.setProfessor(this);
	}

	public void addFeedback(Feedback feedback) {
		getFeedbacks().add(feedback);
		feedback.setProfessor(this);
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

	public Country getCountryNic() {
		return countryNic;
	}

	public void setCountryNic(Country countryNic) {
		this.countryNic = countryNic;
	}

	public String getEncryptedNic() {
		return encryptedNic;
	}

	public void setEncryptedNic(String encryptedNic) {
		this.encryptedNic = encryptedNic;
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

	public Set<LectiveCourse> getLectiveCourses() {

		if (lectiveCourses == null) {
			this.lectiveCourses = new HashSet<LectiveCourse>();
		}

		return lectiveCourses;
	}

	public void setLectiveCourses(Set<LectiveCourse> lectiveCourses) {
		this.lectiveCourses = lectiveCourses;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Set<Feedback> getFeedbacks() {

		if (feedbacks == null) {
			this.feedbacks = new HashSet<Feedback>();
		}

		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

}

package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fcul.grouper.model.types.search.ClassSearchFieldsMapper;
import com.fcul.grouper.model.types.search.StudentSearchFieldsMapper;

@Entity
@Table(name = "project")
@NamedQueries({
		@NamedQuery(name = Project.QUERY_FIND_WORKGROUPS_BY_PROJECT_ID, query = "SELECT w FROM Project p JOIN p.workgroups w JOIN w.students "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + " JOIN s.classes c WHERE (p.id = :" + Project.PARAM_PROJECT_ID
				+ ") AND (" + StudentSearchFieldsMapper.ENTITY_ALIAS + ".firstName like :" + Project.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".lastName like :" + Project.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".email like :" + Project.PARAM_SEARCH + " OR "
				+ StudentSearchFieldsMapper.ENTITY_ALIAS + ".number like :" + Project.PARAM_SEARCH + " OR "
				+ ClassSearchFieldsMapper.ENTITY_ALIAS + ".id.name like :" + Project.PARAM_SEARCH
				+ ") GROUP BY w.id ORDER BY :" + Project.PARAM_ORDER_BY_FIELD + " ASC"),

})
public class Project implements Serializable {

	private static final long serialVersionUID = -4828544120369942314L;

	public static final String QUERY_FIND_WORKGROUPS_BY_PROJECT_ID = "Project.findWorkgroupsByProjectId";

	public static final String PARAM_PROJECT_ID = "projectId";
	public static final String PARAM_SEARCH = "search";
	public static final String PARAM_ORDER_BY_FIELD = "orderField";
	public static final String PARAM_ORDER = "order";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(max = 255)
	@NonNull
	private String name;

	@Column(name = "start_date", nullable = false)
	@NonNull
	private Date startDate;
	
	@Column(name = "description", nullable = true)
	@NonNull
	private String description;

	@Column(name = "end_date", nullable = false)
	@NonNull
	private Date endDate;

	@Column(name = "min_elems", nullable = false)
	@NonNull
	private Integer minElems;

	@Column(name = "max_elems", nullable = false)
	@NonNull
	private Integer maxElems;

	@Column(name = "class_restriction", nullable = false)
	@NonNull
	private Boolean classRestriction;

	@Column(name = "status", nullable = false)
	@NonNull
	private Boolean status;

	@ManyToOne(targetEntity = LectiveCourse.class)
	@NonNull
	@JoinColumns({
			@JoinColumn(updatable = true, insertable = true, name = "professor", referencedColumnName = "professor", nullable = false),
			@JoinColumn(updatable = true, insertable = true, name = "course", referencedColumnName = "course", nullable = false),
			@JoinColumn(updatable = true, insertable = true, name = "year", referencedColumnName = "year", nullable = false) })
	private LectiveCourse lectiveCourse;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinTable(name = "project_workgroup", joinColumns = @JoinColumn(name = "project"), inverseJoinColumns = @JoinColumn(name = "workgroup"))
	private Set<Workgroup> workgroups;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Step> steps;

	public Project() {
	}

	public Project(Long id, @Size(max = 255) String name, Date startDate, Date endDate, Integer minElems,
			Integer maxElems, Boolean classRestriction, Boolean status, LectiveCourse lectiveCourse) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.minElems = minElems;
		this.maxElems = maxElems;
		this.classRestriction = classRestriction;
		this.status = status;
		this.lectiveCourse = lectiveCourse;
	}
	
	public void addWorkgroup(Workgroup workgroup) {
		getWorkgroups().add(workgroup);
		workgroup.setProject(this);
	}

	public void addStep(Step step) {
		getSteps().add(step);
		step.setProject(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getMaxElems() {
		return maxElems;
	}

	public void setMaxElems(Integer maxElems) {
		this.maxElems = maxElems;
	}

	public Boolean getClassRestriction() {
		return classRestriction;
	}

	public void setClassRestriction(Boolean classRestriction) {
		this.classRestriction = classRestriction;
	}

	public Set<Workgroup> getWorkgroups() {
		
		if(workgroups == null) {
			workgroups = new HashSet<Workgroup>();
		}
		
		return workgroups;
	}

	public void setWorkgroups(Set<Workgroup> workgroups) {
		this.workgroups = workgroups;
	}

	public LectiveCourse getLectiveCourse() {
		return lectiveCourse;
	}

	public void setLectiveCourse(LectiveCourse lectiveCourse) {
		this.lectiveCourse = lectiveCourse;
	}

	public Integer getMinElems() {
		return minElems;
	}

	public void setMinElems(Integer minElems) {
		this.minElems = minElems;
	}

	public Set<Step> getSteps() {

		if (steps == null) {
			steps = new HashSet<>();
		}

		return steps;
	}

	public void setSteps(Set<Step> steps) {
		this.steps = steps;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

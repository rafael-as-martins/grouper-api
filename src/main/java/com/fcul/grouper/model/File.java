package com.fcul.grouper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "file")
@NamedQueries({
		@NamedQuery(name = File.QUERY_FIND_BY_WORKGROUP_ID, query = "SELECT f FROM File f JOIN f.workgroup w WHERE w.id = :workgroupId") })
public class File implements Serializable {

	private static final long serialVersionUID = -2705951326114176506L;

	public static final String QUERY_FIND_BY_WORKGROUP_ID = "File.findByWorkgroupId";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "url", nullable = true)
	private String url;

	@Column(name = "data", nullable = true)
	@Lob
	private byte[] data;

	@ManyToOne(targetEntity = Student.class)
	@JoinColumn(insertable = true, updatable = true, name = "student", referencedColumnName = "id")
	private Student student;

	@ManyToOne(targetEntity = Workgroup.class)
	@JoinColumn(insertable = true, updatable = true, name = "workgroup", referencedColumnName = "id")
	private Workgroup workgroup;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}

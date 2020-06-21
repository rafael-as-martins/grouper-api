package com.fcul.grouper.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "meeting")
@NamedQueries({
	@NamedQuery(name = Meeting.QUERY_FIND_BY_WORKGROUP_ID, query = "SELECT m FROM Meeting m JOIN m.workgroup w WHERE w.id = :workgroupId")
})
public class Meeting implements Serializable {

	private static final long serialVersionUID = -5627937218128386169L;

	public static final String QUERY_FIND_BY_WORKGROUP_ID = "Meeting.findByWorkgroupId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "date_time", nullable = false)
	@NonNull
	private Date dateTime;

	@Column(name = "duration", nullable = false)
	@NonNull
	private Integer duration;

	@ManyToOne(targetEntity = Workgroup.class)
	@JoinColumn(insertable = true, updatable = true, nullable = false, name = "workgroup", referencedColumnName = "id")
	@NonNull
	private Workgroup workgroup;

	public Meeting() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

}

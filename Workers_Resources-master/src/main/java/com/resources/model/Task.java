package com.resources.model;

/**
 * @author SumanD
 *
 */
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Task {

	@Id
	@GeneratedValue(generator = "task_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "task_gen", sequenceName = "task_seq", initialValue = 1, allocationSize = 1)
	private Integer taskId;
	@Column(length = 40)
	private String taskName;
	@Column(length = 40)
	private String taskOwner;
	private LocalDate taskStartDate;
	private LocalDate taskEndDate;
	@Column(length = 40)
	@Enumerated(EnumType.STRING)
	private Priority taskPriority;
	@Column(length = 40)
	private String taskDuration;
	@Column(length = 40)
	@Enumerated(EnumType.STRING)
	private Status taskStatus;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "task")
	private Set<Workers> resourcesList;
	
	@ManyToOne
	@JoinColumn(name = "maintenence_id")
	@JsonIgnore
	private Maintenence maintenence;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "trip_id")
	private Trip trip; 
	
	private String taskImg;
	
	
	
	@Override
	public String toString() {
		return "Task [taskName=" + taskName + ", taskOwner=" + taskOwner + ", taskStartDate=" + taskStartDate
				+ ", taskEndDate=" + taskEndDate + ", taskPriority=" + taskPriority + ", taskDuration=" + taskDuration
				+ ", taskStatus=" + taskStatus + "]";
	}



	public Task(String taskName, String taskOwner, LocalDate taskStartDate, LocalDate taskEndDate,
			Priority taskPriority, String taskDuration, Status taskStatus, String taskImg) {
		super();
		this.taskName = taskName;
		this.taskOwner = taskOwner;
		this.taskStartDate = taskStartDate;
		this.taskEndDate = taskEndDate;
		this.taskPriority = taskPriority;
		this.taskDuration = taskDuration;
		this.taskStatus = taskStatus;
		this.taskImg = taskImg;
	}
	
}
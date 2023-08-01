package com.mycompany.group234.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


 
import com.mycompany.group234.model.College;
import com.mycompany.group234.model.Department;
import com.mycompany.group234.model.Student;
import com.mycompany.group234.model.Course;
import com.mycompany.group234.model.Lecturer;
import com.mycompany.group234.converter.DurationConverter;
import com.mycompany.group234.converter.UUIDToByteConverter;
import com.mycompany.group234.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Lob;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "Student")
@Table(name = "\"Student\"", schema =  "\"generated_app\"")
@Data
                        
public class Student {
	public Student () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"StudentID\"", nullable = true )
  private Integer studentID;
	  
  @Column(name = "\"StudentName\"", nullable = true )
  private String studentName;
  
	  
  @Column(name = "\"StudentAddress\"", nullable = true )
  private String studentAddress;
  
	  
  @Column(name = "\"ParentNames\"", nullable = true )
  private String parentNames;
  
	  
  @Column(name = "\"PhoneNumber\"", nullable = true )
  private Long phoneNumber;
  
	  
  @Column(name = "\"State\"", nullable = true )
  private String state;
  
  
  
  
		@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"StudentEnrolledWith\"", referencedColumnName = "\"CourseID\"", insertable = false, updatable = false)
	private Course enrolledWith;
	
	@Column(name = "\"StudentEnrolledWith\"")
	private String studentEnrolledWith;
   
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "Student [" 
  + "StudentID= " + studentID  + ", " 
  + "StudentName= " + studentName  + ", " 
  + "StudentAddress= " + studentAddress  + ", " 
  + "ParentNames= " + parentNames  + ", " 
  + "PhoneNumber= " + phoneNumber  + ", " 
  + "State= " + state 
 + "]";
	}
	
}
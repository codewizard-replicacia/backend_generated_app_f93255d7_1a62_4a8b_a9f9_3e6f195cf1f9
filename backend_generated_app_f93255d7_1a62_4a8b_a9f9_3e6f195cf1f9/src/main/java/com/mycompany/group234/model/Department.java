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

@Entity(name = "Department")
@Table(name = "\"Department\"", schema =  "\"generated_app\"")
@Data
                        
public class Department {
	public Department () {   
  }
	  
  @Id
  @Column(name = "\"DepartmentID\"", nullable = true )
  private String departmentID;
	  
  @Column(name = "\"DepartmentName\"", nullable = true )
  private String departmentName;
  
	  
  @Column(name = "\"HOD\"", nullable = true )
  private String hOD;
  
	  
  @Column(name = "\"DepartmentSize\"", nullable = true )
  private Integer departmentSize;
  
  
  
  
		@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"DepartmentBranchbelongsto\"", referencedColumnName = "\"CourseID\"", insertable = false, updatable = false)
	private Course branchbelongsto;
	
	@Column(name = "\"DepartmentBranchbelongsto\"")
	private String departmentBranchbelongsto;
   
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"DepartmentHas\"",
            joinColumns = @JoinColumn( name="\"DepartmentID\""),
            inverseJoinColumns = @JoinColumn( name="\"StaffID\""), schema = "\"generated_app\"")
private List<Lecturer> has = new ArrayList<>();
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "Department [" 
  + "DepartmentID= " + departmentID  + ", " 
  + "DepartmentName= " + departmentName  + ", " 
  + "HOD= " + hOD  + ", " 
  + "DepartmentSize= " + departmentSize 
 + "]";
	}
	
}
package com.mycompany.group234.model.jointable;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import lombok.Data;
import javax.persistence.*;

import com.mycompany.group234.model.College;
import com.mycompany.group234.model.Department;
import com.mycompany.group234.model.Student;
import com.mycompany.group234.model.Course;
import com.mycompany.group234.model.Lecturer;

@Entity(name = "DepartmentHas")
@Table(schema = "\"generated_app\"", name = "\"DepartmentHas\"")
@Data
public class DepartmentHas{

 	@Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "\"DepartmentID\"")
	private String departmentID;

    
    @Column(name = "\"StaffID\"")
    private String staffID;
 
}
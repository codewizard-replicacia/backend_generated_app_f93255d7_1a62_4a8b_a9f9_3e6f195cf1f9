package com.mycompany.group234.function;

import com.mycompany.group234.model.College;
import com.mycompany.group234.model.Department;
import com.mycompany.group234.model.Student;
import com.mycompany.group234.model.Course;
import com.mycompany.group234.model.Lecturer;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataFunction;
import com.mycompany.group234.repository.DepartmentRepository;
import com.mycompany.group234.repository.CollegeRepository;
import com.mycompany.group234.repository.StudentRepository;
import com.mycompany.group234.repository.CourseRepository;
import com.mycompany.group234.repository.LecturerRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaFunctions implements ODataFunction {


    
    
}
   

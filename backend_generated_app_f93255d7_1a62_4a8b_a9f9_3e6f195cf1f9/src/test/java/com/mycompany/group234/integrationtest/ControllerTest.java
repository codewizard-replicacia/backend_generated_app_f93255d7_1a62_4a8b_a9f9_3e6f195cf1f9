package com.mycompany.group234.integrationtest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.group234.SpringApp;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
class ControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private WebApplicationContext context;
  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  
  
   private JsonNode getJSONFromFile(String filePath) throws IOException {
    try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      return jsonNode;
    }
    catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  private String getPayload(String filePath) throws IOException {
	  String jsonString = mapper.writeValueAsString( getJSONFromFile(filePath) );
	  return jsonString;
  }

  @Test
  void testRetrieveServiceDocument() {
    final String xml = given()
        .accept(ContentType.XML)
        .when()
        .get("/generated_app/")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Collection<Node> n = ((Node) ((Node) path.get("service")).get("workspace")).get("collection");
    assertNotNull(n);
    assertFalse(n.isEmpty());
  }

  @Test
  void  testRetrieveMetadataDocument() {
    final String xml = given()
        .when()
        .get("/generated_app/$metadata")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Node n = ((Node) ((Node) path.get("edmx:Ed mx")).get("DataServices")).get("Schema");
    assertNotNull(n);
    assertEquals("generated_app", n.getAttribute("Namespace"));
    assertNotNull(n.get("EntityContainer"));
  }

	

	
  @Test
  void  testCreateDepartmentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("DepartmentInstance.json"))
        .when()
        .post("/generated_app/Departments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsDepartment() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("DepartmentInstance.json"))
        .when()
        .post("/generated_app/Departments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Departments?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DepartmentID", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Departments/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateCollegeInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("CollegeInstance.json"))
        .when()
        .post("/generated_app/Colleges")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsCollege() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("CollegeInstance.json"))
        .when()
        .post("/generated_app/Colleges")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Colleges?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CollegeID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Colleges/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateStudentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("StudentInstance.json"))
        .when()
        .post("/generated_app/Students")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsStudent() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("StudentInstance.json"))
        .when()
        .post("/generated_app/Students")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Students?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).StudentID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Students/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateCourseInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("CourseInstance.json"))
        .when()
        .post("/generated_app/Courses")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsCourse() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("CourseInstance.json"))
        .when()
        .post("/generated_app/Courses")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Courses?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CourseID", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Courses/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateLecturerInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("LecturerInstance.json"))
        .when()
        .post("/generated_app/Lecturers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsLecturer() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("LecturerInstance.json"))
        .when()
        .post("/generated_app/Lecturers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Lecturers?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).StaffID", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Lecturers/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
           
       
  
  
  
  
 
  @AfterEach
  void  teardown() {
    jdbcTemplate.execute("DELETE FROM generated_app.Department");
    jdbcTemplate.execute("DELETE FROM generated_app.College");
    jdbcTemplate.execute("DELETE FROM generated_app.Student");
    jdbcTemplate.execute("DELETE FROM generated_app.Course");
    jdbcTemplate.execute("DELETE FROM generated_app.Lecturer");
     jdbcTemplate.execute("DELETE FROM generated_app.DepartmentHas");
     jdbcTemplate.execute("DELETE FROM generated_app.CollegeAdmissioned");
     jdbcTemplate.execute("DELETE FROM generated_app.CollegeCollegeStaff");
     jdbcTemplate.execute("DELETE FROM generated_app.CollegeCoursesOffered");

    RestAssuredMockMvc.reset();
  }
}

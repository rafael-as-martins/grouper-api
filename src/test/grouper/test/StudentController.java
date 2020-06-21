package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fcul.grouper.config.TestEnvironment;
import com.fcul.grouper.model.Country;
import com.fcul.grouper.model.Instituition;
import com.fcul.grouper.model.Student;
import com.fcul.grouper.model.types.UserType;

public class StudentController extends TestEnvironment {

	public static final String STUDENT_CONTROLLER_CONTEXT = "/api/student";

	public static final String INSTITUITION_CONTROLLER_CONTEXT = "/api/instituition";

	public static final String COUNTRY_CONTROLLER_CONTEXT = "/api/country";

	@Test
	public void insertCountry() throws Exception {

		String studentUri = new StringBuilder().append(STUDENT_CONTROLLER_CONTEXT).toString();

		Country country = getCountry();
		Instituition instituition = getInstituition();

		Student student = createStudent(country, instituition);

		String inputJson = super.mapToJson(student);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(studentUri).accept(MediaType.ALL_VALUE)
				.contentType("application/json").content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);

	}

	private Student createStudent(final Country country, final Instituition instituition) {

		Student student = new Student();

		student.setFirstName("Student 1");
		student.setLastName("Last Name");
		student.setDegree("Degree");
		student.setNumber("FC----");
		student.setEmail("email@example.com");
		student.setPassword("password");
		student.setUserType(UserType.STUDENT);
		student.setCountryNic(country);
		student.setEncryptedNic("encrypted");
		student.setPhotoPath("photo");
		student.setInstituition(instituition);

		return student;
	}

	private Country getCountry() throws Exception {
		String countryUri = new StringBuilder().append(COUNTRY_CONTROLLER_CONTEXT).append("/1").toString();
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(countryUri).accept("application/hal+json"))
				.andReturn();

		return super.mapFromJson(mvcResult.getResponse().getContentAsString(), Country.class);

	}

	private Instituition getInstituition() throws Exception {
		String instituitionUri = new StringBuilder().append(INSTITUITION_CONTROLLER_CONTEXT).append("/1").toString();

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(instituitionUri).accept("application/hal+json"))
				.andReturn();

		String jsonObject = mvcResult.getResponse().getContentAsString();
		jsonObject = new StringBuilder().append(jsonObject.substring(0, jsonObject.indexOf(",\"_links"))).append("}")
				.toString();

		return super.mapFromJson(jsonObject, Instituition.class);

	}

	@Test
	public void getStudentById() throws Exception {

		String uri = new StringBuilder().append(STUDENT_CONTROLLER_CONTEXT).append("/").append("1").toString();

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept("application/hal+json")).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

}

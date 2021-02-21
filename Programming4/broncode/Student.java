import java.util.HashMap;

public class Student{
	
	private final String studentNumber;
	private String firstName = "unknown";
	private String middelName = "unknown";
	private String lastName = "unknown";
	private char gender = 'U';
	private String fieldOfStudy = "unkown";
	private String house = "unknown";
	private HashMap<String, Course> tmap =  new HashMap<String, Course >();
	
	/**
	 * @param studentNumber {@code String}
	 * @param firstName {@code String}
	 * @param lastName {@code String}
	 * @param middelName {@code String}
	 * @param gender {@code char}	
	 * @param fieldOfStudy {@code String}
	 */
	public Student(String studentNumber, String firstName, String lastName, String middelName, char gender, String fieldOfStudy) {
		this.studentNumber = studentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middelName = middelName;
		this.gender = gender;
		this.fieldOfStudy = fieldOfStudy;
	}
	
	/**
     *
	 * @param studentNumber {@code String}
	 */
	public Student(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public HashMap<String, Course> getTmap() {
		return tmap;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddelName() {
		return middelName;
	}

	public void setMiddelName(String middelName) {
		this.middelName = middelName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}	
	
	
}

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.DoubleStream;

public class Adminstration {
	
	private HashMap<String, Student> students = new HashMap<String, Student>();
	private HashMap<String, Course> courses =  new HashMap<String, Course >();
	
	/**
	 * Creates a new course
	 * @param name {@code String}
	 * @param totalTest {@code int}
	 * @param year {@code int}
	 */
	public void createNewCourse(String name, int totalTest, int year ) {
		courses.put(name, new Course(name, totalTest, year));
	}
	
	/**
	 * Adds a student to the administration
	 * @param students {@code Student...}
	 */
	public void addStudentsToAdministration(Student... students) {
		for(var s : students) {
			this.students.put(s.getStudentNumber(), s);
		}
	}
	
	/**
	 * Adds multiple marks to a specific student
	 * @param course {@code String}
	 * @param student {@code String}
	 * @param numbers {@code double...}
	 */
	public void addMarkToCourseOfStudent(String course, String student, double...numbers) {
		try {
			
			students.get(student).getTmap().get(course).addMarkToGrade(numbers);
			
		} catch (NullPointerException e) {
			System.out.println("Student " + student + " does not have course " + course + "." );
		}
		
	}
	
	/**
	 * Add or changes a mark on a specif place
	 * @param course {@code String}
	 * @param student {@code String}
	 * @param numbers {@code numbers}
	 * @param testNumber {@code int}
	 */
	public void addMarkToCourseOfStudent(String course, String student, double numbers, int markNumber) {
		try {
		
			students.get(student).getTmap().get(course).reExamination(markNumber, numbers);
				
		} catch (NullPointerException e) {
			System.out.println("Student " + student + " does not have course " + course + "." );
		}	
	}

	/**
	 * Adds a course to a specific house
	 * @param course {@code String}
	 * @param house {@code String}
	 */
	public void addCourseToHouse(String course, String house ) {
		var a = courses.get(course);
		students.values().stream().parallel().filter(s -> s.getHouse() == house)
		.forEach(S -> S.getTmap().put(course, a.copy()));
	}
	
	/**
	 * Adds a course to a specific student
	 * @param course {@code String}
	 * @param student {@code String}
	 */
	public void addCourseToStudent(String course, String student) {
		var a = courses.get(course);
		students.get(student).getTmap().put(course, a.copy());
	}
	
	/**
	 * Prints out the specifc data of a student
	 * @param student {@code String}
	 */
	public void statisticsOnStudent(String student) {
		var list = students.get(student).getTmap().values().stream()
		.parallel().map(Course::getGradeList)
		.flatMapToDouble(DoubleStream::of).filter(a -> a != 0)
		.toArray();
		
		var stdev = stdDev(list.length, list);
		System.out.println("\nStudent: "+ student
							+ "\n  variance: " + stdev.get(0)
							+ "\n  stDev:    " + stdev.get(1)
							+ "\n  mean   :  " + stdev.get(2)
							+ "\n  sum:      " + stdev.get(3));
	}
	
	/**
	 * Prints out the statistics of a specific course 
	 * @param Course {@code String}
	 */
	public void statisticsOnCourse(String Course) {
		
		var courseData = students.values().parallelStream()
		.filter(e -> e.getTmap().containsKey(Course)).map(e -> e.getTmap().get(Course)).map(e -> e.getGradeList())
		.flatMapToDouble(DoubleStream::of)
		.filter(e -> e != 0)
		.toArray();
		
		var stdev = stdDev(courseData.length, courseData);
		
		
		System.out.println("\nCourse: "+ Course
							+ "\n  variance: " + stdev.get(0)
							+ "\n  stDev:    " + stdev.get(1)
							+ "\n  mean   :  " + stdev.get(2));
	}
	
	/**
	 * Prints out a list of who passed the course
	 * @param course {@code String}
	 */
	public void dataOnWhoPassed(String course) {
		System.out.println("\nThose who passed Course: " + course);
		
		students.values().parallelStream()
				.filter(e -> e.getTmap().containsKey(course))
				.filter(e -> e.getTmap().get(course).getAvarage() >= 5.5)
				.map(Student::getStudentNumber)
				.forEach(e -> System.out.println("  Student: " + e));
			
	}
	
	/**
	 * Prints the data of a course from a specific student
	 * @param student {@code String}
	 * @param course {@code String}
	 */
	public void courseDataOfStudent(String student, String course) {
		var thisCourse = students.get(student).getTmap().get(course);
		
		if(thisCourse == null) {
			System.out.println("\nStudent " + student + " does not have the course " + course + "." );
			return;
		}
		
		var list = Arrays.stream(thisCourse.getGradeList())
				.filter(e -> e != 0)
				.toArray();
		
		var stdev = stdDev(list.length, list);
		
		System.out.println("\nStatistics of Course: "+ course
				+ " from student: " + student
				+ "\n  variance:  " + stdev.get(0)
				+ "\n  stDev:     " + stdev.get(1)
				+ "\n  Mean:      " + stdev.get(2)
				+ "\n  Grade:     " + thisCourse.getAvarage());
	}
	
	/**
	 * Prints out a list of which courses the student did not passed
	 * @param student {@code String}
	 */
	public void listOfCoursesNotPassed(String student) {
		System.out.println("\nCourses not passed of Student: " + student);
		students.get(student).getTmap().values().stream()
				.parallel().filter(x -> x.getAvarage() <= 5.5)
				.map(Course::getModuleCode)
				.forEach(e -> System.out.println("  Course" + e));

	}
	
	/**
	 * Prints out a list of which courses the student passed
	 * @param student {@code String}
	 */
	public void listOfCoursesPassed(String student) {
		System.out.println("\nCourses passed of Student: " + student);
		students.get(student).getTmap().values().stream()
				.parallel().filter(x -> x.getAvarage() >= 5.5)
				.map(Course::getModuleCode)
				.forEach(e -> System.out.println("  Course" + e));
	
	}
	
	/**
	 * Prints the data of all genders
	 * @param course {@code String}
	 */
	public void whichGenderDidItBetter(String course) {
		var maleList = dataPerGender(course, 'M');
		var femaleList = dataPerGender(course, 'F');
		var unknowGender = dataPerGender(course, 'U');
		
		var maleList2 = stdDev(maleList.length, maleList);
		var femaleList2 = stdDev(femaleList.length, femaleList);
		var unkonwGender2 = stdDev(unknowGender.length, unknowGender);
		
		System.out.println("\nGender statstics of course: " + course);
		System.out.println("  Male: " 
				+ "\n    variance: " + maleList2.get(0)
				+ "\n    stDev:    " + maleList2.get(1)
				+ "\n    mean:     " + maleList2.get(2));
		
		System.out.println("  Female: "
				+ "\n    variance: " + femaleList2.get(0)
				+ "\n    stDev:    " + femaleList2.get(1)
				+ "\n    mean:     " + femaleList2.get(2));
		
		System.out.println("  Unknow: "
				+ "\n    variance: " + unkonwGender2.get(0)
				+ "\n    stDev:    " + unkonwGender2.get(1)
				+ "\n    mean:     " + unkonwGender2.get(2));
	}

	/**
	 * Gets a array of numbers per gender
	 * @param course {@code String}
	 * @param gender {@code char}
	 * @return double[] of numbers
	 */
	private double[] dataPerGender(String course, char gender) {
		return students.values().parallelStream()
				.filter(e -> e.getGender() == gender)
				.map(s -> s.getTmap().get(course))
				.map(Course::getGradeList)
				.flatMapToDouble(DoubleStream::of)
				.filter(e -> e != 0)
				.toArray();
	}
	
	/**
	 * Calculates the stDev in one pass
	 * {@link https://www.strchr.com/standard_deviation_in_one_pass} 
	 * @param number {@code int}
	 * @param array  {@code double...}
	 * @return a double array with the variance, stdev, mean and sum
	 */
	private static List<Double> stdDev(int number, double... array) {
	    double sum = 0;
	    double sq_sum = 0;
	    for (int i = 0; i < number; ++i) {
	        sum += array[i];
	        sq_sum += array[i] * array[i];
	    }
	    double mean = sum / number;
	    double variance = sq_sum / (number) - mean * mean;
	    return Arrays.asList(variance, Math.sqrt(variance), mean, sum);
	}

	public HashMap<String, Student> getStudents() {
		return students;
	}

	public void setStudents(HashMap<String, Student> students) {
		this.students = students;
	}

	public HashMap<String, Course> getCourses() {
		return courses;
	}

	public void setCourses(HashMap<String, Course> courses) {
		this.courses = courses;
	}

	
} 
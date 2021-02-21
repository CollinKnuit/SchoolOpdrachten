public class App {

	public static void main(String[] args) {
		
		// creates a adminstration
		var admin = new Adminstration(); 
		
		// creates new courses
		admin.createNewCourse("TINWIS01", 1, 1);
		admin.createNewCourse("TINWIS04", 3, 1);
		admin.createNewCourse("TINWIS02", 4, 1);
		admin.createNewCourse("TINWIS03-A", 1, 1);
		admin.createNewCourse("TINWIS03-B", 1, 1);
		
		// create 6 students
		var student1 = new Student("0989322", "Collin", "Knuit", "", 'M', "TI");
		var student2 = new Student("0989323", "Frank", "Feiter", "", 'M', "TI");
		var student3 = new Student("0989324", "Kees", "Pizza", "", 'F', "TI");
		var student4 = new Student("0989325", "Klaas", "Foo", "", 'F', "TI");
		var student5 = new Student("0989326", "Ron", "Bar", "", 'U', "TI");
		var student6 = new Student("0989327", "Mate", "Fizz", "", 'U', "TI");
		
		// sets the house of the student
		student1.setHouse("1B");
		student2.setHouse("1B");
		student3.setHouse("1B");
		student4.setHouse("2B");
		student5.setHouse("2B");
		student6.setHouse("2B");
		
		// add the student to the admistration
		admin.addStudentsToAdministration(student1, student2, student3, student4, student5, student6);
		
		// makes a iterator of the values student hashmap
		var iterator = admin.getStudents().values().iterator();
		
		// adds courses to the houses
		admin.addCourseToHouse("TINWIS01", "1B");
		admin.addCourseToHouse("TINWIS04", "2B");
		
		// print out the courses
		while (iterator.hasNext())  System.out.print(iterator.next().getTmap().keySet()); 
		System.out.println(); // sysout for spacing
		
		// adds course to second house
		admin.addCourseToHouse("TINWIS01", "2B");
		
		// adds marks 
		admin.addMarkToCourseOfStudent("TINWIS01", "0989322", 6);
		admin.addMarkToCourseOfStudent("TINWIS01", "0989323", 6.5);
		admin.addMarkToCourseOfStudent("TINWIS01", "0989324", 4.5);
		admin.addMarkToCourseOfStudent("TINWIS01", "0989325", 4.5);
		admin.addMarkToCourseOfStudent("TINWIS01", "0989326", 5.4);
		admin.addMarkToCourseOfStudent("TINWIS01", "0989327", 5.5);
		
		// get data on who passed
		admin.dataOnWhoPassed("TINWIS01");
		
		// changes mark of student
		admin.addMarkToCourseOfStudent("TINWIS01", "0989324", 5.6, 0);
		
		// adds new course to a student
		admin.addCourseToStudent("TINWIS02",  "0989324");
		
		// adds multiple marks
		admin.addMarkToCourseOfStudent("TINWIS02", "0989324", 5.5,6.3);
		
		// get statistics of the student
		admin.statisticsOnStudent("0989324");
		
		// get statistics on the course
		admin.statisticsOnCourse("TINWIS01");
	
		// gets info about which gender does better
		admin.whichGenderDidItBetter("TINWIS01");
		
		// gets the course data of a student
		admin.courseDataOfStudent("0989324", "TINWIS02");
		
		// list of courses not passed
		admin.listOfCoursesNotPassed("0989324");
		
		// list of courses passed
		admin.listOfCoursesPassed("0989324");
		
	}
	

}

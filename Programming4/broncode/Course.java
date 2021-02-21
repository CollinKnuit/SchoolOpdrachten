public class Course {
	
	private final String moduleCode;
	private double[] gradeList;
	private final int totalTests;
	private final int year;
	private byte counter = 0; 
	private double avarage = 0; 
	
	/**
	 * 
	 * @param moduleCode {@code String}
	 * @param totalTests {@code int}
	 * @param year {@code int}
	 */
	public Course(String moduleCode, int totalTests, int year) {
		this.moduleCode = moduleCode;
		this.gradeList = new double[totalTests];
		this.totalTests =  totalTests;
		this.year = year;
	}
	
	/**
	 * 
	 * @return a new Course with the same values
	 */
	public Course copy() {
		return new Course(new String(moduleCode), Integer.valueOf(totalTests) , Integer.valueOf(year));
	}
	
	/**
	 * Adds multiple marks to the course
	 * @param doubles {@code double...}
	 */
	public void addMarkToGrade(double... doubles) {
		if(counter >= totalTests) return;
		
		for(double d: doubles) {
			if(counter >= totalTests) break;
			gradeList[counter] = d;
			++counter;
		}
		
		calculateMean();
	}
	
	/**
	 * Changes or adds a mark on a specific placement
	 * @param id {@code int}
	 * @param mark {@code double}
	 */
	public void reExamination(int id, double mark){
	 
		if(gradeList[id] < mark) gradeList[id] = mark;
		
		calculateMean();
	}

	/**
	 * Calculates the mean to check if he passed the course yet
	 */
	private void calculateMean() {
		double sum = 0;
		for(int i = 0; i< counter; ++i) {
			sum += gradeList[i];
		}
		avarage = sum/totalTests;
	}
	

	public double[] getGradeList() {
		return gradeList;
	}
	
	public String getModuleCode() {
		return moduleCode;
	}


	public int getTotalTests() {
		return totalTests;
	}


	public int getYear() {
		return year;
	}

	public double getAvarage() {
		return avarage;
	}
	
}

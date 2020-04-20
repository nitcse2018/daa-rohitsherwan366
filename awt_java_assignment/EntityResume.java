
import java.io.Serializable;

public static class EntityResume implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String Name;
	
	public int Age;
	public String Qualifications;
	Public Stirng Projects;
	Public Stirng work_experience;
	Public Stirng achievements;


	public String Hobbies;

	public static  EntityResume() {

	}

	public static String toString() {

		return " Name " + name + " Age " + age + " Qualification " + qualifications + "Projects"+projects+"work_experience"+work_experience+"achivements"+achievements+" hobbies " + hobbies ;
	}
}

package il.ac.tau.cs.sw1.ex9.starfleet;

public class myDetailsOfCrewMember implements CrewMember {
	private String name;
	private int age;
	private int yearsInService;
	
	public  myDetailsOfCrewMember(int age, int yearsInService, String name) {
		this.name = name;
		this.age = age;
		this.yearsInService = yearsInService;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getAge() {
		return age;
	}
	@Override
	public int getYearsInService() {
		return yearsInService;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		myDetailsOfCrewMember other = (myDetailsOfCrewMember) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

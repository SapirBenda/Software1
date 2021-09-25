package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.*;


public class StealthCruiser extends Fighter {
		
	public static int numberOfStealthCruiser = 0;
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		numberOfStealthCruiser ++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		this(name, commissionYear, maximalSpeed, crewMembers, Arrays.asList(new Weapon("Laser Cannons",10,100)));
	}

	@Override
	public int getAnnualMaintenanceCost(){
		return 2500 +(int)(1000*this.getMaximalSpeed()) +numberOfStealthCruiser*50 + this.getsumOfAnnualMaintenanceCost();
	}
}

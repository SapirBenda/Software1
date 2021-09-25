package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.*;

public abstract class myDetalisOfSpceShip implements Spaceship {
	private String name;
	private int commissionYear;
	private float maximalSpeed;
	private int firePower;
	private Set< ? extends CrewMember> crewMembers;
	private int annualMaintenanceCost;
	
	public myDetalisOfSpceShip(String name, int commissionYear, float maximalSpeed, int firePower, Set< ? extends CrewMember> crewMembers, int annualMaintenanceCost) {
		this.name = name;
		this.commissionYear = commissionYear;
		this.maximalSpeed = maximalSpeed;
		this.firePower = firePower;
		this.crewMembers = crewMembers;
		this.annualMaintenanceCost = annualMaintenanceCost;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getCommissionYear() {
		return commissionYear;
	}
	@Override
	public float getMaximalSpeed() {
		return maximalSpeed;
	}
	@Override
	public int getFirePower() {
		return firePower;
	}
	@Override
	public Set< ? extends CrewMember> getCrewMembers() {
		return crewMembers;
	}
	@Override
	public int getAnnualMaintenanceCost() {
		return annualMaintenanceCost;
	}
	public void setAnnualMaintenanceCost(int annualMaintenanceCost) {
		this.annualMaintenanceCost = annualMaintenanceCost;
	}
	public void setFirePower(int firePower) {
		this.firePower = firePower;
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
		myDetalisOfSpceShip other = (myDetalisOfSpceShip) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}


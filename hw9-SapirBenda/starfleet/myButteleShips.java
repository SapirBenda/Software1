package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.*;

public abstract class myButteleShips extends myDetalisOfSpceShip {

	private  List <Weapon> weapons;
	private int sumOfAnnualMaintenanceCost;
	private int sumOfFirePower;
	
	public myButteleShips(String name, int commissionYear, float maximalSpeed, int firePower,
			Set<? extends CrewMember> crewMembers, int annualMaintenanceCost,List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, firePower, crewMembers, annualMaintenanceCost);
		this.weapons = weapons;
		int sumOfAMC =0, sumOfFirePower = 10;
		for(Weapon weapon: weapons) {
			sumOfAMC += weapon.getAnnualMaintenanceCost();
			sumOfFirePower += weapon.getFirePower();
		}
		this.sumOfAnnualMaintenanceCost = sumOfAMC;
		this.sumOfFirePower = sumOfFirePower;
	}
	
	public List<Weapon> getWeapon() {
		return this.weapons;
	}
	
	@Override
	public void setAnnualMaintenanceCost(int annualMaintenanceCost) {
		super.setAnnualMaintenanceCost(annualMaintenanceCost);
	}
	@Override
	public void setFirePower(int firePower) {
		super.setFirePower(firePower);
	}
	public int getsumOfAnnualMaintenanceCost() {
		return this.sumOfAnnualMaintenanceCost;
	}
	public int getsumOfFirePower() {
		return this.sumOfFirePower;
	}
}

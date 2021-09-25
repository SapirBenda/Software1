package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends myButteleShips{
	
	private int numberOfnumberOfTechnicians;
	
	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers,	List<Weapon> weapons, int numberOfTechnicians){
		super(name, commissionYear, maximalSpeed, 0,crewMembers,  0, weapons);
		this.numberOfnumberOfTechnicians =numberOfTechnicians;
		this.setAnnualMaintenanceCost((int) (this.getsumOfAnnualMaintenanceCost()*(1-numberOfTechnicians*0.1)) + 5000);
		this.setFirePower(this.getsumOfFirePower());
	}
	
	public int getNumberOfTechnicians() {
		return this.numberOfnumberOfTechnicians;
	}
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		String tab = "	";
		String together = newLine+tab;
		String output = this.getClass().getSimpleName() + together +"Name="+ this.getName() +
				together+ "CommissionYear="+ this.getCommissionYear() + together +"MaximalSpeed="+ this.getMaximalSpeed()+ 
				together +"FirePower="+ this.getFirePower() + together+"CrewMembers=" + this.getCrewMembers().size()+
				together +"AnnualMaintenanceCost=" +this.getAnnualMaintenanceCost()+ together + "WeaponArray=" + this.getWeapon() + together + "NumberOfTechnicians=" + this.getNumberOfTechnicians();
		return output;
	}


}

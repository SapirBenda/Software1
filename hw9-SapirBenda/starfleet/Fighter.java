package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends myButteleShips {
	
	
	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear, maximalSpeed, 0,crewMembers,  0, weapons );
		this.setAnnualMaintenanceCost(this.getsumOfAnnualMaintenanceCost() + 2500 + (int) Math.round(1000*maximalSpeed));
		this.setFirePower(this.getsumOfFirePower());	
	}
	
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		String tab = "\t";
		String together = newLine+tab;
		String output = this.getClass().getSimpleName() + together +"Name="+ this.getName() +
				together+ "CommissionYear="+ this.getCommissionYear() + together +"MaximalSpeed="+ this.getMaximalSpeed()+ 
				together +"FirePower="+ this.getFirePower() + together+"CrewMembers=" + this.getCrewMembers().size()+
				together +"AnnualMaintenanceCost=" +this.getAnnualMaintenanceCost() + together + "WeaponArray="+ this.getWeapon();
		return output;
	}


}

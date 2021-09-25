package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends Fighter {

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.setAnnualMaintenanceCost(3500 + this.getsumOfAnnualMaintenanceCost() + 500*crewMembers.size() + (int)(1200*maximalSpeed));
	}

	

}

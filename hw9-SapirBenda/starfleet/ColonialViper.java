package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends Fighter {

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name,	commissionYear,	maximalSpeed, crewMembers, weapons);
		this.setAnnualMaintenanceCost(4000 + this.getsumOfAnnualMaintenanceCost() + 500*crewMembers.size() +(int)(500*maximalSpeed));
		
	}


}

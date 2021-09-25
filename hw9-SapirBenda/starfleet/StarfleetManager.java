package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.*;
import java.util.Map.Entry;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		List<String> sortedCollection = new ArrayList<>();
		List <Spaceship> fleetlst = (List<Spaceship>) fleet;
		fleetlst.sort(((ship1, ship2) -> {
			int comp = Integer.compare(ship2.getFirePower(), ship1.getFirePower());
			if (comp == 0) {
				comp = Integer.compare(ship2.getCommissionYear(), ship1.getCommissionYear());
				if (comp == 0)
					comp = ship1.getName().compareTo(ship2.getName());
			}
			return comp;}));
		
		for(Spaceship ship: fleetlst) {
			sortedCollection.add(ship.toString());
		}
		return sortedCollection;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		 Map<String,Integer> map = new HashMap<>();
		
		for(Spaceship ship: fleet) {
			String name = ship.getClass().getSimpleName();
			if(map.containsKey(name)) {
				map.replace(name, map.get(name) +1);
			}
			else
				map.put(name, 1);
		}
		return map;
	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		int AMC =0;
		for(Spaceship ship:fleet) {
			AMC +=ship.getAnnualMaintenanceCost();
		}
		return AMC;

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> allWeapons = new HashSet<String>();
		for(Spaceship ship:fleet) {
			if (!ship.getClass().getSimpleName().equals("TransportShip")) {
				List<Weapon> weaponsforShip = ((myButteleShips) ship).getWeapon();
				for(Weapon weapon: weaponsforShip) {
					allWeapons.add(weapon.getName());
				}
			}
		}
		return allWeapons;

	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int numOfAllCrews=0;
		for(Spaceship ship: fleet) {
			numOfAllCrews += ship.getCrewMembers().size();
		}
		return numOfAllCrews;

	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		float NumberOfOfficer =0;
		float SumOfOfficerAge =0;
		for (Spaceship ship : fleet) {
			for(CrewMember member : ship.getCrewMembers()) {
				if (member.getClass().getSimpleName().equals("Officer")) {
					NumberOfOfficer++;
					SumOfOfficerAge += member.getAge();
				}
			}
		}
		return SumOfOfficerAge/NumberOfOfficer;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
	
		Map<Officer,Spaceship> map = new HashMap<>();
		for(Spaceship ship: fleet) {
			List<Officer> officers = new ArrayList<>();
			for(CrewMember member : ship.getCrewMembers()) {
				if (member.getClass().getSimpleName().equals("Officer")) {
					officers.add((Officer) member);
				}
			}
			officers.sort((officer1,officer2) -> officer2.getRank().compareTo(officer1.getRank()));
			if (officers.size()!=0)
				map.put(officers.get(0), ship);
		}
		return map;
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		Map<OfficerRank,Integer> mapOfRanks = new HashMap<>();
		Officer memberAsOfficer;
		OfficerRank officerRank;
		for(Spaceship ship: fleet) {
			for(CrewMember member : ship.getCrewMembers()) {	
				if (member.getClass().getSimpleName().equals("Officer")) {
					memberAsOfficer = (Officer) member;
					officerRank = memberAsOfficer.getRank();
					if (mapOfRanks.containsKey(officerRank)){
						mapOfRanks.replace(officerRank, mapOfRanks.get(officerRank)+1);
					}
					else
						mapOfRanks.put(officerRank, 1);
					
				}
			}
		}
		Set<Entry<OfficerRank, Integer>> mapOfRanksAsEntriys = mapOfRanks.entrySet();
		List<Map.Entry<OfficerRank, Integer>> sortedSet = new ArrayList<>(mapOfRanksAsEntriys);
		sortedSet.sort((element1,element2) -> { 
			Integer comp = Integer.compare(element1.getValue(), element2.getValue());
			if (comp == 0)
				comp = element1.getKey().compareTo(element2.getKey());
			return comp;
		});
		return sortedSet;
	}

}

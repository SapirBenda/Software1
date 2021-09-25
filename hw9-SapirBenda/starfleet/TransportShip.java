package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends myDetalisOfSpceShip{
	
	private int cargoCapacity;
	private int passengerCapacity;
	
	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name,commissionYear,maximalSpeed,10, crewMembers, (3000 + 5*cargoCapacity + 3*passengerCapacity));
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}
	public int getCargoCapacity() {
		return cargoCapacity;
	}
	public int getPassengerCapacity() {
		return this.passengerCapacity;
	}
	
	
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		String tab = "\t";
		String together = newLine+tab;
		String output = this.getClass().getSimpleName() + together +"Name="+ this.getName() +
				together+ "CommissionYear="+ this.getCommissionYear() + together +"MaximalSpeed="+ this.getMaximalSpeed()+ 
				together +"FirePower="+ this.getFirePower() + together+"CrewMembers=" + this.getCrewMembers().size()+
				together +"AnnualMaintenanceCost=" +this.getAnnualMaintenanceCost() + together + "CargoCapacity=" + this.getCargoCapacity()+
				together + "PassengerCapacity=" + this.getPassengerCapacity();
		return output;
	}

}


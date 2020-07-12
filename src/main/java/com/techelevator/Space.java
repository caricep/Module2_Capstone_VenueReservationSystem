package com.techelevator;



public class Space extends Venue {

	private int spaceId;

	private String spaceName;

	private String openFrom;

	private String openTo;
	


	private boolean isAccessible;

	private double dailyRate;

	private int maxOccupancy;
	
	private double totalCost;
	
	
	

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public int getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getOpenFrom() {
		return openFrom;
	}

	public void setOpenFrom(String openFrom) {
		this.openFrom = openFrom;
	}

	public String getOpenTo() {
		return openTo;
	}

	public void setOpenTo(String openTo) {
		this.openTo = openTo;
	}

	public boolean isAccessible() {
		return isAccessible;
	}

	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public int getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	@Override
	public String toString() {
		return "Space [spaceId=" + spaceId + ", spaceName=" + spaceName + ", openFrom=" + openFrom + ", openTo="
				+ openTo + ", isAccessible=" + isAccessible + ", dailyRate=" + dailyRate + ", maxOccupancy="
				+ maxOccupancy + "]";
	}

	
}

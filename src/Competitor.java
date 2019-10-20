
public class Competitor {
	private String competitorName, country;
	private double skill;
	private int numberOfGoldMedals;

	public Competitor(String name, String country, double Skill) {
		this.competitorName = name;
		this.country = country;
		this.skill = Skill;
		this.numberOfGoldMedals = 0;
	}

	String getCompetitorName() {
		return this.competitorName;
	}

	String getCountry() {
		return this.country;
	}

	double getSkill() {
		return this.skill;
	}

	int getNumberOfGoldMedals() {
		return this.numberOfGoldMedals;
	}

	void incrementNumberOfGoldMedals() {
		this.numberOfGoldMedals++;
	}

	public String toString() {
		return "Competitor{competitorName=" + this.competitorName + ", country=" + this.country + ", skill="
				+ this.skill + ", numberOfGoldMedals=" + this.numberOfGoldMedals + "}";
	}
}

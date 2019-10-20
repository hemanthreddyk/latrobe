
public class Sport {
	private String sportName;
	Competitor competitor1, competitor2, competitor3;

	public Sport(String sportName) {
		this.sportName = sportName;
		this.competitor1 = null;
		this.competitor2 = null;
		this.competitor3 = null;
	}

	void addCompetitor(Competitor Competitor) {
		if (this.competitor1 != null && this.competitor2 != null && this.competitor3 != null) {
			System.out.println("Cannot add more than three competitors");
			return;
		}

		if (this.competitor1 == null) {
			this.competitor1 = Competitor;
			return;
		} else if (this.competitor1.getCompetitorName().equals(Competitor.getCompetitorName())
				&& this.competitor1.getCountry().equals(Competitor.getCountry())) {
			System.out.println("Cannot add same competitor twice");
			return;
		}

		if (this.competitor2 == null) {
			this.competitor2 = Competitor;
			return;
		} else if (this.competitor2.getCompetitorName().equals(Competitor.getCompetitorName())
				&& this.competitor2.getCountry().equals(Competitor.getCountry())) {
			System.out.println("Cannot add same competitor twice");
			return;
		}
		if (this.competitor3 == null) {
			this.competitor3 = Competitor;
			return;
		} else if (this.competitor3.getCompetitorName().equals(Competitor.getCompetitorName())
				&& this.competitor3.getCountry().equals(Competitor.getCountry())) {
			System.out.println("Cannot add same competitor twice");
			return;
		}

	}

	Competitor getCompetitors(int competitorNumber) {
		if (competitorNumber == 1) {
			return this.competitor1;
		} else if (competitorNumber == 2) {
			return this.competitor2;
		} else if (competitorNumber == 3) {
			return this.competitor3;
		} else {
			return null;
		}
	}

	String getSportName() {
		return this.sportName;
	}

	public String toString() {
		String str = "Sport{\n" + "	sportName=" + this.sportName;
		if (this.competitor1 != null) {
			str += "\n" + "	competitor1=" + this.competitor1.toString();
		} else {
			str += "\n" + "	competitor1=" + "null";
		}
		if (this.competitor2 != null) {
			str += "\n" + "	competitor2=" + this.competitor2.toString();
		} else {
			str += "\n" + "	competitor2=" + "null";
		}
		if (this.competitor3 != null) {
			str += "\n" + "	competitor3=" + this.competitor3.toString();
		} else {
			str += "\n" + "	competitor3=" + "null";
		}
		str += "\n" + "}";

		return str;
	}

}

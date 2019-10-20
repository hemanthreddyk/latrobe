import java.util.Scanner;

public class Olympics {
	static Scanner sc = new Scanner(System.in);
	private static Sport sport1, sport2, sport3;

	public Olympics() {

	}

	static void displayMenu() {
		int choice;
		System.out.println("\n***MENU***");
		System.out.println("1. Add competitor\n" + "2. Add sport\n" + "4. Remove sport\n" + "5. Display sport\n"
				+ "7. Display all sports\n" + "9. Compete\n" + "10. Exit");
		System.out.print("Enter choice >>");
		choice = sc.nextInt();
		sc.nextLine();
		process(choice);

	}

	static void process(int choice) {
		switch (choice) {
		case 1:
			addCompetitor();
			break;
		case 2:
			addSport();
			break;
		case 4:
			removeSport();
			break;
		case 5:
			displaySport();
			break;
		case 7:
			displayAllSports();
			break;
		case 9:
			compete();
			break;
		case 10:
			System.exit(0);
		default:
			System.out.println("That is not a valid choice");

		}
	}

	static void addCompetitor() {
		String playerName, countryName, sportName;
		double skill;
		if (sport1 == null && sport2 == null && sport3 == null) {
			System.out.println("No sports exist");
		} else {
			System.out.print("Enter players name:");
			playerName = sc.nextLine();
			System.out.print("Enter players country:");
			countryName = sc.nextLine();
			System.out.print("Enter players sport:");
			sportName = sc.nextLine();
			Sport sport = searchSport(sportName);
			if (sport == null) {
				System.out.println("No sport with given name");
				return;
			} else {
				do {
					System.out.print("Enter competitors skill 0-1:");
					skill = sc.nextDouble();
					sc.nextLine();
				} while (!(skill > 0 && skill < 1));
				sport.addCompetitor(new Competitor(playerName, countryName, skill));
			}
		}
	}

	static void addSport() {
		String sportName;
		if (sport1 != null && sport2 != null && sport3 != null) {
			System.out.println("Maximum sports reached");
			return;
		} else {
			System.out.print("Enter sport name: ");
			sportName = sc.nextLine();
			if (searchSport(sportName) != null) {
				System.out.println("Sport already exists");
				return;
			} else if (sport1 == null) {
				sport1 = new Sport(sportName);
			} else if (sport2 == null) {
				sport2 = new Sport(sportName);
			} else {
				sport3 = new Sport(sportName);
			}

		}
	}

	static Sport searchSport(String sportName) {
		if (sport1 != null && sport1.getSportName().equals(sportName)) {
			return sport1;
		} else if (sport2 != null && sport2.getSportName().equals(sportName)) {
			return sport2;
		} else if (sport3 != null && sport3.getSportName().equals(sportName)) {
			return sport3;
		}
		return null;
	}

	static void removeSport() {
		if (sport3 == null && sport2 == null && sport1 == null) {
			System.out.println("No sports exist");
			return;
		}
		if (sport3 != null) {
			sport3 = null;
		} else if (sport2 != null) {
			sport2 = null;
		} else {
			sport1 = null;
		}
	}

	static void displaySport() {
		String sportName;
		if (sport1 == null && sport2 == null && sport3 == null) {
			System.out.println("No sports exist");
			return;
		}
		System.out.print("Enter sport: ");
		sportName = sc.nextLine();
		Sport sport = searchSport(sportName);
		if (sport == null) {
			System.out.println("Sport does not exist");
			return;
		} else {
			System.out.println(sport.toString());
		}

	}

	static void displayAllSports() {
		if (sport1 == null && sport2 == null && sport3 == null) {
			System.out.println("No sports exist");
			return;
		}
		if (sport1 != null) {
			System.out.println(sport1.toString());
		}
		if (sport2 != null) {
			System.out.println(sport2.toString());
		}
		if (sport3 != null) {
			System.out.println(sport3.toString());
		}
	}

	static void compete() {
		if (sport1 == null && sport2 == null && sport3 == null) {
			System.out.println("No sports exist");
			return;
		}
		System.out.print("Enter sport name: ");
		String sportName = sc.nextLine();
		Sport sport = searchSport(sportName);
		if (sport == null) {
			System.out.println("The sport does not exist.");
			return;
		} else if (sport.competitor1 == null || sport.competitor2 == null || sport.competitor3 == null) {
			System.out.println("Not sufficient competitors");
		} else {
			System.out.println("***" + sportName + "***");
			int comp1_Score = (int) (sport.competitor1.getSkill() * Math.random() * 50);
			int comp2_Score = (int) (sport.competitor2.getSkill() * Math.random() * 50);
			int comp3_Score = (int) (sport.competitor3.getSkill() * Math.random() * 50);
			Competitor competitor;
			
			if (comp1_Score > comp2_Score && comp1_Score > comp3_Score) {
				competitor = sport.competitor1;
			} else if (comp2_Score > comp3_Score) {
				competitor = sport.competitor2;
			} else {
				competitor = sport.competitor3;
			}
			System.out.println("Gold: " + competitor.getCompetitorName() + ", " + competitor.getCountry());
			competitor.incrementNumberOfGoldMedals();

		}
	}

	public static void main(String[] args) {
		while (true) {
			displayMenu();
		}

	}
}

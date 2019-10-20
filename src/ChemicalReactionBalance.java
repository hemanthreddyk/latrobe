
import java.util.Scanner;

public class ChemicalReactionBalance {
	public static void main(String[] args) {
		System.out.println("***20121527 Prudhviraju jarathi***");
		System.out.println("Chemical Reaction Balance Check");
		Scanner input = new Scanner(System.in);

		System.out.print("Enter Reactant 1: ");
		String r1 = input.nextLine();
		System.out.print("Enter Reactant 2: ");
		String r2 = input.nextLine();
		System.out.print("Enter Product 1: ");
		String p1 = input.nextLine();
		System.out.print("Enter Product 2: ");
		String p2 = input.nextLine();

		boolean isBalanced = true;
		int coef_r1 = 1, coef_r2 = 1, coef_p1 = 1, coef_p2 = 1;

		String equation = "";
		if (r1.length() > 0) {
			equation += r1;
			if (r2.length() > 0)
				equation += " + " + r2 + " -> ";
		} else {
			equation += r2 + " -> ";
		}
		if (p1.length() > 0) {
			equation += p1;
			if (p2.length() > 0)
				equation += " + " + p2;
		} else {
			equation += p2;
		}

		if (r1.length() > 0 && Character.isDigit(r1.charAt(0))) {
			coef_r1 = Character.getNumericValue(r1.charAt(0));
			r1 = r1.substring(1);
		}
		if (r2.length() > 0 && Character.isDigit(r2.charAt(0))) {
			coef_r2 = Character.getNumericValue(r2.charAt(0));
			r2 = r2.substring(1);
		}
		if (p1.length() > 0 && Character.isDigit(p1.charAt(0))) {
			coef_p1 = Character.getNumericValue(p1.charAt(0));
			p1 = p1.substring(1);
		}
		if (p2.length() > 0 && Character.isDigit(p2.charAt(0))) {
			coef_p2 = Character.getNumericValue(p2.charAt(0));
			p2 = p2.substring(1);
		}

		int len_r1 = r1.length(), len_r2 = r2.length(), len_p1 = p1.length(), len_p2 = p2.length();
		String reactants = r1 + r2, products = p1 + p2;

		String curr_ele_reactant = Character.toString(reactants.charAt(0));
		int nr_atoms = 1;
		for (int i = 1; i < reactants.length() + 1; i++) {
			if (i != reactants.length() && Character.isLowerCase(reactants.charAt(i))) {
				curr_ele_reactant += reactants.charAt(i);
			} else if (i != reactants.length() && Character.isDigit(reactants.charAt(i))) {
				nr_atoms = Character.getNumericValue(reactants.charAt(i));
			} else {
				if (len_r1 > 0 && r1.contains(curr_ele_reactant)) {
					if (len_r2 > 0 && r2.contains(curr_ele_reactant)) {
						int idx_valStart = (len_r1 - 1) + reactants.substring(len_r1).indexOf(curr_ele_reactant) + 1;
						int idx_valend = idx_valStart + curr_ele_reactant.length();
						if (idx_valend < reactants.length() && Character.isDigit(reactants.charAt(idx_valend))) {
							nr_atoms = (coef_r1 * nr_atoms)
									+ (coef_r2 * Character.getNumericValue(reactants.charAt(idx_valend)));
							if (idx_valend + 1 == reactants.length()) {
								reactants = reactants.substring(0, idx_valStart);
							} else {
								reactants = reactants.substring(0, idx_valStart) + reactants.substring(idx_valend + 1);
							}
						} else {
							nr_atoms = (coef_r1 * nr_atoms) + (coef_r2 * 1);
							reactants = reactants.substring(0, idx_valStart);
						}
					} else {
						nr_atoms = coef_r1 * nr_atoms;
					}
				} else {
					nr_atoms = coef_r2 * nr_atoms;
				}

				String curr_ele_product = Character.toString(products.charAt(0));
				int np_atoms = 1;
				int balance_check = 0;

				for (int j = 1; j < products.length() + 1; j++) {
					if (j != products.length() && Character.isLowerCase(products.charAt(j))) {
						curr_ele_product += products.charAt(j);
					} else if (j != products.length() && Character.isDigit(products.charAt(j))) {
						np_atoms = Character.getNumericValue(products.charAt(j));
					} else {
						if (curr_ele_reactant.equals(curr_ele_product)) {
							if (len_p1 > 0 && j <= len_p1 && p1.contains(curr_ele_product)) {
								balance_check += coef_p1 * np_atoms;
							}
							if (len_p2 > 0 && j > len_p1 && p2.contains(curr_ele_product)) {
								balance_check += coef_p2 * np_atoms;
							}

						}
						if (j != products.length()) {
							curr_ele_product = Character.toString(products.charAt(j));
							np_atoms = 1;
						}
					}
				}
				if (balance_check != nr_atoms) {
					isBalanced = false;
					System.out.println(curr_ele_reactant + "\t" + nr_atoms + " != " + balance_check);
				}
				if (i != reactants.length()) {
					curr_ele_reactant = Character.toString(reactants.charAt(i));
					nr_atoms = 1;

				}
			}

		}

		System.out.println(equation);
		System.out.println((isBalanced ? "is balanced" : "is NOT balanced"));
	}
}

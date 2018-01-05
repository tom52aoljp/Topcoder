import java.util.Scanner;

public class ABBADiv1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String initial = sc.next();
		String target = sc.next();

		String result = canObtain(initial,target);
		System.out.print(result);
	}

	public static String canObtain(String initial, String target) {
		
		try {

			StringBuilder sbInitial = new StringBuilder(initial);
			String keepInitial = initial;

			// loop for number of letters between two 
			for (int i = 0; i < (target.length() - initial.length()); i++) {

				String addingLetter = "";

				// match when it's forward: get a letter after the match part
				if (0 <= target.indexOf(keepInitial) &&
					(target.indexOf(keepInitial) + keepInitial.length()) != target.length()) {

					addingLetter = getLetter(keepInitial, target, true);
				// match when it's reverse: get a letter before the match part
				} else if (0 < target.indexOf(reverse(keepInitial))) {
					
					addingLetter = getLetter(keepInitial, target, false);
				// no match
				} else {
					
					return "Impossible";
				}

				sbInitial.append(addingLetter);

				// when "A" was put: just keep the present value
				if ("A".equals(addingLetter)) {

					keepInitial = sbInitial.toString();
				// when "B" was put: keep the present value and reverse it
				} else {
					
					sbInitial.reverse();
					keepInitial = sbInitial.toString();
				}
			}

			// whether the value is equal or not
			if (target.equals(sbInitial.toString())) {
				
				return "Possible";
			} else {
				
				return "Impossible";
			}
		} catch (Exception e) {
			
			System.out.print("Error");
			return "";
		}
	}

	private static String reverse(String rv) {
		
		StringBuffer sb = new StringBuffer(rv);
		rv = sb.reverse().toString();
		return rv;
	}

	private static String getLetter(String initial, String target, boolean ForR) {

		String letter = "";
		if (ForR) {

			letter = target.substring((target.indexOf(initial) + initial.length()),
				(target.indexOf(initial) + initial.length() + 1));
		} else {

			String rvinitial = reverse(initial);
			letter = target.substring((target.indexOf(rvinitial) - 1), target.indexOf(rvinitial));
		}

		return letter;
	}
}
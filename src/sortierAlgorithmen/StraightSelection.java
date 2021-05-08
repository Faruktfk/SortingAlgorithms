package sortierAlgorithmen;

public class StraightSelection extends SortierenSuperClass {

	@Override
	public void sortieren() {
		for (int kopf = 1; kopf < getFeld().length - 1; kopf++) {
			addOneZuweisung();
			addOneZuweisung();
			addOneZuweisung();
			int min = kopf;
			int minWert = getFeld()[min];

			for (int i = kopf + 1; i < getFeld().length; i++) {
				addOneZuweisung();
				addOneVergleich();
				if (minWert > getFeld()[i]) {
					addOneZuweisung();
					addOneZuweisung();
					min = i;
					minWert = getFeld()[min];
				}
			}
			
			addOneTausch();
			int help = getFeld()[kopf];
			getFeld()[kopf] = getFeld()[min];
			getFeld()[min] = help;
		}
	}

	@Override
	public void ausgabe() {
		System.out.println("Straigt selection:");
		super.ausgabe();
	}

}

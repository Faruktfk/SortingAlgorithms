package sortierAlgorithmen;

public class GnomSort extends SortierenSuperClass {

	@Override
	public void sortieren() {

		int i = 1;
		addOneZuweisung();
		addOneVergleich(); // weil die while-Schleife am Ende noch einmal die Bedingung überprüft...
		while (i < getFeld().length-1) {
			addOneVergleich();
			addOneVergleich();
			if (getFeld()[i] > getFeld()[i + 1]) {
				addOneTausch();
				int hilf = getFeld()[i];
				getFeld()[i] = getFeld()[i + 1];
				getFeld()[i + 1] = hilf;
				i--;
				addOneZuweisung();

				addOneVergleich();
				if (i == 0) {
					i = 1;
					addOneZuweisung();
				}
			} else {
				i++;
				addOneZuweisung();
			}
		}
	}

	@Override
	public void ausgabe() {
		System.out.println("Gnom sort:");
		super.ausgabe();
	}

}

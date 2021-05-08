package sortierAlgorithmen;

public class BubbleSort extends SortierenSuperClass{

	@Override
	public void sortieren() {
		for (int i = 1; i < getFeld().length -1; i++) {
			addOneZuweisung();
			for (int j = 1; j < getFeld().length - i; j++) {
				addOneZuweisung();
				
				addOneVergleich();
				if (getFeld()[j + 1] < getFeld()[j]) {
					addOneTausch();
					int help = getFeld()[j];
					getFeld()[j] = getFeld()[j + 1];
					getFeld()[j + 1] = help;
				}
			}
		}
		
	}

	@Override
	public void ausgabe() {
		System.out.println("Bubble sort:");
		super.ausgabe();
	}

}



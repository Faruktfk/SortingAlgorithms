package sortierAlgorithmen;

public class MergeSort extends SortierenSuperClass {
	
	@Override
	public void sortieren() {
		setFeld(mergeSort(getFeld()));
		
		int[] feldOutput = new int[getFeld().length+1];
		feldOutput[0] = 0;
		for(int i = 0; i<getFeld().length; i++) {
			feldOutput[i+1] = getFeld()[i];
		}
		
		setFeld(feldOutput);
		
	}

	@Override
	public void ausgabe() {
		System.out.println("Mergesort: ");
		for (int i = 0; i < getFeld().length; i++) {
			System.out.print(getFeld()[i] + "\t");
		}
		System.out.println();
	}
	
	//Nullte Stelle wird nicht besetzt
	@Override
	public void setFeld(int[] feld) {
		sf(feld);
	}

	//Die Methode, die zu diesem Sortieralgorithmus spezifisch sind.
	private int[] mergeSort(int[] feld) {
		
		addOneVergleich();
		if (feld.length <= 1) {
			return feld;
		}
		
		int mitte = feld.length / 2;
		addOneZuweisung();
		int[] leftFeld = new int[mitte];
		addOneZuweisung();
		int[] rightFeld = null;
		addOneZuweisung();
		
		addOneVergleich();
		if (feld.length % 2 == 0) {
			addOneZuweisung();
			rightFeld = new int[mitte];
		} else {
			addOneZuweisung();
			rightFeld = new int[mitte + 1];
		}

		for (int i = 0; i < mitte; i++) {
			addOneZuweisung();
			leftFeld[i] = feld[i];
			addOneZuweisung();
		}

		for (int j = 0; j < rightFeld.length; j++) {
			addOneZuweisung();
			rightFeld[j] = feld[mitte + j];
			addOneZuweisung();
		}

		int[] ergebnisFeld = new int[feld.length];
		addOneZuweisung();
		
		addOneAufruf();
		leftFeld = mergeSort(leftFeld);
		addOneAufruf();
		rightFeld = mergeSort(rightFeld);

		addOneAufruf();
		ergebnisFeld = merge(leftFeld, rightFeld);

		return ergebnisFeld;

	}

	private int[] merge(int[] linksFeld, int[] rechtsFeld) {
		int[] ergebnisFeld = new int[linksFeld.length + rechtsFeld.length];
		addOneZuweisung();

		int punktLinks = 0;
		addOneZuweisung();
		int punktRechts = 0;
		addOneZuweisung();
		int punktErgebnis = 0;
		addOneZuweisung();
		
		addOneVergleich(); // weil die while-Schleife am Ende die Bedingung noch einmal überprüft
		while (punktLinks < linksFeld.length || punktRechts < rechtsFeld.length) {

			addOneVergleich();
			if (punktLinks < linksFeld.length && punktRechts < rechtsFeld.length) {

				addOneVergleich();
				if (linksFeld[punktLinks] < rechtsFeld[punktRechts]) {
					addOneTausch();
					ergebnisFeld[punktErgebnis++] = linksFeld[punktLinks++];
				} else {
					addOneTausch();
					ergebnisFeld[punktErgebnis++] = rechtsFeld[punktRechts++];

				}

			} else if (punktLinks < linksFeld.length) {
				addOneTausch();
				ergebnisFeld[punktErgebnis++] = linksFeld[punktLinks++];

			} else if (punktRechts < rechtsFeld.length) {
				addOneTausch();
				ergebnisFeld[punktErgebnis++] = rechtsFeld[punktRechts++];

			}

		}

		return ergebnisFeld;

	}

	
	// Weil die Tausche in diesem Algorithmus anders ist, als die bisherigen Algorithmen.
	@Override
	public int getGesamteZuweisungen() {
		return getZuweisung() + getTausch();
	}

	
	
}

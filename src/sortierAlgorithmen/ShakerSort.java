package sortierAlgorithmen;

public class ShakerSort extends SortierenSuperClass{

	@Override
	public void sortieren() {
		boolean änderung = true;
		addOneZuweisung();
		boolean hin = true;
		addOneZuweisung();
		
		addOneVergleich(); // weil die while-Schleife am Ende noch einmal die Bedingung überprüft...
		while(änderung) {
			addOneVergleich();
			addOneZuweisung();
			änderung = false;
			addOneVergleich();
			if(hin) {
				hin = false;
				addOneZuweisung();
				for(int i = 1; i<getFeld().length-1; i++) {
					addOneZuweisung();
					addOneVergleich();
					if(getFeld()[i+1] < getFeld()[i]) {
						addOneTausch();
						int hilf = getFeld()[i];
						getFeld()[i] =getFeld()[i+1];
						getFeld()[i+1] = hilf;	
						addOneZuweisung();
						änderung = true;
					}
				}
			}else {
				hin = true;
				addOneZuweisung();
				for(int i = getFeld().length-1; i>2; i--) {
					addOneZuweisung();
					addOneVergleich();
					if(getFeld()[i] < getFeld()[i-1]) {
						addOneTausch();
						int hilf = getFeld()[i];
						getFeld()[i] =getFeld()[i-1];
						getFeld()[i-1] = hilf;
						addOneZuweisung();
						änderung = true;
						
					}
				}
			}
		}
		
	}

	@Override
	public void ausgabe() {
		System.out.println("Shaker sort:");
		super.ausgabe();
	}
}

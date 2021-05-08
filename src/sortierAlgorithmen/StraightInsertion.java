package sortierAlgorithmen;

public class StraightInsertion extends SortierenSuperClass{

	@Override
	public void sortieren() {

		for (int kopf = 2; kopf < getFeld().length; kopf++) {
			addOneZuweisung();
			addOneZuweisung();
			addOneZuweisung();
			int stelle = kopf;
			int lauf = stelle;
			
			do {
				addOneZuweisung();
				lauf--;
				addOneVergleich();
				if(getFeld()[stelle] < getFeld()[lauf]) {
					addOneTausch();
					int help = getFeld()[stelle];
					getFeld()[stelle] = getFeld()[lauf];
					getFeld()[lauf] = help;
					addOneZuweisung();
					stelle--;
				}
				
				addOneVergleich();
				addOneVergleich();
			}while(stelle - lauf != 1 && lauf!=1);
			
		}
		
	}
	
	@Override
	public void ausgabe() {
		System.out.println("Straigt insertion:");
		super.ausgabe();
	}


}

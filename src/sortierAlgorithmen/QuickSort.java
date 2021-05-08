package sortierAlgorithmen;

	public class QuickSort extends SortierenSuperClass{

		@Override
		public void sortieren() {

			quicksort(0, getFeld().length-1);
			
		}

		public void quicksort(int links, int rechts) {
			
			addOneVergleich();
			if(rechts > links) {
				int p = getFeld()[(rechts + links) / 2];
				int l = links;
				int r = rechts;
				
				do {
					addOneZuweisung();
					while(getFeld()[l] < p) {
						addOneZuweisung();
						l++;
					}
					while(getFeld()[r] > p) {
						addOneZuweisung();
						r--;
					}
					
					addOneVergleich();
					if(l <= r) {
						addOneTausch();
						int help = getFeld()[l];
						getFeld()[l] = getFeld()[r];
						getFeld()[r] = help;
						l++;
						r--;
					}
					
				}while(l <= r);
				
				addOneAufruf();
				quicksort(links, r);
				addOneAufruf();
				quicksort(l, rechts);
				
			}

		}
		
		@Override
		public void ausgabe() {
			System.out.println("Quick sort: ");
			super.ausgabe();
		}

}

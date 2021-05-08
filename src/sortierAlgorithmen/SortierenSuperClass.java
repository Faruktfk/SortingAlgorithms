package sortierAlgorithmen;

public abstract class SortierenSuperClass {

	// Eine Superklasse der Sortieralgorithmen, die die gleichen Methoden und
	// Funktionen beinhaltet, die jede Sortieralgorithmus-Klasse haben soll.

	private int[] feld;
	private int tausch = 0;
	private int zuweisung = 0;
	private int vergleich = 0;
	private int aufruf = 0;

	
	//Konstruktoren
	public SortierenSuperClass() {

	}

	public SortierenSuperClass(int[] feld) {
		this.feld = feld;
	}

	// Das Feld wird mithilfe dieser Methoden abgespeichert und wiedergegeben.
	public int[] getFeld() {
		return feld;
	}

	public void setFeld(int[] feld) {
		int[] feld2 = new int[feld.length + 1];
		feld2[0] = 0;
		for (int i = 1; i < feld2.length; i++) {
			feld2[i] = feld[i - 1];
		}

		this.feld = feld2;
	}

	
	// Diese Methode wird nur beim Mergesort benutzt, da das nullte Index beim
	// Mergesort nicht besetzt werden muss, anders als die anderen
	// Sortieralgorithmen.
	public void sf(int[] feld) {
		this.feld = feld;
	}

	// Nur die "sortieren" Methode wird als "abstract" gelassen, da jede
	// Unterklassn unterschiedlich sortiert wird.
	public abstract void sortieren();

	// Die "ausgabe"-Methode wird in jeden Unterklassen überschrieben, damit es
	// verständlicher wird, wenn man die Ergebnisse in der Konsole sehen möchte.
	public void ausgabe() {
		for (int i = 1; i < feld.length; i++) {
			System.out.print(feld[i] + "\t");
		}
		System.out.println();
	}

	// Die Methoden, die die weiteren Informationen über den vom Benutzer
	// ausgewählten Sortieralgorithmus zeigen, sind hier.
	public int getTausch() {
		return tausch;
	}

	public void addOneTausch() {
		this.tausch++;
	}

	public int getZuweisung() {
		return zuweisung;
	}

	public void addOneZuweisung() {
		this.zuweisung++;
	}

	public int getVergleich() {
		return vergleich;
	}

	public void addOneVergleich() {
		this.vergleich++;
	}

	public void addOneAufruf() {
		this.aufruf++;
	}

	public int getAufrufe() {
		return this.aufruf;
	}
	
	public int getGesamteZuweisungen() {
		return getZuweisung() + 3*getTausch();
	}

}

package sortierAlgorithmen;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;

public class UserFriendlyMain {

	// Die Variablen
	private static long duration;
	private static JLabel dauer;
	private static int tausch;
	private static int vergleich;
	private static int zuweisung;
	private static int gesamtZuweisung;
	private static int aufruf;

	private static JLabel anzahlZahlen;
	private static JLabel anzahlTausch;
	private static JLabel anzahlZuweisung;
	private static JLabel anzahlVergleich;
	private static JLabel anzGesamteZuweisungen;
	private static JLabel anzahlAufruf;

	private static JComboBox<String> comboBox;
	private static JTextField eingabe;
	private static JLabel lbl_0, lbl_1, lbl_2;
	private static int[] feld;
	private static String selected;
	private static JButton btnNewButton;
	private static JLabel fehlerMeldung;
	private static JTextArea ausgabe;
	
	private static Font font = new Font("Calibri", Font.PLAIN, 20);
	private static JTextArea helpEingabeMessage = new JTextArea();
	private static int countFürEingabeFehler = 0;
	private static int countFürAlgorithmusFehler = 0;
	private static JTextArea meldungMessage;
	
	
	

	// Die Exceptions, um die Fehlermeldungen dem Benutzer richtig und verständlich
	// zu vermitteln.
	private static class falscheEingabeException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5828246134445478835L;

		public falscheEingabeException(String string) {
			super(string);
		}
	}

	private static class keinenAlgorithmGewähltException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 512645519439712646L;

		public keinenAlgorithmGewähltException(String string) {
			super(string);
		}
	}

	private static class keineEingabe extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7283051497787053348L;

		public keineEingabe(String string) {
			super(string);
		}
	}

	// Main Methode
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		// Die Einstellungen und Eigenschaften des Fensters und dessen Komponenten.

		JFrame frame = new JFrame("Sortier Algorithmen");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(900, 590);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Eigenschaften von Menu und andere MenuItems
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setBackground(Color.WHITE);
		
		JMenu menuFirst = new JMenu("Settings");
		menuFirst.setFont(new Font("Calibri", Font.PLAIN, 13));
		menuFirst.setBackground(Color.WHITE);
		menuBar.add(menuFirst);
		
		JMenuItem fontMenuItem = new JMenuItem("Font \u00E4ndern");
		fontMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		fontMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		fontMenuItem.setFont(new Font("Calibri", Font.PLAIN, 13));
		fontMenuItem.setBackground(Color.WHITE);
		fontMenuItem.addActionListener(e -> {
			aendereFont();
		});
		menuFirst.add(fontMenuItem);
		
		JSeparator separator = new JSeparator();
		menuFirst.add(separator);
		
		JMenuItem quitMenuItem = new JMenuItem("Schlie\u00DFen");
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		quitMenuItem.setFont(new Font("Calibri", Font.PLAIN, 13));
		quitMenuItem.setBackground(Color.WHITE);
		quitMenuItem.addActionListener(e -> {
			System.exit(0);
		});
		menuFirst.add(quitMenuItem);
		
		JMenu menuSecond = new JMenu("Hilfe");
		menuSecond.setFont(new Font("Calibri", Font.PLAIN, 13));
		menuBar.add(menuSecond);
		
		JMenuItem helpMenuItem = new JMenuItem("Hilfe bei der Eingabe");
		helpMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		helpMenuItem.setFont(new Font("Calibri", Font.PLAIN, 13));
		helpMenuItem.setBackground(Color.WHITE);
		helpMenuItem.addActionListener(e -> {
			getHelpEingabe();
		});		
		menuSecond.add(helpMenuItem);
		
		
		
		// Andere Items 

		lbl_0 = new JLabel("Geben Sie hier Ihre Zahlen ein:");
		lbl_0.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_0.setBounds(10, 21, 611, 20);
		lbl_0.setVisible(true);
		frame.getContentPane().add(lbl_0);
		lbl_0.repaint();

		lbl_1 = new JLabel("W\u00E4hlen Sie einen Algorithmus aus:");
		lbl_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_1.setBounds(98, 127, 499, 20);
		lbl_1.setVisible(true);
		frame.getContentPane().add(lbl_1);
		lbl_1.repaint();

		lbl_2 = new JLabel("Ausgabe:");
		lbl_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_2.setBounds(10, 216, 436, 20);
		lbl_2.setVisible(true);
		frame.getContentPane().add(lbl_2);
		lbl_2.repaint();

		ausgabe = new JTextArea();
		ausgabe.setFont(new Font("Calibri", Font.PLAIN, 20));
		ausgabe.setText("...");
		ausgabe.setBounds(10, 241, 866, 89);
		ausgabe.setLineWrap(true);
		ausgabe.setWrapStyleWord(true);
		ausgabe.setEditable(false);
		ausgabe.setVisible(true);
		frame.getContentPane().add(ausgabe);
		ausgabe.repaint();

		// Die Komponente, die weitere Informationen über den jeweiligen Algorithmus
		// übermitteln.

		anzahlZahlen = new JLabel();
		anzahlZahlen.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		anzahlZahlen.setText("Anzahl der Zahlen: ");
		anzahlZahlen.setBounds(10, 340, 320, 20);
		anzahlZahlen.setVisible(false);
		frame.getContentPane().add(anzahlZahlen);
		anzahlZahlen.repaint();
		
		anzahlZuweisung = new JLabel();
		anzahlZuweisung.setText("Anzahl der Zuweisungen: ");
		anzahlZuweisung.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		anzahlZuweisung.setBounds(10, 363, 320, 20);
		anzahlZuweisung.setVisible(false);
		frame.getContentPane().add(anzahlZuweisung);
		anzahlZuweisung.repaint();
		
		anzahlVergleich = new JLabel();
		anzahlVergleich.setText("Anzahl der Vergleiche: ");
		anzahlVergleich.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		anzahlVergleich.setBounds(10, 386, 320, 20);
		anzahlVergleich.setVisible(false);
		frame.getContentPane().add(anzahlVergleich);
		anzahlVergleich.repaint();

		anzahlTausch = new JLabel();
		anzahlTausch.setText("Anzahl der Tausche: ");
		anzahlTausch.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		anzahlTausch.setBounds(10, 409, 320, 20);
		anzahlTausch.setVisible(false);
		frame.getContentPane().add(anzahlTausch);
		anzahlTausch.repaint();
		
		anzGesamteZuweisungen = new JLabel();
		anzGesamteZuweisungen.setText("Gesamte Zuweisungen: ");
		anzGesamteZuweisungen.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		anzGesamteZuweisungen.setBounds(10, 432, 320, 20);
		anzGesamteZuweisungen.setVisible(false);
		frame.getContentPane().add(anzGesamteZuweisungen);
		anzGesamteZuweisungen.repaint();

		anzahlAufruf = new JLabel();
		anzahlAufruf.setText("Anzahl der Aufrufe: ");
		anzahlAufruf.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		anzahlAufruf.setBounds(10, 455, 320, 20);
		anzahlAufruf.setVisible(false);
		frame.getContentPane().add(anzahlAufruf);
		anzahlAufruf.repaint();

		dauer = new JLabel();
		dauer.setText("Dauer in Nanosekunden: ");
		dauer.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		dauer.setBounds(10, 478, 320, 20);
		dauer.setVisible(false);
		frame.getContentPane().add(dauer);
		dauer.repaint();

		// Ein Zeiger für die jeweilige Fehlermeldung.
		fehlerMeldung = new JLabel();
		fehlerMeldung.setHorizontalAlignment(SwingConstants.CENTER);
		fehlerMeldung.setFont(new Font("Monospaced", Font.PLAIN, 13));
		fehlerMeldung.setText("*Fehler");
		fehlerMeldung.setForeground(Color.RED);
		fehlerMeldung.setBounds(420, 206, 388, 30);
		fehlerMeldung.setVisible(false);
		frame.getContentPane().add(fehlerMeldung);
		fehlerMeldung.repaint();

		// Ein Komponent des Fensters, der den Benutzer eine Auswahl von Algorithmus
		// bietet.
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "---", "Bubble sort", "Straight selection",
				"Straight insertion", "Quick sort", "Shaker sort", "Gnom sort", "Merge sort" }));
		comboBox.setBounds(98, 157, 268, 30);
		comboBox.setVisible(true);
		frame.getContentPane().add(comboBox);
		comboBox.revalidate();
		comboBox.repaint();
		comboBox.addActionListener((e) -> {
			selected = comboBox.getModel().getSelectedItem() + "";
		});

		// Eigenschaften des Eingabebereiches
		eingabe = new JTextField();
		eingabe.setFont(new Font("Calibri", Font.PLAIN, 20));
		eingabe.setBounds(10, 52, 866, 42);
		eingabe.setEditable(true);
		eingabe.setVisible(true);
		frame.getContentPane().add(eingabe);
		eingabe.repaint();
		eingabe.addActionListener((e) -> action());

		// Eigenschaften des Knopfs.
		btnNewButton = new JButton("SORTIEREN");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setFocusable(true);
		btnNewButton.setBounds(549, 157, 131, 42);
		btnNewButton.setVisible(true);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.repaint();
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				action();
			}
		});
		btnNewButton.addActionListener((e) -> action());

	}

	// Die Befehle, die bei einer Eingabe durchgeführt werden sollen.
	public static void action() {


		// Damit das Label nicht sichtbar bleibt, wenn es keine Fehlermeldungen gibt.
		fehlerMeldung.setVisible(false);

		// Damit eine Fehlermeldung richtig gefangen wird.
		try {
			setFeld(eingabe.getText());
			selectAlgorithmus(selected);
			showInfo();
			
			countFürEingabeFehler = 0;
			countFürAlgorithmusFehler = 0;
		} catch (keineEingabe e1) {
			fehlerMeldung.setText("*Bitte geben Sie oben Ihre Zahlen ein.");
			inCaseException();
			
			countFürEingabeFehler++;
			countFürAlgorithmusFehler = 0;

		} catch (keinenAlgorithmGewähltException e1) {
			fehlerMeldung.setText("*Bitte wählen Sie einen Algorithmus aus.");
			inCaseException();
			
			countFürEingabeFehler = 0;
			countFürAlgorithmusFehler++;
			if(countFürAlgorithmusFehler >= 3) {
				meldung("Algorithmus");
			}

		} catch (falscheEingabeException e1) {
			fehlerMeldung.setText("*Bitte verwenden Sie eine gültige Schreibweise.");
			inCaseException();
			
			countFürAlgorithmusFehler = 0;
			countFürEingabeFehler++;
			if(countFürEingabeFehler >= 3) {
				meldung("Eingabe");
			}

		}

	}

	// Eine Methode, die die Wahl des Benutzers richtig interpretiert.
	public static void selectAlgorithmus(String selected) throws keinenAlgorithmGewähltException {

		// Damit das Font, das bei der Ausgabe verwendet werden soll, nicht das gleiche
		// ist, wie bei einer Fehlermeldung.
		ausgabe.setFont(new Font("Calibri", Font.PLAIN, 19));

		// Damit jegliche Fehlermeldungen gefangen werden.
		try {

			if (selected.equals("Bubble sort")) {
				// Ein neues Objekt wird erstellt.
				BubbleSort bubbleSort = new BubbleSort();
				// Das Feld wird eingesetzt
				bubbleSort.setFeld(feld);
				// Der Zeitpunkt von dem Anfang des Sortierenverfahrens wird gespeichert
				long start = System.nanoTime();
				// Sortiere Befehl
				bubbleSort.sortieren();
				// Eine Methode wird aufgerufen, um den zeitlichen Aufwand zu messen.
				timeDurationRechnen(start);
				// Das Ergebnis wird von der Klasse geholt und das Ausgabebereich wird
				// aktualisiert.
				setAusgabe(bubbleSort.getFeld());
				// Die weiteren Informationen über den Algorithmus werden in Variablen
				// abgespeichert
				tausch = bubbleSort.getTausch();
				zuweisung = bubbleSort.getZuweisung();
				vergleich = bubbleSort.getVergleich();
				gesamtZuweisung =bubbleSort.getGesamteZuweisungen();
				aufruf = 0;
			} else if (selected.equals("Straight selection")) {
				StraightSelection straightSelection = new StraightSelection();
				straightSelection.setFeld(feld);
				long start = System.nanoTime();
				straightSelection.sortieren();
				timeDurationRechnen(start);
				setAusgabe(straightSelection.getFeld());
				tausch = straightSelection.getTausch();
				zuweisung = straightSelection.getZuweisung();
				vergleich = straightSelection.getVergleich();
				gesamtZuweisung = straightSelection.getGesamteZuweisungen();
				aufruf = 0;
			} else if (selected.equals("Straight insertion")) {
				StraightInsertion straightInsertion = new StraightInsertion();
				straightInsertion.setFeld(feld);
				long start = System.nanoTime();
				straightInsertion.sortieren();
				timeDurationRechnen(start);
				setAusgabe(straightInsertion.getFeld());
				tausch = straightInsertion.getTausch();
				zuweisung = straightInsertion.getZuweisung();
				vergleich = straightInsertion.getVergleich();
				gesamtZuweisung = straightInsertion.getGesamteZuweisungen();
				aufruf = 0;
			} else if (selected.equals("Quick sort")) {
				QuickSort quickSort = new QuickSort();
				quickSort.setFeld(feld);
				long start = System.nanoTime();
				quickSort.sortieren();
				timeDurationRechnen(start);
				setAusgabe(quickSort.getFeld());
				tausch = quickSort.getTausch();
				zuweisung = quickSort.getZuweisung();
				vergleich = quickSort.getVergleich();
				gesamtZuweisung = quickSort.getGesamteZuweisungen();
				quickSort.addOneAufruf();
				aufruf = quickSort.getAufrufe();
			} else if (selected.equals("Shaker sort")) {
				ShakerSort shakerSort = new ShakerSort();
				shakerSort.setFeld(feld);
				long start = System.nanoTime();
				shakerSort.sortieren();
				timeDurationRechnen(start);
				setAusgabe(shakerSort.getFeld());
				tausch = shakerSort.getTausch();
				zuweisung = shakerSort.getZuweisung();
				vergleich = shakerSort.getVergleich();
				gesamtZuweisung = shakerSort.getGesamteZuweisungen();
				aufruf = 0;
			} else if (selected.equals("Gnom sort")) {
				GnomSort gnomSort = new GnomSort();
				gnomSort.setFeld(feld);
				long start = System.nanoTime();
				gnomSort.sortieren();
				timeDurationRechnen(start);
				setAusgabe(gnomSort.getFeld());
				tausch = gnomSort.getTausch();
				zuweisung = gnomSort.getZuweisung();
				vergleich = gnomSort.getVergleich();
				gesamtZuweisung = gnomSort.getGesamteZuweisungen();
				aufruf = 0;
			} else if (selected.equals("Merge sort")) {
				MergeSort mergeSort = new MergeSort();
				mergeSort.setFeld(feld);
				long start = System.nanoTime();
				mergeSort.sortieren();
				timeDurationRechnen(start);
				setAusgabe(mergeSort.getFeld());
				tausch = mergeSort.getTausch();
				zuweisung = mergeSort.getZuweisung();
				vergleich = mergeSort.getVergleich();
				gesamtZuweisung = mergeSort.getGesamteZuweisungen();
				aufruf = mergeSort.getAufrufe();
			} else {
				// Alle anderen Optionen, die nicht oben erwähnt sind, was eigentlich nicht
				// auftauchen können, wird trotzdem als Fehler gesehen.
				// Daher wird eine Fehlermeldung geworfen.
				throw new keinenAlgorithmGewähltException("keinen Algorithmus gewählt");
			}

			// Alle Fehlermeldungen, die wegen der Befehle oben verursacht werden können,
			// wird hier gehandelt.
		} catch (Exception e) {
			// Da eine Fehlermeldung, die hier gehandelt wird, nur aus einem Grund sein
			// kann, und zwar dass der Benutzer keinen Algorithmus gewählt hat,
			// wird eine Exception geworfen, somit wird es einfacher, dem Benutzer eine
			// passende Benachrichtigung zu zeigen.
			throw new keinenAlgorithmGewähltException("keinen Algorithmus gewählt");

		}

	}

	// Eine Methode, die die Eingaben des Benutzers richtig interpretiert.
	public static void setFeld(String eingabeStr) throws falscheEingabeException, keineEingabe {
		// Die Eingabe des Benutzers wird zuerts als ein String abgespeichert und dann
		// wird dieses String gespaltet.
		// Und da dieser Prozess sehr anfällig für Fehlermeldungen ist, wird alles hier
		// behandelt.
		try {
			eingabeStr = eingabeStr.strip();

			// Falls der Benutzer nichts eingibt.
			if (eingabeStr.isEmpty()) {
				throw new keineEingabe("Keine Eingabe");
			}

			// Die Eingabe wird nach einigen möglichen Eingabetypen wird entsprechend
			// unterschiedlich gespalten.
			String splt = "";
			if (eingabeStr.contains(","))
				splt = ",";
			if (eingabeStr.contains(", "))
				splt = ", ";
			if (eingabeStr.contains(";"))
				splt = ";";
			if (eingabeStr.contains("; "))
				splt = "; ";
			if (!eingabeStr.contains(",") && !eingabeStr.contains(";") && eingabeStr.contains(" "))
				splt = " ";
			if (!eingabeStr.contains(",") && !eingabeStr.contains(";") && eingabeStr.contains("  "))
				splt = "  ";

			// Die gespalteten Teile des Strings werden in Typ "int" konvertiert und im Feld
			// gespeichert.
			String[] strings = eingabeStr.split(splt);
			feld = new int[strings.length];
			for (int i = 0; i < strings.length; i++) {
				feld[i] = Integer.parseInt(strings[i]);
			}
		} catch (Exception e) {
			// Jegliche Fehler werden in die für den Benutzer verständlicheren
			// Fehlermeldungen umgewandelt.
			if (e instanceof keineEingabe) {
				throw new keineEingabe("Keine Eingabe");
			} else {
				throw new falscheEingabeException("falsche Eingabe");
			}
		}

	}

	// Eine Methode, die das Ergebnis auf der benutzerfreundlichen Oberfläche zeigt.
	public static void setAusgabe(int[] ausFeld) {
		String output = "";
		for (int i = 1; i < ausFeld.length; i++) {
			output += ausFeld[i];
			if (i != ausFeld.length - 1) {
				output += ", ";
			}
		}

		ausgabe.setText(output);

	}

	// Eine Methode, die die weiteren Informationen über den ausgewählten
	// Algorithmus aktualisiert und zeigt.
	public static void showInfo() {

		anzahlZahlen.setText("Anzahl der Zahlen:                " + feld.length);
		anzahlZahlen.setVisible(true);

		anzahlZuweisung.setText("Anzahl der Zusweisungen:   " + zuweisung);
		anzahlZuweisung.setVisible(true);

		anzahlVergleich.setText("Anzahl der Vergleiche:         " + vergleich);
		anzahlVergleich.setVisible(true);
		
		anzahlTausch.setText("Anzahl der Tausche:             " + tausch);
		anzahlTausch.setVisible(true);
		
		anzGesamteZuweisungen.setText("Gesamte Zuweisungen:       " + gesamtZuweisung);
		anzGesamteZuweisungen.setVisible(true);

		anzahlAufruf.setText("Anzahl der Aufrufe:              " + aufruf);
		anzahlAufruf.setVisible(true);

		dauer.setText("Dauer in Nanosekunden:     " + duration + " ns");
		dauer.setVisible(true);

	}

	// Eine Methode, die die Befehle beinhaltet, die im Fall eines Fehlers
	// durchgeführt werden sollen.
	public static void inCaseException() {

		// Die Anzeiger, die die weiteren Informationen über den jeweiligen
		// Sortieralgorithmus übermitteln, werden im Fall einer Fehlermeldung
		// unsichtbar gemacht.
		anzahlZahlen.setVisible(false);
		anzahlZuweisung.setVisible(false);
		anzahlVergleich.setVisible(false);
		anzahlTausch.setVisible(false);
		anzGesamteZuweisungen.setVisible(false);
		anzahlAufruf.setVisible(false);
		dauer.setVisible(false);

		// Das Bereich für die Ausgabe gibt eine einfache Benachrichtigung aus.
		fehlerMeldung.setVisible(true);
		ausgabe.setFont(new Font("Monospaced", Font.ITALIC, 19));
		ausgabe.setText("Fehler...");
	}

	// Eine Methode, die den zeitlichen Aufwand eines gewählten Sortieralgorithmus
	// berechnet.
	public static void timeDurationRechnen(long start) {
		duration = System.nanoTime() - start;

	}


	// Methoden für die MenuItems
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void aendereFont() {

		JFrame fontFrame = new JFrame();
		fontFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fontFrame.getContentPane().setBackground(Color.WHITE);
		fontFrame.setSize(500, 400);
		fontFrame.setVisible(true);
		fontFrame.setLocationRelativeTo(null);
		fontFrame.setResizable(false);
		fontFrame.getContentPane().setLayout(null);

		
		JTextArea fontMessage = new JTextArea("Bitte w\u00E4hlen Sie eine Option aus, um den Font zu \u00E4ndern.");
		fontMessage.setLineWrap(true);
		fontMessage.setWrapStyleWord(true);
		fontMessage.setEditable(false);
		fontMessage.setFont(font);
		fontMessage.setBounds(74, 36, 347, 112);
		fontFrame.getContentPane().add(fontMessage);
		fontMessage.repaint();

		String[] fonts = new String[] {"Arial", "Arial Black", "Bahnschrift", "Calibri", "Calibri Light", "Cambria", "Cambria Math", "Candara", "Candara Light", "Comic Sans MS", "Consolas", "Constantia", "Corbel", "Corbel Light", "Courier New", "Dialog", "DialogInput", "Ebrima", "Franklin Gothic Medium", "Gabriola", "Gadugi", "Georgia", "HoloLens MDL2 Assets", "Impact", "Ink Free", "Javanese Text", "Leelawadee UI", "Leelawadee UI Semilight", "Lucida Console", "Lucida Sans Unicode", "Malgun Gothic", "Malgun Gothic Semilight", "Marlett", "Microsoft Himalaya", "Microsoft JhengHei", "Microsoft JhengHei Light", "Microsoft JhengHei UI", "Microsoft JhengHei UI Light", "Microsoft New Tai Lue", "Microsoft PhagsPa", "Microsoft Sans Serif", "Microsoft Tai Le", "Microsoft YaHei", "Microsoft YaHei Light", "Microsoft YaHei UI", "Microsoft YaHei UI Light", "Microsoft Yi Baiti", "MingLiU-ExtB", "MingLiU_HKSCS-ExtB", "Mongolian Baiti", "Monospaced", "MS Gothic", "MS PGothic", "MS UI Gothic", "MV Boli", "Myanmar Text", "Nirmala UI", "Nirmala UI Semilight", "NSimSun", "Palatino Linotype", "PMingLiU-ExtB", "SansSerif", "Segoe MDL2 Assets", "Segoe Print", "Segoe Script", "Segoe UI", "Segoe UI Black", "Segoe UI Emoji", "Segoe UI Historic", "Segoe UI Light", "Segoe UI Semibold", "Segoe UI Semilight", "Segoe UI Symbol", "Serif", "SimSun", "SimSun-ExtB", "Sitka Banner", "Sitka Display", "Sitka Heading", "Sitka Small", "Sitka Subheading", "Sitka Text", "Sylfaen", "Symbol", "Tahoma", "Times New Roman", "Trebuchet MS", "Verdana", "Yu Gothic", "Yu Gothic Light", "Yu Gothic Medium", "Yu Gothic UI", "Yu Gothic UI Light", "Yu Gothic UI Semibold", "Yu Gothic UI Semilight"};
		int selectedIndex = 0;
		for(int i = 0; i<fonts.length; i++) {
			if(fonts[i].equals(font.getFamily())) {
				selectedIndex = i;
			}
		}
		
		
		JComboBox fontsCB = new JComboBox();
		fontsCB.setFont(new Font("Calibri", Font.PLAIN, 18));
		fontsCB.setModel(new DefaultComboBoxModel(fonts));
		fontsCB.setSelectedIndex(selectedIndex);
		fontsCB.setBounds(133, 174, 230, 36);
		fontFrame.getContentPane().add(fontsCB);
		fontsCB.revalidate();
		fontsCB.repaint();
		
		fontsCB.addActionListener(e -> System.out.println("hola"));
		
		
		JButton saveBtn = new JButton("SPEICHERN");
		saveBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		saveBtn.setFont(new Font("Calibri", Font.PLAIN, 15));
		saveBtn.setBackground(Color.LIGHT_GRAY);
		saveBtn.setBounds(186, 250, 123, 29);
		fontFrame.getContentPane().add(saveBtn);
		saveBtn.setVisible(true);
		saveBtn.revalidate();
		saveBtn.repaint();
		saveBtn.addActionListener(e -> {
			font = new Font((String) fontsCB.getSelectedItem(), Font.PLAIN, 20);
			fontMessage.setFont(font);
			eingabe.setFont(font);
			ausgabe.setFont(font);
			lbl_0.setFont(new Font((String) fontsCB.getSelectedItem(), Font.PLAIN, 16));
			lbl_1.setFont(new Font((String) fontsCB.getSelectedItem(), Font.PLAIN, 16));
			lbl_2.setFont(new Font((String) fontsCB.getSelectedItem(), Font.PLAIN, 16));
			btnNewButton.setFont(new Font((String) fontsCB.getSelectedItem(), Font.PLAIN, 17));
			comboBox.setFont(new Font((String) fontsCB.getSelectedItem(), Font.PLAIN, 14));
			helpEingabeMessage.setFont(font);
			meldungMessage.setFont(font);
			
		});
		
	
		
		
	}
	
	public static void getHelpEingabe() {
		
		JFrame helpEingabe = new JFrame();
		helpEingabe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		helpEingabe.getContentPane().setBackground(Color.WHITE);
		helpEingabe.setSize(500, 400);
		helpEingabe.setVisible(true);
		helpEingabe.setLocationRelativeTo(null);
		helpEingabe.setResizable(false);
		helpEingabe.getContentPane().setLayout(null);

		helpEingabeMessage.setText(
				"Bitte verwenden Sie bei der Eingabe eine der folgenden Schreibweisen:\n\n\n"
				+ "1,0,2,9,3,8,4,7,5,6\n\n"
				+ "1 0 2 9 3 8 4 7 5 6\n\n"
				+ "1, 0, 2, 9, 3, 8, 4, 7, 5, 6\n\n"
				+ "1  0  2  9  3  8  4  7  5  6");
		helpEingabeMessage.setLineWrap(true);
		helpEingabeMessage.setWrapStyleWord(true);
		helpEingabeMessage.setEditable(false);
		helpEingabeMessage.setFont(new Font("Calibri", Font.PLAIN, 20));
		helpEingabeMessage.setBounds(55, 42, 386, 288);
		helpEingabe.getContentPane().add(helpEingabeMessage);
		helpEingabeMessage.repaint();
			
	}
	
	// Warnung! Meldung nach zu vielen Fehlern
	public static void meldung(String fehlerTyp) {
		String meldungString = "";
		
		
		if(fehlerTyp.equals("Eingabe")) {
			meldungString = 
					"Bitte geben Sie nur Ziffern ein und verwenden Sie bei der Eingabe eine der gültigen Schreibweisen."
							+ " Dafür sollen die eingegebenen Zahlen entweder mit:"
							+ "\n\n\",\"  (Komma)                  oder mit"
							+ "\n\" \"  (Lücke)                     oder mit"
							+ "\n\", \" (Komma + Lücke)    oder mit"
							+ "\n\"  \" (zwei Lücken)          getrennt werden."
							+ "\n\n\nMehr können Sie auf \"Hilfe > Hilfe bei der Eingabe\" finden";
							
			
		} 
		
		if(fehlerTyp.equals("Algorithmus")) {
			meldungString = "Bitte wählen Sie einen Algorithmus aus.";
		}
		

		JFrame meldung = new JFrame("Meldung");
		meldung.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		meldung.getContentPane().setBackground(Color.WHITE);
		meldung.setSize(500, 460);
		meldung.setVisible(true);
		meldung.setLocationRelativeTo(null);
		meldung.setResizable(false);
		meldung.getContentPane().setLayout(null);

		
		meldungMessage = new JTextArea(meldungString);
		meldungMessage.setFont(font);
		meldungMessage.setLineWrap(true);
		meldungMessage.setWrapStyleWord(true);
		meldungMessage.setEditable(false);
		meldungMessage.setBounds(55, 10, 385, 333);
		meldung.getContentPane().add(meldungMessage);
		meldungMessage.repaint();

		
		JButton confirmBtn = new JButton("OK");
		confirmBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		confirmBtn.setFont(new Font("Calibri", Font.PLAIN, 15));
		confirmBtn.setBackground(Color.LIGHT_GRAY);
		confirmBtn.setBounds(203, 367, 90, 29);
		meldung.getContentPane().add(confirmBtn);
		confirmBtn.setVisible(true);
		confirmBtn.revalidate();
		confirmBtn.repaint();
		confirmBtn.addActionListener(e -> {
			meldung.dispose();
		});
		
	
	}
	
}

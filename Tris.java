package Test;

import java.util.Scanner;

public class Tris {
	
	// Griglia di gioco
	private final char[][] griglia = new char[3][3];
	private char giocatoreCorrente;
	
	
	// Inizializza la griglia di gioco
	public Tris() {
		
		int posizione = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				griglia[i][j] = (char) ('0' + posizione);
				posizione++;
			}
		}
	}
	
	
	// Stampa la griglia di gioco
	public void stampaGriglia() {
		
		for (int i = 0; i < 3;i++) {
			for (int j = 0; j < 3; j++) {
				
				System.out.print(" " + griglia[i][j] + " ");
				if (j < 2) System.out.print("|");
			}
			
			System.out.println();
			if (i < 2) System.out.println("-----------");
		}
	}
	
	
	// Metodo per fare la mossa
	public boolean faiMossa(int numero) {
		
		// Calcolo la posizione nella griglia
		int riga = (numero - 1) / 3;
		int colonna = (numero - 1) % 3;
		
		// Verifica se la casella è vuota
		if (griglia[riga][colonna] != 'X' && griglia[riga][colonna] != 'O') {
			griglia[riga][colonna] = giocatoreCorrente;
			return true;
		} 
		return false;
	}
	
	
	// Alterna i turni dei giocatori
	public void alternaTurni() {
		
		giocatoreCorrente = (giocatoreCorrente == 'X') ? 'O' : 'X';
	}
	
	
	// Metodo per controllare se c'è un vincitore
	public boolean controlloVittoria() {
	    
	    // Controllo righe
	    if (griglia[0][0] == giocatoreCorrente && griglia[0][1] == giocatoreCorrente && griglia[0][2] == giocatoreCorrente) return true;
	    
	    if (griglia[1][0] == giocatoreCorrente && griglia[1][1] == giocatoreCorrente && griglia[1][2] == giocatoreCorrente) return true;
	    
	    if (griglia[2][0] == giocatoreCorrente && griglia[2][1] == giocatoreCorrente && griglia[2][2] == giocatoreCorrente) return true;

	    // Controllo colonne
	    if (griglia[0][0] == giocatoreCorrente && griglia[1][0] == giocatoreCorrente && griglia[2][0] == giocatoreCorrente) return true;
	    
	    if (griglia[0][1] == giocatoreCorrente && griglia[1][1] == giocatoreCorrente && griglia[2][1] == giocatoreCorrente) return true;
	    
	    if (griglia[0][2] == giocatoreCorrente && griglia[1][2] == giocatoreCorrente && griglia[2][2] == giocatoreCorrente) return true;

	    // Controllo diagonali
	    if (griglia[0][0] == giocatoreCorrente && griglia[1][1] == giocatoreCorrente && griglia[2][2] == giocatoreCorrente) return true;
	    
	    if (griglia[0][2] == giocatoreCorrente && griglia[1][1] == giocatoreCorrente && griglia[2][0] == giocatoreCorrente) return true;

	    return false;
	}
	
	
	// Pareggio!!
	public boolean controlloPareggio() {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (griglia[i][j] != 'X' && griglia[i][j] != 'O') {
					return false;
				}
			}
		} return true; // Tutte le caselle sono piene
	}
	
	
	// Fine dei giochi, chi sarà il vincitore???
	public boolean giocoFinito() {
		
		if (controlloVittoria()) {System.out.println("Giocatore " + giocatoreCorrente + " ha vinto!"); return true; }
		
		if (controlloPareggio()) {System.out.println("Pareggio!"); return true; }
		
		return false;
	}
	
	
	// Metodo per gestire il flusso di gioco,     3    2    1    VIA!!!!!
	public void gioca() {
		
		Scanner scanner = new Scanner(System.in);
		
		//  INTRODUZIONE
	    System.out.println("=======================================");
	    System.out.println("🎮 Benvenuto a TRIS (Tic-Tac-Toe)!");
	    System.out.println("Regole:");
	    System.out.println(" - Due giocatori si sfidano alternandosi.");
	    System.out.println(" - Il primo sceglie X o O.");
	    System.out.println(" - Vinci se allinei 3 simboli in riga, colonna o diagonale.");
	    System.out.println(" - Scegli le caselle da 1 a 9 come in un tastierino:");
	    System.out.println();
	    System.out.println(" 1 | 2 | 3 ");
	    System.out.println("-----------");
	    System.out.println(" 4 | 5 | 6 ");
	    System.out.println("-----------");
	    System.out.println(" 7 | 8 | 9 ");
	    System.out.println();
	    System.out.println("Buona fortuna! ✨");
	    System.out.println("=======================================");
		
	    // Scelta del simbolo
		System.out.print("Scegli il tuo simbolo: X oppure O ");
		String scelta = "";
		
		while (true) {
			scelta = scanner.nextLine().toUpperCase(); // Legge la scelta e la rende maiuscola
			if (scelta.equals("X") || scelta.equals("O")) {giocatoreCorrente = scelta.charAt(0); break; }
			else {System.out.print("Scelta non valida. inserisci X o O!");}
		}
		
		while (!giocoFinito()) {
			
			System.out.println("Giocatore " + giocatoreCorrente + " é il tuo turno!\n");
			
			stampaGriglia();

			int numero = -1;
			// Continua a chiedere l'input finchè non è valido
			while (numero < 1 || numero > 9) {

				System.out.println("Inserisci il numero della casella (1-9): ");
				
				// Verifica che l'input è corretto
				if (scanner.hasNextInt()) {numero = scanner.nextInt(); 
					// Verifica che il numero è nel range (1-9)
					if(numero < 1 || numero > 9) System.out.println("Numero non valido, NUMBER OUT OF RANGE."); }
				
				// L'inut non è un numero intero
				else {System.out.println("Inserisci un numero e non un carattere."); scanner.next(); } // Pulisce l'input errato e rinizia il ciclo.
			}
			
			// Ora l'input sono sicuro che l'input sarà un numero tra 1-9 e chiedo di proseguire con la mossa.
			// Contrllando che la casella non sia già occupata.
			if (!faiMossa(numero)) {System.out.println("Casella già occupata, riprova. "); continue; } // Continue per non cambiare giocatore
			
			if (controlloVittoria()) {
				stampaGriglia(); 
				System.out.println();
				System.out.println("╔════════════════════════════╗");
				System.out.println("║  HA VINTO IL GIOCATORE " + giocatoreCorrente + "!  ║");
				System.out.println("╚════════════════════════════╝");
				System.out.println();

				break; 
				}
			
			if (controlloPareggio()) {stampaGriglia(); System.out.println("Pareggio!"); break; }
			
			// tocca all'altro giocatore
			alternaTurni(); 
		}
		
		stampaGriglia();
		scanner.close();
	}
}

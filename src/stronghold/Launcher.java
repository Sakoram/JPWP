package stronghold;
/**
 * Klasa odpalająca cały program.
 * Znajduje się w niej main.
 * @author a
 *
 */
public class Launcher {
/**
 * Main. Tworzy obiekt klasy Game.
 * @param args argumenty startowe, nie używane w tym programie.
 */
	public static void main(String[] args) {
		Game game = new Game("tfierdza", 1280, 1000);
		game.start();
	}

}

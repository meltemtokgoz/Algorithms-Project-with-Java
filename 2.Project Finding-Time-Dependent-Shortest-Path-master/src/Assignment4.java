import java.io.IOException;

public class Assignment4 {

	public static void main(String[] args) throws IOException {
		String network = args[0];
		String distances = args[1];
		String commands = args[2];
		String changeTime = args[3];
		int timeChange = Integer.parseInt(changeTime);

		readInputFile.readIn(network, distances, commands, timeChange);
	}
}

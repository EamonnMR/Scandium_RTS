package game;

import java.util.List;

public abstract class Reciever {
	abstract List<commands.Command>getLatestCommands();
}

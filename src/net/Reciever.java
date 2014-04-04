package net;

import java.util.List;

/**
 * This is where commands come from from the client's point of view.
 * Each tick, commands are loaded from here to the model.
 * @author Eamonn
 *
 */
public interface Reciever {
	public abstract List<commands.Command>getLatestCommands();
}

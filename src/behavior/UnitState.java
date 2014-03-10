package behavior;

public abstract class UnitState {
	public abstract UnitState update(game.Model m, game.Unit u);
	public abstract void updateInterm(game.Model m, game.Unit u, int dt);
}

package turn_based_game;

import entities.Combatant;
import entities.Player;
import items.Item;
import java.util.List;

public class UseItem extends Action {

    private Item itemToUse;
    private List<Combatant> aliveEnemies;

    public UseItem(Item itemToUse) {
        super("Use Item");
        this.itemToUse = itemToUse;
    }

    public void setAliveEnemies(List<Combatant> aliveEnemies) {
        this.aliveEnemies = aliveEnemies;
    }

    @Override
    public void execute(Combatant actor, Combatant target) {
        if (actor instanceof Player) {
            Player player = (Player) actor;

            if (itemToUse != null) {
                List<Combatant> enemies = (aliveEnemies != null) ? aliveEnemies : java.util.Collections.singletonList(target);
                itemToUse.use(player, enemies);
                System.out.println(player.getName() + " used " + itemToUse.getName() + "!");
                // remove the used item
                player.getInventory().remove(itemToUse);

            } else {
                System.out.println("No item selected to use!");
            }
        } else {
            System.out.println("Enemies cannot use items.");
        }
    }
}

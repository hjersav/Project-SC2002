package turn_based_game;

public class UseItem extends Action {
    
    private Item itemToUse;

    public UseItem(Item itemToUse) {
        super("Use Item");
        this.itemToUse = itemToUse;
    }

    @Override
    public void execute(Combatant actor, Combatant target) {
        if (actor instanceof Player) {
            Player player = (Player) actor;
            
            if (itemToUse != null) {
                //abstract Item class needs a method like 'use(Combatant user, Combatant target)'.
         
                itemToUse.use(player, target);
                
                System.out.println(player.getName() + " used a " + itemToUse.getName() + "!");
                
                // need to implement a 'removeItem(Item item)' method in the Player class to update their inventory.
                player.removeItem(itemToUse);
                
            } else {
                System.out.println("No item selected to use!");
            }
        } else {
            System.out.println("Enemies cannot use items.");
        }
    }
}
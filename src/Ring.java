public class Ring extends Accessories{
    private boolean equip;
    /**
     * create new accessory with Ring class
     * side effects: initialize the Ring and set equip to false
     */
    Ring(){
        equip = false;
    }
    /**
     * When your character wear ring , it will buff the stats of your character
     * @param c
     * requires: the character that you want to buff stats by wearing ring
     * side effects: When your character wear ring , the stats such as mana, damage and run-speed will increase.
     */
    public void buffStats(Character c){
        c.setMaxMana(c.getBaseMaxMana() + (5 * c.getLevel()));
        c.setDamage((int) Math.floor(c.getDamage() + (0.45 * c.getLevel())));
        c.setRunSpd(c.getRunSpd() + (0.01 * c.getLevel()));
    }

    public boolean isEquip(){
        return equip;
    }
    public void setEquip(boolean eq){
        equip = eq;
    }
}

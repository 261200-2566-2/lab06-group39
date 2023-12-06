public class Charm extends Accessories{
    private boolean equip;
    /**
     * create new accessory with Charm class
     * side effects: initialize the Charm and set equip to false
     */
    Charm(){
        equip = false;
    }

    /**
     * When your character wear charm , it will buff the stats of your character
     * @param c
     * requires: the character that you want to buff stats by wearing charm
     * side effects: When your character wear charm , the stats such as mana, defense and hp will increase.
     */
    public void buffStats(Character c){
        c.setMaxMana(c.getMaxMana() + (3 * c.getLevel()));
        c.setMana(c.getMana() == c.getBaseMaxMana() ? c.getMaxMana() : c.getMana());
        c.setDefense((int) Math.floor(c.getDefense() + (0.2 * c.getLevel())));
        c.setMaxHp(c.getMaxHp() + (5 * c.getLevel()));
        c.setHp(c.getHp() == c.getBaseMaxHp() ? c.getMaxHp() : c.getHp());
    }

    public boolean isEquip(){
        return equip;
    }
    public void setEquip(boolean eq){
        equip = eq;
    }
}

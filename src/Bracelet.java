public class Bracelet extends Accessories{
    private boolean equip;

    /**
     * create new accessory with Bracelet class
     * side effects: initialize the Bracelet and set equip to false
     */
    Bracelet(){
        equip = false;
    }

    /**
     * When your character wear bracelet , it will buff the stats of your character
     * @param c
     * requires: the character that you want to buff stats by wearing Bracelet
     * side effects : When your character wear Bracelet, the stats such as damage, defense and hp will increase.
     */
    public void buffStats(Character c){
        c.setDamage((int) Math.floor(c.getDamage() + (0.5 * (c.getLevel()))));
        c.setDefense((int) Math.floor(c.getDefense() + (0.4 * c.getLevel())));
        c.setMaxHp(c.getMaxHp() + (6 * c.getLevel()));
        c.setHp(c.getHp() == c.getBaseMaxHp() ? c.getMaxHp() : c.getHp());
    }

    public boolean isEquip(){
        return equip;
    }
    public void setEquip(boolean eq){
        equip = eq;
    }
}

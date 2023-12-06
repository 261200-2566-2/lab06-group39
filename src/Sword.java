public class Sword extends Equipment{
    /**
     * create new equipment with Sword class
     * @param baseDmg
     * @param baseDef
     * requires: the base damage and defense of sword
     * side effect: initialize the equipment with the correct stats of sword class
     */
    Sword(int baseDmg, int baseDef){
        type = "sword";
        level = 1;
        this.baseDmg = baseDmg;
        this.baseDef = baseDef;
        equip = false;
    }
}

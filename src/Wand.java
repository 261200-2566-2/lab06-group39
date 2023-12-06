public class Wand extends Equipment{
    /**
     * create new equipment with Wand class
     * @param baseDmg
     * @param baseDef
     * requires: the base damage and defense of Wand
     * side effect: initialize the equipment with the correct stats of wand class
     */
    Wand (int baseDmg, int baseDef){
        type = "wand";
        level = 1;
        this.baseDmg = baseDmg;
        this.baseDef = baseDef;
        equip = false;
    }
}

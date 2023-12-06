public class Shield extends Equipment{
    /**
     * create new equipment with Shield class
     * @param baseDmg
     * @param baseDef
     * requires: the base damage and defense of Shield
     * side effect: initialize the equipment with the correct stats of shield class
     */
    Shield(int baseDmg, int baseDef){
        type = "shield";
        level = 1;
        this.baseDef = baseDef;
        this.baseDmg = baseDmg;
        equip = false;
    }

}

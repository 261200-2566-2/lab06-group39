public abstract class Character{
    protected String name;
    protected String classes;
    protected boolean alive;
    protected int level;
    protected int baseMaxHp;
    protected int maxHp;
    protected int hp;
    protected int baseMaxMana;
    protected int maxMana;
    protected int mana;
    protected int baseDmg;
    protected int damage;
    protected int baseDef;
    protected int defense;
    protected double baseRunSpd;
    protected double runSpd;
    protected double baseDmgLvlUp;
    protected double swordDmgLvlUp;
    protected double shieldDmgLvlUp;
    protected double wandDmgLvlUp;
    protected double baseDefLvlUp;
    protected double swordDefLvlUp;
    protected double shieldDefLvlUp;
    protected double wandDefLvlUp;
    protected double baseSpdLvlUp;
    protected double swordSpdDec;
    protected double shieldSpdDec;
    protected double wandSpdDec;
    protected Equipment equipment;
    protected Sword sword;
    protected Shield shield;
    protected Wand wand;
    protected Bracelet bracelet;
    protected Charm charm;
    protected Ring ring;

    /**
     * set the damage that the character does by calculating via formula
     * @param baseBonus
     * @param equipBonus
     * requires: damage bonus when leveling up of both the character and the current equipment
     * side effect: set the damage that the character should do to the correct value
     */
    protected void setDmg(double baseBonus, double equipBonus){
        baseDmg = (int) Math.floor(baseDmg + (baseBonus * level));
        equipment.setDmg((int) Math.floor(equipment.getBaseDmg() + (equipBonus * equipment.getLevel())));
        if (equipment.isEquip()) damage = baseDmg + equipment.getDmg();
        else damage = baseDmg;
    }

    /**
     * set the defense of the character by calculating via formula
     * @param baseBonus
     * @param equipBonus
     * requires: defense bonus when leveling up of both the character and the current equipment
     * side effect: set the defense that the character should have to the correct value
     */
    protected void setDef(double baseBonus, double equipBonus){
        baseDef = (int) Math.floor(baseDef + (baseBonus * level));
        equipment.setDef((int) Math.floor(equipment.getBaseDef() + (equipBonus * equipment.getLevel())));
        if (equipment.isEquip()) defense = baseDef + equipment.getDef();
        else defense = baseDef;
    }

    /**
     * set the movement speed of the character by calculating via formula
     * @param baseBonus
     * @param equipDec
     * requires: speed bonus when leveling up of both the character and the current equipment
     * side effect: set the speed that the character should have to the correct value
     */
    protected void setSpd(double baseBonus, double equipDec){
        runSpd = baseRunSpd + (baseBonus * (level - 1)); //0.03
        int spdDecFac = equipment.getLevel() - level;
        equipment.setSpdDec(equipDec * (spdDecFac + 4));

        if (equipment.isEquip()) {
            runSpd -= equipment.getSpdDec() > 0 ? equipment.getSpdDec() : 0;
        }

        if (runSpd < 0.1) runSpd = 0.1;
    }

    /**
     * set the maxHp of the character and decrease the hp if it is more than maxHp
     * side effect: set the maxHp of the character with no hp related buff and set hp to maxHp if hp is more than maxHp
     */
    protected void resetHp(){
        maxHp = baseMaxHp;
        if (hp > maxHp) hp = maxHp;
    }

    /**
     * set the maxMana of the character and decrease the mana if it is more than maxMana
     * side effect: set the maxMana of the character with no mana related buff and set mana to maxMana if mana is more than maxMana
     */
    protected void resetMana(){
        maxMana = baseMaxMana;
        if (mana > maxMana) mana = maxMana;
    }

    /**
     * set hp, damage and defense of the character
     * side effects: check the equipment of the character and set hp, damage and defense of the character correctly
     */
    protected void setStats(){
        double eqDmg = equipment.getType().equals("sword") ? swordDmgLvlUp : equipment.getType().equals("shield") ? shieldDmgLvlUp : wandDmgLvlUp;
        double eqDef = equipment.getType().equals("sword") ? swordDefLvlUp : equipment.getType().equals("shield") ? shieldDefLvlUp : wandDefLvlUp;
        double eqSpd = equipment.getType().equals("sword") ? swordSpdDec : equipment.getType().equals("shield") ? shieldSpdDec : wandSpdDec;

        //set stats from equipment & accessories
        setDmg(baseDmgLvlUp, eqDmg);
        setDef(baseDefLvlUp, eqDef);
        setSpd(baseSpdLvlUp, eqSpd);
        resetHp();
        resetMana();

        if (bracelet.isEquip()) bracelet.buffStats(this);
        if (charm.isEquip()) charm.buffStats(this);
        if (ring.isEquip()) ring.buffStats(this);
    }

    /**
     * print that statuses of the character
     * side effect: print out the fields of the character that show statuses
     */
    public void showStats(){
        System.out.println("Name: " + name);
        System.out.println("Class: " + (classes.equals("knight") ? "Knight" : classes.equals("mage") ? "Mage" : "Tank"));
        System.out.println("Lvl: " + level);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("Mana: " + mana + "/" + maxMana);
        System.out.println("Speed: " + String.format("%.2f", runSpd));
        System.out.println("Damage:" + damage);
        System.out.println("Defense: " + defense);
        String eqLvl = equipment.isEquip() ? "(Lvl " + equipment.getLevel() + ")" : "";
        if (equipment.getType().equals("sword")) System.out.println("Sword Equipped" + eqLvl + ": " + equipment.isEquip());
        else if (equipment.getType().equals("shield")) System.out.println("Shield Equipped" + eqLvl + ": "+ equipment.isEquip());
        else if (equipment.getType().equals("wand")) System.out.println("Wand Equipped" + eqLvl + ": "+ equipment.isEquip());

        System.out.println("========================");
    }

    /**
     * level up the character by lvl
     * @param lvl
     */
    public abstract void levelUp(int lvl);

    /**
     * level up the current equipment of character by lvl
     * @param lvl
     * requires: level to level up by
     * side effect: change the level of the equipment and change character statuses
     */
    public void eqLvlUp(int lvl){
        equipment.setLevel(equipment.getLevel() + lvl);

        setStats();
    }

    /**
     * make character equip or de-equip the equipment that the player chooses
     * @param eq
     * @param type
     * requires: boolean whether you want to equip or de-equip and type of the equipment
     * side effects: character will equip or de-equip the equipment of the type that the player wants
     */
    public void equipWeapon(boolean eq, String type){
        boolean sameType = equipment.getType().equals(type);
        String sType = equipment.getType().equals("sword") ? "Sword" : equipment.getType().equals("shield") ? "Shield" : "Wand";
        Equipment eType = type.equals("sword") ? sword : type.equals("shield") ? shield : wand;
        if (!sameType && !eq){
            System.out.println(sType + " is not equipped");
            return;
        }

        if (!sameType && eq){
            equipment.setEquip(false);
            equipment = eType;
            equipment.setEquip(true);
        }
        else{
            equipment.setEquip(eq);
        }

        setStats();
    }

    /**
     * make the character equip or de-equip the accessory that the player chooses
     * @param eq
     * @param type
     * requires: boolean whether you want to equip or de-equip and type of the accessory
     * side effects: character will equip or de-equip the accessory of the type that the player wants
     */
    public void equipAccessory(boolean eq, String type){
        if (type.equals("bracelet")){
            bracelet.setEquip(eq);
        }
        else if (type.equals("charm")){
            charm.setEquip(eq);
        }
        else if (type.equals("ring")){
            ring.setEquip(eq);
        }

        setStats();
    }

    /**
     * attack the character that the player chooses
     * @param c
     * requires: character that the player want to attack
     * side effect: target character hp decreases via damage-done formula
     */
    public void attack(Character c){
        if (!alive){
            System.out.println(name + " is Ded");
            return;
        }
        if (!c.getAlive()){
            System.out.println(c.getName() + " is already Ded...");
            return;
        }

        int dmgDone = damage - c.getDefense();
        if (dmgDone < 0) dmgDone = 0;

        if (c.getHp() <= dmgDone){
            c.setHp(0);
            c.setAlive(false);
            System.out.println("Eliminated " + c.getName() + " out of Existence.");
        }
        else{
            c.setHp(c.getHp() - dmgDone);
            System.out.println(name + " Deal " + dmgDone + " to " + c.getName() + ", " + c.getName() + " has " + c.getHp() + " health remaining.");
        }
    }

    public String getName() {
        return name;
    }

    public int getHp(){
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }
    public int getBaseMaxHp() {
        return baseMaxHp;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public boolean getAlive(){
        return alive;
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public double getRunSpd() {
        return runSpd;
    }
    public void setRunSpd(double runSpd) {
        this.runSpd = runSpd;
    }

    public int getLevel() {
        return level;
    }

    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getBaseMaxMana() {
        return baseMaxMana;
    }
    public int getMaxMana() {
        return maxMana;
    }
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

}

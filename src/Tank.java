public class Tank extends Character{

    /**
     * create new character with tank class
     * @param name
     * requires: name of the character
     * side effects: initialize the character with the correct stats of tank class
     */
    Tank (String name) {
        this.name = new String(name);
        classes = "tank";
        alive = true;
        level = 1;
        baseMaxHp = 180 + (15 * level);
        maxHp = baseMaxHp;
        hp = maxHp;
        baseMaxMana = 40 + (3 * level);
        maxMana = baseMaxMana;
        mana = maxMana;
        baseDmg = 1;
        damage = baseDmg;
        baseDef = 8;
        defense = baseDef;
        baseRunSpd = 0.6;
        baseDmgLvlUp = 0.2;
        swordDmgLvlUp = 0.4;
        shieldDmgLvlUp = 0.35;
        wandDmgLvlUp = 0.25;
        baseDefLvlUp = 0.8;
        swordDefLvlUp = 0.5;
        shieldDefLvlUp = 1.6;
        baseSpdLvlUp = 0.02;
        swordSpdDec = 0.03;
        shieldSpdDec = 0.035;
        wandSpdDec = 0.01;

        sword = new Sword(4, 4);
        shield = new Shield(2, 10);
        wand = new Wand(3, 1);
        bracelet = new Bracelet();
        charm = new Charm();
        ring = new Ring();

        equipment = shield;

        setStats();
    }

    /**
     * level up the tank character by lvl
     * @param lvl
     * side effects: level up the character and set the stats of the character to match the level
     */
    @Override
    public void levelUp(int lvl){
        level += lvl;

        baseMaxHp = 180 + (15 * level);
        maxHp = baseMaxHp;
        hp = maxHp;
        baseMaxMana = 40 + (3 * level);
        maxMana = baseMaxMana;
        mana = maxMana;

        setStats();
    }

    /**
     * use the character specific skill of tank class
     * side effects: use 10 mana, then buff the defense of itself
     */
    public void Skill(){
        if(alive){
            if(mana>=10){
                System.out.println(name+" Use Protect");
                defense += 4;
                mana -= 10;
            }
            else System.out.println("Your mana is low");
        }
        else System.out.println(name + " is dead.");
    }
}

import java.util.Random;
public class Mage extends Character{
    Random random = new Random();

    /**
     * create new character with mage class
     * @param name
     * requires: name of the character
     * side effects: initialize the character with the correct stats of mage class
     */
    Mage(String name){
        this.name = new String(name);
        classes = "mage";
        alive = true;
        level = 1;
        baseMaxHp = 70 + (6 * level);
        maxHp = baseMaxHp;
        hp = maxHp;
        baseMaxMana = 120 + (8 * level);
        maxMana = baseMaxMana;
        mana = maxMana;
        baseDmg = 4;
        damage = baseDmg;
        baseDef = 0;
        defense = baseDef;
        baseRunSpd = 0.9;
        baseDmgLvlUp = 0.8;
        swordDmgLvlUp = 0.3;
        shieldDmgLvlUp = 0.1;
        wandDmgLvlUp = 1.8;
        baseDefLvlUp = 0.15;
        swordDefLvlUp = 0.2;
        shieldDefLvlUp = 0.3;
        baseSpdLvlUp = 0.03;
        swordSpdDec = 0.05;
        shieldSpdDec = 0.12;
        wandSpdDec = 0.02;

        sword = new Sword(3, 2);
        shield = new Shield(2, 4);
        wand = new Wand(10, 2);
        bracelet = new Bracelet();
        charm = new Charm();
        ring = new Ring();

        equipment = wand;

        setStats();
    }

    /**
     * use the character specific skill of mage class
     * side effects: use 15 mana, then have a chance of 1/3 to buff the damage, hp and defense of itself
     */
    public void Skill(){
        if(alive){
            if(mana>=15){
                mana -= 15;
                System.out.println(name + " Use Gamble");
                int Gambling = random.nextInt(3) + 1;
                if(Gambling==3){
                    System.out.println(name + " Got Jackpot!!!");
                    damage *= 2;
                    hp += 10;
                    if(hp>maxHp) hp=maxHp;
                    defense += 2;
                }
            }
            else System.out.println("Your mana is low");
        }
        else{
            System.out.println(name + " is dead.");
        }

    }

    /**
     * level up the mage character by lvl
     * @param lvl
     * side effects: level up the character and set the stats of the character to match the level
     */
    @Override
    public void levelUp(int lvl){
        level += lvl;

        baseMaxHp = 70 + (6 * level);
        maxHp = baseMaxHp;
        hp = maxHp;
        baseMaxMana = 120 + (8 * level);
        maxMana = baseMaxMana;
        mana = maxMana;

        setStats();
    }


}

//
//
//
public class Tower
{
  private String name;

  private int level = 1;

  private int damage = 1;
  private int abilityDamage = 0;
  private int stickyness = 0;
  private int range = 1;
  private int pierce = 0;
  private int stunChance = 0;
  //private int pierceUsed = 0;
  private int cooldown = 0;
  private int cooldownUsed = 0;

  private Coordinate position;

  public Tower(String type, int x, int y, Map playSpace)
  {
    name = type;
    position = new Coordinate(x, y);
    if(name.equals("Archer"))
    {
      damage = level;
      range = level + 1;
      level = 1;
    }
    if(name.equals("Boom"))
    {
      damage = 1;
      range = 1;
      level = 1;
    }
    if(name.equals("Leach"))
    {
      damage = 0;
      range = 1;
      level = 1;
    }
    if(name.equals("Team"))
    {
      damage = 2;
      range = 1;
      level = 1;
    }
    if(name.equals("Sticky"))
    {
      damage = 0;
      range = 1;
      level = 1;
      stickyness = 1;
    }
    if(name.equals("Destroyer"))
    {
      damage = 2;
      range = 0;
      level = 1;
      pierce = 3;
    }
    if(name.equals("Tazer"))
    {
      damage = 0;
      range = 0;
      level = 1;
      pierce = 1;
      stunChance = 2;
    }
    if(name.equals("Guardian"))
    {
      damage = 2;
      range = 1;
      level = 1;
      pierce = 1;
      stunChance = 0;
      cooldown = 0;
      cooldownUsed = 0;
      abilityDamage = 5;
    }
    playSpace.buildTower(this);    
  }
  //--------------
  public Tower(int x, int y, Map playSpace, Tower[] array)
  {
    name = "Team";
    position = new Coordinate(x, y);
    damage = 2;
    range = 1;
    level = 1;
    playSpace.buildTeamTower(this, array);
  }
  //----------------
  public Tower()
  {
    damage = 0;
    range = 0;
    level = 0;
    position = new Coordinate(0,0);
    name = "not a tower yet";
  }
  //-------------------------------------------------------
  //returns te tower's name
  //-------------------------------------------------------
  public String getName()
  {
    return name;
  }

  //-----------------------------------------------------
  //returns the position of the object
  //-------------------------------------------------------
  public Coordinate getPosition()
  {
    return position;
  }

  //----------------------------------------------------
  //returns the range of the tower
  //-----------------------------------------------------
  public int getRange()
  {
    return range;
  }
  public int getAbilityDamage()
  {
    return abilityDamage;
  }
  public void setAbilityDamage(int abil)
  {
    abilityDamage = abil;
  }
  public int getCoolDown()
  {
    return cooldown;
  }
  public int getCoolDownUsed()
  {
    return cooldownUsed;
  }
  public void setCoolDown(int cooly)
  {
    cooldown = cooly;
  }
  public void timePass()
  {
    cooldownUsed++;
  }
  public void resetCoolDown()
  {
    cooldownUsed = 0;
  }
  public int getStickyness()
  {
    return stickyness;
  }
  public void setStickyness(int newstick)
  {
    stickyness = newstick;
  }
  public void setPierce(int newPierce)
  {
    pierce = newPierce;
  }
  public int getPierce()
  {
    return pierce;
  }
  public void setStunChance(int newStun)
  {
    stunChance = newStun;
  }
  public int getStunChance()
  {
    return stunChance;
  }
  //--------------------------------------------
  //sets range
  //-------------------------------------------------
  public void setRange(int newRange)
  {
    range = newRange;
  }
  
  //----------------------------------------------------
  //returns the damage of the tower
  //-----------------------------------------------------
  public int getDamage()
  {
    return damage;
  }

  //=================================
  //sets damage
  //----------------------------------------
  public void setDamage(int newDamage)
  {
    damage = newDamage;
  }
  //---------------------------------------------------------
  //attacks it's full range
  //-------------------------------------------------------
  public int getLevel()
  {
    return level;
  }

  public void setLevel(int newLevel)
  {
    level = newLevel;
  }

  public static void towerDescription(String towerType)
  {
    if (towerType.equals("Archer"))
    {
      System.out.println("The Archer Tower is known for its impressive range and");
      System.out.println("high single target damage.");
      System.out.println("As far as which enemy the tower decides to attack when");
      System.out.println("there are multiple enemies in it's range, it's not all \nthat clear exactly.");
      System.out.println("Here are the specific stats:");
      System.out.println("costs 300 to build, 200 to upgrade both times.");
      System.out.println("Level 1: 1 damage, 2 range, targets 1 enemy per attck");
      System.out.println("Level 2: 2 damage, 3 range, targets 1 enemy per attck");
      System.out.println("Level 3: 3 damage, 4 range, targets 1 enemy per attck");

    }
    else if (towerType.equals("Boom"))
    {
      System.out.println("The Boom Tower is known for its ability to make use");
      System.out.println("of its small range to deal BIG damage to groups of enemies.");
      System.out.println("Here are the specific stats:");
      System.out.println("costs 500 to build.");
      System.out.println("costs 400 to upgrade 1st time, costs 600 2nd time.");
      System.out.println("Level 1: 1 damage, 1 range, attacks all enemies in range");
      System.out.println("Level 2: 2 damage, 1 range, attacks all enemies in range");
      System.out.println("Level 3: 3 damage, 2 range, attacks all enemies in range");
    }
    else if (towerType.equals("Leach"))
    {
      System.out.println("The Leach Tower is not like the other girls.");
      System.out.println("Unlike other towers, when an enemy is within it's range,");
      System.out.println("it doesn't attack them in any way.");
      System.out.println("Instead, this tower makes you extra cash for each enemy in its range.");
      System.out.println("Here are the specific stats:");
      System.out.println("costs 500 to build.");
      System.out.println("costs 500 to upgrade 1st time, costs 700 2nd time.");
      System.out.println("Level 1: 0 damage, 1 range, earns 3 coins per enemy in range");
      System.out.println("Level 2: 0 damage, 1 range, earns 5 coins per enemy in range");
      System.out.println("Level 3: 0 damage, 2 range, earns 5 coins per enemy in range");
    }
    else if (towerType.equals("Team"))
    {
      System.out.println("The Team Tower is unique in how it is upgraded.");
      System.out.println("A single Team tower is rather weak, but when you place one down,");
      System.out.println("it upgrades all other Team Towers it boarders.");
      System.out.println("Team Towers can't be upgraded any other way.");
      System.out.println("Here are the specific stats:");
      System.out.println("costs 400 to build.");
      System.out.println("Level 1: 2 damage, 1 range, attacks up to 1 enemy ");
      System.out.println("Level 2: 3 damage, 1 range, attacks up to 1 enemy");
      System.out.println("Level 3: 4 damage, 2 range, attacks up to 1 enemy");
      System.out.println("Level 4: 5 damage, 3 range, attacks up to 1 enemy");
    }
    else if (towerType.equals("Sticky"))
    {
      System.out.println("The Sticky Tower may not seem that good at first, but it's an essential");
      System.out.println("part of a late gam defense.");
      System.out.println("Sticky Towers are the arch nemesis of the snowmen.");
      System.out.println("While they have no effect on spiders, the Sticky Towers slow down the Snowmen.");
      System.out.println("This can give your other towers time to attack them so they don't sprint past");
      System.out.println("Here are the specific stats:");
      System.out.println("costs 400 to build.");
      System.out.println("costs 300 to upgrade both times.");
      System.out.println("Level 1: 0 damage, 1 range, reduces speed of all snowmen in range by 1.");
      System.out.println("Level 2: 0 damage, 1 range, reduces speed of all snowmen in range by 3.");
      System.out.println("Level 3: 1 damage, 2 range, reduces speed of all snowmen in range by 4.");
    }
    else if (towerType.equals("Destroyer"))
    {
      System.out.println("The Destroyer is the king of group damage.");
      System.out.println("Destroyer towers attatck several enemies to the right and left of them.");
      System.out.println("They only attack in a straight line, not up or down.");
      System.out.println("However, they can shoot accross the entire track to the left or right of them.");
      System.out.println("This is perfect for clearing out close groups of enemies.");
      System.out.println("Here are the specific stats:");
      System.out.println("costs 1000 to build.");
      System.out.println("costs 1000 to upgrade 1st time, costs 2000 2nd time.");
      System.out.println("Level 1: 2 damage, infinite range(left/right), attacks up to 3 enemies.");
      System.out.println("Level 1: 4 damage, infinite range(left/right), attacks up to 4 enemies.");
      System.out.println("Level 3: 6 damage, infinite range(left/right), attacks up to 5 enemies.");
    }
    else if (towerType.equals("Tazer"))
    {
      System.out.println("The Tazer Tower is the Sticky Tower's cooler brother.");
      System.out.println("With unlimited range, this tower provides much needed support across the map.");
      System.out.println("It finds the highest level enemy and has a chance to stun it, immobolizing it for a short time.");
      System.out.println("The tower does no damage itself, but can help your other towers do much more damage.");
      System.out.println("Here are the specific stats:");
      System.out.println("costs 800 to build.");
      System.out.println("costs 700 to upgrade both times.");
      System.out.println("Level 1: 0 damage, infinite range, 30% stun chance on highest level enemy.");
      System.out.println("Level 1: 0 damage, infinite range, 50% stun chance on highest level enemy.");
      System.out.println("Level 3: 0 damage, infinite range, 70% stun chance on highest level enemy.");
    }
    else if (towerType.equals("Guardian"))
    {
      System.out.println("The Guardian Tower is most well known for it's powerful special ability.");
      System.out.println("Starting at level 2, the Guardian Tower has an ability you can activiate.");
      System.out.println("It take a few seconds for it to charge, but once it does, you can change the tide of battle.");
      System.out.println("When the ability is ready, you will get to choose a spot on the map to be attacked.");
      System.out.println("The tower will launch a powerful attack damaging all enemies in a 3x3 square.");
      System.out.println("In addition to it's ability, the tower can also do some group damage locally.");
      System.out.println("Here are specific stats:");
      System.out.println("costs 700 to build");
      System.out.println("costs 1000 to upgrade first time");
      System.out.println("costs 2500 to upgrade second time");
      System.out.println("Level 1: 2 damage, 1 range, attacks up to 1 enemy");
      System.out.println("Level 2: 3 damage, 1 range, attacks up to 1 enemy");
      System.out.println("LEVEL 2 ABILITY: 5 damage in 3x3 square you select, 20 second recharge");
      System.out.println("Level 3: 3 damage, 1 range, attacks all enemies in range");
      System.out.println("LEVEL 3 ABILITY: 10 damage in 3x3 square you select, 10 second recharge");
    }
  }
}

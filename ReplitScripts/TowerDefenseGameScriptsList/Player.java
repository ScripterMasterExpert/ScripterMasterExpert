public class Player
{
  private int money = 1000;
  private int lives = 100;
  private int income = 200;
  private boolean alive = true;
  public Player()
  {
    money = 1000;
    lives = 100;
    alive = true;
  }

  //-------------------------------------------
  //Buys a tower of a given name
  //--------------------------------------------
  public void buyTower(String towerName)
  {
    if(towerName.equals("Archer"))
    {
      money -= 300;
      System.out.println("You bought an Archer Tower for 300 coins.");
    }
  }

  //------------------------------------------------------
  //Prints rules
  //------------------------------------------------------
  public void rules()
  {
    System.out.println("The following are the rules:");
    System.out.println("This is a tower-defense style game which is entirely text based.");
    System.out.println("The goal of the game is to survive wave after wave of enemies as they march through the map.");
    System.out.println("To survive, you can build defenseive towers which will attack the enemies before they reach you.");
    System.out.println("Towers can be built in any spot that looks like this:");
    System.out.println("[-------]");
    System.out.println("[-------]");
    System.out.println("[-------]");
    System.out.println("Towers can attack the enemies in a variety of ways, and deciding where and what towers you should build");
    System.out.println("is the strategy you must develop.");
    System.out.println("Building and upgrading towers costs money.");
    System.out.println("Every round you get coins");
    System.out.println("Enemies will follow the path, walking at a pace of 1 spot per time unit.");
    System.out.println("If an enemy makes it all the way to the end, it will damage you.");
    System.out.println("If your health reaches 0, you will lose.");
    System.out.println("After round 10, you will enter freeplay.");
    System.out.println("At this point the difficulty of the rounds will start to ramp up significantly.");
    System.out.println("How far can you make it?");

  }

  public void guide()
  {
    System.out.println("In this game, there are 2 types of enemies to keep an eye out for.");
    System.out.println();
    System.out.println("The first type is the spider: 🕷");
    System.out.println("Spiders can have a lot of health, especially in later rounds, but they move real slow.");
    System.out.println("No matter how high of a level a spider is, it will always move one square at a time.");
    System.out.println();
    System.out.println("The second type is the snowman: ☃");
    System.out.println("Now, snowman are different.");
    System.out.println("depending on how high of a level the snowman is, it will move much faster than a spider.");
    System.out.println("The amount of squares a snowman moves through at a time is double it's health.");
    System.out.println("This means that snowmans may be fast, but they're really weak.");
    System.out.println("If you can damage the snowman a little bit before it runs through your defenses, then you can");
    System.out.println("kill it with ease.");
    System.out.println();
  }
  //---------------------------------------------------------
  //returns lives
  //---------------------------------------------------------
  public int getLives()
  {
    return lives;
  }

  //---------------------------------------------------------
  //returns lives
  //---------------------------------------------------------
  public int getMoney()
  {
    return money;
  }

  //---------------------------------------------------------
  //returns income
  //----------------------------------
  public int getIncome()
  {
    return income;
  }

  //---------------------------------------------------------
  //sets income
  //----------------------------------
  public void setIncome(int newIncome)
  {
    income = newIncome;
  }

  //--------------------------------------------------------
  //set money value to something new
  //------------------------------------------------------------
  public void setMoney(int newMoney)
  {
    money = newMoney;
  }
  //take damage, lower health. if lives is reduced to 0, the lose the game
  public void takeDamage()
  {
    lives -= 10;
    if (lives <= 0)
    {
      loseGame();
    }
  }

  //End the game because you lost
  private void loseGame()
  {
    alive = false;
  }

  public void reviePlayer()
  {
    alive = true;
  }

  public boolean isAlive()
  {
    return alive;
  }
}

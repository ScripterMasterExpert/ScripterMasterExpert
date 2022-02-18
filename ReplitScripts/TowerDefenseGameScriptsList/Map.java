import java.util.Scanner;
public class Map
{
  int[][] playSpace;//carries the program.

  boolean shouldAttackAgain;
  boolean usingAbility = false;

  final int Spawn = 50;
  final int Goal_Zone = 100;
  final int Real_Estate = 0;
  final int Boarder = 1;

  final int Walk_Way_Straight = 2;
  final int Walk_Way_Down = 3;
  final int Walk_Way_Corner_Right = 4;
  final int Walk_Way_Corner_Left = 5; 
  final int Walk_Way_Corner_Right2 = 6;
  final int Walk_Way_Corner_Left2 = 7; 

  //enemy occupied areas are the same as non-occupied areas +10
  final int Enemy_Occupied_Walk_Way_Straight = 12;
  final int Enemy_Occupied_Walk_Way_Down = 13;
  final int Enemy_Occupied_Walk_Way_Corner_Right = 14;
  final int Enemy_Occupied_Walk_Way_Corner_Left = 15;
  final int Enemy_Occupied_Walk_Way_Corner_Right2 = 16;
  final int Enemy_Occupied_Walk_Way_Corner_Left2 = 17;
  //super enemy occupied
  final int Super_Enemy_Occupied_Walk_Way_Straight = 112;
  final int Super_Enemy_Occupied_Walk_Way_Down = 113;
  final int Super_Enemy_Occupied_Walk_Way_Corner_Right = 114;
  final int Super_Enemy_Occupied_Walk_Way_Corner_Left = 115;
  final int Super_Enemy_Occupied_Walk_Way_Corner_Right2 = 116;
  final int Super_Enemy_Occupied_Walk_Way_Corner_Left2 = 117;

  //Tower areas
  final int Tower_Archer_1 = 20;
  final int Tower_Archer_2 = 21;
  final int Tower_Archer_3 = 22;

  final int Tower_Boom_1 = 25;
  final int Tower_Boom_2 = 26;
  final int Tower_Boom_3 = 27;

  final int Tower_Leach_1 = 30;
  final int Tower_Leach_2 = 31;
  final int Tower_Leach_3 = 32;

  final int Tower_Team_1 = 35;
  final int Tower_Team_2 = 36;
  final int Tower_Team_3 = 37;
  final int Tower_Team_4 = 38;

  final int Tower_Sticky_1 = 40;
  final int Tower_Sticky_2 = 41;
  final int Tower_Sticky_3 = 42;

  final int Tower_Destroyer_1 = 60;
  final int Tower_Destroyer_2 = 61;
  final int Tower_Destroyer_3 = 62;

  final int Tower_Tazer_1 = 65;
  final int Tower_Tazer_2 = 66;
  final int Tower_Tazer_3 = 67;

  final int Tower_Guardian_1 = 70;
  final int Tower_Guardian_2 = 71;
  final int Tower_Guardian_3 = 72;
  //attack counters
  int pirCount = 0;
  public Map()
  {
    playSpace = new int[12][18];

    //Sets up Boarders
    for (int y = 0; y <= 11; y++)
    {
      playSpace[y][0] = Boarder;
      playSpace[y][17] = Boarder;
    }
    for (int x = 0; x <= 17; x++)
    {
      playSpace[0][x] = Boarder;
      playSpace[11][x] = Boarder;
    }

    //Sets up Walk Ways
    for (int y = 2; y <= 8; y = y+2)
    {
      for (int x = 2; x <= 14; x++)
      {
        playSpace[y][x] = Walk_Way_Straight;
      }
    }
    playSpace[3][15] = Walk_Way_Down;
    playSpace[5][2] = Walk_Way_Down;
    playSpace[7][15] = Walk_Way_Down;
    playSpace[2][15] = Walk_Way_Corner_Right;
    playSpace[4][15] = Walk_Way_Corner_Right2;
    playSpace[4][2] = Walk_Way_Corner_Left;
    playSpace[6][2] = Walk_Way_Corner_Left2;
    playSpace[6][15] = Walk_Way_Corner_Right;
    playSpace[8][15] = Walk_Way_Corner_Right2;

    playSpace[2][2] = Spawn;

    playSpace[8][2] = Goal_Zone;
  }
  
  //--------------------------------------------------------
  //changes a spot of real estate to a tower
  //--------------------------------------------------------
  public void buildTower(Tower tower)
  {
    int x = tower.getPosition().getX();
    int y = tower.getPosition().getY();
    if (playSpace[y][x] == Real_Estate)
    {
      if (tower.getName().equals("Archer"))
      {
        playSpace[y][x] = Tower_Archer_1;
      }
      else if (tower.getName().equals("Boom"))
      {
        playSpace[y][x] = Tower_Boom_1;
      }
      else if (tower.getName().equals("Leach"))
      {
        playSpace[y][x] = Tower_Leach_1;
      }
      else if (tower.getName().equals("Sticky"))
      {
        playSpace[y][x] = Tower_Sticky_1;
      }
      else if (tower.getName().equals("Destroyer"))
      {
        playSpace[y][x] = Tower_Destroyer_1;
      }
      else if (tower.getName().equals("Tazer"))
      {
        playSpace[y][x] = Tower_Tazer_1;
      }
      else if (tower.getName().equals("Guardian"))
      {
        playSpace[y][x] = Tower_Guardian_1;
      }
    }
  }

  //--------------------------------------------
  //build team towers specifically
  //----------------------------------------------
  public void buildTeamTower(Tower tower, Tower[] Tarry)
  {
    int x = tower.getPosition().getX();
    int y = tower.getPosition().getY();
    if (playSpace[y][x] == Real_Estate)
    {
      playSpace[y][x] = Tower_Team_1;
      for (int i = 0; i < Tarry.length; i++)
      {
        if( (Tarry[i].getPosition().getX() == x+1 && Tarry[i].getPosition().getY() == y+1)//up right
          ||(Tarry[i].getPosition().getX() == x+1 && Tarry[i].getPosition().getY() == y)//right
          ||(Tarry[i].getPosition().getX() == x   && Tarry[i].getPosition().getY() == y+1)//up
          ||(Tarry[i].getPosition().getX() == x-1 && Tarry[i].getPosition().getY() == y-1)//down left
          ||(Tarry[i].getPosition().getX() == x-1 && Tarry[i].getPosition().getY() == y)//left
          ||(Tarry[i].getPosition().getX() == x   && Tarry[i].getPosition().getY() == y-1)//down
          ||(Tarry[i].getPosition().getX() == x+1 && Tarry[i].getPosition().getY() == y-1)//down right
          ||(Tarry[i].getPosition().getX() == x-1 && Tarry[i].getPosition().getY() == y+1) )//up left
        {
          if (Tarry[i].getName().equals("Team"))
          {
            Tarry[i] = upgradeTower(Tarry[i]);
          }
        }
      }
    }
  }
  //-------------------------------------
  //Upgrades the level and stats of a tower
  //--------------------------------------
  public Tower upgradeTower(Tower tower)
  {
    int level = tower.getLevel();
    Tower output = tower;
    if (level == 1)
    {
      output.setLevel(2);
    }
    else if (level == 2)
    {
      output.setLevel(3);
    }
    else if (level == 3 && tower.getName().equals("Team"))
    {
      output.setLevel(4);
    }
    else
    {
      System.out.println("max level has been reached");
    }
    level = output.getLevel();//used to say tower, not output
    if(tower.getName().equals("Archer"))
    {
      output.setDamage(level);
      output.setRange(level + 1);
      if (output.getLevel() == 2)
      {
        System.out.println("Your tower is level 2 now");
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Archer_2;
      }
      else if (output.getLevel() >= 3)
      {
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Archer_3;
        System.out.println("Your tower is level 3 now");
      }
    }
    else if(tower.getName().equals("Boom"))
    {
      output.setDamage(output.getLevel());
      if (output.getLevel() == 2)
      {
        System.out.println("Your tower is level 2 now");
        output.setRange(1);
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Boom_2;
      }
      else if (output.getLevel() >= 3)
      {
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Boom_3;
        System.out.println("Your tower is level 3 now");
        output.setRange(2);
      }
    }
    else if(tower.getName().equals("Leach"))
    {
      output.setDamage(output.getLevel());
      if (output.getLevel() == 2)
      {
        System.out.println("Your tower is level 2 now");
        output.setRange(1);
        output.setDamage(0);
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Leach_2;
      }
      else if (output.getLevel() >= 3)
      {
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Leach_3;
        System.out.println("Your tower is level 3 now");
        output.setRange(2);
        output.setDamage(0);
      }
    }
    else if(tower.getName().equals("Team"))
    {
      if (output.getLevel() == 2)
      {
        System.out.println("Your tower is level 2 now");
        output.setRange(1);
        output.setDamage(3);
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Team_2;
      }
      else if (output.getLevel() == 3)
      {
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Team_3;
        System.out.println("Your tower is level 3 now");
        output.setRange(2);
        output.setDamage(4);
      }
      else if (output.getLevel() == 4)
      {
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Team_4;
        System.out.println("Your tower is level 4 now");
        output.setRange(3);
        output.setDamage(5);
      }
    }
    else if(tower.getName().equals("Sticky"))
    {
      if (output.getLevel() == 2)
      {
        System.out.println("Your tower is level 2 now");
        output.setRange(1);
        output.setDamage(0);
        output.setStickyness(3);
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Sticky_2;
      }
      else if (output.getLevel() == 3)
      {
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Sticky_3;
        System.out.println("Your tower is level 3 now");
        output.setRange(2);
        output.setDamage(1);
        output.setStickyness(4);
      }
    }
    else if(tower.getName().equals("Destroyer"))
    {
      if (output.getLevel() == 2)
      {
        System.out.println("Your tower is level 2 now");
        output.setRange(0);
        output.setDamage(4);
        output.setPierce(4);
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Destroyer_2;
      }
      else if (output.getLevel() == 3)
      {
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Destroyer_3;
        System.out.println("Your tower is level 3 now");
        output.setRange(0);
        output.setDamage(6);
        output.setPierce(5);
        output.setStickyness(0);
      }
    }
    else if(tower.getName().equals("Tazer"))
    {
      if (output.getLevel() == 2)
      {
        System.out.println("Your tower is level 2 now");
        output.setStunChance(5);
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Tazer_2;
      }
      else if (output.getLevel() == 3)
      {
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Tazer_3;
        System.out.println("Your tower is level 3 now");
        output.setStunChance(7);
      }
    }
    else if(tower.getName().equals("Guardian"))
    {
      if (output.getLevel() == 2)
      {
        System.out.println("Your tower is level 2 now");
        output.setCoolDown(20);
        output.resetCoolDown();
        output.setAbilityDamage(5);
        output.setDamage(3);
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Guardian_2;
      }
      else if (output.getLevel() == 3)
      {
        playSpace[tower.getPosition().getY()][tower.getPosition().getX()] = Tower_Guardian_3;
        System.out.println("Your tower is level 3 now");
        output.setCoolDown(10);
        output.resetCoolDown();
        output.setAbilityDamage(10);
      }
    }
    return output;
  }
  //------------------------------------------------------------------
  //Has a tower look around and attack enemies in its range,
  // returns a substitute for the enemy array
  //------------------------------------------------------------------
  public Enemy[] towerAttack(Tower tower, Enemy[] enemyArray, Player you)
  {
    int range = tower.getRange();
    shouldAttackAgain = true;
    Enemy[] output = enemyArray;
    Coordinate spot = new Coordinate(tower.getPosition().getX(), tower.getPosition().getY());

    for(int i = 1; (i <= range)&&(shouldAttackAgain); i++)
    {
      for(int j = 1; (j <= 8)&&(shouldAttackAgain); j++)
      {//loop which runs 8 times so that we hit every spot in the circle  (start)
        spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());//resets spot's value to tower pos
        //System.out.println(spot.getX() + " " + spot.getY());
        if (j == 1 &&(shouldAttackAgain))//x+1 y
        {
          spot.setCoordinate(spot.getX() + i, spot.getY());
          output = checkSpot(spot, output, tower, you);
          spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
          for (int g = 1; g < i && (shouldAttackAgain); g++)
          {
            spot.setCoordinate(spot.getX() + i, spot.getY() + g);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
            spot.setCoordinate(spot.getX() + i, spot.getY() - g);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
          }
          //System.out.println(spot.getX() + " " + spot.getY());
        }
        else if  (j == 2 &&(shouldAttackAgain))
        {
          spot.setCoordinate(spot.getX() - i, spot.getY());
          output = checkSpot(spot, output, tower, you);
          spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
          for (int g = 1; g < i && (shouldAttackAgain); g++)
          {
            spot.setCoordinate(spot.getX() - i, spot.getY() + g);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
            spot.setCoordinate(spot.getX() - i, spot.getY() - g);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
          }
          //System.out.println(spot.getX() + " " + spot.getY());
        }
        else if  (j == 3 &&(shouldAttackAgain))
        {
          //spot.setCoordinate(spot.getX(), spot.getY()+i);//x y+1
          spot.setCoordinate(spot.getX(), spot.getY()+i);
          output = checkSpot(spot, output, tower, you);
          spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
          for (int g = 1; g < i && (shouldAttackAgain); g++)
          {
            spot.setCoordinate(spot.getX() + g, spot.getY() + i);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
            spot.setCoordinate(spot.getX() - g, spot.getY() + i);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
          }
          //System.out.println(spot.getX() + " " + spot.getY());
        }
        else if (j == 4 &&(shouldAttackAgain))
        {
          //spot.setCoordinate(sX, sY -1);//x y-1
          spot.setCoordinate(spot.getX(), spot.getY()-i);
          output = checkSpot(spot, output, tower, you);
          spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
          for (int g = 1; g < i &&(shouldAttackAgain); g++)
          {
            spot.setCoordinate(spot.getX() + g, spot.getY() - i);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
            spot.setCoordinate(spot.getX() - g, spot.getY() - i);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(tower.getPosition().getX(), tower.getPosition().getY());
          }
          //System.out.println(spot.getX() + " " + spot.getY());
        }
        else if (j == 5 && (shouldAttackAgain))
        {
          spot.setCoordinate(spot.getX()+i, spot.getY()+i);//x+1 y+1
          output = checkSpot(spot, output, tower, you);
          //System.out.println(spot.getX() + " " + spot.getY());
        }
        else if (j == 6 && (shouldAttackAgain))
        {
          spot.setCoordinate(spot.getX()-i, spot.getY()+i);//x-1 y+1
          output = checkSpot(spot, output, tower, you);
          //System.out.println(spot.getX() + " " + spot.getY());
        }
        else if (j == 7 && (shouldAttackAgain))
        {
          spot.setCoordinate(spot.getX()+i, spot.getY()-i);//x+1 y-1
          output = checkSpot(spot, output, tower, you);
          //System.out.println(spot.getX() + " " + spot.getY());
        }
        else if (j == 8 && (shouldAttackAgain))
        {
          spot.setCoordinate(spot.getX()-i, spot.getY()-i);//x-1 y-1
          output = checkSpot(spot, output, tower, you);
          //System.out.println(spot.getX() + " " + spot.getY());
        }

      }//loop which runs 8 times so that we hit every spot in the circle  (end)

    }//loop which runs once for each range
    if (tower.getName().equals("Destroyer"))
    {
      int pir = tower.getPierce();
      for (int b = 1; b <= 15; b++)
      {
        spot.setCoordinate(spot.getX()+1, spot.getY());
        if(pirCount <= pir)
        {
          output = checkSpot(spot, output, tower, you);
        }
      }
      spot.setCoordinate(tower.getPosition().getX(), spot.getY());
      for (int b = 1; b <= 15; b++)
      {
        spot.setCoordinate(spot.getX()-1, spot.getY());
        if(pirCount <= pir)
        {
          output = checkSpot(spot, output, tower, you);
        }
      }
      pirCount = 0;
    }
    if (tower.getName().equals("Tazer"))
    {
      Enemy strongest = output[0];
      int index = 0;
      for (int i = 0; i < output.length; i++)
      {
        if (output[i].getLevel() >strongest.getLevel())
        {
          index = i;
          if (!(output[i].getStunStatus()) && output[i].isAlive())
          {
            strongest = output[i];  
          }
        }
      }
      if(strongest.isAlive())
      {
        spot.setCoordinate(strongest.getPosition().getX(), strongest.getPosition().getY());
        output = checkSpot(spot, output, tower, you);
      }
    }
    if (tower.getName().equals("Guardian"))
    {
      if (tower.getLevel() >= 2)
      {
        tower.timePass();
        if (tower.getCoolDown() <= tower.getCoolDownUsed())
        {
          //use ability
          usingAbility = true;
          displayMini();
          System.out.println("You're guardianTower's ability has been charged.");
          System.out.println("It's time to display the Guardian's might!");
          System.out.println("Eneter where you want to launch the attack (ex: 3,5)");
          System.out.println("Type the x coordinate, then the y coordinate, seperated by a comma");
          Scanner scan = new Scanner(System.in);
          String input = scan.next();
          try
          {
            int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
            int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
            spot.setCoordinate(x,y);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(x+1,y);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(x-1,y);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(x,y+1);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(x,y-1);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(x+1,y-1);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(x+1,y+1);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(x-1,y-1);
            output = checkSpot(spot, output, tower, you);
            spot.setCoordinate(x-1,y+1);
            output = checkSpot(spot, output, tower, you);
            usingAbility = false;
            tower.resetCoolDown();
          }
          catch(Exception e)
          {
            System.out.println("That is not a valid coordinate.");
            System.out.println("You must only type the x coordinate, then a comma, and then the y coordinate.");
            System.out.println("If you want to selcet the spot 4 to the right and 3 down, you would enter:");
            System.out.println("4,3");
            System.out.println("Enter anything to continue");
            input = scan.next();
          }

        }
      }
    }
    return output;
  }//end of method


    

  //--------------------------------------
  //Checks spot for enemy to deal attack
  //--------------------------------------
  private Enemy[] checkSpot(Coordinate spot, Enemy[] output, Tower tower, Player you)
  {
    //Enemy[] _output = output;
    //Coordinate spot = new Coordinate
    int eX;
    int eY;
    //System.out.println("spot x = " + spot.getX());
    //System.out.println("spot y = " + spot.getY());
    if(  (spot.getX() < 0) || (spot.getX() >= 18) || (spot.getY() < 0) || (spot.getY() >= 12) ) 
    {
      //check nothing
      //System.out.println("an error is in bound");
    }
    else if ((playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Straight)
      ||(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Down)
      ||(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Corner_Right)
      ||(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Corner_Right2)
      ||(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Corner_Left)
      ||(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Corner_Left2)
      ||(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Straight)//start of super
      ||(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Down)
      ||(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Corner_Right)
      ||(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Corner_Right2)
      ||(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Corner_Left)
      ||(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Corner_Left2)
      )//check to see if we hit any kind of enemy occupied square
    {
      for (int x = 0; (x < output.length)&&(shouldAttackAgain); x++)//runs through array to see who was hit and damages them
      {
        //System.out.println("YAY! gun go pew pew!");
        eX = output[x].getPosition().getX();
        eY = output[x].getPosition().getY();
        if ((eX == spot.getX()) && (eY ==spot.getY()))//(output[x].getPosition().equals(spot))
        {
          //System.out.println("should atleast hit");
          if (usingAbility)
          {
            output[x].takeDamage(tower.getAbilityDamage());
          }
          else
          {
            output[x].takeDamage(tower.getDamage());
          }
          if (tower.getName().equals("Archer") || (tower.getName().equals("Team")) || tower.getName().equals("Guardian"))//stops archer towers from attacking 2 enemies
          {
            if (tower.getName().equals("Guardian") && tower.getLevel() >= 2 )
            {
              shouldAttackAgain = true;
            }
            else{
              shouldAttackAgain = false;
            }

          }
          if (tower.getName().equals("Destroyer"))
          {
            pirCount++;
          }
          if (tower.getName().equals("Tazer"))
          {
            int rand = (int)(Math.random()*10 + 1);
            {
              if (tower.getStunChance() >= rand)
              {
                output[x].stun();
              }
            }
          }
          if (tower.getName().equals("Leach"))
          {
            if (tower.getLevel() == 1)
            {
              you.setMoney(you.getMoney() + 3);
            }
            else
            {
              you.setMoney(you.getMoney() + 6);
            }
          }
          if (tower.getName().equals("Sticky"))
          {
            output[x].setSlowed(tower.getStickyness());
          }
          //System.out.println("it's got " + output[x].getHealth() + "health left");
          if (output[x].getHealth() <= 0)
          {
            //System.out.println("Bruh, why thing not dying?");
            //playSpace[spot.getY()][spot.getX()] -= 10;//turns the occupied square to unoccupied

            if(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Straight)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Straight;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Down)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Down;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Corner_Right)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Corner_Right;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Corner_Right2)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Corner_Right2;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Corner_Left2)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Corner_Left2;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Enemy_Occupied_Walk_Way_Corner_Left)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Corner_Left;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Straight)//start of supers
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Straight;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Down)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Down;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Corner_Right)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Corner_Right;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Corner_Right2)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Corner_Right2;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Corner_Left2)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Corner_Left2;
            }
            else if(playSpace[spot.getY()][spot.getX()] == Super_Enemy_Occupied_Walk_Way_Corner_Left)
            {
              playSpace[spot.getY()][spot.getX()] = Walk_Way_Corner_Left;
            }
          }
        }
      }
    }
    return output;
  }
  //---------------------------------------------------------
  //Spawns an enemy
  //---------------------------------------------------------
  public void spawnEnemy(Enemy spider)
  {
    spider.revive();
    spider.setPosition(3,2);
  }

  //------------------------------------------------------------
  //S
  //------------------------------------------------------------
  public void spawnSuperEnemy(Enemy spider)
  {
    spider.revive();
    spider.setPosition(3,2);
  }

  //-----------------------------------------------------------
  //Moves an enemy
  //-----------------------------------------------------------
  public void translateEnemy(Enemy spider, Player user)
  {
    int x = spider.getPosition().getX();
    int y = spider.getPosition().getY();
    if(!spider.getStunStatus())
    {
      if (((y == 2) && (x < 15)) || ((y == 6) && (x < 15)))//go right
      {
        if (playSpace[y][x+1] != Enemy_Occupied_Walk_Way_Corner_Right
        &&  playSpace[y][x+1] != Super_Enemy_Occupied_Walk_Way_Corner_Right
        &&  playSpace[y][x+1] != Enemy_Occupied_Walk_Way_Corner_Left2
        &&  playSpace[y][x+1] != Super_Enemy_Occupied_Walk_Way_Corner_Left2
        &&  playSpace[y][x+1] != Enemy_Occupied_Walk_Way_Straight
        &&  playSpace[y][x+1] != Super_Enemy_Occupied_Walk_Way_Straight)  
        {
          //spider.movePositionRight();//move to the right 1
          if (playSpace[y][x+1] == 4)//4 = wwcr1
          {
            playSpace[y][x+1] = Enemy_Occupied_Walk_Way_Corner_Right;
            playSpace[y][x] = Walk_Way_Straight;
          }
          else if(playSpace[y][x] == Enemy_Occupied_Walk_Way_Corner_Left2)
          {
            playSpace[y][x+1] = Enemy_Occupied_Walk_Way_Straight;
            playSpace[y][x] = Walk_Way_Corner_Left2;
          }
          else
          { 
            playSpace[y][x+1] = Enemy_Occupied_Walk_Way_Straight;
            playSpace[y][x] = Walk_Way_Straight;
          }
          spider.movePositionRight();//move to the right 1, add more conditions (maybe, not yet)
        }
      }
      else if(((y == 4) && (x > 2)) || ((y == 8) && (x > 3)))//go left
      {
        if (playSpace[y][x-1] != Enemy_Occupied_Walk_Way_Corner_Left
        &&  playSpace[y][x-1] != Enemy_Occupied_Walk_Way_Corner_Right2
        &&  playSpace[y][x-1] != Enemy_Occupied_Walk_Way_Straight
        &&  playSpace[y][x-1] != Super_Enemy_Occupied_Walk_Way_Corner_Left
        &&  playSpace[y][x-1] != Super_Enemy_Occupied_Walk_Way_Corner_Right2
        &&  playSpace[y][x-1] != Super_Enemy_Occupied_Walk_Way_Straight)
        {
          if (playSpace[y][x-1] == Walk_Way_Corner_Left)
          {
            playSpace[y][x-1] = Enemy_Occupied_Walk_Way_Corner_Left;
            playSpace[y][x] = Walk_Way_Straight;
          }
          else if(playSpace[y][x] == Enemy_Occupied_Walk_Way_Corner_Right2)
          {
            playSpace[y][x-1] = Enemy_Occupied_Walk_Way_Straight;
            playSpace[y][x] = Walk_Way_Corner_Right2;
          }
          else
          { 
            playSpace[y][x-1] = Enemy_Occupied_Walk_Way_Straight;
            playSpace[y][x] = Walk_Way_Straight;
          }
          spider.movePositionLeft();//move to the left 1
        }
      }
      else if ((playSpace[y + 1][x] == Walk_Way_Down) || (playSpace[y + 1][x] == Walk_Way_Corner_Left2) || (playSpace[y + 1][x] == Walk_Way_Corner_Right2))//go down
      {
        if (playSpace[y+1][x] != Enemy_Occupied_Walk_Way_Down
        &&  playSpace[y+1][x] != Enemy_Occupied_Walk_Way_Corner_Left2
        &&  playSpace[y+1][x] != Enemy_Occupied_Walk_Way_Corner_Right2
        &&  playSpace[y+1][x] != Super_Enemy_Occupied_Walk_Way_Down
        &&  playSpace[y+1][x] != Super_Enemy_Occupied_Walk_Way_Corner_Left2
        &&  playSpace[y+1][x] != Super_Enemy_Occupied_Walk_Way_Corner_Right2)
        {
          if(playSpace[y][x]== Enemy_Occupied_Walk_Way_Corner_Right)
          {
            playSpace[y+1][x] = Enemy_Occupied_Walk_Way_Down;
            playSpace[y][x] = Walk_Way_Corner_Right;
          }
          else if(playSpace[y][x] == Enemy_Occupied_Walk_Way_Corner_Left)
          {
            playSpace[y+1][x] = Enemy_Occupied_Walk_Way_Down;
            playSpace[y][x] = Walk_Way_Corner_Left;
          }
          else if(playSpace[y+1][x] == Walk_Way_Corner_Right2)
          {
            playSpace[y+1][x] = Enemy_Occupied_Walk_Way_Corner_Right2;
            playSpace[y][x] = Walk_Way_Down;
          }
          else if(playSpace[y + 1][x] == Walk_Way_Corner_Left2)
          {
            playSpace[y+1][x] = Enemy_Occupied_Walk_Way_Corner_Left2;
            playSpace[y][x] = Walk_Way_Down;
          }

          spider.movePositionDown(); //move down 1
        }
      }
      else if((y == 8) && (x == 3))
      {
        user.takeDamage();  //lose health
        spider.die();      //kill the spider
        playSpace[8][3] = Walk_Way_Straight;
        System.out.println("You were damaged");
      }
    }
    spider.stunWearOff();
  }
  //-----------------------------------------------------------
  //Moves a Super enemy
  //-----------------------------------------------------------
  public void translateSuperEnemy(Enemy spider, Player user)
  {
    int x = spider.getPosition().getX();
    int y = spider.getPosition().getY();
    int distance = spider.getSpeed();
    int sticky = spider.getSlowed();
    if (!spider.getStunStatus())
    {
      distance -= sticky;
      if (distance <= 1)
      {
        distance = 1;
      }
      //System.out.println("This spider has a speed of " + distance);
      for (int i = 1; i <= distance; i++)
      {
        x = spider.getPosition().getX();
        y = spider.getPosition().getY();
        if (((y == 2) && (x < 15)) || ((y == 6) && (x < 15)))//go right
        {
          if (playSpace[y][x+1] != Enemy_Occupied_Walk_Way_Corner_Right
          &&  playSpace[y][x+1] != Super_Enemy_Occupied_Walk_Way_Corner_Right
          &&  playSpace[y][x+1] != Enemy_Occupied_Walk_Way_Corner_Left2
          &&  playSpace[y][x+1] != Super_Enemy_Occupied_Walk_Way_Corner_Left2
          &&  playSpace[y][x+1] != Enemy_Occupied_Walk_Way_Straight
          &&  playSpace[y][x+1] != Super_Enemy_Occupied_Walk_Way_Straight)  
          {  
            //spider.movePositionRight();//move to the right 1
            if (playSpace[y][x+1] == 4)//4 = wwcr1
            {
              playSpace[y][x+1] = Super_Enemy_Occupied_Walk_Way_Corner_Right;
              playSpace[y][x] = Walk_Way_Straight;
            }
            else if(playSpace[y][x] == Super_Enemy_Occupied_Walk_Way_Corner_Left2)
            {
              playSpace[y][x+1] = Super_Enemy_Occupied_Walk_Way_Straight;
              playSpace[y][x] = Walk_Way_Corner_Left2;
            }
            else if(playSpace[y][x+1] == Walk_Way_Straight)
            { 
              playSpace[y][x+1] = Super_Enemy_Occupied_Walk_Way_Straight;
              playSpace[y][x] = Walk_Way_Straight;
            }
            spider.movePositionRight();//move to the right 1, add more conditions (maybe, not yet)
          }
          else
          {
            i = 1000;
          }
          
        }
        else if(((y == 4) && (x > 2)) || ((y == 8) && (x > 3)))//go left
        {
          if (playSpace[y][x-1] != Enemy_Occupied_Walk_Way_Corner_Left
          &&  playSpace[y][x-1] != Enemy_Occupied_Walk_Way_Corner_Right2
          &&  playSpace[y][x-1] != Enemy_Occupied_Walk_Way_Straight
          &&  playSpace[y][x-1] != Super_Enemy_Occupied_Walk_Way_Corner_Left
          &&  playSpace[y][x-1] != Super_Enemy_Occupied_Walk_Way_Corner_Right2
          &&  playSpace[y][x-1] != Super_Enemy_Occupied_Walk_Way_Straight)
          {
            if (playSpace[y][x-1] == Walk_Way_Corner_Left)
            {
              playSpace[y][x-1] = Super_Enemy_Occupied_Walk_Way_Corner_Left;
              playSpace[y][x] = Walk_Way_Straight;
            }
            else if(playSpace[y][x] == Super_Enemy_Occupied_Walk_Way_Corner_Right2)
            {
              playSpace[y][x-1] = Super_Enemy_Occupied_Walk_Way_Straight;
              playSpace[y][x] = Walk_Way_Corner_Right2;
            }
            else if (playSpace[y][x-1] == Walk_Way_Straight)
            { 
              playSpace[y][x-1] = Super_Enemy_Occupied_Walk_Way_Straight;
              playSpace[y][x] = Walk_Way_Straight;
            }
            spider.movePositionLeft();//move to the left 1
          }
          else
          {
            i = 1000;
          }
        }
        else if ((playSpace[y + 1][x] == Walk_Way_Down) || (playSpace[y + 1][x] == Walk_Way_Corner_Left2) || (playSpace[y + 1][x] == Walk_Way_Corner_Right2))//go down
        {
          if (playSpace[y+1][x] != Enemy_Occupied_Walk_Way_Down
          &&  playSpace[y+1][x] != Enemy_Occupied_Walk_Way_Corner_Left2
          &&  playSpace[y+1][x] != Enemy_Occupied_Walk_Way_Corner_Right2
          &&  playSpace[y+1][x] != Super_Enemy_Occupied_Walk_Way_Down
          &&  playSpace[y+1][x] != Super_Enemy_Occupied_Walk_Way_Corner_Left2
          &&  playSpace[y+1][x] != Super_Enemy_Occupied_Walk_Way_Corner_Right2)
          {
            if(playSpace[y][x]== Super_Enemy_Occupied_Walk_Way_Corner_Right)
            {
              playSpace[y+1][x] = Super_Enemy_Occupied_Walk_Way_Down;
              playSpace[y][x] = Walk_Way_Corner_Right;
            }
            else if(playSpace[y][x] == Super_Enemy_Occupied_Walk_Way_Corner_Left)
            {
              playSpace[y+1][x] = Super_Enemy_Occupied_Walk_Way_Down;
              playSpace[y][x] = Walk_Way_Corner_Left;
            }
            else if(playSpace[y+1][x] == Walk_Way_Corner_Right2)
            {
              playSpace[y+1][x] = Super_Enemy_Occupied_Walk_Way_Corner_Right2;
              playSpace[y][x] = Walk_Way_Down;
            }
            else if(playSpace[y + 1][x] == Walk_Way_Corner_Left2)
            {
              playSpace[y+1][x] = Super_Enemy_Occupied_Walk_Way_Corner_Left2;
              playSpace[y][x] = Walk_Way_Down;
            }
            spider.movePositionDown(); //move down 1
          }
          else
          {
            i = 1000;
          }
        }
        else if((y == 8) && (x == 3))
        {
          user.takeDamage();  //lose health
          spider.die();      //kill the spider
          i = 100000;
          playSpace[8][3] = Walk_Way_Straight;
          System.out.println("You were damaged");
        }
      }
      spider.setSlowed(0);
    }
    spider.stunWearOff();
  }
  //------------------------------------------------------------------------------------
  //counts the amount on enemy occupied spaces, and retruns said number
  //--------------------------------------------------------------------------------------
  public int enemyOccupiedSpaces()
  {
    int count = 0;
    for (int y = 0; y <=11; y++)
    {
      for (int x = 0; x<=17; x++)
      {
        if ( ((playSpace[y][x] >= 12)&&(playSpace[y][x]<=17))  || ((playSpace[y][x] >= 112) && (playSpace[y][x] <= 117))  )
        {
          count++;
        }
      }
    }
    return count;
  } 

  //------------------------------------------------------------
  //Prints out the Play Space
  //------------------------------------------------------------
  public void display()
  {
    int spot;
    System.out.println("██████1████████2████████3████████4████████5████████6████████7████████8████████9████████10███████11███████12███████13███████14███████15███████16███");
    for (int y = 0; y <= 11; y++)
    {
      for (int i = 1; i <= 3; i++)
      {
        if ((i == 2)&&(y != 0)&& (y != 11))
        {
          if (y == 10)
          {
            System.out.print(y);
          }
          else
          {
            System.out.print(y + "█");
          }
        }
        else if ((y <=10)&&(y!=0))
        {
          System.out.print("██");
        }
        for (int x = 0; x <= 17; x++)
        {
          spot = playSpace[y][x];
          if (spot == Boarder)
          {
            //System.out.print("");
          }
          //------------------------------------------------
          else if (spot == Real_Estate)
          {
            System.out.print("[-------]");
          }
          //--------------------------------------------------
          else if (spot == Walk_Way_Straight)
          {
            if(i == 1)
            {
              System.out.print("=========");
            }
            else if(i == 2)
            {
              System.out.print("         ");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //-------------------------------------
          else if(spot == Walk_Way_Down)
          {
            if(i == 1)
            {
              System.out.print("=       =");
            }
            else if(i == 2)
            {
              System.out.print("=       =");
            }
            else
            {
              System.out.print("=       =");
            }
          } 
          //------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Right)
          {
            if(i == 1)
            {
              System.out.print("=========");
            }
            else if(i == 2)
            {
              System.out.print("        =");
            }
            else
            {
              System.out.print("=       =");
            }
          }
          //-------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Right2)
          {
            if(i == 1)
            {
              System.out.print("=       =");
            }
            else if(i == 2)
            {
              System.out.print("        =");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Left)
          {
            if(i == 1)
            {
              System.out.print("=========");
            }
            else if(i == 2)
            {
              System.out.print("=        ");
            }
            else
            {
              System.out.print("=       =");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Left2)
          {
            if(i == 1)
            {
              System.out.print("=       =");
            }
            else if(i == 2)
            {
              System.out.print("=        ");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Spawn)
          {
            if(i == 1)
            {
              System.out.print("=========");
            }
            else if(i == 2)
            {
              System.out.print("--Spawn--");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Goal_Zone)
          {
            if(i == 1)
            {
              System.out.print("=========");
            }
            else if(i == 2)
            {
              System.out.print("=-HOME⚐-=");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //-------------------------------------------------------------------------------------

          //-------------------------------------------------------------------------------------
          //Enemy occupied walk ways
          else if (spot == Enemy_Occupied_Walk_Way_Straight)
          {
            if(i == 1)
            {
              System.out.print("=========");
            }
            else if(i == 2)
            {
              System.out.print("    🕷    ");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //----------------------------------------------------------------------------------
          else if(spot == Enemy_Occupied_Walk_Way_Down)
          {
            if(i == 1)
            {
              System.out.print("=       =");
            }
            else if(i == 2)
            {
              System.out.print("=   🕷   =");
            }
            else
            {
              System.out.print("=       =");
            }
          }
          //-------------------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Right)
          {
            if(i == 1)
            {
              System.out.print("=========");
            }
            else if(i == 2)
            {
              System.out.print("    🕷   =");
            }
            else
            {
              System.out.print("=       =");
            }
          }
          //-------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Right2)
          {
            if(i == 1)
            {
              System.out.print("=       =");
            }
            else if(i == 2)
            {
              System.out.print("   🕷    =");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Left)
          {
            if(i == 1)
            {
              System.out.print("==========");
            }
            else if(i == 2)
            {
              System.out.print("=    🕷   ");
            }
            else
            {
              System.out.print("=       =");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Left2)
          {
            if(i == 1)
            {
              System.out.print("=       =");
            }
            else if(i == 2)
            {
              System.out.print("=   🕷    ");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //super enemey
          else if (spot == Super_Enemy_Occupied_Walk_Way_Straight)
          {
            if(i == 1)
            {
              System.out.print("=========");
            }
            else if(i == 2)
            {
              System.out.print("    ☃    ");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //----------------------------------------------------------------------------------
          else if(spot == Super_Enemy_Occupied_Walk_Way_Down)
          {
            if(i == 1)
            {
              System.out.print("=       =");
            }
            else if(i == 2)
            {
              System.out.print("=   ☃   =");
            }
            else
            {
              System.out.print("=       =");
            }
          }
          //-------------------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Right)
          {
            if(i == 1)
            {
              System.out.print("=========");
            }
            else if(i == 2)
            {
              System.out.print("    ☃   =");
            }
            else
            {
              System.out.print("=       =");
            }
          }
          //-------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Right2)
          {
            if(i == 1)
            {
              System.out.print("=       =");
            }
            else if(i == 2)
            {
              System.out.print("   ☃    =");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Left)
          {
            if(i == 1)
            {
              System.out.print("==========");
            }
            else if(i == 2)
            {
              System.out.print("=    ☃   ");
            }
            else
            {
              System.out.print("=       =");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Left2)
          {
            if(i == 1)
            {
              System.out.print("=       =");
            }
            else if(i == 2)
            {
              System.out.print("=   ☃    ");
            }
            else
            {
              System.out.print("=========");
            }
          }
          //------------------------------------------------------------------------------
          //towers
          //-----------------------------------------------------------------------------
          else if (spot == Tower_Archer_1)
          {
            if(i == 1)
            {
              System.out.print("___/^\\___");
            }
            else if(i == 2)
            {
              System.out.print("|⥉Archer|");
            }
            else
            {
              System.out.print("____1____");
            }
          }
          else if (spot == Tower_Archer_2)
          {
            if(i == 1)
            {
              System.out.print("_|//⥉\\\\|_");
            }
            else if(i == 2)
            {
              System.out.print("|⥉Archer|");
            }
            else
            {
              System.out.print("\\|⥉_2_⥉|/");
            }
          }
          else if (spot == Tower_Archer_3)
          {
            if(i == 1)
            {
              System.out.print("/^/^⥉^\\^\\");
            }
            else if(i == 2)
            {
              System.out.print("(⚐Archer)");
            }
            else
            {
              System.out.print("\\|⥉⥉3⥉⥉|/");
            }
          }
          else if (spot == Tower_Boom_1)
          {
            if(i == 1)
            {
              System.out.print("___|💣|__");
            }
            else if(i == 2)
            {
              System.out.print("|BOOM!!!|");
            }
            else
            {
              System.out.print("|_:_:_:_|");
            }
          }
          else if (spot == Tower_Boom_2)
          {
            if(i == 1)
            {
              System.out.print("__|💣💣|_");
            }
            else if(i == 2)
            {
              System.out.print("||BOOM!||");
            }
            else
            {
              System.out.print("|::💣::_|");
            }
          }
          else if (spot == Tower_Boom_3)
          {
            if(i == 1)
            {
              System.out.print("💣💣💣💣\\");
            }
            else if(i == 2)
            {
              System.out.print("||BOOM!||");
            }
            else
            {
              System.out.print("|_::💣::|");
            }
          }
          //
          else if (spot == Tower_Leach_3)
          {
            if(i == 1)
            {
              System.out.print("_|$$$$$|_");
            }
            else if(i == 2)
            {
              System.out.print("($Leach$)");
            }
            else
            {
              System.out.print("|_:_3_:_|");
            }
          }
          else if (spot == Tower_Leach_2)
          {
            if(i == 1)
            {
              System.out.print("__|$$$|__");
            }
            else if(i == 2)
            {
              System.out.print("|$Leach$|");
            }
            else
            {
              System.out.print("|_:_2_:_|");
            }
          }
          else if (spot == Tower_Leach_1)
          {
            if(i == 1)
            {
              System.out.print("/___$___\\");
            }
            else if(i == 2)
            {
              System.out.print("|$Leach$|");
            }
            else
            {
              System.out.print("|_:-1-:_|");
            }
          }
          else if (spot == Tower_Team_4)
          {
            if(i == 1)
            {
              System.out.print("/||TTT||\\");
            }
            else if(i == 2)
            {
              System.out.print("Team GOD⚐");
            }
            else
            {
              System.out.print("|\\*(*)*/|");
            }
          }
          else if (spot == Tower_Team_3)
          {
            if(i == 1)
            {
              System.out.print("_/|TTT|\\_");
            }
            else if(i == 2)
            {
              System.out.print("(Team: 3)");
            }
            else
            {
              System.out.print("|-\\***/-|");
            }
          }
          else if (spot == Tower_Team_2)
          {
            if(i == 1)
            {
              System.out.print("__/|T|\\__");
            }
            else if(i == 2)
            {
              System.out.print("[Team: 2]");
            }
            else
            {
              System.out.print("[_--*--_]");
            }
          }
          else if (spot == Tower_Team_1)
          {
            if(i == 1)
            {
              System.out.print("__/ T \\__");
            }
            else if(i == 2)
            {
              System.out.print("|Team: 1|");
            }
            else
            {
              System.out.print("[_-----_]");
            }
          }
          else if (spot == Tower_Sticky_1)
          {
            if(i == 1)
            {
              System.out.print("__.⊆|⊇.__");
            }
            else if(i == 2)
            {
              System.out.print("(Sticky🍯");
            }
            else
            {
              System.out.print("|∼∼∼∼∼∼∼|");
            }
          }
          else if (spot == Tower_Sticky_2)
          {
            if(i == 1)
            {
              System.out.print("__///🜙___");
            }
            else if(i == 2)
            {
              System.out.print("<Sticky🍯>");
            }
            else
            {
              System.out.print("[≈≈≈≈≈≈≈]");
            }
          }
          else if (spot == Tower_Sticky_3)
          {
            if(i == 1)
            {
              System.out.print("__/𒄉_____");
            }
            else if(i == 2)
            {
              System.out.print("(Sticky⚐)");
            }
            else
            {
              System.out.print("[_-----_]");
            }
          }
          else if (spot == Tower_Destroyer_1)
          {
            if(i == 1)
            {
              System.out.print("<--<->-->");
            }
            else if(i == 2)
            {
              System.out.print("Destroyer");
            }
            else
            {
              System.out.print("<\\<-V->/>");
            }
          }
          else if (spot == Tower_Destroyer_2)
          {
            if(i == 1)
            {
              System.out.print("<<</*\\>>>");
            }
            else if(i == 2)
            {
              System.out.print("Destroyer");
            }
            else
            {
              System.out.print("<\\<<V>>/>");
            }
          }
          else if (spot == Tower_Destroyer_3)
          {
            if(i == 1)
            {
              System.out.print("<<</*\\>>>");
            }
            else if(i == 2)
            {
              System.out.print("Destroyer");
            }
            else
            {
              System.out.print("<\\<<⚐>>/>");
            }
          }
          else if (spot == Tower_Tazer_1)
          {
            if(i == 1)
            {
              System.out.print("<--\\⚛/-->");
            }
            else if(i == 2)
            {
              System.out.print("(Tazer 1)");
            }
            else
            {
              System.out.print("::W-W-W::");
            }
          }
          else if (spot == Tower_Tazer_2)
          {
            if(i == 1)
            {
              System.out.print("<-<⚡️>-->");
            }
            else if(i == 2)
            {
              System.out.print("(Tazer 2)");
            }
            else
            {
              System.out.print(":{W~W~W}:");
            }
          }
          else if (spot == Tower_Tazer_3)
          {
            if(i == 1)
            {
              System.out.print("<<<⚡️︎->>>");
            }
            else if(i == 2)
            {
              System.out.print("(Tazer 3)");
            }                  
            else
            {
              System.out.print(":-W~⚐~W-:");
            }
          }
          else if (spot == Tower_Guardian_1)
          {
            if(i == 1)
            {
              System.out.print("<:-[☄]-:>");
            }
            else if(i == 2)
            {
              System.out.print("Guardian1");
            }
            else
            {
              System.out.print("M^_|-|_^M");
            }
          }
          else if (spot == Tower_Guardian_2)
          {
            if(i == 1)
            {
              System.out.print("<[☄]:[☄]>");
            }
            else if(i == 2)
            {
              System.out.print("Guardian2");
            }
            else
            {
              System.out.print("MM_|||_MM");
            }
          }
          else if (spot == Tower_Guardian_3)
          {
            if(i == 1)
            {
              System.out.print("[☄][☄][☄]");
            }
            else if(i == 2)
            {
              System.out.print("Guardian3");
            }                  
            else
            {
              System.out.print("^MW~⚐~WM^");
            }
          }
        }
        if (y > 0)
        {
          System.out.println();
        }
      }
    }
  }

  //-----------------------------------------------------------------𒄉
  //
  //displays the map, but smaller and condensed
  //
  //------------------------------------------------------------------
  public void displayMini()
  {
    int spot;
    System.out.println("███1██2██3██4██5██6██7██8██9██10█11█12█13█14█15█16");
    for (int y = 0; y <= 11; y++)
    {
      for (int i = 1; i <= 2; i++)
      {
        if ((i == 1)&&(y != 0)&& (y != 11))
        {
          if (y == 10)
          {
            System.out.print(y);
          }
          else
          {
            System.out.print(y + "█");
          }
        }
        else if ((y <=10)&&(y!=0))
        {
          System.out.print("██");
        }
        for (int x = 0; x <= 17; x++)
        {
          spot = playSpace[y][x];
          if (spot == Boarder)
          {
            //System.out.print("");
          }
          //------------------------------------------------
          else if (spot == Real_Estate)
          {
            System.out.print("[-]");
          }
          //--------------------------------------------------
          else if (spot == Walk_Way_Straight)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("   ");
            }
          }
          //-------------------------------------
          else if(spot == Walk_Way_Down)
          {
            if(i == 1)
            {
              System.out.print("= =");
            }
            else if(i == 2)
            {
              System.out.print("= =");
            }
          } 
          //------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Right)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("  =");
            }
          }
          //-------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Right2)
          {
            if(i == 1)
            {
              System.out.print("= =");
            }
            else if(i == 2)
            {
              System.out.print("  =");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Left)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("=  ");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Left2)
          {
            if(i == 1)
            {
              System.out.print("= =");
            }
            else if(i == 2)
            {
              System.out.print("=  ");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Spawn)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("-S-");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Goal_Zone)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("⚐H⚐");
            }
          }
          //-------------------------------------------------------------------------------------

          //-------------------------------------------------------------------------------------
          //Enemy occupied walk ways
          else if (spot == Enemy_Occupied_Walk_Way_Straight)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("  🕷");
            }            
          }
          //----------------------------------------------------------------------------------
          else if(spot == Enemy_Occupied_Walk_Way_Down)
          {
            if(i == 1)
            {
              System.out.print("= =");
            }
            else if(i == 2)
            {
              System.out.print("=🕷=");
            }
          }
          //-------------------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Right)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("🕷 =");
            }
          }
          //-------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Right2)
          {
            if(i == 1)
            {
              System.out.print("= =");
            }
            else if(i == 2)
            {
              System.out.print("🕷 =");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Left)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("=🕷 ");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Left2)
          {
            if(i == 1)
            {
              System.out.print("= =");
            }
            else if(i == 2)
            {
              System.out.print("=🕷 ");
            }
          }
          //--------------Super enemy-----------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Straight)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print(" ☃ ");
            }            
          }
          //----------------------------------------------------------------------------------
          else if(spot == Super_Enemy_Occupied_Walk_Way_Down)
          {
            if(i == 1)
            {
              System.out.print("= =");
            }
            else if(i == 2)
            {
              System.out.print("=☃=");
            }
          }
          //-------------------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Right)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("☃ =");
            }
          }
          //-------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Right2)
          {
            if(i == 1)
            {
              System.out.print("= =");
            }
            else if(i == 2)
            {
              System.out.print("☃ =");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Left)
          {
            if(i == 1)
            {
              System.out.print("===");
            }
            else if(i == 2)
            {
              System.out.print("=☃ ");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Left2)
          {
            if(i == 1)
            {
              System.out.print("= =");
            }
            else if(i == 2)
            {
              System.out.print("=☃ ");
            }
          }
          //------------------------------------------------------------------------------
          //towers
          //-----------------------------------------------------------------------------
          else if (spot == Tower_Archer_1)
          {
            if(i == 1)
            {
              System.out.print("_^_");
            }
            else if(i == 2)
            {
              System.out.print("⥉A1");
            }
          }
          else if (spot == Tower_Archer_2)
          {
            if(i == 1)
            {
              System.out.print("/^\\");
            }
            else if(i == 2)
            {
              System.out.print("⥉A2");
            }
          }
          else if (spot == Tower_Archer_3)
          {
            if(i == 1)
            {
              System.out.print("/⥉\\");
            }
            else if(i == 2)
            {
              System.out.print("⚐A1");
            }
          }
          else if (spot == Tower_Boom_1)
          {
            if(i == 1)
            {
              System.out.print("|_|");
            }
            else if(i == 2)
            {
              System.out.print("💣1");
            }
          }
          else if (spot == Tower_Boom_2)
          {
            if(i == 1)
            {
              System.out.print("^_^");
            }
            else if(i == 2)
            {
              System.out.print("💣2");
            }
          }
          else if (spot == Tower_Boom_3)
          {
            if(i == 1)
            {
              System.out.print("*^*");
            }
            else if(i == 2)
            {
              System.out.print("💣3");
            }
          }
          else if (spot == Tower_Leach_1)
          {
            if(i == 1)
            {
              System.out.print("|+|");
            }
            else if(i == 2)
            {
              System.out.print("$1$");
            }
          }
          else if (spot == Tower_Leach_2)
          {
            if(i == 1)
            {
              System.out.print("+-+");
            }
            else if(i == 2)
            {
              System.out.print("$2$");
            }
          }
          else if (spot == Tower_Leach_3)
          {
            if(i == 1)
            {
              System.out.print("+^+");
            }
            else if(i == 2)
            {
              System.out.print("$3$");
            }
          }
          else if (spot == Tower_Team_1)
          {
            if(i == 1)
            {
              System.out.print("{|}");
            }
            else if(i == 2)
            {
              System.out.print("T:1");
            }
          }
          else if (spot == Tower_Team_2)
          {
            if(i == 1)
            {
              System.out.print("(|)");
            }
            else if(i == 2)
            {
              System.out.print("T:2");
            }
          }
          else if (spot == Tower_Team_3)
          {
            if(i == 1)
            {
              System.out.print("(^)");
            }
            else if(i == 2)
            {
              System.out.print("T:3");
            }
          }
          else if (spot == Tower_Team_4)
          {
            if(i == 1)
            {
              System.out.print("{T}");
            }
            else if(i == 2)
            {
              System.out.print("GOD");
            }
          }
          else if (spot == Tower_Sticky_1)
          {
            if(i == 1)
            {
              System.out.print("{🍯}");
            }
            else if(i == 2)
            {
              System.out.print("S:1");
            }
          }
          else if (spot == Tower_Sticky_2)
          {
            if(i == 1)
            {
              System.out.print("(🍯)");
            }
            else if(i == 2)
            {
              System.out.print("S:2");
            }
          }
          else if (spot == Tower_Sticky_3)
          {
            if(i == 1)
            {
              System.out.print("(🍯)");
            }
            else if(i == 2)
            {
              System.out.print("S3⚐");
            }
          }
          else if (spot == Tower_Destroyer_1)
          {
            if(i == 1)
            {
              System.out.print("Des");
            }
            else if(i == 2)
            {
              System.out.print("<1>");
            }
          }
          else if (spot == Tower_Destroyer_2)
          {
            if(i == 1)
            {
              System.out.print("Des");
            }
            else if(i == 2)
            {
              System.out.print("<2>");
            }
          }
          else if (spot == Tower_Destroyer_3)
          {
            if(i == 1)
            {
              System.out.print("Des");
            }
            else if(i == 2)
            {
              System.out.print("<3>");
            }
          }
          else if (spot == Tower_Tazer_1)
          {
            if(i == 1)
            {
              System.out.print("Taz");
            }
            else if(i == 2)
            {                  
              System.out.print("⚛︎ 1");
            }
          }
          else if (spot == Tower_Tazer_2)
          {
            if(i == 1)
            {
              System.out.print("Des");
            }
            else if(i == 2)
            {
              System.out.print("⚛︎ 2");
            }
          }
          else if (spot == Tower_Tazer_3)
          {
            if(i == 1)
            {
              System.out.print("Taz");
            }
            else if(i == 2)
            {
              System.out.print("⚛︎ 3");
            }
          }
          else if (spot == Tower_Guardian_1)
          {
            if(i == 1)
            {
              System.out.print("G 1");
            }
            else if(i == 2)
            {                  
              System.out.print("[☄]");
            }
          }
          else if (spot == Tower_Guardian_2)
          {
            if(i == 1)
            {
              System.out.print("G 2");
            }
            else if(i == 2)
            {
              System.out.print("[☄]");
            }
          }
          else if (spot == Tower_Guardian_3)
          {
            if(i == 1)
            {
              System.out.print("G 3");
            }
            else if(i == 2)
            {
              System.out.print("[☄]");
            }
          }
        }
        if (y > 0)
        {
          System.out.println();
        }
      }
    }
  }

  public void displayMedium()
  {
    int spot;
    System.out.println("████1███2███3███4███5███6███7███8███9███10██11██12██13██14██15██16█");
    for (int y = 0; y <= 11; y++)
    {
      for (int i = 1; i <= 3; i++)
      {
        if ((i == 1)&&(y != 0)&& (y != 11))
        {
          if (y == 10)
          {
            System.out.print(y + "█");
          }
          else
          {
            System.out.print(y + "██");
          }
        }
        else if ((y <=10)&&(y!=0))
        {
          System.out.print("███");
        }
        for (int x = 0; x <= 17; x++)
        {
          spot = playSpace[y][x];
          if (spot == Boarder)
          {
            //System.out.print("");
          }
          //------------------------------------------------
          else if (spot == Real_Estate)
          {
            System.out.print("[--]");
          }
          //--------------------------------------------------
          else if (spot == Walk_Way_Straight)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print("    ");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //-------------------------------------
          else if(spot == Walk_Way_Down)
          {
            if(i == 1)
            {
              System.out.print("=  =");
            }
            else if(i == 2)
            {
              System.out.print("=  =");
            }
            else if(i == 3)
            {
              System.out.print("=  =");
            }
          } 
          //------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Right)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print("   =");
            }
            else if(i == 3)
            {
              System.out.print("=  =");
            }
          }
          //-------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Right2)
          {
            if(i == 1)
            {
              System.out.print("=  =");
            }
            else if(i == 2)
            {
              System.out.print("   =");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Left)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print("=   ");
            }
            else if(i == 3)
            {
              System.out.print("=  =");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Walk_Way_Corner_Left2)
          {
            if(i == 1)
            {
              System.out.print("=  =");
            }
            else if(i == 2)
            {
              System.out.print("=   ");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Spawn)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print("-S -");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Goal_Zone)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print("⚐H ⚐");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //-------------------------------------------------------------------------------------

          //-------------------------------------------------------------------------------------
          //Enemy occupied walk ways
          else if (spot == Enemy_Occupied_Walk_Way_Straight)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print("  🕷 ");
            }            
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //----------------------------------------------------------------------------------
          else if(spot == Enemy_Occupied_Walk_Way_Down)
          {
            if(i == 1)
            {
              System.out.print("=  =");
            }
            else if(i == 2)
            {
              System.out.print("=🕷 =");
            }
            else if(i == 3)
            {
              System.out.print("=  =");
            }
          }
          //-------------------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Right)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print(" 🕷 =");
            }
            else if(i == 3)
            {
              System.out.print("=  =");
            }
          }
          //-------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Right2)
          {
            if(i == 1)
            {
              System.out.print("=  =");
            }
            else if(i == 2)
            {
              System.out.print(" 🕷 =");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Left)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print("= 🕷 ");
            }
            else if(i == 3)
            {
              System.out.print("=  =");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Enemy_Occupied_Walk_Way_Corner_Left2)
          {
            if(i == 1)
            {
              System.out.print("=  =");
            }
            else if(i == 2)
            {
              System.out.print("= 🕷 ");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //--------------Super enemy-----------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Straight)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print("  ☃ ");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }            
          }
          //----------------------------------------------------------------------------------
          else if(spot == Super_Enemy_Occupied_Walk_Way_Down)
          {
            if(i == 1)
            {
              System.out.print("=  =");
            }
            else if(i == 2)
            {
              System.out.print("= ☃=");
            }
            else if(i == 3)
            {
              System.out.print("=  =");
            }
          }
          //-------------------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Right)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print(" ☃ =");
            }
            else if(i == 3)
            {
              System.out.print("=  =");
            }
          }
          //-------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Right2)
          {
            if(i == 1)
            {
              System.out.print("=  =");
            }
            else if(i == 2)
            {
              System.out.print(" ☃ =");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //------------------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Left)
          {
            if(i == 1)
            {
              System.out.print("====");
            }
            else if(i == 2)
            {
              System.out.print("= ☃ ");
            }
            else if(i == 3)
            {
              System.out.print("=  =");
            }
          }
          //-----------------------------------------------------------------------------
          else if (spot == Super_Enemy_Occupied_Walk_Way_Corner_Left2)
          {
            if(i == 1)
            {
              System.out.print("=  =");
            }
            else if(i == 2)
            {
              System.out.print("= ☃ ");
            }
            else if(i == 3)
            {
              System.out.print("====");
            }
          }
          //------------------------------------------------------------------------------
          //towers
          //-----------------------------------------------------------------------------
          else if (spot == Tower_Archer_1)
          {
            if(i == 1)
            {
              System.out.print("_^^_");
            }
            else if(i == 2)
            {
              System.out.print("⥉A1⥉");
            }
            else if(i == 3)
            {
              System.out.print("{--}");
            }
          }
          else if (spot == Tower_Archer_2)
          {
            if(i == 1)
            {
              System.out.print("/^^\\");
            }
            else if(i == 2)
            {
              System.out.print("⥉A2⥉");
            }
            else if(i == 3)
            {
              System.out.print("{~~}");
            }
          }
          else if (spot == Tower_Archer_3)
          {
            if(i == 1)
            {
              System.out.print("/⥉⥉\\");
            }
            else if(i == 2)
            {
              System.out.print("⚐A1⚐");
            }
            else if(i == 3)
            {
              System.out.print("\\++/");
            }
          }
          else if (spot == Tower_Boom_1)
          {
            if(i == 1)
            {
              System.out.print("|__|");
            }
            else if(i == 2)
            {
              System.out.print("💣B1");
            }
            else if(i == 3)
            {
              System.out.print("VVVV");
            }
          }
          else if (spot == Tower_Boom_2)
          {
            if(i == 1)
            {
              System.out.print("^__^");
            }
            else if(i == 2)
            {
              System.out.print("💣B2");
            }
            else if(i == 3)
            {
              System.out.print("VWWV");
            }
          }
          else if (spot == Tower_Boom_3)
          {
            if(i == 1)
            {
              System.out.print("|💣|");
            }
            else if(i == 2)
            {
              System.out.print("💣B3");
            }
            else if(i == 3)
            {
              System.out.print("W++W");
            }
          }
          else if (spot == Tower_Leach_1)
          {
            if(i == 1)
            {
              System.out.print("|++|");
            }
            else if(i == 2)
            {
              System.out.print("$L1$");
            }
            else if(i == 3)
            {
              System.out.print("(##)");
            }
          }
          else if (spot == Tower_Leach_2)
          {
            if(i == 1)
            {
              System.out.print("+--+");
            }
            else if(i == 2)
            {
              System.out.print("$L2$");
            }
            else if(i == 3)
            {
              System.out.print("(())");
            }
          }
          else if (spot == Tower_Leach_3)
          {
            if(i == 1)
            {
              System.out.print("+^^+");
            }
            else if(i == 2)
            {
              System.out.print("$L3$");
            }
            else if(i == 3)
            {
              System.out.print("⚐()⚐");
            }
          }
          else if (spot == Tower_Team_1)
          {
            if(i == 1)
            {
              System.out.print("{->}");
            }
            else if(i == 2)
            {
              System.out.print("T: 1");
            }
            else if(i == 3)
            {
              System.out.print("⚐<>⚐");
            }
          }
          else if (spot == Tower_Team_2)
          {
            if(i == 1)
            {
              System.out.print("<-->");
            }
            else if(i == 2)
            {
              System.out.print("T: 2");
            }
            else if(i == 3)
            {
              System.out.print("⚐[]⚐");
            }
          }
          else if (spot == Tower_Team_3)
          {
            if(i == 1)
            {
              System.out.print("<-+>");
            }
            else if(i == 2)
            {
              System.out.print("T:v3");
            }
            else if(i == 3)
            {
              System.out.print("⚐{}⚐");
            }
          }
          else if (spot == Tower_Team_4)
          {
            if(i == 1)
            {
              System.out.print("<<>>");
            }
            else if(i == 2)
            {
              System.out.print("GOD⚐");
            }
            else if(i == 3)
            {
              System.out.print("⚐!!⚐");
            }
          }
          else if (spot == Tower_Sticky_1)
          {
            if(i == 1)
            {
              System.out.print("{🍯>}");
            }
            else if(i == 2)
            {
              System.out.print("S: 1");
            }
            else if(i == 3)
            {
              System.out.print("ssss");
            }
          }
          else if (spot == Tower_Sticky_2)
          {
            if(i == 1)
            {
              System.out.print("(🍯>)");
            }
            else if(i == 2)
            {
              System.out.print("S: 2");
            }
            else if(i == 3)
            {
              System.out.print("s--s");
            }
          }
          else if (spot == Tower_Sticky_3)
          {
            if(i == 1)
            {
              System.out.print("(🍯>)");
            }
            else if(i == 2)
            {
              System.out.print("S:3⚐");
            }
            else if(i == 3)
            {
              System.out.print("s++s");
            }
          }
          else if (spot == Tower_Destroyer_1)
          {
            if(i == 1)
            {
              System.out.print("Des💥");
            }
            else if(i == 2)
            {
              System.out.print("D: 1");
            }
            else if(i == 3)
            {
              System.out.print("<==>");
            }
          }
          else if (spot == Tower_Destroyer_2)
          {
            if(i == 1)
            {
              System.out.print("Des💥");
            }
            else if(i == 2)
            {
              System.out.print("D: 2");
            }
            else if(i == 3)
            {
              System.out.print("<<>>");
            }
          }
          else if (spot == Tower_Destroyer_3)
          {
            if(i == 1)
            {
              System.out.print("Des💥");
            }
            else if(i == 2)
            {
              System.out.print("D: 3");
            }
            else if(i == 3)
            {
              System.out.print("</\\>");
            }
          }
          else if (spot == Tower_Tazer_1)
          {
            if(i == 1)
            {
              System.out.print("Taz⌁");
            }
            else if(i == 2)
            {                  
              System.out.print("⚛︎: 1");
            }
            else if(i == 3)
            {
              System.out.print("<%%>");
            }
          }
          else if (spot == Tower_Tazer_2)
          {
            if(i == 1)
            {
              System.out.print("Taz⌁");
            }
            else if(i == 2)
            {
              System.out.print("⚛: 2");
            }
            else if(i == 3)
            {
              System.out.print("<⌁⌁>");
            }
          }
          else if (spot == Tower_Tazer_3)
          {
            if(i == 1)
            {
              System.out.print("Taz⌁");
            }
            else if(i == 2)
            {
              System.out.print("⌁⌁3⚐");
            }
            else if(i == 3)
            {
              System.out.print("⌁⌁⌁>");
            }
          }
          else if (spot == Tower_Guardian_1)
          {
            if(i == 1)
            {
              System.out.print("[☄] ");
            }
            else if(i == 2)
            {                  
              System.out.print("G: 1");
            }
            else if(i == 3)
            {
              System.out.print("=++=");
            }
          }
          else if (spot == Tower_Guardian_2)
          {
            if(i == 1)
            {
              System.out.print("[☄]^");
            }
            else if(i == 2)
            {
              System.out.print("G: 2");
            }
            else if(i == 3)
            {
              System.out.print("}<>{");
            }
          }
          else if (spot == Tower_Guardian_3)
          {
            if(i == 1)
            {
              System.out.print("<|☄|");
            }
            else if(i == 2)
            {
              System.out.print("G 3⚐");
            }
            else if(i == 3)
            {
              System.out.print("⌁⌁⌁>");
            }
          }
        }
        if (y > 0)
        {
          System.out.println();
        }
      }
    }
  }
  ///////
  
}
//🕷🕷🕷☃☃☃☃☃☃💦💦💦🍯🍯☄☄

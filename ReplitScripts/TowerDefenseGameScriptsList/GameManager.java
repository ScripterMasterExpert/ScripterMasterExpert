mport java.util.Scanner;
public class GameManager
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    Map playSpace = new Map(); //the map
    Player you = new Player();
    boolean isSmall = false;
    boolean isMed = false;
    boolean isBig = false;
    boolean shouldSpawn = true;
    boolean everyOther = true;
    Enemy[] enemyArray = new Enemy[10];

    boolean hasGuardian = false;
    int guardX = 0;
    //
    //instantiates all enemies to something
    //
    for (int i = 0; i < enemyArray.length; i++)
    {
      enemyArray[i] = new Enemy(1);
    }
    Tower[] towerArray = new Tower[50];
    for (int i = 0; i < towerArray.length; i++)
    {
     towerArray[i] = new Tower(); 
    }
    int enemies = 0; //current amount of enemies
    int towers = 0;
    int enemiesSpawned = 0; //amount of enemies spawned thus far this round
    
    int Enemy_Max = 10; //maximum amount of enemies allowed at a time

    boolean isPlaying = false; //represents whether or not a wave is being sent out
    int wave = 0;//represents the wave number you are on 
    String input;
    int timeToSkip = 1;
    String blank;
    int timeUnit = 0; //1 for each unit of time

    //testing code
    System.out.println("Input \"start\" to start the game:");
    input = scan.next();
    if (input.equals("start"))
    {
      isPlaying = true;
    }
    System.out.println("This game requires a fairly large screen to display properly.");
    System.out.println("If you would like, the game can instead run in mini-mode.");
    System.out.println("In mini-mode, the game will be condensed and not look as good, but it will fit on your screen (probably).");
    System.out.println("To play in BIG mode enter (1)");
    System.out.println("To play in Medium mode enter (2)");
    System.out.println("To play in mini-mode enter (3)");
    input = scan.next();
    if (input.equals("1")||input.equals("(1)"))
    {
      isSmall = false;
      isBig = true;
      isMed = false;
    }
    else if (input.equals("2")||input.equals("(2)"))
    {
      isSmall = false;
      isBig = false;
      isMed = true;
    }
    else
    {
      isSmall = true;
      isBig = false;
      isMed = false;
    }
    you.setMoney(300);
    while(isPlaying)
    {
      wave++;
      System.out.println("Starting wave: " + wave);
      enemyArray = Enemy.prepareWave(wave, enemyArray);
      Enemy_Max = enemyArray.length;
      you.setMoney(you.getMoney() + you.getIncome());//increases your money
      //prepares the wave's enemies
      while (isPlaying && ((enemies != 0) || (enemiesSpawned != Enemy_Max-1))) //continue this wave
      {
        for(int j = 0; (j < timeToSkip)&&(isPlaying); j++)  
        {
          /////////////////
          if(wave >= 10)
          {
            shouldSpawn = true;
          }
          else if (timeUnit % 2 == 0)
          {
            shouldSpawn = true;
          }
          else
          {
            shouldSpawn = false;
          }
          /////////////////////
          if(enemiesSpawned < Enemy_Max-1 && shouldSpawn  )//every other time unit, an enemy is spawned until 9 have been spawned
          {
            playSpace.spawnEnemy(enemyArray[enemiesSpawned]);
            enemies++;
            enemiesSpawned++;
          }
          for (int i = 0; (i < enemyArray.length); i++)//move the enemies
          {
            if (enemyArray[i].getEType() == 1)
            {
              playSpace.translateEnemy(enemyArray[i], you); 
            }
            else
            {
              playSpace.translateSuperEnemy(enemyArray[i], you);
            }
          }
          
          for (int i = 0; (i < towers) && (towers != 0); i++)//have towers attack
          {
            enemyArray = playSpace.towerAttack(towerArray[i], enemyArray, you);
          }
          enemies = playSpace.enemyOccupiedSpaces();
          timeUnit++;
          isPlaying = you.isAlive();//checks if you lost already
        }
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        timeToSkip = 0;//attempts to stop 400 seconds error
        for(int gh = 1; gh <= 10; gh++)
        {
          System.out.println();
        } 
        if (isSmall)
        {
          playSpace.displayMini();
        }
        else if(isBig)
        {
          playSpace.display();
        }
        else
        {
          playSpace.displayMedium();
        }
        if (isPlaying && ((enemies != 0) || (enemiesSpawned != Enemy_Max-1)))
        {
          System.out.println("You are on wave " + wave);
          System.out.println(timeUnit + " seconds have past.");
          System.out.println(enemiesSpawned + " enemies have been spawned.");
          System.out.println("You have " + you.getLives() + " lives left.");
          System.out.println("You have " + you.getMoney() + " money left.");
          if (hasGuardian)
          {
            System.out.println("You Guardian ability will be ready in " + (towerArray[guardX].getCoolDown() - towerArray[guardX].getCoolDownUsed()) +" seconds.");
          }
          System.out.println("What will you do? (Input the number for your choice)");
          System.out.println("(1) advance time");
          System.out.println("(2) buy a tower");
          System.out.println("(3) upgrade a tower");
          System.out.println("(4) read rules");
          System.out.println("(5) read enemy guide");
          
          blank = scan.next();
          if (blank.equals("1"))//pass time
          {
            System.out.println("How many units of time would you like to skip?");
            timeToSkip = 0;
            try
            {
              timeToSkip = scan.nextInt();
              System.out.println("You will now skip that time");
            }
            catch(Exception e)
            {
              System.out.println("That's not a valid amount of time. Try again. ");
              System.out.println("If you entered in a really big number, don't.");
            }
          }
          else if(blank.equals("2"))//buy new tower
          {
            timeToSkip = 1;
            System.out.println("What Tower would you like to buy?");
            System.out.println("(1) Archer Tower  --- price: 300");
            System.out.println("(2) Boom Tower    --- price: 500");
            System.out.println("(3) Leach Tower   --- price: 500");
            System.out.println("(4) Team Tower    --- price: 400");
            System.out.println("(5) Sticky Tower  --- price: 400");
            System.out.println("(6) Destroyer     ---  price: 1000");
            System.out.println("(7) Tazer Tower   ---  price: 800");
            System.out.println("(8) Guardian Tower---  price: 700 (you can only have 1)");
            System.out.println("(11) Read about Archer Tower");
            System.out.println("(12) Read about Boom Tower");
            System.out.println("(13) Read about Leach Tower");
            System.out.println("(14) Read about Team Tower");
            System.out.println("(15) Read about Team Tower");
            System.out.println("(16) Read about Destroyer Tower");
            System.out.println("(17) Read about Tazer Tower");
            input = scan.next();
            if(input.equals("1"))//buy Archer tower
            {
              if ((you.getMoney() - 300) >= 0)
              {  
                System.out.println("Eneter where you want to build the tower (ex: 3,5)");
                System.out.println("Type the x coordinate, then the y coordinate, seperated by a comma");
                input = scan.next();
                try
                {
                  int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
                  int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
                  towerArray[towers]  = new Tower("Archer", x, y, playSpace);//puts the tower in the spot which opened up for it
                  System.out.println(towerArray[towers].getName());
                  System.out.println("You bought an Archer tower for 500 coins");
                  you.setMoney(you.getMoney() - 300);
                  towers++;//amount of towers increases by 1
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
              else
              {
                System.out.println("bruh, you don't have the cash.");
              }
            }
            else if(input.equals("2"))//buy Boom tower
            {
              System.out.println("Eneter where you want to build the tower (ex: 3,5)");
              System.out.println("Type the x coordinate, then the y coordinate, seperated by a comma");
              input = scan.next();
              if ((you.getMoney() - 500) >= 0)
              {  
                try
                {
                  int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
                  int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
                  towerArray[towers]  = new Tower("Boom", x, y, playSpace);//puts the tower in the spot which opened up for it
                  System.out.println(towerArray[towers].getName());
                  System.out.println("You bought a Boom tower for 500 coins");
                  you.setMoney(you.getMoney() - 500);
                  towers++;//amount of towers increases by 1
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
              else
              {
                System.out.println("bruh, you don't have the cash.");
              }
            }
            else if(input.equals("3"))//buy Leach tower
            {
              if ((you.getMoney() - 500) >= 0)
              {  
                System.out.println("Eneter where you want to build the tower (ex: 3,5)");
                input = scan.next();
                try
                {
                  int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
                  int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
                  towerArray[towers]  = new Tower("Leach", x, y, playSpace);//puts the tower in the spot which opened up for it
                  System.out.println(towerArray[towers].getName());
                  System.out.println("You bought a Leach tower for 500 coins");
                  you.setMoney(you.getMoney() - 500);
                  towers++;//amount of towers increases by 1
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
              else
              {
                System.out.println("bruh, you don't have the cash.");
              }
            }
            else if(input.equals("4"))//buy Team tower
            {
              if ((you.getMoney() - 400) >= 0)
              {  
                System.out.println("Eneter where you want to build the tower (ex: 3,5)");
                input = scan.next();
                try
                {
                  int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
                  int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
                  towerArray[towers]  = new Tower( x, y, playSpace, towerArray);//puts the tower in the spot which opened up for it
                  System.out.println(towerArray[towers].getName());
                  System.out.println("You bought a Team tower for 400 coins");
                  you.setMoney(you.getMoney() - 400);
                  towers++;//amount of towers increases by 1
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
              else
              {
                System.out.println("bruh, you don't have the cash.");
              }
            }
            else if(input.equals("5"))//buy Sticky tower
            {
              if ((you.getMoney() - 400) >= 0)
              {  
                System.out.println("Eneter where you want to build the tower (ex: 3,5)");
                input = scan.next();
                try
                {
                  int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
                  int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
                  towerArray[towers]  = new Tower("Sticky", x, y, playSpace);//puts the tower in the spot which opened up for it
                  System.out.println(towerArray[towers].getName());
                  System.out.println("You bought a Sticky tower for 400 coins");
                  you.setMoney(you.getMoney() - 400);
                  towers++;//amount of towers increases by 1
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
              else
              {
                System.out.println("bruh, you don't have the cash.");
              }
            }
            else if(input.equals("6"))//buy destroyer
            {
              if ((you.getMoney() - 1000) >= 0)
              {  
                System.out.println("Eneter where you want to build the tower (ex: 3,5)");
                input = scan.next();
                try
                {
                  int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
                  int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
                  towerArray[towers]  = new Tower("Destroyer", x, y, playSpace);//puts the tower in the spot which opened up for it
                  System.out.println(towerArray[towers].getName());
                  System.out.println("You bought a Destroyer tower for 1000 coins");
                  you.setMoney(you.getMoney() - 1000);
                  towers++;//amount of towers increases by 1
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
              else
              {
                System.out.println("bruh, you don't have the cash.");
              }
            }
            else if(input.equals("8"))//buy Guardian
            {
              if (!hasGuardian &&(you.getMoney() - 700) >= 0)
              {  
                System.out.println("Eneter where you want to build the tower (ex: 3,5)");
                input = scan.next();
                try
                {
                  int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
                  int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
                  towerArray[towers]  = new Tower("Guardian", x, y, playSpace);//puts the tower in the spot which opened up for it

                  System.out.println(towerArray[towers].getName());
                  System.out.println("You bought a Guardian tower for 1000 coins");
                  you.setMoney(you.getMoney() - 700);
                  towers++;//amount of towers increases by 1
                  guardX = towers -1;
                  //guardY = y;
                  hasGuardian = true;
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
              else
              {
                System.out.println("bruh, you don't have the cash.");
              }
            }
            else if(input.equals("7"))//buy Tazer
            {
              if ((you.getMoney() - 800) >= 0)
              {  
                System.out.println("Eneter where you want to build the tower (ex: 3,5)");
                input = scan.next();
                try
                {
                  int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
                  int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
                  towerArray[towers]  = new Tower("Tazer", x, y, playSpace);//puts the tower in the spot which opened up for it
                  System.out.println(towerArray[towers].getName());
                  System.out.println("You bought a Destroyer tower for 1000 coins");
                  you.setMoney(you.getMoney() - 800);
                  towers++;//amount of towers increases by 1
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
              else
              {
                System.out.println("bruh, you don't have the cash.");
              }
            }
            else if(input.equals("11"))
            {
              Tower.towerDescription("Archer");
              System.out.println("input something random to continue:");
              input = scan.next();
            }
            else if(input.equals("12"))
            {
              Tower.towerDescription("Boom");
              System.out.println("input something random to continue:");
              input = scan.next();
            }
            else if(input.equals("13"))
            {
              Tower.towerDescription("Leach");
              System.out.println("input something random to continue:");
              input = scan.next();
            }
            else if(input.equals("14"))
            {
              Tower.towerDescription("Team");
              System.out.println("input something random to continue:");
              input = scan.next();
            }
            else if(input.equals("15"))
            {
              Tower.towerDescription("Sticky");
              System.out.println("input something random to continue:");
              input = scan.next();
            }
            else if(input.equals("16"))
            {
              Tower.towerDescription("Destroyer");
              System.out.println("input something random to continue:");
              input = scan.next();
            }
            else if(input.equals("17"))
            {
              Tower.towerDescription("Tazer");
              System.out.println("input something random to continue:");
              input = scan.next();
            }
            else
            {
              Tower.towerDescription("Guardian");
              System.out.println("input something random to continue:");
              input = scan.next();
            }
          }
          else if(blank.equals("3"))//upgrade tower
          {
            timeToSkip = 0;
            System.out.println("Here are the prices for upgrades:");
            System.out.println("Archer: upgrade 1: 200   upgrade 2: 200");
            System.out.println("Boom: upgrade 1: 400   upgrade 2: 600");
            System.out.println("Leach: upgrade 1: 500   upgrade 2: 700");
            System.out.println("Team: build a new Team tower next to another one");
            System.out.println("Sticky: upgrade 1: 300  upgrade 2: 300");
            System.out.println("Destroyer: upgrade 1: 1000 upgrade 2: 2000");
            System.out.println("Tazer: upgrade 1: 700  upgrade 2: 700 ");
            System.out.println("Guardian: upgrade 1: 1000  upgrade 2: 2500");
            System.out.println("What tower would you like to upgrade?");
            System.out.println("input the coordinate of the tower (ex 4,5):");
            input = scan.next();

            try
            {
              int x = Integer.parseInt(input.substring(0,input.indexOf(",")));
              int y = Integer.parseInt(input.substring(input.indexOf(",")+1));
              for (int a = 0; a <= towers; a++)
              {
                if ((towerArray[a].getPosition().getX() == x)&&(towerArray[a].getPosition().getY() == y))
                {
                  if (towerArray[a].getLevel() < 3)
                  {
                    if (towerArray[a].getName().equals("Archer"))
                    {
                      if ((you.getMoney() - 200) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 200);
                      }
                      else
                      {
                        System.out.println("You don't have the cash for that bro.");
                      }  
                    }
                    else if (towerArray[a].getName().equals("Boom"))
                    {
                      if ((towerArray[a].getLevel() == 1) && (you.getMoney() - 400) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 400);
                      }
                      else if ((towerArray[a].getLevel() == 2) && (you.getMoney() - 600) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 600);
                      }
                      else
                      {
                        System.out.println("You don't have the cash for that bro.");
                      }
                    }
                    else if (towerArray[a].getName().equals("Leach"))
                    {
                      if ((towerArray[a].getLevel() == 1) && (you.getMoney() - 500) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 500);
                      }
                      else if ((towerArray[a].getLevel() == 2) && (you.getMoney() - 700) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 700);
                      }
                      else
                      {
                        System.out.println("You don't have the cash for that bro.");
                      }
                    }
                    else if (towerArray[a].getName().equals("Sticky"))
                    {
                      if ((towerArray[a].getLevel() == 1) && (you.getMoney() - 300) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 300);
                      }
                      else if ((towerArray[a].getLevel() == 2) && (you.getMoney() - 300) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 300);
                      }
                      else
                      {
                        System.out.println("You don't have the cash for that bro.");
                      }
                    }
                    else if (towerArray[a].getName().equals("Destroyer"))
                    {
                      if ((towerArray[a].getLevel() == 1) && (you.getMoney() - 1000) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 1000);
                      }
                      else if ((towerArray[a].getLevel() == 2) && (you.getMoney() - 2000) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 2000);
                      }
                      else
                      {
                        System.out.println("You don't have the cash for that bro.");
                      }
                    }
                    else if (towerArray[a].getName().equals("Tazer"))
                    {
                      if ((towerArray[a].getLevel() == 1) && (you.getMoney() - 700) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 700);
                      }
                      else if ((towerArray[a].getLevel() == 2) && (you.getMoney() - 700) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 700);
                      }
                      else
                      {
                        System.out.println("You don't have the cash for that bro.");
                      }
                    }
                    else if (towerArray[a].getName().equals("Guardian"))
                    {
                      if ((towerArray[a].getLevel() == 1) && (you.getMoney() - 1000) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 1000);
                      }
                      else if ((towerArray[a].getLevel() == 2) && (you.getMoney() - 2500) >= 0)
                      {
                        towerArray[a] = playSpace.upgradeTower(towerArray[a]);
                        you.setMoney(you.getMoney() - 2500);
                      }
                      else
                      {
                        System.out.println("You don't have the cash for that bro.");
                      }
                    }
                  }
                }
              }
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
          else if(blank.equals("4"))//read rules
          {
            timeToSkip = 1;
            you.rules();
            System.out.println("input something random to continue:");
            input = scan.next();
          }
          else if(blank.equals("5"))//read enemy guide
          {
            timeToSkip = 1;
            you.guide();
            System.out.println("input something random to continue:");
            input = scan.next();
          }
          else
          {
            System.out.println("You gotta enter 1, 2, 3, 4, or 5. I don't understand whatever it is you just typed.");
            System.out.println("I'm a computer, not a psychic. I can't interpret the your cryptic text the way you humans can understand language.");
            System.out.println("All I understand are numbers, specifically 1, 2, 3, 4, and 5.");
            System.out.println("Just try and do what your told next time.");
            System.out.println("input something random to continue:");
            input = scan.next();
          }
        }
      }
      if (isPlaying)
      {
        System.out.println("You have beaten wave " + wave);
        System.out.println("Input (ok) to begin the next wave.");
        blank = scan.next();
        enemiesSpawned = 0;
      }
    }
    System.out.println();
    System.out.println("GAME OVER");
    System.out.println("You made it to wave " + wave + ".");
    System.out.println("That's pretty good I guess.");
    System.out.println("Maybe comment how far you made it or something.");
    System.out.println();
    System.out.println("Thanks for playing, have a nice day!");
    System.out.println("Have a nice day!");
    System.out.println("-Lucas");
  }
}

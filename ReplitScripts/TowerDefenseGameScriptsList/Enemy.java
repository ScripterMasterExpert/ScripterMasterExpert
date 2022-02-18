public class Enemy
{
  private int speed;
  private int slowed;
  private int health;
  private int maxHealth;
  private int level;

  private Coordinate position;
  private int eType = 1;
  private String graphic = "🕷";

  private boolean Alive;
  private boolean isStunned = false;

  public Enemy(int newlevel)
  {
    eType = 1;
    level = newlevel;
    defineEnemy();
    position = new Coordinate(0,0);
    Alive = false;
  }
  public Enemy(int newlevel, int type)
  {
    eType = type;
    level = newlevel;
    defineEnemy();
    position = new Coordinate(0,0);
    Alive = false;
  }

  private void defineEnemy()
  {
    if (eType == 1)
    {
      health = level;
      maxHealth = health;
      speed = 1;
    }
    else if (eType == 2)
    {
      health = (int)(level/2);
      maxHealth = health;
      speed = level;
    }
  }
  //returns the position value
  public Coordinate getPosition()
  {
    return position;
  }
  public int getLevel()
  {
    return level;
  }
  //returns the health
  public int getHealth()
  {
    return health;
  }
  public boolean isAlive()
  {
    return Alive;
  }
  //returns the speed
  public int getSpeed()
  {
    return speed;
  }

  //returns the eType
  public int getEType()
  {
    return eType;
  }
  
  public int getSlowed()
  {
    return slowed;
  }

  public void setSlowed(int newslow)
  {
    slowed = newslow;
  }
  public void stun()
  {
    isStunned = true;
  }
  public void stunWearOff()
  {
    isStunned = false;
  }
  public boolean getStunStatus()
  {
    return isStunned;
  }
  //sets the position value
  public void setPosition(int x, int y)
  {
    if (Alive)
    {
      position.setCoordinate(x, y);
    }
  }
  
  //moves the position of the enemy down 1
  public void movePositionDown()
  {
    if (Alive)
    {
      setPosition(position.getX(), position.getY() + 1);
    }
  
  }

  //moves the position of the enemy left 1
  public void movePositionLeft()
  {
    if (Alive)
    {
      setPosition(position.getX() - 1, position.getY());
    }

  }

  //moves the position of the enemy right 1
  public void movePositionRight()
  {
    if (Alive)
    {
      setPosition(position.getX() + 1, position.getY());
    }
  }

  //takesDamage
  public void takeDamage(int damage)
  {
    health -= damage;
    if (health <= 0)
    {
      die();
    }
  }

  //dies
  public void die()
  {
    position.setCoordinate(0,0);
    Alive = false;
  }

  //revive
  public void revive()
  {
    health = maxHealth;
    Alive = true;
  }

  public static Enemy[] prepareWave(int wave, Enemy[] enemyArray)
  {
    Enemy[] output = new Enemy[enemyArray.length];
    for (int v = 0; v < output.length; v++)
    {
      output[v] = new Enemy(1);
    }
    
    if(wave == 1)
    {
      for (int i = 0; i < output.length; i++)
      {
        output[i] = new Enemy(1);
      }
    }
    else if(wave == 2)
    {
      output = new Enemy[7];
      for (int i = 0; i < output.length; i++)
      {
        output[i] = new Enemy(2);
      }
    }
    else if(wave == 3)
    {
      output = new Enemy[10];
      for (int i = 0; i < output.length; i++)
      {
        if (i <=5)
        {
          output[i] = new Enemy(1);
        }
        else
        {
          output[i] = new Enemy(3, 2);
        }
      }
    }
    else if(wave == 4)
    {
      output = new Enemy[10];
      for (int i = 0; i < output.length; i++)
      {
        output[i] = new Enemy(3, 2);
      }
    }
    else if(wave == 5)
    {
      output = new Enemy[10];
      for (int i = 0; i < output.length; i++)
      {
        output[i] = new Enemy(4, 2);
      }
    }
    else if(wave == 6)
    {
      output = new Enemy[10];
      for (int i = 0; i < output.length; i++)
      {
        if (i <7)
        {
          output[i] = new Enemy(3, 2);
        }
        else
        {
          output[i] = new Enemy(15);
        }
      }
    }
    else if(wave == 7)
    {
      output = new Enemy[10];
      for (int i = 0; i < output.length; i++)
      {
        output[i] = new Enemy(10);
      }
    }
    else if(wave == 8)
    {
      output = new Enemy[10];
      for (int i = 0; i < output.length; i++)
      {
        output[i] = new Enemy(8, 2);
      }
    }
    else if(wave == 9)
    {
      output = new Enemy[10];
      for (int i = 0; i < output.length; i++)
      {
        if ((i % 2)==0)
        {
          output[i] = new Enemy(8, 2);
        }
        else
        {
          output[i] = new Enemy(25);
        }
      }
    }
    else if(wave == 10)
    {
      output = new Enemy[10];
      for (int i = 0; i < output.length; i++)
      {
        if (i==9)
        {
          output[i] = new Enemy(35);
        }
        else
        {
          output[i] = new Enemy(25);
        }
      }
    }
    else
    {
      if (wave <= 15)
      {
        output = new Enemy[wave];
        for (int i = 0; i < output.length; i++)
        {
          if (i==1)
          {
            output[i] = new Enemy(25 + (2 * wave));
          }
          else
          {
            output[i] = new Enemy(wave - 3, 2);
          }
        }
      }
      else if(wave <= 20)
      {
        output = new Enemy[wave];
        for (int i = 0; i < output.length; i++)
        {
          if (i==1)
          {
            output[i] = new Enemy(25 + (3 * wave));
          }
          else
          {
            output[i] = new Enemy(wave - 3, 2);
          }
        }
      }
      else if(wave <= 30)
      {
        output = new Enemy[wave];
        for (int i = 0; i < output.length; i++)
        {
          if (i <= 1)
          {
            output[i] = new Enemy(25 + (5 * wave));
          }
          else
          {
            output[i] = new Enemy((2*wave)- 25, 2);
          }
        }
      }
      else if (wave <= 40)
      {
        output = new Enemy[wave];
        for (int i = 0; i < output.length; i++)
        {
          if (i <= 2)
          {
            output[i] = new Enemy(25 + (10 * wave));
          }
          else
          {
            output[i] = new Enemy((2*wave)-25, 2);
          }
        }
      }
      else
      {
        output = new Enemy[wave];
        for (int i = 0; i < output.length; i++)
        {
          if (i <= 4)
          {
            output[i] = new Enemy(25 + ((int)Math.pow(wave, 2)));
          }
          else
          {
            output[i] = new Enemy((wave), 2);
          }
        }
      }
    }
    return output;
  }
}

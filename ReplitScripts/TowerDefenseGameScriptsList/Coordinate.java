class Solution {
    public int[][] updateMatrix(int[][] mat) 
    {
        int[][] nearest = new int[mat.length][mat[0].length];    
        for(int y = 0; y< nearest.length; y++)
        {
            for(int x = 0; x < nearest[0].length;x++)
            {
                nearest[y][x] = distanceFromZero(mat,x,y);
            }
        }
        return nearest;
    }
    
    private int distanceFromZero(int[][] grid,int x,int y)
    {
        if(grid[y][x] == 0)
        {
            return 0;
        }
        boolean found = false;
        int counter = 1;
        while(!found)
        {
            int curX = x -counter;
            int curY =y-counter;
            //checking around in a box
            for(int b = 1; b <= 4; b++)
            {
                for(int i = 0; i < (counter*2+1); i++)//x->
                {
                    //if(curX >= 0 && curX < grid[0].length && curY >= 0 && curY < grid.length)
                    //{
                        if(b == 1)
                        {
                            if((curY >= 0 && curY < grid.length && curX+i >=0 && curX+i < grid[0].length)
                               &&grid[curY][curX+i]== 0)
                            {
                                found = true;
                                return counter;
                            }
                        }
                        else if(b == 2)
                        {
                            if(grid[curY+i][curX]== 0)
                            {
                                found = true;
                                return counter;
                            }
                        }
                        else if(b == 3)
                        {
                            if(grid[curY+(counter*2)][curX+i]== 0)
                            {
                                found = true;
                                return counter;
                            }
                        }
                        else if(b == 4)
                        {
                            if(grid[curY+i][curX+(counter*2)]== 0)
                            {
                                found = true;
                                return counter;
                            } 
                        }
                    //}
                }                       
            }
            counter++;
        }
        return 0;
    }
}

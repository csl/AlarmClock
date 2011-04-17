package irdc.ex03_01;

import android.util.Log;

public class Pic {
	public static final int NOTHING = 0;
	
	public static final int UP_ONE = 13;
	
	public static final int DOWN_HIT = -9;
	
	int currentType = NOTHING;
	
	public void toNext(){
		if(currentType > 0){
			currentType --;
			if(currentType == NOTHING){
			  WhacAmoleView.self.hp --;
			}
		}
		else if(currentType < 0){
			currentType ++;
		}
	}

	public void toShow() {
		currentType = UP_ONE;
	}
	
	public void click(int indexX, int indexY, int [][]Matrix){
		if(currentType > NOTHING){
			currentType = DOWN_HIT;
			Matrix[indexX][indexY]++;
			
      //Log.d("TAG", WhacAmoleView.RandomMatrixMapX + " " + WhacAmoleView.RandomMatrixMapY);
      //Log.d("TAG", indexX + " " + indexY);
			
			if (WhacAmoleView.RandomMatrixMapX == indexY+1 && WhacAmoleView.RandomMatrixMapY == indexX+1)
			{
			  Log.d("TAGXY", indexX + " " + indexY);
			  if (Matrix[indexX][indexY] == WhacAmoleView.Randomclick)
			  {
    			  //press
    			  WhacAmoleView.Stage++;
    			  WhacAmoleView.WhacAmoleScore =  WhacAmoleView.Stage * 20; 
    			  if (WhacAmoleView.StageNum > WhacAmoleView.Stage)
    			  {
    			    for (int i=0; i<3; i++)
    			    {
    			      for (int j=0; j<4; j++)
    			      {
    			        Matrix[i][j] = 0;
    			      }     
    			    }	
    			    WhacAmoleView.RandomMatrixMapX = WhacAmoleView.random.nextInt(4) + 1;
    			    WhacAmoleView.RandomMatrixMapY = WhacAmoleView.random.nextInt(3) + 1;
    			    WhacAmoleView.Randomclick =  WhacAmoleView.random.nextInt(2) +1;
    			  }
			  }
			}
		}
	}
}

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
			
      WhacAmoleView.WhacAmoleScore =  WhacAmoleView.WhacAmoleScore + 20;
			
		}
	}
}

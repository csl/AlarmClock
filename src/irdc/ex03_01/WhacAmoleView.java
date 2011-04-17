package irdc.ex03_01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class WhacAmoleView extends View{
	
	static Random random = new Random();
	static int WhacAmoleScore;
	private int[][] MatrixMap = new int[3][4];
	static int StageNum = 3;	
	static int Stage = 0;
  static int RandomMatrixMapX = 0;
  static int RandomMatrixMapY = 0;
  static int Randomclick = 0;
	
	public WhacAmoleView(Context context) {
		super(context);
		self = this;
		
		WhacAmoleScore = 0;
		Stage = 0;
		
		RandomMatrixMapX = random.nextInt(4) + 1;
   RandomMatrixMapY = random.nextInt(3) + 1;
   Randomclick =  random.nextInt(2) +1;
		
		WhacAmoleView.this.postDelayed(flush80ms, 80);
		WhacAmoleView.this.postDelayed(flush1000ms, 1000);
		
		for (int i=0; i<3; i++)
		{
	    for (int j=0; j<4; j++)
	    {
	      MatrixMap[i][j] = 0;
	    }		  
		}
	}
	
	private List<Pic> holeQuite = new ArrayList<Pic>(Constants.HOLE_COUNT);
	{
		for(int i=0; i<Constants.HOLE_COUNT; i++){
			holeQuite.add(new Pic());
		}
	}
	
	int startX = 35;
	int startY = 60;
	
	static WhacAmoleView self;
	
	
	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		
		if(WhacAmoleScore>=60){
			getHandler().removeCallbacks(flush1000ms);
			getHandler().removeCallbacks(flush80ms);
			doGameOver();
			return ;
		}
		
		canvas.drawColor(Color.WHITE);
		drawInfoPanel(canvas);
		
		for(int i=0; i<holeQuite.size(); i++){
			Pic pic = holeQuite.get(i);
			
			int y = i / Constants.COLUMN_COUNT;
			int x = i % Constants.COLUMN_COUNT;
			
				Bitmap  bm = ImageManager.getBitmap(pic.currentType);
				canvas.drawBitmap(bm, startX + x*Constants.TILE_SIZE, 
						startY + y*Constants.TILE_SIZE, 
						PaintSuite.paintForQuite);
				pic.toNext();
		}
	}
	
	private void doGameOver() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("");
		builder.setMessage("遊戲結束了, 起床!!");
		builder.setCancelable(false);
		builder.setNeutralButton("CLick", new android.content.DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) 
			{
			  System.exit(0);
			}
		}).show();
	}

	public int hp = 40;
	public int progress = 800;
	public int aimProgress = 0;
	
	private void drawInfoPanel(Canvas canvas)
	{
		canvas.drawText("rule: (" + RandomMatrixMapX +  ","  + RandomMatrixMapY + ")," + "click " + Randomclick, 29, 20, PaintSuite.KV4text);
		canvas.drawText("Score:" + WhacAmoleScore , 29, 50, PaintSuite.KV4text);
	}


	Runnable flush80ms = new Runnable(){
		public void run(){
		  WhacAmoleView.this.invalidate();
		  WhacAmoleView.this.postDelayed(this, 100);
		}
	};

	Runnable flush1000ms = new Runnable(){
		public void run(){
			
			LinkedList<Pic> temp = new LinkedList<Pic>();
			for(Pic each : holeQuite)
			{
				if(each.currentType == Pic.NOTHING){
					temp.add(each);
				}
			}
			
			int size = temp.size();
			if(size == 1){
				temp.poll().toShow();
				WhacAmoleView.this.invalidate();
			}else if(size > 1){
				for(int i=0; i<random.nextInt(2) + 1; i++){
					temp.remove(random.nextInt(temp.size())).toShow();
				}
				WhacAmoleView.this.invalidate();
			}
			
			WhacAmoleView.this.postDelayed(this, progress + random.nextInt(500));
			progress -= 10;
		}
	};
	

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		if(event.getAction() != MotionEvent.ACTION_UP)
		{
			return true;
		}

		float x = event.getX();
		float y = event.getY();

		float offsetIndexX = x - startX;
		float offsetIndexY = y - startY;
		
		int indexX = (int)offsetIndexX / 80;
		int indexY = (int)offsetIndexY / 80;
		
		if(indexX>=3 || indexX <0 || indexY>=4|| indexY<0)
		{
			return true;
		}
		
		holeQuite.get(indexY* 3 + indexX).click(indexX, indexY, MatrixMap);
		return true;
	}
}

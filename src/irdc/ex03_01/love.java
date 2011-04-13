



package irdc.ex03_01;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;



/* 新建資料Activity */
public class love extends Activity
{
  Intent intent_love = new Intent();

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.love);
    
    final ListView list3 = (ListView) findViewById(R.id.tab4_lovelistview);
    /*setOnItemClickListener*/
    
    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
        this,android.R.layout.simple_list_item_1,new String[]{
            "保持規律的睡眠時間，生活作息要規律，即使前一天沒睡好，隔天仍要按時起床，以免影響晚上的睡眠",
            "睡前可泡熱水澡、喝杯熱牛奶或吃些小點心，有助睡眠品質，但最好不要吃太飽，以免腸胃消化不良，影響睡眠品質",
            "嚴格限制在床上的時間，白天時盡量不要待在床上，只有在晚上想睡覺時才上床睡覺",
            "不要強迫自己入睡，如果躺在床上超過三十分鐘仍然睡不著，就起床做些溫和的活動，直到想睡了再上床睡覺",
            "睡眠只要讓隔天覺得有精神就夠了，7-8小時以下的睡覺時間就足夠，太長的睡眠時間反而睡不好",
            "工作上的勞動及在緊張的氣氛下的運動，並不能幫助睡眠，適時的放鬆的心情去運動，對身心有益",
            "注意保持床舖及四週環境的舒適，房間若不大，要保持空氣的流通，空氣不流通的情況，可能造成早上醒來時昏昏沉沉的感覺",
            "光線越暗越好，若必須留盞小燈，以黃色燈光為宜，且光線不宜直接照射到臉部，因為少許光線的刺激，可能破壞生理時鐘的規律性",
            "太熱、太冷都會影響睡眠，最理想的寢室室溫為25℃左右",
            "長期抽煙會影響睡眠，酒精有幫助入眠的效果，卻會造成片斷淺眠；茶及咖啡會引起失眠，避免在下午六點以後飲用",
            "避免進食太晚或太豐盛的晚餐，晚上不要喝大量水分",
            "睡前兩小時勿食用水果，因為某些水果含有豐富的鉀，有利尿的作用，會造成頻尿",
            "每天定量的運動可以提昇睡眠品質，不定量的劇烈運動則可能因筋骨酸痛而影響睡眠",
            "睡不著時不要一直看鐘錶，那會使你更著急，無法放鬆，更睡不著",
            "床舖要平整且軟硬適中，棉被建議要舒適輕盈，而略高且不會太軟的枕頭可幫助入眠",
            "請將睡覺看成很重要的事!充足的睡眠可以讓你工作和學習更有效率",
            "保持一個睡眠紀錄，可以幫助你知道你睡覺的規律而找到你難以入眠的原因",
            "不要在床上工作或者學習，這樣會讓你的身體覺得床上不是睡覺的地方"            
            });
    list3.setAdapter(adapter2);
    updateListView();
    list3.setOnItemClickListener
    (new ListView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
        if(id==0)
          {
            /*intent.setClass(EX03_01.this, hard.class);
            startActivity(intent);
            finish();*/
          }
        else if(id==1)
        {
             /* intent2.setClass(EX03_01.this, music.class);
              startActivity(intent2);
              finish(); */
        }
        else if(id==2)
        {
          /*intent3.setClass(EX03_01.this, love.class);
          startActivity(intent3);
          finish();*/
        }

      }
      public void onNothingSelected(AdapterView<?> parent) {
      }

     }
    );
    
    
    
  }

  private void updateListView()
  {
    // TODO Auto-generated method stub
    
  }
  @Override 
  public boolean onCreateOptionsMenu(Menu menu) {
    // TODO Auto-generated method stub  
    MenuInflater menuInflater = getMenuInflater();  
    menuInflater.inflate(R.layout.tab2_item, menu);  
    return true; 
    }
    public boolean onOptionsItemSelected(MenuItem item) 
    {  // TODO Auto-generated method stub  
      switch(item.getItemId()){  
        case (R.id.opt1):   
          break;  
        case (R.id.opt2):   
          break;  
        
        }  
      return true; 
      
    }
}

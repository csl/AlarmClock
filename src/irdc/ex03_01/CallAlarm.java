package irdc.ex03_01;

/* import¬ÛÃöclass */
import java.util.Calendar;
import java.util.StringTokenizer;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;

/* ©I¥s¾xÄÁAlertªºReceiver */
public class CallAlarm extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {    
    /* create Intent¡A©I¥sAlarmAlert.class */
    String weekofday = intent.getStringExtra("weekofday");
    int gameid = intent.getIntExtra("gameid", -1);
    Calendar cal = Calendar.getInstance();    
    int w = cal.get(Calendar.DAY_OF_WEEK);
    
    boolean toDo = false;
    
    
    StringTokenizer Tok = new StringTokenizer(weekofday, ",");
    Log.d("TAG", weekofday);
    while (Tok.hasMoreElements())
    {
      int index = Integer.valueOf((String) Tok.nextElement());
      
      if (index+1 == w) toDo = true;
      //System.out.println("" + ++n +": "+Tok.nextElement());
     }                      

    
    if (toDo == true)
    {
      if (gameid == 0)
      {
        Intent i = new Intent(context, WhacAMole.class);
        Bundle bundleRet = new Bundle();
        bundleRet.putString("STR_CALLER", "123");
        i.putExtras(bundleRet);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);             
      }
      else  if (gameid == 1)
      {
        Intent i = new Intent(context, MathProblem.class);
        
        Bundle bundleRet = new Bundle();
        bundleRet.putString("STR_CALLER", "456");
        i.putExtras(bundleRet);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);            
        
      }
    }   
  }
}
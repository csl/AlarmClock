package irdc.ex03_01;

/* import¬ÛÃöclass */
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.os.Bundle;

/* ©I¥s¾xÄÁAlertªºReceiver */
public class CallAlarm extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {    
    /* create Intent¡A©I¥sAlarmAlert.class */
    int weekofday = intent.getIntExtra("weekofday", -1);
    int gameid = intent.getIntExtra("gameid", -1);
    Calendar cal = Calendar.getInstance();    
    int w = cal.get(Calendar.DAY_OF_WEEK);
    
    if (w == weekofday || weekofday == 0)
    {
      if (gameid == 0)
      {
        Intent i = new Intent(context, MathProblem.class);
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
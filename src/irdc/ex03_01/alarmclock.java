package irdc.ex03_01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.app.AlertDialog.Builder;

public class alarmclock extends Activity 
{
 
  private EditText am;
  private EditText ed;
  private Spinner rt;
  private Spinner game;
  private TimePicker tPicker;

  private AlertDialog.Builder builder;
  
  private Bundle bunde;
  private Intent intent;

  private String next_id;
  private String list_item;
  private Long id;
  private String ThisID;

  private AlertDialog.Builder builderr;
  
  private String tables[] = { "sleeptb", "alarmclock" }; //��Ʈw��ƪ�
  private String fieldNames[][] =     /* ��Ʈw���W�� */
  {
    { "sleep_id", "sleep_day","wakeup_day", "sleep_time", "wakeup_time" ,"longtime_hour","longtime_min"},
    { "alarm_id", "alarm_name", "alarm_hourmin", "alarm_section", "alarm_repeat", "alarm_game"}
  };
  
  public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.alarmclock);
	    
      //Fetch data form Inquire    
      intent=this.getIntent();
      bunde = intent.getExtras();
      next_id = String.valueOf(bunde.getInt("next_id"));  
      id = bunde.getLong("id");  
      list_item = bunde.getString("list_item");  

      am = (EditText) findViewById(R.id.alarmname);
  		ed = (EditText) findViewById(R.id.section);
  		rt = (Spinner) findViewById(R.id.repeat);
      tPicker=(TimePicker)findViewById(R.id.tpr);
      game = (Spinner) findViewById(R.id.cgame);

      builderr = new AlertDialog.Builder(this);

  		//add repeat spinner
  		String ispinner_list[] = this.getResources().getStringArray(R.array.ispinner_list);
  		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ispinner_list);
  		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  		rt.setAdapter(adapter);

      //add repeat spinner
      String lgame[] = this.getResources().getStringArray(R.array.game);
      ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lgame);
      adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      game.setAdapter(adapter2);
      
      Button delete_b = (Button) findViewById(R.id.delete_b);
      delete_b.setOnClickListener(new Button.OnClickListener()
      {
              public void onClick(View v)
              {       
                builderr.setMessage("delete?");
                builderr.setCancelable(false);
               
                builderr.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                      //delete database item

                      AlarmManager am  = (AlarmManager) getSystemService(ALARM_SERVICE);
                      
                      //�����x��
                      Intent intent = new Intent(alarmclock.this, CallAlarm.class);
                      PendingIntent pi = PendingIntent.getBroadcast(alarmclock.this, Integer.valueOf(ThisID), intent, 0);
                      am.cancel(pi);
                    }
                });
               
                builderr.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                    
                    }
                });
                
                AlertDialog alert = builder.create();
                alert.show();
             
              }
      });

      if (id == 0)
      {
        rt.setSelection(0);
        game.setSelection(0);
        delete_b.setEnabled(false);
      }
      else
      {
        //find ThisID
        StringTokenizer Tok = new StringTokenizer(list_item, ",");
        String name = (String) Tok.nextElement();
        String time =  (String) Tok.nextElement();
        
        
        Cursor finddata = 
          EX03_01.dbHelper.select(tables[1], fieldNames[1], "alarm_name='" + name + "' and alarm_hourmin='" + time + "'", null, null, null, null);
        /*
        finddata.moveToNext();
        
        ThisID = finddata.getString(0);
        am.setText(name);

        StringTokenizer Tokr = new StringTokenizer(time, ":");
        String hour = (String) Tok.nextElement();
        String min =  (String) Tok.nextElement();
        
        tPicker.setCurrentHour(Integer.valueOf(hour));
        tPicker.setCurrentMinute(Integer.valueOf(min));
        ed.setText(finddata.getString(3));
        rt.setSelection(Integer.valueOf(finddata.getString(4)));
        game.setSelection(Integer.valueOf(finddata.getString(5)));
*/        

        
        delete_b.setEnabled(true);      
      }
  		  		
      
      
      //add button
      Button additem=(Button) findViewById(R.id.add);
      additem.setOnClickListener(new Button.OnClickListener()
      {
              public void onClick(View v)
              {           
                /* ���o�]�w�����j��� */
                  String alarmname = am.getText().toString();
                  int times = Integer.parseInt(ed.getText().toString())*1000;
                  int repeat = rt.getSelectedItemPosition();
                  int rrgame = game.getSelectedItemPosition();
                  
                  Calendar aclock = Calendar.getInstance();                 
                  
                  /* ���o�]�w���}�l�ɶ��A��β@��]��0 */
                  aclock.setTimeInMillis(System.currentTimeMillis());
                  //aclock.set(Calendar.DAY_OF_WEEK_IN_MONTH, Calendar.WEDNESDAY);
                  aclock.set(Calendar.HOUR_OF_DAY, tPicker.getCurrentHour());
                  aclock.set(Calendar.MINUTE, tPicker.getCurrentMinute());
                  aclock.set(Calendar.SECOND,0);
                  aclock.set(Calendar.MILLISECOND,0);
                  
                  /* ��s��ܪ��]�w�x���ɶ� */    
                  String tmpS = format(tPicker.getCurrentHour())+"�G"+
                              format(tPicker.getCurrentMinute());

                  if (id == 0)
                  {
                    //�g�J��Ʈw
                    String a[] = {next_id, alarmname, tmpS , Integer.toString(times/1000), Integer.toString(repeat),  Integer.toString(rrgame)};         
                    long rowid = EX03_01.dbHelper.insert(tables[1], fieldNames[1] , a);  
                    
                    /* ���w�x���]�w�ɶ���ɭn����CallAlarm.class */
                    Intent intent = new Intent(alarmclock.this, CallAlarm.class);
                    PendingIntent sender = PendingIntent.getBroadcast(alarmclock.this, Integer.valueOf(next_id) , intent, 0);
                    
                    /* setRepeating()�i���x�����а��� */
                    AlarmManager am  = (AlarmManager) getSystemService(ALARM_SERVICE);
                    am.setRepeating(AlarmManager.RTC_WAKEUP, aclock.getTimeInMillis(), times, sender);
    
                    /* �HToast���ܳ]�w�w���� */
                    Toast.makeText(alarmclock.this,"�]�w�x���ɶ���" + tmpS
                                   +"�}�l�A���ж��j��"+times/1000+"��",
                        Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                    //�g�J��Ʈw
                    //String a[] = {id, alarmname, tmpS , Integer.toString(times/1000), Integer.toString(repeat),  Integer.toString(rrgame)};         
                    //long rowid = EX03_01.dbHelper.insert(tables[1], fieldNames[1] , a);  
                    
                    /* ���w�x���]�w�ɶ���ɭn����CallAlarm.class */
                    //Intent intent = new Intent(alarmclock.this, CallAlarm.class);
                    //PendingIntent sender = PendingIntent.getBroadcast(alarmclock.this, 0/*Integer.valueOf(id)*/, intent, 0);
                    
                    /* setRepeating()�i���x�����а��� */
                    //AlarmManager am  = (AlarmManager) getSystemService(ALARM_SERVICE);
                    //am.setRepeating(AlarmManager.RTC_WAKEUP, aclock.getTimeInMillis(), times, sender);
    
                    /* �HToast���ܳ]�w�w���� */
                    Toast.makeText(alarmclock.this,"�ק粒���A�]�w�x���ɶ���" + tmpS
                                   +"�}�l�A���ж��j��"+times/1000+"��",
                        Toast.LENGTH_SHORT).show();
                  }
              }
      }
      
     );
    

    //exit button
      Button exit_b=(Button) findViewById(R.id.exit_b);
      exit_b.setOnClickListener(new Button.OnClickListener()
       {
            public void onClick(View v)
            {
              Intent intent = new Intent();
              intent.setClass(alarmclock.this, EX03_01.class);

              startActivity(intent);
              alarmclock.this.finish();   
            }
        }
      );
   
	}
  
  /* ����ɶ���ܨ��ƪ�method */
  private String format(int x)
  {
    String s=""+x;
    if(s.length()==1) s="0"+s;
    return s;
  }
  
  //error message
  private void openOptionsDialog(String info)
{
    new AlertDialog.Builder(this)
    .setTitle("message")
    .setMessage(info)
    .setPositiveButton("OK",
        new DialogInterface.OnClickListener()
        {
         public void onClick(DialogInterface dialoginterface, int i)
         {
         }
         }
        )
    .show();
}
}
package irdc.ex03_01;
 

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
 
public class EX03_01 extends TabActivity {
  //db star
  private MySQLiteOpenHelper dbHelper=null;
  private int version = 1;
  private int dposition;
  private AlertDialog.Builder builder;
  private MyAdapter mSimpleAdapter;
  private ListView alarmclock_view;
  private ArrayList<HashMap<String, Object>> alarmclock_list;
  
  private int id;
  private String tables[] = { "sleeptb", "alarmclock" }; //��Ʈw��ƪ�
  private String fieldNames[][] =     /* ��Ʈw���W�� */
  {
    { "sleep_id", "sleep_day","wakeup_day", "sleep_time", "wakeup_time" ,"longtime_hour","longtime_min"},
    { "alarm_id", "alarm_name", "alarm_hourmin", "alarm_repeat", "alarm_game"}
  };
  
  /* ��Ʈw����ƫ��A */
  private String fieldTypes[][] =
  {
    { "INTEGER PRIMARY KEY AUTOINCREMENT", "text" , "text", "text", "text","text","text"},
    { "INTEGER PRIMARY KEY AUTOINCREMENT", "text" , "text", "text", "text"}
  };

  Intent intent = new Intent();
  Intent intent2 = new Intent();
  Intent intent_love = new Intent();
  Intent intent_music = new Intent();
  Intent intent_dream = new Intent();
  private TextView mDateDisplay;
  private TextView mtimeview,setTime2;
  private ListView list2;
  private Time mtime=new Time(); //��ܮɶ�
  private ImageButton mtime_but; //�p�ɶs
  private Time memotime =new Time(); //�ɶ�����
  private int clickcount=0; //�p�ɫ��s�O�_�Q�I�L
  private int calday;
  public int calhour;
  public int calmin;
  private static final String[] array =
  { "11/02/16   15:30   16:30   1.0", "11/02/17   13:30   18:00   4.0" };
  LinearLayout myLinearLayout;
  //TextView myTextView;
  ListView myListView;
  Calendar aclock=Calendar.getInstance();

  private ListView listCats;
  private String catsName[]={"�q���E�g��","��ë�E�P��","�оɡE�ͬ�","�����E�e��", "�Ϋ��E�G�D"};

  
  static final int DATE_DIALOG_ID = 0;    
           
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Resources res = getResources();
    
    setTitle("�ίv�޲z");

    builder = new AlertDialog.Builder(this);

    /*����������*/
    TabHost tabHost = getTabHost();
    LayoutInflater.from(this).inflate(R.layout.main,tabHost.getTabContentView(), true);
    tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("����",
        res.getDrawable(R.drawable.record2)).setContent(R.id.tab1));
    tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("�έp",
        res.getDrawable(R.drawable.anzy2)).setContent(R.id.tab2));
    tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("�x��",
        res.getDrawable(R.drawable.clock2)).setContent(R.id.tab3));
    tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("�p����",
        res.getDrawable(R.drawable.fixbox2)).setContent(R.id.tab4));
        
    mDateDisplay = (TextView) findViewById(R.id.dateview);
    mtimeview = (TextView) findViewById(R.id.Timeview);
    mtime_but = (ImageButton) findViewById(R.id.time_but);

    ImageButton btn_input =(ImageButton) findViewById(R.id.IB_pic2);
    btn_input.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        
        intent.setClass(EX03_01.this, about.class);
        startActivity(intent);
        finish();
      }
    });
    
    /*ImageButton btn_input =(ImageButton) findViewById(R.id.IB_pic2);
    btn_input.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        
        intent.setClass(EX03_01.this, about.class);
        startActivity(intent);
        finish();
      }
    });*/
    

    updateDisplay();
    dbHelper = new MySQLiteOpenHelper(this, "sleepdb", null, version, tables, fieldNames, fieldTypes);

    
    mtime_but.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        ///�p�ɶs
        if( clickcount==0) //��ܨS�I�L
        {
          memotime.setToNow();
          clickcount++;
          mtime_but.setImageResource(R.drawable.index_light);
        }
        else  //��ܥ��e�O���L�ɶ�
        {
          mtime.setToNow(); //��ɶ�
          
          //�p��ɶ�
          calday=(mtime.monthDay) - (memotime.monthDay);
          calhour=(mtime.hour + (calday * 24))-(memotime.hour);  
          calmin=(mtime.minute) - (memotime.minute); 
          
          
        //��ܵ��G
          if (calhour ==0)
          {
            mtimeview.setText(""+ " --" + "     " +calmin);
          }
          else
          {
            mtimeview.setText(""+ calhour + "      " +calmin);   
          }
          
          //��Ʈw
          String sleep_day=Integer.toString(memotime.year) +"/"+Integer.toString(memotime.month+1)+"/"+Integer.toString(memotime.monthDay);
          String wakeup_day= Integer.toString(mtime.hour) +"/"+Integer.toString(mtime.month)+"/"+Integer.toString(mtime.monthDay);
          String sleep_time=Integer.toString(memotime.hour) +":"+Integer.toString(memotime.minute);
          String wakeup_time=Integer.toString(mtime.hour) +":"+Integer.toString(mtime.minute);
          
          String f2[] = { "sleep_day", "wakeup_day","sleep_time", "wakeup_time","longtime_hour","longtime_min"};
          String a[] = {sleep_day,wakeup_day,sleep_time,wakeup_time,Integer.toString(calhour),Integer.toString(calmin)};         
          long rowid = dbHelper.insert(tables[0], f2 , a);//�g�J��Ʈw
 
          mtime_but.setImageResource(R.drawable.index_night);
          clickcount=0; //�p�⧹�� �k�s
        }       
      }
    });



    final Cursor c = null;
    
    Spinner Spinner_dangerous = (Spinner) findViewById(R.id.sip_day); 
    //�إߤ@��ArrayAdapter����A�é�m�U�Կ�檺���e 
    final ArrayAdapter<String> adapter = new ArrayAdapter<String>
    (this,android.R.layout.simple_spinner_item,new String[]{
        "����","�T�Ѥ�","�@�g","��g"}); 
    //�]�w�U�Կ�檺�˦� 
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
    Spinner_dangerous.setAdapter(adapter); 
    //�]�w���سQ������᪺�ʧ@ 
    Spinner_dangerous.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
    { 
      public void onItemSelected(AdapterView adapterView, View view, int position, long id){ 

        Toast.makeText(EX03_01.this, ""+adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show(); 

        String today= Integer.toString(mtime.year) +"/"+Integer.toString(mtime.month+1)+"/"+Integer.toString(mtime.monthDay);
        String rull="sleep_day=?";
        int iday=0;
                
        if (adapterView.getSelectedItem().toString()=="����")
        {
          iday=0;
        }
        else if(adapterView.getSelectedItem().toString()=="�T�Ѥ�")
        {
          iday=2;
        }
        else if(adapterView.getSelectedItem().toString()=="�@�g")
        {
          iday=6;
        }
        else if(adapterView.getSelectedItem().toString()=="��g")
        {
         iday=13; 
        }
    
        
        String selectionArgs[] = new String[iday+1];
        selectionArgs[0]= today;      
        
        
        if(iday != 0)
        {
          java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
          "yyyy/M/d");
          Calendar calendar = Calendar.getInstance();
          calendar.set(mtime.year, mtime.month,mtime.monthDay);
          
          for (int i=1;i<=iday;i++)
          {
            calendar.add(Calendar.DAY_OF_YEAR, -i);
            selectionArgs[i]=sdf.format(calendar.getTime());
 
            rull= rull+ " or sleep_day=?";      
          }
        }
        
        
        String f2[] = { "sleep_day","sleep_time","wakeup_time" ,"longtime_hour","longtime_min"};
        Cursor c=dbHelper.select(tables[0],f2, rull, selectionArgs, null, null, null);   
              
        TableLayout tableLayout = (TableLayout)findViewById(R.id.TableLayout01);
        tableLayout.setStretchAllColumns(true);
        tableLayout.removeAllViewsInLayout();
        
        while (c.moveToNext())
        {
           TableRow tableRow=new TableRow(EX03_01.this);
           
           TextView tv=new TextView(EX03_01.this);
           tv.setTextColor(getResources().getColorStateList(android.R.color.black));
           tv.setTextSize(18);
           tv.setText(c.getString(0));
           tableRow.addView(tv);
           
           tv=new TextView(EX03_01.this);
           tv.setTextColor(getResources().getColorStateList(android.R.color.black));
           tv.setTextSize(18);
           tv.setText(c.getString(1));
           tableRow.addView(tv);
                
           tv=new TextView(EX03_01.this);
           tv.setTextColor(getResources().getColorStateList(android.R.color.black));
           tv.setTextSize(18);
           tv.setText(c.getString(2));
           tableRow.addView(tv);
           
           tv=new TextView(EX03_01.this);
           tv.setTextColor(getResources().getColorStateList(android.R.color.black));
           tv.setTextSize(18);
           tv.setText(c.getString(3)+":"+c.getString(4));
           tableRow.addView(tv);
           
           tableLayout.addView(tableRow, new TableLayout.LayoutParams(c.getCount(), c.getColumnCount()-1));
        }     
       
        //mtimeview.setText(rull);
       //mtimeview.setText(Integer.toString(i));
         
    

    }

      public void onNothingSelected(AdapterView arg0) { 
        //Toast.makeText(EX03_01.this, "�z�S����ܥ��󶵥�", Toast.LENGTH_SHORT).show(); 
    }
    });


    
    
    //tab4��item
    final ListView list1 = (ListView) findViewById(R.id.tab4_itemview);
    /*setOnItemClickListener*/
    
    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
        this,android.R.layout.simple_list_item_1,new String[]{
            "�ίv����",
            "���ҼҦ�",
            "�R���m�{",
            "�ڹҸѪR"});
    list1.setAdapter(adapter2);
    updateListView();
    list1.setOnItemClickListener
    (new ListView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
        if(id==0)
          {
            intent.setClass(EX03_01.this, hard.class);
            startActivity(intent);
            finish();
          }
        else if(id==1)
        {
              intent_music.setClass(EX03_01.this, music.class);
              startActivity(intent_music);
              finish(); 
        }
        else if(id==2)
        {
              intent_love.setClass(EX03_01.this, love.class);
              startActivity(intent_love);
              finish(); 
        }
        else if(id==3)
        {
              intent_dream.setClass(EX03_01.this, dream.class);
              startActivity(intent_dream);
              finish(); 
        }
      }
      public void onNothingSelected(AdapterView<?> parent) 
      {
      }

     });
/*
 * alarm*/

    LayoutInflater factory = LayoutInflater.from(this);
    final View setView = factory.inflate(R.layout.timeset,null);
    final TimePicker tPicker=(TimePicker)setView.findViewById(R.id.tPicker);
    tPicker.setIs24HourView(true);

    final AlertDialog di = new AlertDialog.Builder(EX03_01.this)
    .setIcon(R.drawable.clock_icon)
    .setTitle("�]�w")
    .setView(setView)
    .setPositiveButton("�T�w",new DialogInterface.OnClickListener()
     {
       public void onClick(DialogInterface dialog, int which)
       {
         /* ���o�]�w�����j��� */
         EditText ed=(EditText)setView.findViewById(R.id.mEdit);
         int times=Integer.parseInt(ed.getText().toString())*1000;
         
         /* ���o�]�w���}�l�ɶ��A��β@��]��0 */
         aclock.setTimeInMillis(System.currentTimeMillis());
         //aclock.set(Calendar.DAY_OF_WEEK_IN_MONTH, Calendar.WEDNESDAY);
         aclock.set(Calendar.HOUR_OF_DAY, tPicker.getCurrentHour());
         aclock.set(Calendar.MINUTE, tPicker.getCurrentMinute());
         aclock.set(Calendar.SECOND,0);
         aclock.set(Calendar.MILLISECOND,0);
         
         /* ���w�x���]�w�ɶ���ɭn����CallAlarm.class */
         Intent intent = new Intent(EX03_01.this, CallAlarm.class);
         PendingIntent sender = PendingIntent.getBroadcast(EX03_01.this, 0/*Integer.valueOf(id)*/, intent, 0);
         
         /* setRepeating()�i���x�����а��� */
         AlarmManager am  = (AlarmManager) getSystemService(ALARM_SERVICE);
         am.setRepeating(AlarmManager.RTC_WAKEUP, aclock.getTimeInMillis(), times, sender);
         

         /* ��s��ܪ��]�w�x���ɶ� */    
         String tmpS = format(tPicker.getCurrentHour())+"�G"+
                     format(tPicker.getCurrentMinute());
         
         //setTime2.setText("�]�w�x���ɶ���"+tmpS+"�}�l�A���ж��j��" + times/1000 + "��");
         
         //�g�J��Ʈw
         //String a[] = {tmpS , Integer.toString(times/1000) };         
         //long rowid = dbHelper.insert(tables[0], fieldNames[1] , a);  
         
         updateListView();

         /* �HToast���ܳ]�w�w���� */
         Toast.makeText(EX03_01.this,"�]�w�x���ɶ���" + tmpS
                        +"�}�l�A���ж��j��"+times/1000+"��",
             Toast.LENGTH_SHORT).show();
       }
     })
    .setNegativeButton("����",new DialogInterface.OnClickListener()
     {
       public void onClick(DialogInterface dialog, int which)
       {
       }
     }).create();

    
    //tab3��item
    
    alarmclock_list = new ArrayList<HashMap<String,Object>>();

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("ItemTitle", "�s�W�x��");
    map.put("ItemText", "�I��U�s�W");
    alarmclock_list.add(map);
    
    map.put("ItemTitle", "test");
    map.put("ItemText", "test");
    alarmclock_list.add(map);

    alarmclock_view = (ListView) findViewById(R.id.tab3_itemview);
    /*setOnItemClickListener*/
    /*
    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
    this,android.R.layout.simple_list_item_1, new String[]{
            "�s�W�x��",
            "�s�W�x��",
            "�s�W�x��",
            "�s�W�x��",
            "�s�W�x��"
    });
*/    
    mSimpleAdapter = new MyAdapter(this, alarmclock_list, R.layout.listview_style_1, 
        new String[]{"ItemTitle","ItemText"}, new int[]{R.id.topTextView,R.id.bottomTextView});  
    
    alarmclock_view.setAdapter(mSimpleAdapter);     
    
    alarmclock_view.setOnItemLongClickListener(new OnItemLongClickListener() {
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) 
      {
        dposition = position;
        
        builder.setMessage("delete?");
        builder.setCancelable(false);
       
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
              openOptionsDialog("Item LONG clicked. Position:" + dposition);
            }
        });
       
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
            
            }
        });
        
        AlertDialog alert = builder.create();
        alert.show();
      
        
        return true;
      }
     });
    
    
       
    updateListView();

    alarmclock_view.setOnItemClickListener
    (new ListView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent,View view,int position,long id) 
      {
        if (id == 0)
        {
          /* ���o���U���s�ɪ��ɶ�����tPicker���w�]�� */
            aclock.setTimeInMillis(System.currentTimeMillis());
            tPicker.setCurrentHour(aclock.get(Calendar.HOUR_OF_DAY));
            tPicker.setCurrentMinute(aclock.get(Calendar.MINUTE));
            /* ���X�]�w�e��di */
            di.show();
        }
        else
        {
          //modify
          
        }
      }
      public void onNothingSelected(AdapterView<?> parent) 
      {
      }



     });
    
    /*�̥~���*/
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
          case (R.id.opt3):   
            break;  
          }  
        return true; 
        
      }
  
  
  
  private void updateDisplay() {
    mtime.setToNow(); //���o�{�b�ɶ�
    mDateDisplay.setText(
        new StringBuilder()
                .append(mtime.year).append("�~").append(" ").append("-").append(" ")
                .append(mtime.month).append("��").append("-").append(" ")
                .append(mtime.monthDay).append("��")
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
  
  public class MyAdapter extends SimpleAdapter {
    
    Map<Integer, Boolean> map;
   
    LayoutInflater mInflater;
   
    private List<? extends Map<String, ?>> mList;
   
    public MyAdapter(Context context, ArrayList<HashMap<String, Object>> data,
                    int resource, String[] from, int[] to) 
    {
            super(context, data, resource, from, to);

            map = new HashMap<Integer, Boolean>();
            mInflater = LayoutInflater.from(context);
            mList = data;
            for(int i = 0; i < data.size(); i++) 
            {
                    map.put(i, false);
            }
    }
   
    @Override
    public int getCount() {
            return mList.size();
    }

    @Override
    public Object getItem(int position) {
            return position;
    }

    @Override
    public long getItemId(int position) {
            return position;
    }
    
   
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
            if(convertView == null) {
                    convertView = mInflater.inflate(R.layout.listview_style_1, null);
            }
            TextView tN = (TextView) convertView.findViewById(R.id.topTextView);
            tN.setText((String)mList.get(position).get("ItemTitle"));
           
            TextView tP = (TextView) convertView.findViewById(R.id.bottomTextView);
            tP.setText((String)mList.get(position).get("ItemText"));
           
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.CheckBox02);
   
            checkBox.setChecked(map.get(position));
           
            return convertView;
    }
   
}
  
}
  


  
    

  

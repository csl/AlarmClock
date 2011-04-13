package irdc.ex03_01;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;




/* 新建資料Activity */
public class music extends Activity {
  Intent intent_music = new Intent();
  Intent intent_volume = new Intent();
 final int LIST_DIALOG_SINGLE = 1; 
 final int LIST_DIALOG_SINGLE2 = 2; 
 
 private ImageView myImage;
 private ImageButton downButton;
 private ImageButton upButton;
 private ProgressBar myProgress;
 private AudioManager audioMa;
 private int volume=0;
 

    @Override
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);   
        Button btn = (Button)findViewById(R.id.music_change);
        Button btn3 = (Button)findViewById(R.id.music_long);
        
        btn.setOnClickListener(new View.OnClickListener() { 
        public void onClick(View v) {
        showDialog(LIST_DIALOG_SINGLE);  
               }
           });
        btn3.setOnClickListener(new View.OnClickListener() { 
        public void onClick(View v) {
        showDialog(LIST_DIALOG_SINGLE2);  
              }
          });
        audioMa = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        myImage = (ImageView)findViewById(R.id.myImage); 
        myProgress = (ProgressBar)findViewById(R.id.myProgress); 
        downButton = (ImageButton)findViewById(R.id.downButton); 
        upButton = (ImageButton)findViewById(R.id.upButton); 

        volume=audioMa.getStreamVolume(AudioManager.STREAM_RING); 
        myProgress.setProgress(volume);
        int mode=audioMa.getRingerMode();
        
        downButton.setOnClickListener(new Button.OnClickListener() 
        { 
          public void onClick(View arg0) 
          {
            /* 設定音量調小聲一格 */
            audioMa.adjustVolume(AudioManager.ADJUST_LOWER, 0); 
            volume=audioMa.getStreamVolume(AudioManager.STREAM_RING);
            myProgress.setProgress(volume);
            /* 設定調整後聲音模式 */
            int mode=audioMa.getRingerMode();
            if(mode==AudioManager.RINGER_MODE_NORMAL)
            {
              myImage.setImageDrawable(getResources()
                                      .getDrawable(R.drawable.normal));
            }
            else if(mode==AudioManager.RINGER_MODE_SILENT)
            {
              myImage.setImageDrawable(getResources()
                                       .getDrawable(R.drawable.mute));
            }
            else if(mode==AudioManager.RINGER_MODE_VIBRATE)
            {
              myImage.setImageDrawable(getResources()
                                      .getDrawable(R.drawable.vibrate));
            }
          } 
        }); 
           
        /* 音量調大聲的Button */
        upButton.setOnClickListener(new Button.OnClickListener() 
        { 
          public void onClick(View arg0) 
          { 
            /* 設定音量調大聲一格 */
            audioMa.adjustVolume(AudioManager.ADJUST_RAISE, 0);
            volume=audioMa.getStreamVolume(AudioManager.STREAM_RING);
            myProgress.setProgress(volume);
            /* 設定調整後的聲音模式 */
            int mode=audioMa.getRingerMode();
            if(mode==AudioManager.RINGER_MODE_NORMAL)
            {
              myImage.setImageDrawable(getResources()
                                       .getDrawable(R.drawable.normal));
            }
            else if(mode==AudioManager.RINGER_MODE_SILENT)
            {
              myImage.setImageDrawable(getResources()
                                       .getDrawable(R.drawable.mute));
            }
            else if(mode==AudioManager.RINGER_MODE_VIBRATE)
            {
              myImage.setImageDrawable(getResources()
                                      .getDrawable(R.drawable.vibrate));
            }
          } 
        }); 
     
        
        
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
 protected Dialog onCreateDialog(int id) {   
  Dialog dialog = null;  
  Builder b = new AlertDialog.Builder(this); 
  switch(id){   
  case LIST_DIALOG_SINGLE:
   //Builder b = new AlertDialog.Builder(this); 
   b.setIcon(R.drawable.icon);   
   b.setTitle("選擇你要的音樂");    
   b.setSingleChoiceItems(      
     R.array.music,
     0,         
     new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
       TextView textview = (TextView)findViewById(R.id.view_change);
       textview.setText("你選擇了:"
         + getResources().getStringArray(R.array.music)[which]);
      
      }
      });
   break;
  case LIST_DIALOG_SINGLE2:
    //Builder b = new AlertDialog.Builder(this); 
    b.setIcon(R.drawable.icon);   
    b.setTitle("選擇你要的長度");    
    b.setSingleChoiceItems(      
      R.array.musiclong,
      0,         
      new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int which) {
        TextView textview2 = (TextView)findViewById(R.id.view_long);
        textview2.setText("你選擇了:"
          + getResources().getStringArray(R.array.musiclong)[which]);
       }
      });
  
   break;
  
  default:
   break;
  }
  
  b.setPositiveButton(     
      "OK",      
      new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int which){}
      });
      dialog = b.create();     
  
  return dialog;         
 }
}


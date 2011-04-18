package irdc.ex03_01;

/* import相關class */
import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MathProblem extends Activity
{
  private static Random random = new Random();
  private Handler handler = new Handler();
  
  private int [] ButtonArray  = new int[10];
  private boolean [] Buttond  = new boolean[10];
  
  private MediaPlayer mMediaPlayer; 

  private TextView question;
  private TextView answer;
  
  private ImageButton []  num = new ImageButton[10];

  private Button clear;
  private ImageButton ek;

  private String answer_str;
  private int equal;
  private int total_question;

  @Override
  protected void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.mpgame);

    total_question = 3;
    
    answer_str = "";
    
    mMediaPlayer = new MediaPlayer();

    try
    {
      mMediaPlayer.setDataSource( "/sdcard/music_clock.wav" );
      mMediaPlayer.setLooping(true);
      mMediaPlayer.prepare();
      mMediaPlayer.start(); 
    } catch (IllegalArgumentException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalStateException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    //GUI
    question = (TextView)findViewById(R.id.Question);
    answer = (TextView)findViewById(R.id.answer);
    num[0] = (ImageButton)findViewById(R.id.numtop1);
    num[1] = (ImageButton)findViewById(R.id.numtop2);
    num[2] = (ImageButton)findViewById(R.id.numtop3);
    num[3] = (ImageButton)findViewById(R.id.nummid1);
    num[4] = (ImageButton)findViewById(R.id.nummid2);
    num[5] = (ImageButton)findViewById(R.id.nummid3);
    num[6] = (ImageButton)findViewById(R.id.numbottom1);
    num[7] = (ImageButton)findViewById(R.id.numbottom2);
    num[8] = (ImageButton)findViewById(R.id.numbottom3);
    num[9] = (ImageButton)findViewById(R.id.numbottom4);
    clear = (Button)findViewById(R.id.clear);
    ek = (ImageButton)findViewById(R.id.ek);
    
    ImageButton_layout();
    
    num[0].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v)
       { 
        handleNumber(ButtonArray[0]);
       }
      });

    num[1].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v){ 
        handleNumber(ButtonArray[1]); 
        }
      });
    
    num[2].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v){ 
        handleNumber(ButtonArray[2]); 
        }
      });
    
    num[3].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v){ 
        handleNumber(ButtonArray[3]); 
        }
      });
    
    num[4].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v){ 
        handleNumber(ButtonArray[4]); 
        }
      });
    
    num[5].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v){ 
        handleNumber(ButtonArray[5]); 
        }
      });
    
    num[6].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v){ 
        handleNumber(ButtonArray[6]); 
        }
      });
    
    num[7].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v){ 
        handleNumber(ButtonArray[7]); 
        }
      });
    
    num[8].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v){ 
        handleNumber(ButtonArray[8]); 
      }
      });
    
    num[9].setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v){ 
        handleNumber(ButtonArray[9]); 
        }
      });

    clear.setOnClickListener(new Button.OnClickListener() { 
        public void onClick (View v){ 
          answer_str=""; 
          answer.setText(answer_str);   
        }
      }
    );

    ek.setOnClickListener(new Button.OnClickListener() { 
      public void onClick (View v)
      {
        
        if (!answer.getText().toString().equals(""))
        {
          if ( equal == Integer.valueOf(answer.getText().toString())) 
            total_question--;
          else
            Toast.makeText(MathProblem.this, "答錯了哦!",   Toast.LENGTH_SHORT).show();
        }
        else
        {
          Toast.makeText(MathProblem.this, "答錯了哦!",   Toast.LENGTH_SHORT).show();
          
        }

        answer_str=""; 
        answer.setText(answer_str);  

        
        if (total_question == 0)
        {
          openOptionsDialog("題目都做完了, 起床!!");
        }
        else
        {
          handleQuestion();
          Toast.makeText(MathProblem.this, "還有" + Integer.toString(total_question) + "題, 加油哦",   Toast.LENGTH_SHORT).show();
        }
      }
      });
    
    handler.removeCallbacks(updateTimer);
    handler.postDelayed(updateTimer, 5000);
    
    answer.setText(answer_str); 
    handleQuestion();
  } 
  
  private void handleQuestion()
  {
    String qestion = "";
    int data;
    int sign;
    int count = 0;
    
    equal = 0;
    while (count < 4)
    {
      data = random.nextInt(20) + 1;

      if (count == 0)
      {
        equal = data;
        qestion = qestion + Integer.toString(data);
      }
      else
      {
        sign = random.nextInt(2);
        
        if (sign == 0)
        {
          if (count == 3 && equal + data < 0)
          {
            data = random.nextInt(60) + 1;
            int tmp_equal = equal + data;
            while (tmp_equal < 0)
            {
              data = random.nextInt(60) + 1;
              tmp_equal = tmp_equal + data;
            }
          }
          equal = equal + data;
          qestion = qestion + "+" + Integer.toString(data);      
        }          
        else
        {
          if (count == 3 && equal - data < 0)
          {
            data = random.nextInt(60) + 1;
            int tmp_equal = equal + data;
            while (tmp_equal < 0)
            {
              data = random.nextInt(60) + 1;
              tmp_equal = tmp_equal + data;
            }

            equal = equal + data;
            qestion = qestion + "+" + Integer.toString(data);
          }
          else
          {
            equal = equal - data;
            qestion = qestion + "-" + Integer.toString(data);
          }
          
        }         
      }
      count++;
    }
    question.setText(qestion); 
  }
  
  private void ImageButton_layout()
  {
    int index = 0;
    
    for (int i=0; i <10; i++)
    {
      Buttond[i] = false;
    }
    
    for (int i=0; i <10; i++)
    {
      index = random.nextInt(10);
      while (Buttond[index])
      {
        index = random.nextInt(10);
      }
      
      ButtonArray[i] = index;
      Buttond[index] = true;
      
      switch (index)
      {
        case 0:
            num[i].setImageResource(R.drawable.num0);
            break;
        case 1:
            num[i].setImageResource(R.drawable.num1);
            break;
        case 2:
            num[i].setImageResource(R.drawable.num2);
            break;
        case 3:
            num[i].setImageResource(R.drawable.num3);
            break;
        case 4:
            num[i].setImageResource(R.drawable.num4);
            break;
        case 5:
            num[i].setImageResource(R.drawable.num5);
            break;
        case 6:
            num[i].setImageResource(R.drawable.num6);
            break;
        case 7:
            num[i].setImageResource(R.drawable.num7);
            break;
        case 8:
            num[i].setImageResource(R.drawable.num8);
            break;
        case 9:
            num[i].setImageResource(R.drawable.num9);
            break;
      }
    }
  }
  
  private void handleNumber(int num)
  {
    answer_str = answer_str + String.valueOf(num);
    answer.setText(answer_str); 
  }
  
  private Runnable updateTimer = new Runnable() {
    public void run() {
      
        ImageButton_layout();

        handler.postDelayed(this, 5000);
    }
};
  
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
           finish();
           mMediaPlayer.release();   
         }
         }
        )
    .show();
  }
}
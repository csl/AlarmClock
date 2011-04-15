package irdc.ex03_01;

/* import相關class */
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MathProblem extends Activity
{
  private static Random random = new Random();
  private Handler handler = new Handler();
  
  private TextView question;
  private TextView answer;
  
  private Button numtop1;
  private Button numtop2;
  private Button numtop3;

  private Button nummiddle1;
  private Button nummiddle2;
  private Button nummiddle3;

  private Button numbottom1;
  private Button numbottom2;
  private Button numbottom3;
  private Button numbottom4;

  private Button clear;
  private Button ek;

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
    
    //GUI
    question = (TextView)findViewById(R.id.Question);
    answer = (TextView)findViewById(R.id.answer);
    numtop1 = (Button)findViewById(R.id.numtop1);
    numtop2 = (Button)findViewById(R.id.numtop2);
    numtop3 = (Button)findViewById(R.id.numtop3);
    nummiddle1 = (Button)findViewById(R.id.nummid1);
    nummiddle2 = (Button)findViewById(R.id.nummid2);
    nummiddle3 = (Button)findViewById(R.id.nummid3);
    numbottom1 = (Button)findViewById(R.id.numbottom1);
    numbottom2 = (Button)findViewById(R.id.numbottom2);
    numbottom3 = (Button)findViewById(R.id.numbottom3);
    numbottom4 = (Button)findViewById(R.id.numbottom4);
    clear = (Button)findViewById(R.id.clear);
    ek = (Button)findViewById(R.id.ek);
    
    numtop1.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(numtop1.getText().toString()));}});
    numtop2.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(numtop2.getText().toString())); }});
    numtop3.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(numtop3.getText().toString())); }});
    nummiddle1.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(nummiddle1.getText().toString())); }});
    nummiddle2.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(nummiddle2.getText().toString())); }});
    nummiddle3.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(nummiddle3.getText().toString())); }});
    numbottom1.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(numbottom1.getText().toString())); }});
    numbottom2.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(numbottom2.getText().toString())); }});
    numbottom3.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(numbottom3.getText().toString())); }});
    numbottom4.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ handleNumber(Integer.valueOf(numbottom4.getText().toString())); }});

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
  
  private void handleNumber(int num)
  {
    answer_str = answer_str + String.valueOf(num);
    answer.setText(answer_str); 
  }
  
  private Runnable updateTimer = new Runnable() {
    public void run() {
        handler.postDelayed(this, 1000);
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
         }
         }
        )
    .show();
  }
}
package irdc.ex03_01;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;



/* 新建資料Activity */
public class about extends Activity
{
  Intent intent_about = new Intent();

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.about);
    
    Button btn_input =(Button) findViewById(R.id.IB_enter);
    btn_input.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        
        intent_about.setClass(about.this, EX03_01.class);
        startActivity(intent_about);
        finish();
      }
    });
    
    
    
  }
  
}


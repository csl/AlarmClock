package irdc.ex03_01;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;



/* 新建資料Activity */
public class index extends Activity
{
  Intent intent_index = new Intent();

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.index);
    
    Button btn_index =(Button) findViewById(R.id.btn_index);
    btn_index.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        
        intent_index.setClass(index.this, about.class);
        startActivity(intent_index);
        finish();
      }
    });   
    
    
  }
  
}


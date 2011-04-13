package irdc.ex03_01;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;



/* 新建資料Activity */
public class hard extends Activity
{
  Intent intent_hard = new Intent();
  int spa1,sum=0,spa2;
  private Button dialogButton; 
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.hard);
    /**/
    dialogButton = (Button) findViewById(R.id.tab4_hardsum); 
    
    final Spinner Spinner_A1 = (Spinner) findViewById(R.id.A_1);
    Spinner Spinner_A2 = (Spinner) findViewById(R.id.A_2);
    Spinner Spinner_A3 = (Spinner) findViewById(R.id.A_3);
    Spinner Spinner_A4 = (Spinner) findViewById(R.id.A_4);
    Spinner Spinner_A5 = (Spinner) findViewById(R.id.A_5);
    Spinner Spinner_A6 = (Spinner) findViewById(R.id.A_6);
    Spinner Spinner_A7 = (Spinner) findViewById(R.id.A_7);
    Spinner Spinner_A8 = (Spinner) findViewById(R.id.A_8);
    
    //建立一個ArrayAdapter物件，並放置下拉選單的內容 
    ArrayAdapter<String> adapter_spinner = new ArrayAdapter<String>
    (this,android.R.layout.simple_spinner_item,new String[]{
        "請選擇","0=不會打瞌睡",
        "1=很少會打瞌睡",
        "2=有時會打瞌睡",
        "3=經常會打瞌睡"}); 
    //設定下拉選單的樣式 
    adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
    Spinner_A1.setAdapter(adapter_spinner); 
    //設定項目被選取之後的動作 
    Spinner_A1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
    { 
      public void onItemSelected(AdapterView adapterView, View view, int position, long id)
      { 
        Toast.makeText(hard.this, ""+adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show(); 
        if(id==0)
        {
        Toast.makeText(hard.this, ""+adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show(); 
        }
        else if(id==1)
        {
          sum=spa1+1;
        }
        else if(id==2)
        {
          sum=spa1+2;
        }
        else if(id==3)
        {
          sum=spa1+3;
        }
        else if(id==4)
        {
          sum=spa1+4;
        }

      }
      public void onNothingSelected(AdapterView arg0) { 
        //Toast.makeText(hard.this, "您沒有選擇任何項目", Toast.LENGTH_SHORT).show(); 
    }
    });
    Spinner_A2.setAdapter(adapter_spinner);
    Spinner_A2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
    { 
      public void onItemSelected(AdapterView adapterView, View view, int position, long id)
      { 
        if(id==0)
        {
        Toast.makeText(hard.this, ""+adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show(); 
        }
        else if(id==1)
        {
          spa2=spa2+1;
        }
        else if(id==2)
        {
          spa2=spa2+2;
        }
        else if(id==3)
        {
          spa2=spa2+3;
        }
        else if(id==4)
        {
          spa2=spa2+4;
        }
        
      }
      public void onNothingSelected(AdapterView arg0) { 
        //Toast.makeText(hard.this, "您沒有選擇任何項目", Toast.LENGTH_SHORT).show(); 
    }
    });
    Spinner_A3.setAdapter(adapter_spinner);
    Spinner_A4.setAdapter(adapter_spinner);
    Spinner_A5.setAdapter(adapter_spinner);
    Spinner_A6.setAdapter(adapter_spinner);
    Spinner_A7.setAdapter(adapter_spinner);
    Spinner_A8.setAdapter(adapter_spinner);

    
    
    
    //String s = String.valueOf(sum); // 其中value 為任意一種數字類型。

    String s = String.valueOf(sum);
    final AlertDialog alertDialog = getAlertDialog("--計算結果出爐--",s);
 
    dialogButton.setOnClickListener(new Button.OnClickListener() { 
        public void onClick(final View view) { 
            //顯示對話框 
            alertDialog.show(); 
        } 
    });    
  }
  
  private AlertDialog getAlertDialog(String title,String message){ 
  Builder builder = new AlertDialog.Builder(hard.this); 
  builder.setTitle(title); 
  builder.setMessage(message); 
  builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
      public void onClick(DialogInterface dialog, int which) { 
          //按下按鈕時顯示快顯 
          //Toast.makeText(hard.this, "您按下OK按鈕", Toast.LENGTH_SHORT).show(); 
      } 
  }); 
  
  builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
     public void onClick(DialogInterface dialog, int which) { 
          //按下按鈕時顯示快顯 
          //Toast.makeText(hard.this, "您按下Cancel按鈕", Toast.LENGTH_SHORT).show(); 
      } 
  }); 
  return builder.create(); 
} 
}


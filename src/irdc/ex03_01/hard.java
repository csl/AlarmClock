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



/* �s�ظ��Activity */
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
    
    //�إߤ@��ArrayAdapter����A�é�m�U�Կ�檺���e 
    ArrayAdapter<String> adapter_spinner = new ArrayAdapter<String>
    (this,android.R.layout.simple_spinner_item,new String[]{
        "�п��","0=���|���O��",
        "1=�ܤַ|���O��",
        "2=���ɷ|���O��",
        "3=�g�`�|���O��"}); 
    //�]�w�U�Կ�檺�˦� 
    adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
    Spinner_A1.setAdapter(adapter_spinner); 
    //�]�w���سQ������᪺�ʧ@ 
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
        //Toast.makeText(hard.this, "�z�S����ܥ��󶵥�", Toast.LENGTH_SHORT).show(); 
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
        //Toast.makeText(hard.this, "�z�S����ܥ��󶵥�", Toast.LENGTH_SHORT).show(); 
    }
    });
    Spinner_A3.setAdapter(adapter_spinner);
    Spinner_A4.setAdapter(adapter_spinner);
    Spinner_A5.setAdapter(adapter_spinner);
    Spinner_A6.setAdapter(adapter_spinner);
    Spinner_A7.setAdapter(adapter_spinner);
    Spinner_A8.setAdapter(adapter_spinner);

    
    
    
    //String s = String.valueOf(sum); // �䤤value �����N�@�ؼƦr�����C

    String s = String.valueOf(sum);
    final AlertDialog alertDialog = getAlertDialog("--�p�⵲�G�X�l--",s);
 
    dialogButton.setOnClickListener(new Button.OnClickListener() { 
        public void onClick(final View view) { 
            //��ܹ�ܮ� 
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
          //���U���s����ܧ��� 
          //Toast.makeText(hard.this, "�z���UOK���s", Toast.LENGTH_SHORT).show(); 
      } 
  }); 
  
  builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
     public void onClick(DialogInterface dialog, int which) { 
          //���U���s����ܧ��� 
          //Toast.makeText(hard.this, "�z���UCancel���s", Toast.LENGTH_SHORT).show(); 
      } 
  }); 
  return builder.create(); 
} 
}


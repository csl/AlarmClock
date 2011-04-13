package irdc.ex03_01;

/* import����class */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/* ��ڸ��X�x�aDialog��Activity */
public class AlarmAlert extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ���X���x�aĵ��  */
    new AlertDialog.Builder(AlarmAlert.this)
        .setIcon(R.drawable.clock_icon)
        .setTitle("�x���T�F!!")
        .setMessage("���ְ_�ɧa!!!")
        .setPositiveButton("�����L",new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int whichButton)
          {
            /* ����Activity */
             
            AlarmAlert.this.finish();
          }
        })
        .show();
  } 
}
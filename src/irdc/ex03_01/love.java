



package irdc.ex03_01;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;



/* �s�ظ��Activity */
public class love extends Activity
{
  Intent intent_love = new Intent();

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.love);
    
    final ListView list3 = (ListView) findViewById(R.id.tab4_lovelistview);
    /*setOnItemClickListener*/
    
    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
        this,android.R.layout.simple_list_item_1,new String[]{
            "�O���W�ߪ��ίv�ɶ��A�ͬ��@���n�W�ߡA�Y�ϫe�@�ѨS�Φn�A�j�Ѥ��n���ɰ_�ɡA�H�K�v�T�ߤW���ίv",
            "�Ϋe�i�w�������B�ܪM�������ΦY�Ǥp�I�ߡA���U�ίv�~��A���̦n���n�Y�ӹ��A�H�K�z�G���Ƥ��}�A�v�T�ίv�~��",
            "�Y�歭��b�ɤW���ɶ��A�դѮɺɶq���n�ݦb�ɤW�A�u���b�ߤW�Q��ı�ɤ~�W�ɺ�ı",
            "���n�j���ۤv�J�ΡA�p�G���b�ɤW�W�L�T�Q�������M�Τ��ۡA�N�_�ɰ��ǷũM�����ʡA����Q�ΤF�A�W�ɺ�ı",
            "�ίv�u�n���j��ı�o���믫�N���F�A7-8�p�ɥH�U����ı�ɶ��N�����A�Ӫ����ίv�ɶ��ϦӺΤ��n",
            "�u�@�W���ҰʤΦb��i����^�U���B�ʡA�ä������U�ίv�A�A�ɪ����P���߱��h�B�ʡA�鶴�ߦ��q",
            "�`�N�O�����E�Υ|�g���Ҫ��ξA�A�ж��Y���j�A�n�O���Ů𪺬y�q�A�Ů𤣬y�q�����p�A�i��y�����W���Ӯɩ����I�I���Pı",
            "���u�V�t�V�n�A�Y�����d���p�O�A�H����O�����y�A�B���u���y�����Ӯg���y���A�]���ֳ\���u����E�A�i��}�a�Ͳz�������W�ߩ�",
            "�Ӽ��B�ӧN���|�v�T�ίv�A�̲z�Q����ǫǷŬ�25�J���k",
            "������Ϸ|�v�T�ίv�A�s�릳���U�J�v���ĪG�A�o�|�y�����_�L�v�F���Ω@�ط|�ް_���v�A�קK�b�U�Ȥ��I�H�ᶼ��",
            "�קK�i���ӱߩΤ��ײ������\�A�ߤW���n�ܤj�q����",
            "�Ϋe��p�ɤŭ��Τ��G�A�]���Y�Ǥ��G�t���״I���[�A���Q�����@�ΡA�|�y���W��",
            "�C�ѩw�q���B�ʥi�H���@�ίv�~��A���w�q���@�P�B�ʫh�i��]�����ĵh�Ӽv�T�ίv",
            "�Τ��ۮɤ��n�@���������A���|�ϧA��۫�A�L�k���P�A��Τ���",
            "���E�n����B�n�w�A���A�ֳQ��ĳ�n�ξA���աA�Ӳ����B���|�ӳn���E�Y�i���U�J�v",
            "�бN��ı�ݦ��ܭ��n����!�R�����ίv�i�H���A�u�@�M�ǲߧ󦳮Ĳv",
            "�O���@�Ӻίv�����A�i�H���U�A���D�A��ı���W�ߦӧ��A���H�J�v����]",
            "���n�b�ɤW�u�@�Ϊ̾ǲߡA�o�˷|���A������ı�o�ɤW���O��ı���a��"            
            });
    list3.setAdapter(adapter2);
    updateListView();
    list3.setOnItemClickListener
    (new ListView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
        if(id==0)
          {
            /*intent.setClass(EX03_01.this, hard.class);
            startActivity(intent);
            finish();*/
          }
        else if(id==1)
        {
             /* intent2.setClass(EX03_01.this, music.class);
              startActivity(intent2);
              finish(); */
        }
        else if(id==2)
        {
          /*intent3.setClass(EX03_01.this, love.class);
          startActivity(intent3);
          finish();*/
        }

      }
      public void onNothingSelected(AdapterView<?> parent) {
      }

     }
    );
    
    
    
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
        
        }  
      return true; 
      
    }
}

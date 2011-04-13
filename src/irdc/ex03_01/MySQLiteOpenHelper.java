package irdc.ex03_01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MySQLiteOpenHelper extends SQLiteOpenHelper
{
  public String TableNames[];
  public String FieldNames[][];
  public String FieldTypes[][];
  public static String NO_CREATE_TABLES = "no tables";
  private String message = "";
  
  public MySQLiteOpenHelper(Context context, String dbname, CursorFactory factory, int version, String tableNames[], String fieldNames[][], String fieldTypes[][])
  {
    super(context, dbname, factory, version);
    TableNames = tableNames;
    FieldNames = fieldNames;
    FieldTypes = fieldTypes;
  }

  @Override
  public void onCreate(SQLiteDatabase db)
  {
    if (TableNames == null)
    {
      message = NO_CREATE_TABLES;
      return;
    }
    /* �إ�table */
    for (int i = 0; i < TableNames.length; i++)
    {
      String sql = "CREATE TABLE " + TableNames[i] + " (";
      for (int j = 0; j < FieldNames[i].length; j++)
      {
        sql += FieldNames[i][j] + " " + FieldTypes[i][j] + ",";
      }
      sql = sql.substring(0, sql.length() - 1);
      sql += ")";
      db.execSQL(sql);
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int arg1, int arg2)
  {
    for (int i = 0; i < TableNames[i].length(); i++)
    {
      String sql = "DROP TABLE IF EXISTS " + TableNames[i];
      db.execSQL(sql);
    }
    onCreate(db);
  }

  public void execSQL(String sql) throws java.sql.SQLException
  {
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL(sql);
  }

  /**
   * �d�߸��
   * 
   * @param table
   *          �d�ߪ�table name
   * @param columns
   *          �d�ߪ���ƪ����W��
   * @param selection
   *          �d�߱���r�� �p�Gfield1 = ? and field2 = ?
   * @param selectionArgs
   *          �d�߱��󪺭� �p�G["a","b"]
   * @param groupBy
   *          groupBy�᭱���r�� �p�Gfield1,field2
   * @param having
   *          having�᭱���r��
   * @param orderBy
   *          orderBy�᭱���r��
   * @return Cursor �]�t�F���o����ƶ�
   */
  public Cursor select(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
  {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    return cursor;
  }

  /**
   * �s�W���
   * 
   * @param table
   *          �s�W��ƪ�table name
   * @param fields
   *          �s�W��ƪ����W��
   * @param values
   *          �s�W��ƪ�����
   * @return long row id
   */
  public long insert(String table, String fields[], String values[])
  {
    SQLiteDatabase db = this.getWritableDatabase();
    /* �N�s�W���ȩ�JContentValues */
    ContentValues cv = new ContentValues();
    for (int i = 0; i < fields.length; i++)
    {
      cv.put(fields[i], values[i]);
    }
    return db.insert(table, null, cv);
  }

  /**
   * �R�����
   * 
   * @param table
   *          �R����ƪ�table name
   * @param where
   *          �R����ƪ�����
   * @param whereValue
   *          �R����ƪ������
   * @return int �R��������
   */
  public int delete(String table, String where, String[] whereValue)
  {
    SQLiteDatabase db = this.getWritableDatabase();

    return db.delete(table, where, whereValue);
  }

  /**
   * ��s���
   * 
   * @param table
   *          ��s��ƪ�table name
   * @param fields
   *          ��s��ƪ����W��
   * @param values
   *          ��s��ƪ�����
   * @param where
   *          ��s����ƪ�����
   * @param whereValue
   *          ��s��ƪ������
   * @return int ��s������
   */
  public int update(String table, String updateFields[],
      String updateValues[], String where, String[] whereValue)
  {
    SQLiteDatabase db = this.getWritableDatabase();

    /* �N�ק諸�ȩ�JContentValues */
    ContentValues cv = new ContentValues();
    for (int i = 0; i < updateFields.length; i++)
    {
      cv.put(updateFields[i], updateValues[i]);
    }
    return db.update(table, cv, where, whereValue);
  }

  public String getMessage()
  {
    return message;
  }

  @Override
  public synchronized void close()
  {
    // TODO Auto-generated method stub
    super.close();
  }
}

package hk.ust.cse.comp107x.chatclientcolors;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Oleksandr on 03.09.2015.
 */
public class DBHelper extends SQLiteOpenHelper implements BaseColumns{
    public static final String DATABASE_NAME = "myProfileDB.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "profile";

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String AGE = "age";
    public static final String SKYPE = "skype";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";

    private static final String DATABASE_TABLE_SCRIPT = "create table " + DATABASE_TABLE +
            " (" + BaseColumns._ID + " integer primary key autoincrement, " +  NAME + " text, " +
            SURNAME + " text, " + AGE + " integer, " + SKYPE + " text, " + EMAIL + " text, " +
            PHONE + " integer);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public  boolean insertData(String name, String surname,String age,String skype,String email,String phone){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(SURNAME, surname);
        contentValues.put(AGE,age);
        contentValues.put(SKYPE, skype);
        contentValues.put(EMAIL, email);
        contentValues.put(PHONE, phone);
        long result = database.insert(DATABASE_TABLE, null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(long id,String name, String surname,String age,String skype,String email,String phone){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, id);
        contentValues.put(NAME, name);
        contentValues.put(SURNAME, surname);
        contentValues.put(AGE,age);
        contentValues.put(SKYPE, skype);
        contentValues.put(EMAIL, email);
        contentValues.put(PHONE, phone);
        database.update(DATABASE_TABLE, contentValues,_ID + "=" + id ,null);
        return  true;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_SCRIPT);
        Log.i("TAG","DB are Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}

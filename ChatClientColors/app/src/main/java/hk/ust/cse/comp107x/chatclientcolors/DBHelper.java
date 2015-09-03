package hk.ust.cse.comp107x.chatclientcolors;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Oleksandr on 03.09.2015.
 */
public class DBHelper extends SQLiteOpenHelper implements BaseColumns{
    private static final String DATABASE_NAME = "myProfileDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "profile";

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String AGE = "age";
    private static final String SKYPE = "skype";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_SCRIPT);
        Log.i("TAG","DB Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}

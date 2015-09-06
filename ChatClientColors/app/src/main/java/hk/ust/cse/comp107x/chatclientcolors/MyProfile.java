package hk.ust.cse.comp107x.chatclientcolors;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MyProfile extends ActionBarActivity {
    final Context context = this;
    Button buttonEditProfile;
    EditText editTextName, editTextSurname, editTextAge, editTextSkype, editTextEmail, editTextPhone;
    TextView textViewName, textViewSurname, textViewAge, textViewSktpe, textViewEmail, textViewPhone;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        //LayoutInflater layoutInflaterEditProfile = LayoutInflater.from(context);

        dbHelper = new DBHelper(this, "myProfileDB.db", null, 1);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewSurname = (TextView) findViewById(R.id.textViewSurname);
        textViewAge = (TextView) findViewById(R.id.textViewAge);
        textViewSktpe = (TextView) findViewById(R.id.textViewSkype);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewPhone = (TextView) findViewById(R.id.textViewPhone);

        Cursor cursor = sqLiteDatabase.query(dbHelper.DATABASE_TABLE, new String[] {dbHelper.NAME, dbHelper.SURNAME,
                dbHelper.AGE, dbHelper.SKYPE, dbHelper.EMAIL, dbHelper.PHONE}, null, null, null, null,null);

        if( cursor != null && cursor.moveToFirst() ){
            String stringName = cursor.getString(cursor.getColumnIndex(dbHelper.NAME));
            String stringSurname = cursor.getString(cursor.getColumnIndex(dbHelper.SURNAME));
            String stringAge = cursor.getString(cursor.getColumnIndex(dbHelper.AGE));
            String stringSkype = cursor.getString(cursor.getColumnIndex(dbHelper.SKYPE));
            String stringEmail = cursor.getString(cursor.getColumnIndex(dbHelper.EMAIL));
            String stringPhone = cursor.getString(cursor.getColumnIndex(dbHelper.PHONE));

            textViewName.setText(stringName);
            textViewSurname.setText(stringSurname);
            textViewAge.setText(stringAge);
            textViewSktpe.setText(stringSkype);
            textViewEmail.setText(stringEmail);
            textViewPhone.setText(stringPhone);
            cursor.close();
        } else {
            textViewName.setText("Name Defaut");
            textViewSurname.setText("Surname Default");
            textViewAge.setText("Age Default");
            textViewSktpe.setText("Skype Default");
            textViewEmail.setText("Email Default");
            textViewPhone.setText("Phone Default");
        }



        buttonEditProfile = (Button) findViewById(R.id.buttonEditProfile);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickEditProfile(View view) {
        LayoutInflater layoutInflaterEditProfile = LayoutInflater.from(context);
        View view1EditProfile =  layoutInflaterEditProfile.inflate(R.layout.edit_profile, null);
        editTextName = (EditText) view1EditProfile.findViewById(R.id.editTextEnterName);
        editTextSurname = (EditText) view1EditProfile.findViewById(R.id.editTextEnterSurname);
        editTextAge = (EditText) view1EditProfile.findViewById(R.id.editTextEnterAge);
        editTextSkype = (EditText) view1EditProfile.findViewById(R.id.editTextEnterSkype);
        editTextEmail = (EditText) view1EditProfile.findViewById(R.id.editTextEnterEmail);
        editTextPhone = (EditText) view1EditProfile.findViewById(R.id.editTextEnterPhone);

        final AlertDialog.Builder alertDialogEditProfile = new AlertDialog.Builder(MyProfile.this);
        alertDialogEditProfile.setTitle("Edit profile");
        alertDialogEditProfile.setView(view1EditProfile);
        alertDialogEditProfile.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.updateData(1,
                        editTextName.getText().toString(),
                        editTextSurname.getText().toString(),
                        editTextAge.getText().toString(),
                        editTextSkype.getText().toString(),
                        editTextEmail.getText().toString(),
                        editTextPhone.getText().toString());
                Log.i("TAG", "DATA added");

            }
        });
        alertDialogEditProfile.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialogEditProfile.show();
    }

}

package hk.ust.cse.comp107x.chatclientcolors;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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


public class MyProfile extends ActionBarActivity {
    final Context context = this;
    Button buttonEditProfile;
    EditText editTextName, editTextSurname, editTextAge, editTextSkype, editTextEmail, editTextPhone;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        LayoutInflater layoutInflaterEditProfile = LayoutInflater.from(context);

        dbHelper = new DBHelper(this, "myProfileDB.db", null, 1);

        sqLiteDatabase = dbHelper.getWritableDatabase();

        buttonEditProfile = (Button) findViewById(R.id.buttonEditProfile);

        editTextName = (EditText) findViewById(R.id.editTextEnterName);
   /*     editTextSurname = (EditText) findViewById(R.id.editTextEnterSurname);
        editTextAge = (EditText) findViewById(R.id.editTextEnterAge);
        editTextSkype = (EditText) findViewById(R.id.editTextEnterSkype);
        editTextEmail = (EditText) findViewById(R.id.editTextEnterEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextEnterPhone);*/
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
                dbHelper.insertData(editTextName.getText().toString(),
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

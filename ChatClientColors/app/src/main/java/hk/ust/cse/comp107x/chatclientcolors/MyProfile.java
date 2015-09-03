package hk.ust.cse.comp107x.chatclientcolors;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

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

        AlertDialog.Builder alertDialogEditProfile = new AlertDialog.Builder(MyProfile.this);
        alertDialogEditProfile.setTitle("Invite friend");
        alertDialogEditProfile.setView(view1EditProfile);

        editTextName = (EditText) findViewById(R.id.editTextEnterName);
        editTextSurname = (EditText) findViewById(R.id.editTextEnterSurname);

        alertDialogEditProfile.show();
    }
}

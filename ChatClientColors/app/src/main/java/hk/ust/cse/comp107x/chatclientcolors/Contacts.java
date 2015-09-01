package hk.ust.cse.comp107x.chatclientcolors;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;


public class Contacts extends AppCompatActivity implements AdapterView.OnItemClickListener {
    final Context context = this;
    Toolbar toolbar;
    String[] namesOnline, namesOffline;
    ImageButton buttonSendEmail;
    ListView friendOnlineView, friendOfflineView;
    EditText editTextEmailEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        buttonSendEmail = (ImageButton) findViewById(R.id.imageButtonEmail);

        TabHost tabHostOnlineOffline = (TabHost) findViewById(R.id.tabHostOnlineOffline);
        tabHostOnlineOffline.setup();

        TabHost.TabSpec tabSpec;
        tabSpec = tabHostOnlineOffline.newTabSpec("TAG1");
        tabSpec.setIndicator("Online", getResources().getDrawable(R.drawable.online));
        tabSpec.setContent(R.id.tabOnline);
        tabHostOnlineOffline.addTab(tabSpec);

        tabSpec = tabHostOnlineOffline.newTabSpec("TAG2");
        tabSpec.setIndicator("Offline");
        tabSpec.setContent(R.id.tabOffline);
        tabHostOnlineOffline.addTab(tabSpec);


        namesOnline = getResources().getStringArray(R.array.friendsOnline);
        namesOffline = getResources().getStringArray(R.array.friendsOffline);

        // If you are using a ListView widget, then your activity should implement
        // the onItemClickListener. Then you should set the OnItemClickListener for
        // teh ListView.
        friendOnlineView = (ListView) findViewById(R.id.friendListView);
        friendOnlineView.setAdapter(new ArrayAdapter<String>(this, R.layout.friend_item, namesOnline));
        friendOnlineView.setOnItemClickListener(this);

        friendOfflineView = (ListView) findViewById(R.id.listViewFriendOffline);
        friendOfflineView.setAdapter(new ArrayAdapter<String>(this, R.layout.friend_item, namesOffline));
        friendOfflineView.setOnItemClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_contacts); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
    }

    public void onEmailClick(View view) {
        LayoutInflater layoutInflaterEmail = LayoutInflater.from(context);
        View view1InviteEmail = layoutInflaterEmail.inflate(R.layout.invite_email, null);

        AlertDialog.Builder alertDialogSendEmail = new AlertDialog.Builder(Contacts.this);
        alertDialogSendEmail.setTitle("Invite friend");
        alertDialogSendEmail.setView(view1InviteEmail);

        editTextEmailEnter = (EditText) findViewById(R.id.editTextEmailFriend);

        //TODO

        alertDialogSendEmail.show();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Intent mIntent = new Intent(this,ChatClient.class);
        mIntent.putExtra(getString(R.string.friend), namesOnline[position]);
        startActivity(mIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(Contacts.this);
                builder.setTitle("About").setMessage("Oleksand_M \nTernopil, Ukraine").setNegativeButton("Nice", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}

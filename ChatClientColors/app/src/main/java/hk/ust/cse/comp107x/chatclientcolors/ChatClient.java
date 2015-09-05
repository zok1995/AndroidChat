package hk.ust.cse.comp107x.chatclientcolors;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Date;


public class ChatClient extends AppCompatActivity implements View.OnClickListener {

    final Context context = this;
    ImageButton sendMessageButton;
    EditText messageText;
    RecyclerView messageList;
    RecyclerView.Adapter mAdapter = null;
    ArrayList<Message> messages = null;
    int in_index = 0;
    Toolbar toolbar;
    Button buttonAddSomething, buttonTakeApicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_client);
        LayoutInflater layoutInflaterAddFile = LayoutInflater.from(context);
        View viewSendFile =  layoutInflaterAddFile.inflate(R.layout.send_file, null);

        buttonAddSomething = (Button) findViewById(R.id.buttonAddFotoVideoOnline);
        buttonTakeApicture = (Button) viewSendFile.findViewById(R.id.buttonTakeAPicture);
        buttonTakeApicture.setOnClickListener(this);

        sendMessageButton = (ImageButton) findViewById(R.id.sendButton);
        sendMessageButton.setOnClickListener(this);

        messageText = (EditText) findViewById(R.id.messageText);

        messages = new ArrayList<Message>();
        mAdapter = new MyAdapter(this, messages);

        messageList = (RecyclerView) findViewById(R.id.messageList);
        messageList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        messageList.setLayoutManager(llm);
        messageList.setAdapter(mAdapter);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // берем імя друга з інтенту
        Intent in = getIntent();
        String friendName = in.getStringExtra(getString(R.string.friend));

        getSupportActionBar().setTitle(friendName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatClient.this);
                builder.setTitle("About").setMessage("Oleksand_M \nTernopil, Ukraine").setNegativeButton("Nice", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setNeutralButton("Author on FB", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uriAddress = Uri.parse("https://www.facebook.com/profile.php?id=100005239003799");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uriAddress);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendButton:
                String messString = messageText.getText().toString();
                if (!messString.equals("")) {
                    Message message = new Message("", messString, true, new Date());
                    messages.add(message);
                    messageList.scrollToPosition(messages.size() - 1);
                    mAdapter.notifyDataSetChanged();
                    sendMessage();
                    message = null; //після відсилання очистити поле
                    messageText.setText("");
                }
                break;
            default:
                break;
        }
    }

    private void sendMessage() {
        String[] incoming = {"Hey, How's it going?",
                "Super! Let's do lunch tomorrow",
                "How about Mexican?",
                "Great, I found this new place around the corner",
                "Ok, see you at 12 then!"};

        if (in_index < incoming.length) {
            Message message = new Message("John", incoming[in_index], false, new Date());
            messages.add(message);
            in_index++;
            messageList.scrollToPosition(messages.size() - 1);
            mAdapter.notifyDataSetChanged();
        }
    }


    public void onClickAddSomething(View view) {
        final AlertDialog.Builder alertDialogSendSomethting = new AlertDialog.Builder(ChatClient.this);
        LayoutInflater layoutInflaterAddFile = LayoutInflater.from(context);
        View viewSendFile = layoutInflaterAddFile.inflate(R.layout.send_file, null);

        alertDialogSendSomethting.setView(viewSendFile);
        alertDialogSendSomethting.setTitle("Select File for Sending");
        alertDialogSendSomethting.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialogSendSomethting.show();

    }

    public void onClickSendFile(View view) {
        Log.i("TAG", "It clicked");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CAMERA_BUTTON);
        intent.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_CAMERA));
        sendOrderedBroadcast(intent, null);
    }
}
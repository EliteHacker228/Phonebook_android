package com.example.max.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText name, bday, phone;
    private Button save_btn, abort_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        Intent intent = getIntent();

        name = (EditText)findViewById(R.id.edit_txt_name);
        bday = (EditText)findViewById(R.id.edit_txt_bday);
        phone = (EditText)findViewById(R.id.edit_txt_phone);
        save_btn=(Button)findViewById(R.id.edit_save_button);
        abort_btn=(Button)findViewById(R.id.edit_abort_button);

        String a1 = intent.getStringExtra("source_name");
        String a2 = intent.getStringExtra("source_bday");
        String a3 = intent.getStringExtra("source_phone");


        name.setText(a1);
        bday.setText(a2);
        phone.setText(a3);

       save_btn.setOnClickListener(SAVE_BTN_LISTENER);
       abort_btn.setOnClickListener(ABORT_BTN_LISTENER);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }

    View.OnClickListener SAVE_BTN_LISTENER = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            long ID = intent.getLongExtra("sorce_id", 0);

            ContactsHelper ch = new ContactsHelper(getApplicationContext());

            String new_name = name.getText().toString();
            String new_birthday = bday.getText().toString();
            String new_phone = phone.getText().toString();

            Contact new_contact = new Contact(1,new_name, new_birthday, new_phone);
            ch.insert(new_name, new_birthday, new_phone);
            ch.update(new_contact);

            Intent intent2 = new Intent(EditActivity.this, MainActivity.class);
            startActivity(intent2);
        }
    };

    View.OnClickListener ABORT_BTN_LISTENER = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = getIntent();
            long ID = intent.getLongExtra("sorce_id", 0);

            ContactsHelper ch = new ContactsHelper(getApplicationContext());

            String new_name = name.getText().toString();
            String new_birthday = bday.getText().toString();
            String new_phone = phone.getText().toString();

            Contact new_contact = new Contact(1,new_name, new_birthday, new_phone);
            ch.insert(new_name, new_birthday, new_phone);
            ch.update(new_contact);

            Intent intent2 = new Intent(EditActivity.this, MainActivity.class);
            startActivity(intent2);

        }
    };
}

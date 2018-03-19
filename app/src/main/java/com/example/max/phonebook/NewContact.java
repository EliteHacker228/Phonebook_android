package com.example.max.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewContact extends AppCompatActivity {

    TextView name, birthday, phone;
    Button CRTbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_contact);
        name = (TextView)findViewById(R.id.nc_txt_name);
        birthday = (TextView)findViewById(R.id.nc_txt_bday);
        phone = (TextView)findViewById(R.id.nc_txt_phone);

        CRTbutton = (Button)findViewById(R.id.nc_create_button);
        CRTbutton.setOnClickListener(newContact);

    }

    View.OnClickListener newContact = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String arg_name = name.getText().toString();
            String arg_bday = birthday.getText().toString();
            String arg_phone = phone.getText().toString();
            if(arg_name.equals("")||arg_bday.equals("")||arg_phone.equals("")){
                Toast.makeText(NewContact.this, "Нельзя оставлять пустые" +
                        " поля", Toast.LENGTH_SHORT).show();
            }else{
                ContactsHelper ch = new ContactsHelper(getApplicationContext());
                ch.insert(name.getText().toString(),birthday.getText().toString(), phone.getText().toString());
                Contact addedContact = new Contact(0,arg_name, arg_bday, arg_phone);
                ch.update(addedContact);
                Toast.makeText(NewContact.this, "Контакт добавлен", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewContact.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };
}

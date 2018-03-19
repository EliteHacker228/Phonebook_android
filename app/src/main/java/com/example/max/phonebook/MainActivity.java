package com.example.max.phonebook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    FloatingActionButton NCFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NCFab = (FloatingActionButton)findViewById(R.id.new_contact_fab);
        NCFab.setOnClickListener(NewContactBTN);


        List<Contact> contacts;
        ContactsHelper ch = new ContactsHelper(getApplicationContext());
        contacts = ch.getAll();

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(new RVAdapter(contacts, this));
    }

    View.OnClickListener NewContactBTN = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivity(intent);


        }
    };

}

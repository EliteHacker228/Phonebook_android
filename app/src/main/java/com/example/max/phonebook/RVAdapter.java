package com.example.max.phonebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max on 18.03.2018.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ContactsViewHolder> {
    private List<Contact> contacts;
    private Context context;



    public RVAdapter(List<Contact> contacts, Context context){
        Collections.sort(contacts, Contact.NAME_COMPARATOR);

        this.contacts = contacts;
        this.context = context;
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder{

        TextView Name, Bday, Phone;
        Button rmBtn;
        CardView cv;

        CardViewClickListener  cvListener = new CardViewClickListener();
        ButtonRemoveClickListener btnClickListener = new ButtonRemoveClickListener();

        ContactsViewHolder(View itemView){
            super(itemView);

            Name = itemView.findViewById(R.id.txt_book_name);
            Bday = itemView.findViewById(R.id.txt_book_bday);
            Phone = itemView.findViewById(R.id.txt_book_phone);

            rmBtn = itemView.findViewById(R.id.btn_rv_remove);
            cv = itemView.findViewById(R.id.card_rv);

            cv.setOnClickListener(cvListener);
            rmBtn.setOnClickListener(btnClickListener);
        }
    }

    class CardViewClickListener implements View.OnClickListener{

        private Contact contact;

        @Override
        public void onClick(View v){
            //Здесь будет код, который будет открывать активность
            //редактированияя контакта
            Intent intent = new Intent(context,EditActivity.class);
            intent.putExtra("source_name",contact.getName());
            intent.putExtra("source_bday", contact.getBirthday());
            intent.putExtra("source_phone", contact.getPhone());
            intent.putExtra("source_id", contact.getId());

            int position = contacts.indexOf(contact);
            contacts.remove(contact);
            ContactsHelper ch = new ContactsHelper(context);
            ch.delete(contact.getId());
            notifyItemRemoved(position);

            ((Activity)context).startActivity(intent);
        }

        void setRecord(Contact contact){
            this.contact=contact;
        }
    }

    class ButtonRemoveClickListener implements View.OnClickListener{

        Contact contact;

        @Override
        public void onClick(View view) {
            int position = contacts.indexOf(contact);
            contacts.remove(contact);
            ContactsHelper ch = new ContactsHelper(context);
            ch.delete(contact.getId());
            notifyItemRemoved(position);
        }

        void setRecord(Contact contact){
            this.contact = contact;
        }
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_rv, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        final Contact contact = contacts.get(position);

        holder.Name.setText(String.valueOf(contact.getName()));
        holder.Bday.setText(String.valueOf(contact.getBirthday()));
        holder.Phone.setText(String.valueOf(contact.getPhone()));

        holder.cvListener.setRecord(contact);
        holder.btnClickListener.setRecord(contact);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}

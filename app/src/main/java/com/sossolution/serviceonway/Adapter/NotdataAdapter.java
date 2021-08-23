package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Notservice;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;

public class NotdataAdapter extends RecyclerView.Adapter<NotdataAdapter.Myviewholder>
{
    private Context context2;
    private ArrayList<Notservice> datalistnotservice;

    public NotdataAdapter(Context context, ArrayList<Notservice>datalistnotservice )
    {
        this.context2=context;
        this.datalistnotservice=datalistnotservice;

    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notinclude,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, int position)
    {

        Notservice notservice= datalistnotservice.get(position);
        String s1=notservice.getService();
        Log.d("s1value",s1);

        /*String []s5=name.split(",");
        String s6="";
        for(int m=0;m<s5.length;m++)
        {
            s6+=s5[m]+"\n";

        }*/
        // textView_notinclude.setText();*/
      //  holder.text_name.setText(s6+"\n");
        holder.textView.setText(s1);
       // Toast.makeText(context2,s1.toString()+"not include data", Toast.LENGTH_SHORT).show();


    }

    @Override
    public int getItemCount() {
        return datalistnotservice.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public Myviewholder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.service11);
        }
    }
}

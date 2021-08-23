package com.sossolution.serviceonway.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Activity.Subscription_my_item;
import com.sossolution.serviceonway.Interface.My_subscription_bike_interfac;
import com.sossolution.serviceonway.R;

import java.util.ArrayList;

public class My_subscription_adapter extends RecyclerView.Adapter<My_subscription_adapter.My_viewholder>
{
    private Context context;
    private ArrayList<Subscription_my_item> my_subscription_item;
    private Subscription_my_item subscription_my_item;
    private My_subscription_bike_interfac my_subscription_bike_interfac;

    public My_subscription_adapter(Context context, ArrayList<Subscription_my_item> my_subscription_item1,Subscription_my_item subscription_my_item1,My_subscription_bike_interfac my_subscription_bike_interfac1)
    {
        this.context=context;
        this.my_subscription_item=my_subscription_item1;
        this.subscription_my_item=subscription_my_item1;
        this.my_subscription_bike_interfac=my_subscription_bike_interfac1;
    }

    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_subscription_bike,parent,false);
        return new My_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_subscription_adapter.My_viewholder holder, int position)
    {
        subscription_my_item=my_subscription_item.get(position);
        String time_period= subscription_my_item.getYear();
        Log.d("time_period",time_period);
        String price= subscription_my_item.getPrice();
        String text= subscription_my_item.getText();
        String[] srvice_value=text.split(",");
        Log.d("text_service",text);

        for( int i = 0; i < srvice_value.length; i++ )
        {
            TextView textView = new TextView(context);
            textView.setText(srvice_value[i]);
            //textView.setTextColor(Color.WHITE);
           // textView.setTextColor(Color.parseColor("#000000"));
            //textView.setTextColor(ContextCompat.getColor(context,c));
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextColor(context.getResources().getColor(R.color.color1));
           // Drawable img = context.getResources().getDrawable(R.drawable.service_1);
            //textView.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
            textView.setTextSize(15);
            holder.layout.addView(textView);
        }



       // holder.bind(walletlist.get(position),myinterfacehistary);
        holder.textView_time.setText(time_period);
        holder.textView_price.setText(price);
        //holder.textView_service.setText(srvice_value);

       holder.bind(my_subscription_item.get(position),my_subscription_bike_interfac);



    }

    @Override
    public int getItemCount()
    {
        Log.d("item", String.valueOf(my_subscription_item.size()));
        return my_subscription_item.size();

    }

    public class My_viewholder extends RecyclerView.ViewHolder
    {
        TextView textView_time,textView_price,textView_service;
        Button btn_id;
        LinearLayout layout;
        CardView cardView;
        Button button;
        public My_viewholder(@NonNull View itemView)
        {
            super(itemView);
            textView_price=itemView.findViewById(R.id.dynamic_text_price);
            textView_time=itemView.findViewById(R.id.dynamic_time);
          //  textView_service=itemView.findViewById(R.id.dynamic_general_service);
            //btn_id=itemView.findViewById(R.id.button_id);
            layout=itemView.findViewById(R.id.layout_id);
            cardView=itemView.findViewById(R.id.cardView_1);
            button=itemView.findViewById(R.id.button_id);
           /* button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    View mView = LayoutInflater.from(context).inflate(R.layout.my_desing,null);
                    final EditText txt_inputText1 = mView.findViewById(R.id.edit_text_contact);
                    final EditText txt_inputText2 = mView.findViewById(R.id.rc_no);
                    final EditText txt_inputText3 = mView.findViewById(R.id.model_name);
                    final EditText txt_inputTex3 = mView.findViewById(R.id.NAME_PLATE_NO);
                    Button btn_cancel = mView.findViewById(R.id.pay_amount);
                    Button btn_okay = mView.findViewById(R.id.pay_amount);
                    alert.setView(mView);
                    final AlertDialog alertDialog = alert.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    btn_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    btn_okay.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                           //myCustomMessage.setText(txt_inputText.getText().toString());
                            alertDialog.dismiss();


                        }
                    });
                    alertDialog.show();
                    final EditText txt_inputText =mView.findViewById(R.id.edit_text_contact);
                   // Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
                   // Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
                    alert.setView(mView);
                    final AlertDialog alertDialog1 = alert.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    btn_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog1.dismiss();
                        }
                    });
                    btn_okay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //myCustomMessage1.setText(txt_inputText.getText().toString());
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();

                }
            });
*/
        }

        public void bind(Subscription_my_item my_item, My_subscription_bike_interfac my_subscription_bike_interfac) {

            button=itemView.findViewById(R.id.button_id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // myinterfacehistary.newitem(wallet_history);
                    my_subscription_bike_interfac. my_item_subscription(my_item);
                }
            });


        }
    }
}

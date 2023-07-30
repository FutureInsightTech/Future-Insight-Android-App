package com.futureinsight.futureinsight.Utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;

import com.org.futureinsight.databinding.NoInternetConnectionLayoutBinding;

import java.util.zip.Inflater;

public class NetworkBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!isNetworkConnected(context))
        {
            //Calling the layout into java Code
            NoInternetConnectionLayoutBinding binding = NoInternetConnectionLayoutBinding.inflate(LayoutInflater.from(context));
            //Using AlertDialog to show the layout
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(binding.getRoot());
            //Making the dialog box not cancelable until connected to internet
            builder.setCancelable(false);
            Dialog dialog = builder.create();
            dialog.show();

            //try agian button click listener & logic
            binding.BtnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isNetworkConnected(context))
                    {   //if internet is connected then dismiss the dialog
                        dialog.dismiss();
                    }
                }
            });
        }
    }
    //this function is checking internet connection
    private boolean isNetworkConnected(Context context)
    {
        try {
            ConnectivityManager connectionMonitor = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionMonitor.getActiveNetworkInfo();
            //noinspection deprecation
            return networkInfo != null && networkInfo.isConnected();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Internet Not Connected");
            return false;
        }
    }
}

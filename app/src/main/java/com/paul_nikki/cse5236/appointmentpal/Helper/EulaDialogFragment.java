package com.paul_nikki.cse5236.appointmentpal.Helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;

import com.paul_nikki.cse5236.appointmentpal.R;

/**
 * Created by nikkbandy on 11/30/15.
 */
public class EulaDialogFragment extends DialogFragment{

    public void setEulaAccepted(){
        SharedPreferences prefs = getActivity().getSharedPreferences(getString(R.string.prefs), 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(getString(R.string.eula_accepted_key), true).apply();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("License Agreement")
                .setMessage(Html.fromHtml(getString(R.string.eula)))
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setEulaAccepted();
                    }
                })
                .setNegativeButton(R.string.decline, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        getActivity().finish();
                    }
                });
        return builder.create();
    }
}

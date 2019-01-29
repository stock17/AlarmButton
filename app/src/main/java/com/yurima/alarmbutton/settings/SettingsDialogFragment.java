package com.yurima.alarmbutton.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yurima.alarmbutton.R;
import com.yurima.alarmbutton.SettingsManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.R.id.edit;
import static butterknife.ButterKnife.bind;

/**
 * Created by Yury on 29.01.2019.
 */

public class SettingsDialogFragment extends DialogFragment {

    private Unbinder unbinder;

    @BindView(R.id.dialog_menu_ok_button) Button okButton;
    @OnClick(R.id.dialog_menu_ok_button)
    void onClickOkButton(){
        //TODO save settings
        SharedPreferences settings = getActivity().getSharedPreferences(SettingsManager.SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(SettingsManager.PHONE_NO, phoneText.getText().toString());
        editor.commit();
        dismiss();
    }

    @BindView(R.id.dialog_menu_cancel_button) Button cancelButton;
    @OnClick(R.id.dialog_menu_cancel_button)
    void onClickCancelButton(){
        dismiss();
    }

    @BindView(R.id.dialog_menu_phone_no)
    EditText phoneText;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_menu, null);
        builder.setView(view);
        unbinder = ButterKnife.bind(this, view);

        SharedPreferences settings = getActivity().getSharedPreferences(SettingsManager.SETTINGS, Context.MODE_PRIVATE);
        phoneText.setText(settings.getString(SettingsManager.PHONE_NO, "000"));
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}

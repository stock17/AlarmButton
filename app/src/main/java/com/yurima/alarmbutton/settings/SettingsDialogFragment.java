package com.yurima.alarmbutton.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.yurima.alarmbutton.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    }

    @BindView(R.id.dialog_menu_cancel_button) Button cancelButton;
    @OnClick(R.id.dialog_menu_cancel_button)
    void onClickCancelButton(){
        dismiss();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_menu, null);
        builder.setView(view);
        unbinder = ButterKnife.bind(this, view);
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}

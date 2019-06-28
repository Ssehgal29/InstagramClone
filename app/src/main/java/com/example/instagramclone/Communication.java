package com.example.instagramclone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class Communication extends AppCompatActivity implements View.OnClickListener {
    private TextView txtFeedback,txtAboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        txtFeedback=findViewById(R.id.txtFeedback);
        txtAboutUs=findViewById(R.id.txtAboutUs);

        txtFeedback.setOnClickListener(this);
        txtAboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtFeedback:

                final PrettyDialog prettyDialogFeedBack = new PrettyDialog(Communication.this);
                prettyDialogFeedBack.setTitle("Feedback").setIcon(R.drawable.user).setMessage("Give Your Valuable Feedback by Calling us").addButton("Call Us", R.color.colorAccent, R.color.colorPrimary, new PrettyDialogCallback() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:+919599499967"));
                        if (ActivityCompat.checkSelfPermission(Communication.this, Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions(Communication.this,new String[]{Manifest.permission.CALL_PHONE},0);
                            return;
                        }
                        prettyDialogFeedBack.dismiss();
                        startActivity(intent);
                    }
                }).show();

                break;
            case R.id.txtAboutUs:

                final  PrettyDialog prettyDialogAboutUs = new PrettyDialog(this);
                prettyDialogAboutUs.setTitle("About Us").setMessage("Instagram Clone"+"\n"+"Version: 1.0.0"+"\n"+"Instagram Clone is a smart app which is designed with simple and optimized user interface. " +
                        "Instagram Clone is an Entertainment application."+"\n"+"Developer: SAHIL SEHGAL").setIcon(R.drawable.app_icon).addButton("Done", R.color.colorAccent, R.color.colorPrimary, new PrettyDialogCallback() {
                    @Override
                    public void onClick() {
                        prettyDialogAboutUs.dismiss();
                    }
                }).show();
                break;
        }
    }
}

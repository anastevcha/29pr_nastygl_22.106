package com.example.a29pr_nastygl_22106;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    Button foto, back;
    ImageView fotoView;
    Context mContext;
    private static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mContext = this;
        foto = findViewById(R.id.btnCamera);
        foto.setOnClickListener(this);
        back = findViewById(R.id.btnBack);
        back.setOnClickListener(this);
        fotoView = findViewById(R.id.fotoView);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCamera) {
            Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try {
                startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}



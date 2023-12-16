package com.example.a29pr_nastygl_22106;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton buttonCamera, buttonInternet, buttonCalc, buttonMap, buttonPhone;
    Context mContext;
    String UrlYandex = "https://ya.ru/";
    String geoDoma = "geo:55.10210447716254, 82.97156124413391";//координаты моего дома
    private static final int PICK_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCamera = findViewById(R.id.imageButton_camera);
        buttonCamera.setOnClickListener(this);
        buttonInternet = findViewById(R.id.imageButton_internet);
        buttonInternet.setOnClickListener(this);
        buttonCalc = findViewById(R.id.imageButton_calc);
        buttonCalc.setOnClickListener(this);
        buttonMap = findViewById(R.id.imageButton_map);
        buttonMap.setOnClickListener(this);
        buttonPhone=findViewById(R.id.imageButton_phone);
        buttonPhone.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.imageButton_camera){
            if(checkCameraHardware(this) == true){
                startActivity(new Intent(this, CameraActivity.class));
            }
            else {
                Toast.makeText(mContext, "На устройстве нет камеры :(", Toast.LENGTH_LONG).show();
            }
        }
        else if(view.getId() == R.id.imageButton_internet){
            openWebPage(UrlYandex);
        }
        else if(view.getId() == R.id.imageButton_calc){
            pickContact();
        }
        else if(view.getId() == R.id.imageButton_map){
            startMap(geoDoma);
        }
        else if(view.getId() == R.id.imageButton_phone){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            startActivity(intent);

        }
    }
    // Проверка наличия камеры
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }
    //метод для открытия браузера
    private void openWebPage(String url) {
        Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
        myWebLink.setData(Uri.parse(url));
        startActivity(myWebLink);
    }

    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }



    protected void startMap(String geo){
        Uri intentUri = Uri.parse(geo);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
        mapIntent.setPackage("com.google.android.apps.maps"); //пакет Google Maps
        if (mapIntent.resolveActivity(getPackageManager()) != null)
            startActivity(mapIntent); //если есть Google Maps
        else {
            //иначе запускаем дефолтное мап приложение
            startActivity(new Intent(Intent.ACTION_VIEW, intentUri));
        }
    }
}
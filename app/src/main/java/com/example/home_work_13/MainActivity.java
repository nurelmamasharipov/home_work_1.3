package com.example.home_work_13;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button openGallary;
    private ImageView gallery;
    private View myText;
    private Button myButton;
    private Button openGmail;
    private Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = findViewById(R.id.text);
        myButton = findViewById(R.id.btn_secondActivity);
        openGallary = findViewById(R.id.btn_openGallery);
        gallery = findViewById(R.id.img_gallery);
        openGmail = findViewById(R.id.btn_openGmail);

        myButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("key", myText.toString());
            startActivity(intent);
        });
        openGallary.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });
        openGmail.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "что хотите написать");
            intent.putExtra(Intent.EXTRA_TEXT, "кому хотите написать");
            startActivity(Intent.createChooser(intent, ""));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            image = data.getData();
            gallery.setImageURI(image);
        }
    }
}
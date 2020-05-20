package org.tensorflow.lite.examples.classification.myappview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.tensorflow.lite.examples.classification.ClassifierActivity;
import org.tensorflow.lite.examples.classification.R;

public class MainActivity extends AppCompatActivity {

    Button button_image_to_text;
    Button button_about_us;
    Button button_text_to_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_image_to_text = (Button)findViewById(R.id.button_image_to_text);
        button_about_us = (Button)findViewById(R.id.button_about_us);
        button_text_to_image = (Button)findViewById(R.id.button_text_to_image);

        button_image_to_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClassifierActivity.class);
                startActivity(intent);
            }
        });

        button_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        button_text_to_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DictionaryActivity.class);
                startActivity(intent);
            }
        });
    }
}

package org.tensorflow.lite.examples.classification.myappview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.tensorflow.lite.examples.classification.R;

public class DictionaryActivity extends AppCompatActivity {

    Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        button_back = (Button)findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DictionaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

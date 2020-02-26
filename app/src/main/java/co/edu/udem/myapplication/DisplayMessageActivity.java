package co.edu.udem.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent i = getIntent();
        String message = i.getStringExtra("mensaje1");
        TextView tv = findViewById(R.id.textView);
        tv.setText(message);
    }
}

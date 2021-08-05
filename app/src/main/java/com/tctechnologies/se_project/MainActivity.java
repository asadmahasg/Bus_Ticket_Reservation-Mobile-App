package com.tctechnologies.se_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button SignUpClick = (Button) findViewById(R.id.SignUpButton);
        SignUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUpIntent = new Intent(MainActivity.this,SignUpActivity.class);
                MainActivity.this.startActivity(SignUpIntent);
            }
        });

        final Button LogInClick = (Button) findViewById(R.id.LogInButton);
        LogInClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LogInIntent = new Intent(MainActivity.this,LogInActivity.class);
                MainActivity.this.startActivity(LogInIntent);
            }
        });
    }
}

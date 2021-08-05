package com.tctechnologies.se_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button BookingClick = (Button) findViewById(R.id.BookingButton);
        BookingClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BookingIntent = new Intent(Home.this,Booking.class);
                Home.this.startActivity(BookingIntent);
            }
        });

        final Button ReservationClick = (Button) findViewById(R.id.ReservationButton);
        ReservationClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ReservationIntent = new Intent(Home.this,Reservation.class);
                Home.this.startActivity(ReservationIntent);
            }
        });

        final Button ScheduleClick = (Button) findViewById(R.id.ScheduleButton);
        ScheduleClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ScheduleIntent = new Intent(Home.this,Schedule.class);
                Home.this.startActivity(ScheduleIntent);
            }
        });

        final Button SignOutClick = (Button) findViewById(R.id.SignOutButton);
        SignOutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent SignOutIntent = new Intent(Home.this,MainActivity.class);
                Home.this.startActivity(SignOutIntent);
            }
        });
    }
}

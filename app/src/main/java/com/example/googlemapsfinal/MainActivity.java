package com.example.googlemapsfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mdatabase;
    private Button btnSave;
    private Button btnProceed;
    private EditText edtName;
    private  EditText edtLatitude;
    private EditText edtLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnProceed = findViewById(R.id.btnProceed);
        edtName = findViewById(R.id.editTextName);
        edtLatitude = findViewById(R.id.editTextLatitude);
        edtLongitude = findViewById(R.id.editTextLongitude);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        btnSave.setOnClickListener(this);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);

            }
        });
    }
    private void saveUsersInfo(){
        String name = edtName.getText().toString().trim();
        double latitude = Double.parseDouble(edtLatitude.getText().toString().trim());
        double longitude = Double.parseDouble(edtLongitude.getText().toString().trim());
        UserInformation userInformation = new UserInformation(name, latitude, longitude);
        mdatabase.child("Users").setValue(userInformation);
        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if(view == btnProceed){
            finish();
        }
        if(view == btnSave){
            saveUsersInfo();
            edtName.getText().clear();
            edtLatitude.getText().clear();
            edtLongitude.getText().clear();
        }

    }
}

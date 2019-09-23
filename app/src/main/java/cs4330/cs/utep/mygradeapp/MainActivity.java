package cs4330.cs.utep.mygradeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView userID;
    private TextView pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userID = findViewById(R.id.userId);
        pin = findViewById(R.id.pin);

    }

    public void onClick(View view){
        Intent intent = new Intent(this, GradeActivity.class);
        intent.putExtra("userId", userID.getText().toString());
        intent.putExtra("pin", pin.getText().toString());
        startActivity(intent);
    }
}

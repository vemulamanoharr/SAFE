package shsu.manohar.safe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static android.os.Process.*;

public class disclaimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);


    }
    public void agreeTc(View view){
        SharedPreferences prefs = getSharedPreferences("TCfile",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("tc", true);
        editor.apply();
        finish();
    }
    public void disagreeTc(View view){
        SharedPreferences prefs = getSharedPreferences("TCfile",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("tc", false);
        editor.apply();
        killProcess(myPid());


    }
}

package shsu.manohar.safe;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Context;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    DbHelper myDb;
    private static Button button_help;
    private static Button button_settings;

    private static Button button_search;
    public boolean tc_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DbHelper(this);
        try {

            myDb.Create();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        SharedPreferences prefs = getSharedPreferences("TCfile",Context.MODE_PRIVATE);
        tc_flag = prefs.getBoolean("tc",false);
        if (!tc_flag){
            Intent intent_1 = new Intent("shsu.manohar.safe.disclaimer");
            startActivity(intent_1);
        }
        onClickButtonListener();
    }

    public void onClickButtonListener(){
        button_help = (Button)findViewById(R.id.help);
        button_settings = (Button) findViewById(R.id.settings);
        button_search = (Button) findViewById(R.id.search);
        button_help.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("shsu.manohar.safe.help");
                        startActivity(intent);
                    }
                }
        );
        button_settings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("shsu.manohar.safe.setting");
                        startActivity(intent);
                    }
                }
        );
        button_search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("shsu.manohar.safe.search");
                        startActivity(intent);

                    }
                }
        );


    }



}

package shsu.manohar.safe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;



public class Search extends AppCompatActivity {
    private static Button type;
    private static Button name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        onClickButtonListener();
    }

    public void onClickButtonListener(){
        type = (Button)findViewById(R.id.type);
        name = (Button)findViewById(R.id.name);
        type.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("shsu.manohar.safe.type_search");
                        startActivity(intent);
                    }
                }
        );
        name.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("shsu.manohar.safe.name_search");
                        startActivity(intent);
                    }
                }
        );
    }

}

package shsu.manohar.safe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class name_search extends AppCompatActivity {
    private static Button name_enter;
    TextView name;
    DbHelper myDb;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DbHelper(this);
        setContentView(R.layout.activity_name_search);
        name = (TextView)findViewById(R.id.name_search);
        onClickButtonListener();
    }
    public void onClickButtonListener() {
        name_enter = (Button) findViewById(R.id.name_enter);
        name_enter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText type = (EditText) findViewById(R.id.name_search);
                        SharedPreferences sharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE);
                        String stored_level, stored_state;
                        stored_level = sharedPreferences.getString("Level", "Strict");
                        stored_state = sharedPreferences.getString("State", "TX");
                        Cursor res = myDb.getNameData(type.getText().toString(), stored_level, stored_state);
                        if (res.getCount() == 0) {
                            showMessage("Error", "No data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Item Name:" + res.getString(0) + "\n");
                            buffer.append("Sulphites:" + res.getString(1) + "\n");
                            buffer.append("Store:" + res.getString(2) + "\n\n");
                        }
                        showMessage("Results", buffer.toString());
                    }
                }

                    );
                }

    public void showMessage(String title, String Messgae) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messgae);
        builder.show();

    }
}

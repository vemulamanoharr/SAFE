package shsu.manohar.safe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class type_search extends AppCompatActivity {
    String[] types = {"Baking", "Dairy", "Flours", "Grains", "Legumes",
            "Nuts/Seeds", "OilSauces", "Snacks", "Broth", "Vegetables"};
    AutoCompleteTextView typelist;

    private static Button type_enter;
    DbHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DbHelper(this);
        setContentView(R.layout.activity_type_search);
        typelist = (AutoCompleteTextView) findViewById(R.id.typelist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, types);
        typelist.setThreshold(2);
        typelist.setAdapter(adapter);
        onClickButtonListener();

    }

    public void onClickButtonListener() {
        type_enter = (Button) findViewById(R.id.type_enter);
        type_enter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText type = (EditText) findViewById(R.id.typelist);
                        SharedPreferences sharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE);
                        String stored_level, stored_state;
                        stored_level = sharedPreferences.getString("Level", "Strict");
                        stored_state = sharedPreferences.getString("State", "TX");
                        Cursor res = myDb.getItemData(type.getText().toString(), stored_level, stored_state);
                        if (res.getCount() == 0) {
                            showMessage("Error", "No data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Item Name:" + res.getString(0) + "\n");
                            buffer.append("Sulphites:" + res.getString(1) + "\n");
                            buffer.append("Store Name:" + res.getString(2)+"\n\n");
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

package shsu.manohar.safe;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class    setting extends AppCompatActivity {
    String[] states = {"AL","AK","AS","AZ","AR","CA","CO","CT","DE","DC",
            "FM","FL","GA","GU","HI","ID","IL","IN","IA","KS","KY","LA","ME",
            "MH","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM",
            "NY","NC","ND","MP","OH","OK","OR","PW","PA","PR","RI","SC","SD",
            "TN","TX","UT","VT","VI","VA","WA","WV","WI","WY"};
    AutoCompleteTextView stateView;
    RadioGroup radioLevelGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        stateView = (AutoCompleteTextView) findViewById(R.id.state);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,states);
        stateView.setThreshold(1);
        stateView.setAdapter(adapter);

    }
    public void save(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        radioLevelGroup = (RadioGroup) findViewById(R.id.Levelradio);
        int selectedLevel = radioLevelGroup.getCheckedRadioButtonId();
        if(selectedLevel == R.id.trace)
            editor.putString("Level","Trace");
        else
            editor.putString("Level","Strict");

        EditText state =(EditText)findViewById(R.id.state);
        editor.putString("State",state.getText().toString());
        editor.apply();
    }

}

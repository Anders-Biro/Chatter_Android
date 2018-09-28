package ca.nait.abiro.chatter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class SimpleSpinnerActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_spinner);

        Spinner gradeSpinner = (Spinner)findViewById(R.id.spinner_grades);

        //SimpleSpinnerListener listener = new SimpleSpinnerListener();

        gradeSpinner.setOnItemSelectedListener(new SimpleSpinnerListener());
    }
    class SimpleSpinnerListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View item, int position, long id)
        {
            TextView textViewGrade = (TextView)SimpleSpinnerActivity.this.findViewById(R.id.textview_grade);
            String grade = parent.getResources().getStringArray(R.array.grade_names)[position];
            textViewGrade.setText("You earned a " + grade);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView)
        {
            // no code required
        }
    }
}


package abiro.nait.ca.picker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity
{
    DateFormat formatter = DateFormat.getDateTimeInstance();
    TextView tvDateTime;
    Calendar calendar = Calendar.getInstance();

    Chronometer chrono;
    Button buttonStart, buttonStop, buttonReset;
    TextView chronoText;

    long elapsedTime = 0;
    String currentTime = "";
    long startTime = SystemClock.elapsedRealtime();
    boolean resume = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDateTime = (TextView) findViewById(R.id.dateAndTime);

        chrono = (Chronometer)findViewById(R.id.chrono);
        chronoText = (TextView)findViewById(R.id.chronoText);
        buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStop = (Button)findViewById(R.id.buttonStop);
        buttonReset = (Button)findViewById(R.id.buttonReset);

        buttonStop.setEnabled(false);
        buttonReset.setEnabled(false);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.buttonStart:
            {
                buttonStart.setEnabled(false);
                buttonStop.setEnabled(true);
                buttonReset.setEnabled(true);
                if(!resume)
                {
                    chrono.setBase(SystemClock.elapsedRealtime());
                }
                chrono.start();
                break;
            }
            case R.id.buttonStop:
            {
                buttonStart.setEnabled(true);
                buttonStop.setEnabled(false);
                buttonReset.setEnabled(true);
                chrono.stop();
                chronoText.setText(currentTime);
                resume = true;

                break;
            }
            case R.id.buttonReset:
            {
                chrono.stop();
                chrono.setText("00:00");
                resume = false;
                buttonStart.setEnabled(true);
                buttonStop.setEnabled(false);
                break;
            }
        }
    }

    public void chooseDate(View v)
    {
        new DatePickerDialog(this, d, calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void chooseTime(View v)
    {
        new TimePickerDialog(this, t, calendar.get(Calendar.HOUR_OF_DAY),
                                        calendar.get(Calendar.MINUTE),
                                        true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day)
        {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute)
        {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    };

    private void updateLabel()
    {
        tvDateTime.setText(formatter.format(calendar.getTime()));
    }
}































package sg.edu.rp.c346.id20019648.song;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    Button btn;
    Spinner spnYear;
    TextView tvInfo, tvTitle, tvStar, tvYear;
    RadioGroup rgStars;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        btn = findViewById(R.id.btn);
        spnYear = findViewById(R.id.spnYear);
        tvInfo = findViewById(R.id.tvInfo);
        tvStar = findViewById(R.id.tvStar);
        tvYear = findViewById(R.id.tvYear);
        tvTitle = findViewById(R.id.tvTitle);

        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spnYear.getSelectedItem().toString();

                if (!selected.equals("Date")) {
                    String Year = selected;
                    spnYear.setSelection(position);
                    tvYear.setText(Year);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private int getStars(int stars) {
        stars = 1;
        if (rgStars.getCheckedRadioButtonId() == R.id.rb1) {
            stars = 1;
        } else if (rgStars.getCheckedRadioButtonId() == R.id.rb5);
        stars = 5;
        return stars;
    }
}
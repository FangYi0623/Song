package sg.edu.rp.c346.id20019648.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class ThirdActivity extends AppCompatActivity {
    Button btnDelete, btnUpdate,btnCancel;
    EditText etTitle, etSinger, etYear, etId;
    TextView tvTitle, tvSinger, tvYear, tvStar;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    RadioGroup rg;
    Spinner spnYear;
    ListView lv;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updated_songs);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        etId = findViewById(R.id.etId);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        tvSinger = findViewById(R.id.tvSinger);
        tvTitle = findViewById(R.id.tvTitle);
        tvYear = findViewById(R.id.tvYear);
        tvStar = findViewById(R.id.tvStar);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        rg = findViewById(R.id.rg);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(ThirdActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        Song song;

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("data");
        etId.setText(String.valueOf(song.getId()));
        etTitle.setText(getTitle());
        etYear.setText(song.getYear());
        etSinger.setText(song.getSingers());
        int stars = song.getStars();

        if (stars == 1) {
            rb1.setChecked(true);
        } else if (stars == 2 ) {
            rb2.setChecked(true);
        } else if (stars == 3 ) {
            rb3.setChecked(true);
        } else if (stars == 4 ) {
            rb4.setChecked(true);
        } else {
            rb5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.setSingers(etSinger.getText().toString());
                song.setYear(parseInt(etYear.getText().toString()));
                song.setTitle(etTitle.getText().toString());
                song.setStars(Song.getStars());

                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.updateSong(song);
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(song.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ThirdActivity.this);
        al.clear();
        aa.notifyDataSetChanged();
        dbh.close();
    }
}
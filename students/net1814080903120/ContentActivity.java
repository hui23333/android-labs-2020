package edu.hzuapps.androidlabs.net1814080903120;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_person);

        final ContentActivity thisActivity = this;


        String URL=ContentProviderOfBooks.URL;
        Cursor cursor=getContentResolver().query(ContentProviderOfBooks.CONTENT_URI,null,null,null,null);


        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.library_person, cursor, new String[] { "time", "status" },
                new int[] { R.id.time_id, R.id.status_id }, 0);

        ListView listView = (ListView) findViewById(R.id.show);
        listView.setAdapter(adapter);
    }









}


package edu.hzuapps.androidlabs.net1814080903120;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ContentProviderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);

        final ContentProviderActivity thisActivity = this;

        // 保存图书信息
        findViewById(R.id.save_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisActivity.saveBook();
            }
        });

//        //展示个人中心
        findViewById(R.id.person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ContentProviderActivity.this, ContentActivity.class);
                startActivity(intent);
            }
        });



    }
    // 保存图书信息
    private void saveBook() {
        String bookname = ((EditText) findViewById(R.id.bookname)).getText().toString();
        String writer = ((EditText) findViewById(R.id.writer)).getText().toString();
        String home=((EditText)findViewById(R.id.home)).getText().toString();
        String status="请求已发送！";
        String time="";
        // 格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat();
        // a为am/pm的标记
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();// 获取当前时间
        time=sdf.format(date);

        // 插入新记录
        ContentValues book = new ContentValues();
        //book.put("name", name); // Map <- Key:Value
        book.put(ContentProviderOfBooks.BOOKNAME, bookname);
        book.put(ContentProviderOfBooks.WRITER, writer);
        book.put(ContentProviderOfBooks.HOME,home);
        book.put(ContentProviderOfBooks.STATUS,status);
        book.put(ContentProviderOfBooks.TIME,time);

        System.out.println(time);
        Uri uri = getContentResolver() // 执行插入操作
                .insert(ContentProviderOfBooks.CONTENT_URI, book);

        Toast.makeText(getBaseContext(),
                "借书请求已发送 \n" + uri.toString(), Toast.LENGTH_LONG).show();

        this.showBookInfo("","",""); // 清除内容
    }

    private void showBookInfo(String bookname, String writer,String home) {
        ((EditText) findViewById(R.id.bookname)).setText(bookname);
        ((EditText) findViewById(R.id.writer)).setText(writer);
        ((EditText)findViewById(R.id.home)).setText(home);
    }




}
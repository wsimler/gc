package com.zxc.roomkotlin.pdf;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.zxc.roomkotlin.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PdfPage extends AppCompatActivity {

    Map<String, File> pdfList = new HashMap<>();
    ListView listView;
    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_page);
        loader = (ProgressBar) findViewById(R.id.loader);
        listView = (ListView) findViewById(R.id.lv_pdf);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }, 1000);
    }

    private void init() {
        loader.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        findPdfs(Environment.getExternalStorageDirectory());
        setPdfs();
    }

    public void findPdfs(File dir) {
        File files[] = dir.listFiles();
        if (files != null) for (File f : files)
            if (f.isDirectory()) findPdfs(f);
            else {
                if (f.getName().endsWith(".pdf"))
                    pdfList.put(f.getName(), f);
            }
    }

    public void setPdfs() {
        loader.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);

        String[] l = pdfList.keySet().toArray(new String[]{});
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, l);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = (String) adapterView.getItemAtPosition(i);
                startActivity(new Intent(PdfPage.this, OpenPdf.class).putExtra("PATH", pdfList.get(s).getAbsolutePath()));
            }
        });
    }
}

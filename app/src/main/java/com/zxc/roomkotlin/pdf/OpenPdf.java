package com.zxc.roomkotlin.pdf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.zxc.roomkotlin.R;

import java.io.File;

public class OpenPdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pdf);

        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        String path = getIntent().getStringExtra("PATH");
        File file = new File(path);
        if (file.canRead())
            pdfView
                    .fromFile(file)
                    .defaultPage(1)
                    .swipeVertical(true)
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {
                            Toast.makeText(OpenPdf.this, String.valueOf(nbPages), Toast.LENGTH_LONG).show();
                        }
                    })
                    .load();

    }
}

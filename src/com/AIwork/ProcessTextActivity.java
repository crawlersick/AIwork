package com.AIwork;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

/**
 * Created by sick on 8/6/13.
 */

public class ProcessTextActivity extends Activity{
    private TextView textView;
    private File txtfile;
    String filecode="";
    int filetype=0;
    String message;
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        FileFormatAnalyst ffa;

        try {
            ffa=new FileFormatAnalyst(message);
            filecode=ffa.getCode();
            filetype=ffa.getFiletype();
        } catch (Exception e) {
            e.printStackTrace();
        }


        txtfile=new File(message);


        textView = new TextView(this);
       // ReadView textView = (ReadView) findViewById(R.id.read_view);
       // textView.setMaxLines(20);
        //textView.setTextSize(10);
        setContentView(textView);



       // textView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
       // int width = textView.getMeasuredWidth();
       // int height = textView.getMeasuredHeight();

       // int lineCount = textView.getLineCount();
      //  Toast.makeText(this, "test num：" + width + " " + height, Toast.LENGTH_SHORT).show();




    }

    @Override
    public void onStart(){
        super.onStart();
      //  int lineCount = textView.getLineCount();
       // Toast.makeText(this, "test num：" + lineCount, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        StringBuffer sBuffer = new StringBuffer();
        try {
            FileInputStream fInputStream = new FileInputStream(txtfile);
            InputStreamReader inputStreamReader;
            if (filetype==1)
                inputStreamReader = new InputStreamReader(fInputStream, filecode);
            else
                inputStreamReader = new InputStreamReader(fInputStream, "GBK");

            BufferedReader in = new BufferedReader(inputStreamReader);
            String strTmp=null;

            while (( strTmp = in.readLine()) != null) {
                sBuffer.append(strTmp + "\n");
                //  break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textView.setText(message + "\n" + "code is : " + filecode + "\n" + "\n" + sBuffer.toString());

        if (this.hasWindowFocus()){

        super.onWindowFocusChanged(hasFocus);
          int lineCount = textView.getHeight()/textView.getLineHeight();
          //int charcount=textView
        String fullString="--------------------------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------------------";

        int totalCharstoFit= textView.getPaint().breakText(fullString,  0, fullString.length(),
                true, textView.getWidth(), null);

         Toast.makeText(this, "test num：" + lineCount+" || " + totalCharstoFit, Toast.LENGTH_SHORT).show();
        }
    }

    public void FillInOnePage(){

    }


}

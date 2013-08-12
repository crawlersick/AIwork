package com.AIwork;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
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
    int MaxPage=65535;
    int PageChars[]=new int[MaxPage];
    int CurrentPage=-1;
    String PageC[]=new String[MaxPage];
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

        View.OnTouchListener onc=new View.OnTouchListener(){

            public boolean onTouch(View v, MotionEvent event) {

                int x =(int) event.getX();
                int y=(int) event.getY();
                Toast.makeText(v.getContext(),String.valueOf(x)+"     "+String.valueOf(y), Toast.LENGTH_SHORT).show();
               // mCustomDrawableView.changetext("HAHAHAHA");
               // mLinearLayout.invalidate();
                if (CurrentPage<MaxPage)
                {CurrentPage++;

                textView.setText(PageC[CurrentPage]);
                textView.invalidate();}
                return false;

            }
        };
        textView.setOnTouchListener(onc);



    }

    @Override
    public void onStart(){
        super.onStart();
      //  int lineCount = textView.getLineCount();
       // Toast.makeText(this, "test num：" + lineCount, Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (this.hasWindowFocus()){
           if(CurrentPage==-1)
            {initPageSet();}
            /*do what to do load page*/

/*

            int lineCount = textView.getHeight()/textView.getLineHeight();
            //int charcount=textView
            String fullString="告歌广告费吞吞吐吐天" ;
            textView.setText(fullString);
            int totalCharstoFit= textView.getPaint().breakText(fullString,  0, fullString.length(),
                    true, textView.getWidth(), null);

            Toast.makeText(this, "test num：" + lineCount+" || " + totalCharstoFit, Toast.LENGTH_SHORT).show();
*/
            CurrentPage++;
            textView.setText(PageC[CurrentPage]);
        //    Toast.makeText(this, "test num：" + MaxPage+"---------"+PageChars[0], Toast.LENGTH_SHORT).show();
        }
    }

    public void initPageSet(){
        int lineCount = textView.getHeight()/textView.getLineHeight()-2;
        StringBuffer sBuffer = new StringBuffer();
        int totalCharstoFit;
        try {
            FileInputStream fInputStream = new FileInputStream(txtfile);
            InputStreamReader inputStreamReader;
            if (filetype==1)
                inputStreamReader = new InputStreamReader(fInputStream, filecode);
            else
                inputStreamReader = new InputStreamReader(fInputStream, "GBK");

            BufferedReader in = new BufferedReader(inputStreamReader);
            String strTmp=null;
            int TempPageChars=0;
            int TempPageLines=0;
            int TempPagenum=0;
            PageC[0]="";
            boolean finishedFlag=false;
            String LineTail="";
            while (( strTmp = in.readLine()) != null) {
                strTmp=LineTail+strTmp;
                for(;;){
                    finishedFlag=false;
                totalCharstoFit= textView.getPaint().breakText(strTmp,  0, strTmp.length(),
                        true, textView.getWidth(), null);
                if (totalCharstoFit < strTmp.length())
                {
                    TempPageLines++;
                    TempPageChars+=totalCharstoFit;

                    PageC[TempPagenum]=PageC[TempPagenum]+strTmp.substring(0,totalCharstoFit);

                    strTmp=strTmp.substring(totalCharstoFit,strTmp.length());

                }
                else
                {
                    TempPageLines++;
                    TempPageChars+=strTmp.length();

                    PageC[TempPagenum]=PageC[TempPagenum]+strTmp+"\n";


                    strTmp="";
                    finishedFlag=true;

                }
                if (TempPageLines==lineCount)
                {
                    PageChars[TempPagenum]=TempPageChars;
                    TempPagenum++;
                    TempPageChars=0;
                    TempPageLines=0;
                    PageC[TempPagenum]="";
                }
                if (finishedFlag)
                    {
                        break;
                    }

                }
                LineTail=strTmp;

                //sBuffer.append(strTmp + "\n");
                //  break;
            }
            MaxPage=TempPagenum;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}

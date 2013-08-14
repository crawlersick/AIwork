package com.AIwork;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.*;
import java.nio.CharBuffer;

/**
 * Created by sick on 8/6/13.
 */

public class ProcessTextActivity extends Activity{
    ReadView textView;
   // private TextView textView;
    private File txtfile;
    BufferedReader reader;
    String filecode="";
    int filetype=0;
    String message;
    String finalcode;
    CharBuffer buffer = CharBuffer.allocate(8000);
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


        if (filetype==1)
            finalcode = filecode;
        else
            finalcode =  "GBK";


        txtfile=new File(message);
        //textView = new TextView(this);
        //textView.getPaint().setSubpixelText(true);
       // setContentView(textView);

        setContentView(R.layout.txt_reader);
        textView = (ReadView) findViewById(R.id.txtRead_view);

/*
        try {
            txtfile=new File(message);
            FileInputStream in = new FileInputStream(txtfile);

            reader = new BufferedReader(new InputStreamReader(in, finalcode));
            reader.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        View.OnTouchListener onc=new View.OnTouchListener(){

            public boolean onTouch(View v, MotionEvent event) {

                int x =(int) event.getX();
                int y=(int) event.getY();
                Toast.makeText(v.getContext(),String.valueOf(x)+"     "+String.valueOf(y), Toast.LENGTH_SHORT).show();
                if (CurrentPage<MaxPage)
                {CurrentPage++;

                textView.setText(PageC[CurrentPage]);
                textView.invalidate();}
                return false;

            }
        };
        textView.setOnTouchListener(onc);

       // initPageSet();
     //   CurrentPage=0;
      //  textView.setText(PageC[CurrentPage]);
      //  textView.invalidate();

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
            CurrentPage++;
            textView.setText(PageC[CurrentPage]);
        }
    }

    public void initPageSet(){

        int lineCount = textView.getHeight()/textView.getLineHeight();
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
            int TolPageChars=0;
            int TempPageLines=0;
            int TempPagenum=0;
            PageC[0]="";
            boolean finishedFlag=false;
          //  String LineTail="";
            char tempmem[]=new char[4096];
            char tempreadin[]=new char[2048];
            int readn;
            int prereadn=0;
            int startpos=0;
            int nextstartpos=0;
            int endpos=4096;
            while (( readn = in.read(tempmem,startpos,endpos)) != -1) {
              //  strTmp=LineTail+strTmp;
                textView.setText(tempmem,0,endpos);
                TempPageChars=textView.getCharNum();
                PageC[TempPagenum]=new String(tempmem,0,TempPageChars);
                nextstartpos=endpos-TempPageChars;
                for(int i =0;i<nextstartpos;i++)
                {
                    tempmem[i]=tempmem[i+endpos-TempPageChars];

                }
                startpos=nextstartpos;
                TempPagenum++;
                prereadn=readn;
                if(TempPagenum>=65534)
                    break;
            }

            startpos=0;
            while(startpos<prereadn){
                textView.setText(tempmem,startpos,prereadn);
                TempPageChars=textView.getCharNum();

                PageC[TempPagenum]=new String(tempmem,startpos,TempPageChars);
                startpos+=TempPageChars;

                TempPagenum++;
                if(TempPagenum>=65534)
                    break;
            }



            MaxPage=TempPagenum;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}

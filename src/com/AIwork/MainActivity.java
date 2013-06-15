package com.AIwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;
import com.AIwork.Views.ChatViewImpl;

public class MainActivity extends Activity
{
     // LinearLayout mLinearLayout;
      TableLayout mLinearLayout;
       CustomDrawableView mCustomDrawableView;
       
       Handler myHandler = new Handler() {  
           @Override
          public void handleMessage(Message msg) {   
               super.handleMessage(msg);   
               String MsgString = (String)msg.obj;
               adapter.add(new OneComment(false,MsgString));
                lv.setSelection(lv.getCount() - 1);
          }   
     };  
      
    /** Called when the activity is first created. */
                      private DiscussArrayAdapter adapter;
	private EditText editText1;
        	private ChatViewImpl lv;
                      private String tempstr;
    
                      
    public ChatViewImpl getListView(){
        return lv;
    }                  
                      
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_discuss);
    lv = (ChatViewImpl) findViewById(R.id.listView1);
    adapter = new DiscussArrayAdapter(getApplicationContext(), R.layout.listitem_discuss);
    lv.setAdapter(adapter);
    editText1 = (EditText) findViewById(R.id.editText1);
    editText1.setOnFocusChangeListener(
                        new OnFocusChangeListener() {
                                                                            public void onFocusChange(View v, boolean hasFocus) {               
                                                                                                      getListView().setSelection(getListView().getCount());
                                                                                                                                                                                             }
                                                                                      }
                                                                            );

    editText1.setOnClickListener(new View.OnClickListener() {

        public void onClick(View view) {
            getListView().setSelection(getListView().getCount());
        }
    }
            );
    		editText1.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
                                                                                                           tempstr =  editText1.getText().toString();
                                                                                                           if (tempstr.trim().equals(""))
                                                                                                           {
                                                                                                               editText1.setText("");
                                                                                                               return false;
                                                                                                           }
					adapter.add(new OneComment(true,tempstr));
					editText1.setText("");
                                        lv.setSelection(lv.getCount() - 1);
                                        // set the msg to new thread for further handle
                                        Anastr ast=new Anastr(myHandler);
                                        ast.start();
                                        
					return true;
				}
				return false;
			}
		});
    }
       
       
           // @Override
    public void onCreate_firstver(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
 
 mLinearLayout = new TableLayout(this);
  ImageView i = new ImageView(this);
  i.setImageResource(R.drawable.myim);
  i.setOnTouchListener(onc);
  i.setAdjustViewBounds(true); 
  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300);
i.setLayoutParams(layoutParams);
  mCustomDrawableView = new CustomDrawableView(this,"HI, MY M");
  mLinearLayout.addView(i);
  mLinearLayout.addView(mCustomDrawableView);

  setContentView(mLinearLayout);
    }
View.OnTouchListener onc=new OnTouchListener(){

        public boolean onTouch(View v, MotionEvent event) {
        
            int x =(int) event.getX();
            int y=(int) event.getY();
Toast.makeText(v.getContext(),String.valueOf(x)+"     "+String.valueOf(y), Toast.LENGTH_SHORT).show();
mCustomDrawableView.changetext("HAHAHAHA");
mLinearLayout.invalidate();
            return false;
           
        }
    };

}
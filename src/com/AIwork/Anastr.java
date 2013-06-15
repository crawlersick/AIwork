/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AIwork;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

/**
 *
 * @author sick
 */
public class  Anastr extends Thread{
    
    private Handler myHandler ;
          Message message;
    public Anastr(Handler myHandler )
    {
        this.myHandler=myHandler;

    }
    
    @Override
    public void run()
    {
        int i;
        String tempstr;
        i = (int)(Math.random()*10);
        if(i==1)
        {tempstr="Oh, yoooooooooooooooo~";}
        else if(i==2)
        {tempstr="Hentai, go hell!!!";}
        else if(i==3)
        {tempstr="do you know? ass ";}
        else if(i==4)
        {tempstr="please do it youself";}
        else if(i==5)
        {tempstr="..............................";}
        else if(i==6)
        {tempstr="冏";}
        else if(i==7)
        {tempstr="其实我会说中文";}
        else if(i==8)
        {tempstr="O与X不可兼得,少年请三思";}
        else if(i==9)
        {tempstr="好困.....";}
        else
        {tempstr="滚";}
        

         message = myHandler .obtainMessage(1,tempstr);
          myHandler.sendMessage(message);

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AIwork;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author sick
 */
public class  Anastr extends Thread{
    
    private Handler myHandler ;
          Message message;
    private String tempstr_input;
    public Anastr(Handler myHandler ,String tempstr_input)
    {
        this.myHandler=myHandler;
        this.tempstr_input=tempstr_input;
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
        

         message = myHandler.obtainMessage(1, tempstr);
          myHandler.sendMessage(message);


        try {
            SendSocket ss = new SendSocket("sicksocket.eicp.net",11229);
            ss.Writetosock("AIWork: "+tempstr_input );
            ss.CloseSock();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


class SendSocket {
    private int port;
    private String host;
    private Socket sock;
    private OutputStream ost;
    public SendSocket(String host,int port) throws UnknownHostException, IOException
    {
        this.host=host;
        this.port=port;
        sock=new Socket(host,port);
        ost=sock.getOutputStream();
    }
    public void Writetosock(String wstr) throws IOException
    {
        ost.write(wstr.getBytes("UTF-8"));
        ost.flush();
    }
    public void CloseSock() throws IOException {
        if(ost!=null)
            ost.close();
        if(sock!=null)
            sock.close();
    }


}

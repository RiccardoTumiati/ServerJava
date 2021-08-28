/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverjava;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riccardo
 */
public class WebConnection extends Thread {
    private Socket socket;
    
    private InputStream inFlow;
    private OutputStream outFlow;
    private byte[] inBuffer;
    private byte[] outBuffer;
    
    public WebConnection(Socket s){
        socket=s;
        
        inBuffer=new byte[1024];
        outBuffer=new byte[1024];
    }
    
    @Override
    public void run(){
        try {
            inFlow=socket.getInputStream();
            outFlow=socket.getOutputStream();
            
            inFlow.read(inBuffer);
            String content=getContent(inBuffer);
            System.out.println("Pacchetto ricevuto da: "+socket.getInetAddress()+" Contenuto del messaggio: "+content);
            System.out.println("Inserire risposta da inoltrare: ");
            Scanner scan=new Scanner(System.in);
            content=scan.nextLine();
            outBuffer=content.getBytes();
            outFlow.write(outBuffer);
            System.out.println("Pacchetto inviato correttamente al mittente, chiusura connessione ...");
            socket.close();
        } catch (IOException ex) {
            System.out.println("WebConnection error: impossibile get stream from the socket");
        }
    }
    
    private String getContent(byte[] b){
        String original;
        String output="";
        
        try {
            original=new String(b,"UTF-8");
        } catch (UnsupportedEncodingException ex) {
            System.out.println("WebConnection error: unsupported encoding exception");
            return "";
        }
        System.out.println("Stringa di uscita: "+original);
        for(int i=0; i<original.length(); i++){
           char c=original.charAt(i);
           if(c==0)
               break;
           output=output+c;
        }
        return output;
    }
}

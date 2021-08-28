/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverjava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riccardo
 */
public class ServerJava extends Thread {
    private ServerSocket server;
    
    public ServerJava(int port){
        try {
            server=new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("ServerJava error: impossible open server");
        }
    }
    
    
    @Override
    public void run(){
        System.out.println("Server avviato in attesa di connessioni");
        while(true){
            try {
                Socket s=server.accept();
                System.out.println("Nuova connessione creata con: "+s.getInetAddress());
                new WebConnection(s).start();
            } catch (IOException ex) {
                System.out.println("ServerJava error: server accept method fail");
                return;
            }
        }
    }
}

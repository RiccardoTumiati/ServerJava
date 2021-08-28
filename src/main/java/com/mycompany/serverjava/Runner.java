/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverjava;

/**
 *
 * @author riccardo
 */
public class Runner {
    public static void main(String[] args){
        ServerJava server=new ServerJava(7777);
        server.start();
    }
}

package com.jpmorgan;

import com.jpmorgan.models.Sail;
import com.jpmorgan.services.ProcessingMessages;
import com.jpmorgan.services.Utils;
import sun.misc.Lock;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        System.out.println("STARTING PROCESS");
        // Populating the input messages
        List<Sail> inputMessages = Utils.generateInputMessages(50, true);
        // Launch the process
        ProcessingMessages.processMessages(inputMessages);

        // put the main thread in wait
        Object obj = new Lock();
        synchronized (obj) {
            while(true) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

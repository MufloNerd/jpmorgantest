package com.jpmorgan.services;

import com.jpmorgan.models.Operation;
import com.jpmorgan.models.Sail;
import com.jpmorgan.models.SailList;
import com.jpmorgan.models.SailOperation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lmontunato on 12/06/2018
 */
public class Utils {
    public static List<Sail> generateInputMessages(int numberOfMessages, boolean random) {
        List<Sail> inputMessages = new ArrayList<>();
        // Generating messages with random parameters
        int i = 0;
        while (i < numberOfMessages) {
            if (i % 3 == 0) {
                inputMessages.add(new Sail("apple", BigDecimal.valueOf(new Random().nextInt(10)+1)));
                inputMessages.add(new Sail("ananas", BigDecimal.valueOf(new Random().nextInt(10)+1)));
            } else if (i % 3 == 1) {
                inputMessages.add(new SailList("banana", BigDecimal.valueOf(new Random().nextInt(10)+1), new Random().nextInt(20)));
            } else {
                inputMessages.add(new SailOperation("apple", BigDecimal.valueOf(new Random().nextInt(10)+1), (random?getRandomOp():Operation.ADD)));
                inputMessages.add(new SailOperation("ananas", BigDecimal.valueOf(new Random().nextInt(10)+1), (random?getRandomOp():Operation.ADD)));
            }
            i++;
        }
        return inputMessages;
    }

    private static Operation getRandomOp(){
        if((new Random().nextInt(2) + 1) % 2 ==  0){
            return Operation.ADD;
        } else if((new Random().nextInt(2) + 1) % 2 ==  1){
            return Operation.SUB;
        } else {
            return Operation.MUL;
        }
    }
}

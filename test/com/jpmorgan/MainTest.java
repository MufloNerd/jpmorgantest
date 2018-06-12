package com.jpmorgan;

import com.jpmorgan.models.Sail;
import com.jpmorgan.services.ProcessingMessages;
import com.jpmorgan.services.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by lmontunato on 12/06/2018
 */

public class MainTest {
    @Test
    public void happyPath() {
        List<Sail> inputMessages = Utils.generateInputMessages(50, true);
        List<Sail> outputMessages = ProcessingMessages.processMessages(inputMessages);
        System.out.println("outputMessages.size() = " + outputMessages.size());
        assert (outputMessages!= null);
    }

    @Test
    public void shortPath() {
        List<Sail> inputMessages = Utils.generateInputMessages(30, true);
        List<Sail> outputMessages = ProcessingMessages.processMessages(inputMessages);
        System.out.println("outputMessages.size() = " + outputMessages.size());
        assert (outputMessages!= null);
    }

    @Test
    public void largePath() {
        List<Sail> inputMessages = Utils.generateInputMessages(200, true);
        List<Sail> outputMessages = ProcessingMessages.processMessages(inputMessages);
        System.out.println("outputMessages.size() = " + outputMessages.size());
        assert (outputMessages!= null);
    }

    @Test
    public void onlyAPath() {
        List<Sail> inputMessages = Utils.generateInputMessages(200, false);
        List<Sail> outputMessages = ProcessingMessages.processMessages(inputMessages);
        System.out.println("outputMessages.size() = " + outputMessages.size());
        assert (outputMessages!= null);
    }
}

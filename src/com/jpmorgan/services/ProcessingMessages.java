package com.jpmorgan.services;

import com.jpmorgan.models.Report;
import com.jpmorgan.models.Sail;
import com.jpmorgan.models.SailList;
import com.jpmorgan.models.SailOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lmontunato on 12/06/2018
 */
public class ProcessingMessages {

    public static List<Sail> processMessages(List<Sail> inputMessages) {
        List<Sail> outputMessages = new ArrayList<>();
        Map<String, Report> adjustmentReport = new HashMap<>();

        System.out.println("PROESSING MESSAGES");
        // polling the input list of messages - it could be a buffer of messages
        for (int j = 1; j <= inputMessages.size() && j <= 50; j++) {
            final Sail inputSail = inputMessages.get(j - 1);
            // check for the type of the message and do different actions
            if (inputSail instanceof SailOperation) {
                SailOperation op = (SailOperation) inputSail;
                // Do an operation on all the sails grouped by product type
                outputMessages.stream().filter(x -> x.getProductType().equals(op.getProductType())).forEach(y -> {
                    // Storing the report info for the adjustments
                    Report report = new Report();
                    report.setProductType(op.getProductType());
                    switch (op.getOperation()) {
                        case ADD:
                            // adding the price to all the occurrences
                            y.setPrice(y.getPrice().add(op.getPrice()));
                            // repo fill strategy
                            report.setTotalPrice(report.getTotalPrice().add(op.getPrice()));
                            if (adjustmentReport.size() == 0 || adjustmentReport.get(op.getProductType()) == null) {
                                adjustmentReport.put(op.getProductType(), report);
                            } else {
                                Report oldReport = adjustmentReport.get(op.getProductType());
                                oldReport.setTotalPrice(oldReport.getTotalPrice().add(op.getPrice()));
                            }
                            break;
                        case SUB:
                            // subtracting the price to all the occurrences
                            y.setPrice(y.getPrice().subtract(op.getPrice()));
                            // repo fill strategy
                            report.setTotalPrice(report.getTotalPrice().subtract(op.getPrice()));
                            if (adjustmentReport.size() == 0 || adjustmentReport.get(op.getProductType()) == null) {
                                adjustmentReport.put(op.getProductType(), report);
                            } else {
                                Report oldReport = adjustmentReport.get(op.getProductType());
                                oldReport.setTotalPrice(oldReport.getTotalPrice().subtract(op.getPrice()));
                            }
                            break;
                        case MUL:
                            // multiplying the price to all the occurrences
                            y.setPrice(y.getPrice().multiply(op.getPrice()));
                            // report fill strategy
                            report.setTotalPrice(report.getTotalPrice().multiply(op.getPrice()));
                            if (adjustmentReport.size() == 0 || adjustmentReport.get(op.getProductType()) == null) {
                                adjustmentReport.put(op.getProductType(), report);
                            } else {
                                Report oldReport = adjustmentReport.get(op.getProductType());
                                oldReport.setTotalPrice(oldReport.getTotalPrice().multiply(op.getPrice()));
                            }
                            break;
                    }
                });
            } else if (inputSail instanceof SailList) {
                for (int z = 0; z < ((SailList) inputSail).getNumberOfProducts(); z++) {
                    // adding a number of sails indicated into the message
                    outputMessages.add(new Sail(inputSail.getProductType(), inputSail.getPrice()));
                }
            } else {
                // simple message adding
                outputMessages.add(new Sail(inputSail.getProductType(), inputSail.getPrice()));
            }
            if (j % 10 == 0) {
                // check for the 10th message to print a simple report
                System.out.println("STEP REPO AFTER " + j + " MESSAGES");
                simpleReport(outputMessages);
            }
            if (j % 50 == 0) {
                // check for 50th message to print the adjustment repo
                System.out.println("ADJUSTMENT REPO: ");
                adjustmentReport.values().stream().forEach(x -> System.out.println(x.getProductType() + " : " + x.getTotalPrice() + "p"));
                System.out.println("PAUSING THE APPLICATION AFTER " + j + " MESSAGES");
            }
        }
        return outputMessages;
    }

    private static void simpleReport(List<Sail> sails) {
        Map<String, Report> simpleReports = new HashMap<>();
        // report fill strategy for 10th message
        sails.stream().forEach(x -> {
            if (simpleReports.size() == 0 || simpleReports.get(x.getProductType()) == null) {
                Report newReport = new Report();
                newReport.setNumberOfSails(1);
                newReport.setProductType(x.getProductType());
                newReport.setTotalPrice(x.getPrice());
                simpleReports.put(x.getProductType(), newReport);
            } else {
                Report oldReport = simpleReports.get(x.getProductType());
                oldReport.setTotalPrice(oldReport.getTotalPrice().add(x.getPrice()));
                oldReport.setNumberOfSails(oldReport.getNumberOfSails() + 1);
            }
        });
        // print the repo for each product type
        simpleReports.values().stream().forEach(
                x -> System.out.println(x.getProductType() + " : " + x.getNumberOfSails() + " : " + x.getTotalPrice() + "p"));
    }
}


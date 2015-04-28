package utils;

import broker.Message_Broker;

/*
    Coding Standards -> http://www.oracle.com/technetwork/java/codeconvtoc-136057.html
 */

/**
 * @author Conor Hayes
 * Constants
 */
public class Constants {
    public static final String APPLICATION_PATH = "/bidder";
    public static final String PATH = "/services";
    public static final String PUB_ADR = "tcp://*:2900";
    public static final String TOPIC = "BidPlaced";
    public static Message_Broker BROKER = Message_Broker.ZeroMq;
}

package utils;

import broker.MESSAGE_BROKER;

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
    public static MESSAGE_BROKER BROKER = MESSAGE_BROKER.ZeroMq;
}

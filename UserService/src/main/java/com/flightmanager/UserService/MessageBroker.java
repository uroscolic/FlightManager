package com.flightmanager.UserService;


import org.apache.activemq.broker.BrokerService;


public class MessageBroker {

    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.start();

        // Keep the application running
        System.out.println("Message broker started. Press [Enter] to shut down.");
        System.in.read();  // This will block the main thread until the user presses [Enter]

        broker.stop();
        System.out.println("Message broker stopped.");
    }
}

package com.integ.ptm;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create("http://localhost:9797/webapp/"), new JerseyApp());
        System.out.println("Press any key to shutdown");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        server.shutdown();
    }
}

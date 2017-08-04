package main;

import rpc.ClientPRC;
import rpc.ServerRPC;

/**
 * Created by cpu11118-local on 21/06/2017.
 */
public class RPCMain {

    public static void main(String[] args){
        ClientPRC clientPRC = new ClientPRC();
        ServerRPC serverRPC = new ServerRPC();
        serverRPC.serverListen();
        System.out.println("Waiting for result ...");
        String result = clientPRC.call(String.valueOf(45));
        System.out.println(result);
    }
}

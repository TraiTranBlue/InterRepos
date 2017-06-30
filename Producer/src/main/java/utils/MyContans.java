package utils;

/**
 * Created by cpu11118-local on 15/06/2017.
 */
public class MyContans {
    public static String URL_SERVER = "mongodb://127.0.0.1:27017";
    public static String DATA_NAME = "MyDB";
    public static String QUEUE_NAME = "QueueHelloWorld";
    public static String HOST_RABBITMQ = "172.17.0.1";
    public static String LOCALHOST_RABBITMQ = "localhost";
    public static String FANOUT_EXCHANGE_NAME = "fanoutLogs";
    public static String DIRECT_EXCHANGE_NAME = "directLogs";
    public static String TOPIC_EXCHANGE_NAME = "topicLogs";
    public static String FANOUT_EXCHANGE = "fanout";
    public static String DIRECT_EXCHANGE = "direct";
    public static String TOPIC_EXCHANGE = "topic";
    public static String RPC_QUEUE_NAME = "rpcQueue";
    public static String RPC_RESPON_QUEUE_NAME = "rpcResponse";

    public static int PROT = 15672;

    public static String[] keyRountingTopic = new String[]{"*.info.#", "#.error.#", "#.warning.#"};
}

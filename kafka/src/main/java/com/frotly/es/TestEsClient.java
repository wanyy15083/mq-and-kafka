package com.frotly.es;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.Transport;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Frotly on 2017/3/14.
 */
public class TestEsClient {
    public static void main(String[] args) {
        try {
            Settings settings = Settings.builder().put("cluster.name", "elasticsearch").put("client.transport.sniff", true).build();
            TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(
                    new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
            GetResponse response = client.prepareGet("blog", "article", "1").execute().actionGet();
            System.out.println(response.getSourceAsString());
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

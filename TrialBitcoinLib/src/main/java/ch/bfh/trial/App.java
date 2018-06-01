package ch.bfh.trial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import wf.bitcoin.javabitcoindrpcclient.*;

import java.net.URL;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            URL u = new URL("http://user:password@127.0.0.1:18332");
            System.out.println(u.getUserInfo());
            BitcoinJSONRPCClient client = new BitcoinJSONRPCClient(u);
            BitcoindRpcClient.Block block2 = client.getBlock(client.getBlockCount());
            System.out.println(block2.toString());
            //System.out.println(client.getRawTransactionHex("c90ff2d1e031af63742cb1ca2267ba40cff80349eb7e271703adb5143b202a12"));

            System.out.println(block2.time().toString());
            BitcoindRpcClient.Block block3 = client.getBlock(1);
            System.out.println(block3.toString());

            System.out.println(block3.time().toString());
            for(int i=54528;i<=54540;i++) {
                BitcoindRpcClient.Block blocki = client.getBlock(i);
                System.out.println(gson.toJson(blocki));
                for(String txs: blocki.tx()){
                    System.out.println(txs);
                    String txr=client.getRawTransactionHex(txs);
                    if(!txr.contains("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"))
                        continue;
                    System.out.print("false");
                    System.out.println(txr);
                    BitcoindRpcClient.RawTransaction tx = client.decodeRawTransaction(txr);
                    System.out.println(gson.toJson(tx));
                }
            }

            BitcoindRpcClient.Block block4 = client.getBlock(834624);
            System.out.println( gson.toJson(block4));
            System.out.println( gson.toJson(
                    block4.tx().stream().map(x->client.decodeRawTransaction(client.getRawTransactionHex(x))).collect(Collectors.toList())));
        }
        catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

}

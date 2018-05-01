package ch.bfh.trial;
import fr.acinq.bitcoin.*;
import wf.bitcoin.javabitcoindrpcclient.*;

import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Block block = Block.LivenetGenesisBlock();
        System.out.println(block.toString());
        try {
            URL u = new URL("http://user:password@127.0.0.1:18332");
            System.out.println(u.getUserInfo());
            BitcoinJSONRPCClient client = new BitcoinJSONRPCClient(u);
            BitcoindRpcClient.Block block2 = client.getBlock(client.getBlockCount());
            System.out.println(block2.toString());
            System.out.println(client.getRawTransactionHex("88fa46d5fa8ee676b0b242b7e4033e179b3332d19f4622ab33e7391b4a8933ef"));

            System.out.println(block2.time().toString());
            BitcoindRpcClient.Block block3 = client.getBlock(1);
            System.out.println(block3.toString());

            System.out.println(client.getRawTransactionHex("88fa46d5fa8ee676b0b242b7e4033e179b3332d19f4622ab33e7391b4a8933ef"));
            BitcoindRpcClient.RawTransaction transaction =client.decodeRawTransaction(client.getRawTransactionHex("88fa46d5fa8ee676b0b242b7e4033e179b3332d19f4622ab33e7391b4a8933ef"));
            System.out.println(transaction.toString());
            System.out.println(block3.time().toString());
            for(int i=client.getBlockCount()-20;i<=client.getBlockCount();i++)
            {
                System.out.println( client.getBlock(i).time().toString());
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

}

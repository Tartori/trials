package ch.bfh.trial;
import fr.acinq.bitcoin.*;
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

        Block block = Block.LivenetGenesisBlock();
        System.out.println(block.toString());
        try {
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
            for(int i=client.getBlockCount()-20;i<=client.getBlockCount();i++)
            {
                BitcoindRpcClient.Block blocki = client.getBlock(i);
                System.out.println( blocki.time().toString());

                for(BitcoindRpcClient.RawTransaction tx : blocki.tx().stream().map(x->client.decodeRawTransaction(client.getRawTransactionHex(x))).collect(Collectors.toList()))
                {
                    System.out.println(tx.txId());
                    System.out.println(tx.vOut().stream().map(x->x.scriptPubKey().type()).reduce("",String::concat));
                }
            }

            BitcoindRpcClient.Block block4 = client.getBlock(834624);
            System.out.println(block4.toString());
            for(BitcoindRpcClient.RawTransaction tx : block2.tx().stream().map(x->client.decodeRawTransaction(client.getRawTransactionHex(x))).collect(Collectors.toList()))
            {
                System.out.println(tx.txId());
                System.out.println(tx.vOut().stream().map(x->x.scriptPubKey().type()).reduce("",String::concat));
            }

            BitcoindRpcClient.RawTransaction transaction =client.decodeRawTransaction(client.getRawTransactionHex("8cd3ef3817b5e6a072a9803eafb041140e180936915c00b5aa22785cd6c46f77"));
            System.out.println(transaction.toString());


        }
        catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

}

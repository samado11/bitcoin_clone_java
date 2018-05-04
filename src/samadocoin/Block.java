/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samadocoin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar SamadOo0
 */
public class Block {
    String timestamp;
    Transactions transactions;
    String previousHash;
    String hash;
    int nonce;
    Block(String timestamp,Transactions transactions,String previousHash ) {
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.hash = this.calculateHash();
        this.nonce = 0;
    }

    Block() {
        
    }

    public String calculateHash() {
        MessageDigest digest=null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }
        String txt= timestamp+previousHash+transactions+nonce;
        final byte  bytes[]=digest.digest(txt.getBytes());
        final StringBuilder builder =new StringBuilder();
        for(final byte b : bytes){
            String hex =Integer.toHexString(0xff & b);
            
            if(hex.length()==1){
                builder.append('0');
            }
            builder.append(hex);
        }
        return builder.toString();
    
    }

    public void mineBlock(int difficulty,String address) {
       StringBuilder builder=new StringBuilder();
            for(int i=0;i<difficulty;i++){
               builder.append("0");
            }
        while (!this.hash.substring(0, difficulty).equals(builder.toString())) {
            this.nonce++;
            this.hash = this.calculateHash();
        }
         for(int i=0;i<SamadoCoin.users.size();i++){
            if(address.equals(SamadoCoin.users.elementAt(i).address)){
                SamadoCoin.users.elementAt(i).balance++;
                System.out.println("one Samado coin added to your account");
            }
            }
        System.out.println("BLOCK MINED: " + this.hash);
        
    }
    
    
}

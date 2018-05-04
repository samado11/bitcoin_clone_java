/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samadocoin;

import java.util.Vector;
import java.util.Date;

/**
 *
 * @author Omar SamadOo0
 */
public class BlockChain {
    public Vector<Block> blockChain=new Vector<Block>();
    int difficulty;
    Vector<Transactions>pendingTransactions=new Vector<Transactions>();
    int miningReward;
     BlockChain() {
         Transactions t=new Transactions("", "", 0);
        Block bb= new Block("01/01/2017", t, "0");
        
        this.blockChain.addElement(bb);
        this.difficulty = 2;
        this.miningReward = 100;
    }

    /*createGenesisBlock() {
        return new Block(Date.parse("2017-01-01"), [], "0");
    }*/

    public String getLatestBlock() {
        return this.blockChain.elementAt(this.blockChain.size()-1).hash ;
    }

    public void minePendingTransactions( String from){
        Date now = new Date();
        String temp=String.valueOf(now);
        for(int i=0;i<this.pendingTransactions.size();i++){
        Block block = new Block(temp, this.pendingTransactions.elementAt(i), this.getLatestBlock());
        block.mineBlock(this.difficulty,this.pendingTransactions.elementAt(i).toAddress);
        System.out.print("Block successfully mined!");
        this.blockChain.add(block);
            
        }
        for(int i=0;i<SamadoCoin.users.size();i++){
            if(from.equals(SamadoCoin.users.elementAt(i).address)){
                SamadoCoin.users.elementAt(i).balance--;
                System.out.println("one Samado coin added to your account");
            }
            }
        this.pendingTransactions.clear();
        
    }

    public void createTransaction(Transactions transaction){
        this.pendingTransactions.add(transaction);
    }

    public boolean getBalanceOfAddress(User user,int amount){
        int balance = 0;
        for(int i=0;i<SamadoCoin.users.size();i++){
            if(user.address.contains(SamadoCoin.users.elementAt(i).address)){
                 balance=SamadoCoin.users.elementAt(i).balance;
              }
           }
         if(balance>=amount){
            return true;
        }
        else
            return false;
    }
        public boolean isChainValid() {
        for (int i = 0; i < this.blockChain.size(); i++){
            String currentHash = this.blockChain.elementAt(i).hash;
            String previousHash = this.blockChain.elementAt(i).hash;

            if (currentHash != previousHash) {
                return false;
            }
        }

        return true;
    }
    
}

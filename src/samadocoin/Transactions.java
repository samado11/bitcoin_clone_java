/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samadocoin;

/**
 *
 * @author Omar SamadOo0
 */
public class Transactions {
    String fromAddress;
    String toAddress;
    int amount;
    Transactions(String fromAddress,String toAddress,int amount){
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
    }
    
}

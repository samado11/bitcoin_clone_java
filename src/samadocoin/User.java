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
public class User {
    String password;
    int balance;
    String address;

    User(String pass, String address) {
          this.password=password;
        this.balance=0;
        this.address=address;
    }

    User() {
        
    }

    
    public void sign_up(String password,String address){
        this.password=password;
        this.balance=0;
        this.address=address;
        SamadoCoin.users.addElement(this);
        System.out.println(SamadoCoin.users.size());
    }
   
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samadocoin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;
import static javax.management.remote.JMXConnectorFactory.connect;
import jdk.nashorn.internal.ir.BreakNode;
import jdk.nashorn.internal.ir.ContinueNode;

/**
 *
 * @author Omar SamadOo0
 */
public class SamadoCoin {
    public static Vector<User> users = new Vector<User>();
    
     public boolean sign_in(String password,String address){
         boolean flag = false;
        for(int i=0;i<users.size();i++){
            if(address.contains(users.elementAt(i).address) &password.contains(users.elementAt(i).password)){
                System.out.println("login successfully");
                flag=true;
                break;
            }

        }
        if(flag==false){
            System.out.println("wrong user name");
            
        }
        return flag;
    }
     public boolean user_exist(String address){
         boolean flag = false;
        for(int i=0;i<users.size();i++){
            if(address.contains(users.elementAt(i).address) ){
                System.out.println("login successfully");
                flag=true;
                break;
            }

        }
        if(flag==false){
            System.out.println("wrong user name or password");
            
        }
        return flag;
    }
    public static void main(String[] args) throws IOException {
        
        BlockChain B=new BlockChain();
        SamadoCoin c=new SamadoCoin();
        int yes =0 ,no =0;
        boolean flag;
        while(true){
            System.out.println("Enter your choice \n 1)mine samado coins \n 2)transfere samado coins \n 3)sign up \n 4)my credite in samado coins");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            int n = reader.nextInt();
            if(n==1){
            System.out.println("Enter your address");
            Scanner reade = new Scanner(System.in);
            String address=reade.nextLine();
            System.out.println("Enter your password");
            String pass=reade.nextLine();
            flag=c.sign_in(pass, address);
            if(flag==false){
                continue;
            }
            Date now = new Date();
            String temp=String.valueOf(now);
            Transactions t=new Transactions("", address, 0);
            Block b= new Block(temp,t,B.getLatestBlock());
            b.mineBlock(4,address);
            B.blockChain.add(b); 
            }
            else if(n==2){
            System.out.println("Enter your address");
            Scanner read = new Scanner(System.in);  // Reading from System
            
            String address=read.nextLine();
            System.out.println("Enter your password");
            String pass=read.nextLine();
            flag=c.sign_in(pass, address);
            if(flag==false){
                continue;
            }

                System.out.println("Enter the other address \n");
                String to = read.nextLine();
                boolean temp=c.user_exist(to);
                if(temp==false){
                    continue;
                }
                User u=new User(pass,address);
                System.out.println("Enter the amount \n");
                int amount = read.nextInt();
                boolean f=B.getBalanceOfAddress(u,amount);
                if(f==false){
                    System.out.println("no enougth creadit");
                    continue;
                    
                }
                Transactions tt=new Transactions(address, to, amount);
                B.createTransaction(tt);
                B.minePendingTransactions(address);
            }
            else if(n==3){
                Scanner read = new Scanner(System.in);
                System.out.println("Enter your address \n");
                String new_address=read.nextLine();
                System.out.println("Enter your password \n");
                String new_pass=read.nextLine();
                User user=new User();
                user.sign_up(new_pass, new_address);
            }
            else if(n==4){ 
                Scanner read = new Scanner(System.in);
                System.out.println("Enter your address \n");
                String address=read.nextLine();
                System.out.println("Enter your password \n");
                String pass=read.nextLine();
                for(int i=0;i<SamadoCoin.users.size();i++){
                    
                 if(address.equals(SamadoCoin.users.elementAt(i).address)&pass.equals(SamadoCoin.users.elementAt(i).password)){
       
                System.out.println("you have "+users.elementAt(i).balance+" samado coins in your account \n \n");
            }
                 else
                        System.out.println("wrong address or password");
                }
            }
            else
                System.out.println("enter valid choice");
           
        }


          
    }

    
          
    }



    


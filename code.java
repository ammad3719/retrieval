/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Trie {

    
    
     private static class Node {
        Map<Character, Node> Maping;   // Character as a key and Node as 
        boolean Terminator;             // a value for HashMaps
        public Node() {
            Maping = new HashMap<>();
            Terminator = false;
        }
    }

    static final Node root = new Node();

    
    public   Trie() {
     //   root = new Node();
    }

    /*
     * Implementation of insert into trie
     */
    public void insert(String Temp) {
        Node current = root;
        for (int i = 0; i < Temp.length(); i++) {
            char ch = Temp.charAt(i);
            Node node = current.Maping.get(ch);
            if (node == null) {
                node = new Node();
                current.Maping.put(ch, node);
            }
            current = node;
        }
        //mark the current nodes Terminator as true
        current.Terminator = true;
        System.out.println("Word Inserted Successfully. ");
    }


    /*
     * Implementation of search into trie.
     */
    public  boolean search(String Temp) {
        Node current = root;
        for (int i = 0; i < Temp.length(); i++) {
            char ch = Temp.charAt(i);
            Node node = current.Maping.get(ch);
            //if node does not exist for given char then return false
            if (node == null) {
                System.out.println("Word Not Found.");
                return false;
            }
            current = node;
        }
        //return true of current's Terminator is true else return false.
        System.out.println("Word Found. ");
        return current.Terminator;
    }


    /*
     * Delete word from trie.
     */
    public  boolean delete(String Temp) {
       
        return (delete(root, Temp, 0));
    }

    /**
     * Returns true if parent should delete the mapping
     */
    private  boolean delete(Node current, String Temp, int index) {
        if (index == Temp.length()) {
            //only delete if currrent.Terminator is true.
            if (!current.Terminator) {
                System.out.println("Prefix Found, Not Deleted.");
                return false;
                
            }
            current.Terminator = false;
            System.out.println("Word Deleted Successfully.");
            //no other mapping then return true
            return current.Maping.isEmpty();
        }
        char ch = Temp.charAt(index);
        Node node = current.Maping.get(ch);
        if (node == null) {
            System.out.println("Word Does not exist.");
            return false;
        }
        boolean CurrentNodeEmpty = delete(node, Temp, index + 1);

        //if true is returned then delete the mapping of character and trienode reference from map.
        if (CurrentNodeEmpty) {
            current.Maping.remove(ch);
            //return true if no mappings are left in the map.
            return current.Maping.isEmpty();
        }
        
        return false;
        
    }
    
      
    public static void main(String[] args) {
     
        Trie obj = new Trie ();
      
        Scanner sc = new Scanner (System.in);
      
        System.out.println("Press 1 for Insertion of Word.");   //Menu
        System.out.println("Press 2 for Searching of Word.");  
        System.out.println("Press 3 for Deletion of Word.");
        System.out.println("Press 4 for Exit.");
        System.out.println();
        System.out.println();
        
        for (int i =0; i <100; i++ ) {
            
            int option = sc.nextInt();
         
        if (option ==1) {
            String word = sc.next();  
            obj.insert(word);  //insertion
        }
            
        if (option ==2) {
            String word = sc.next();
            obj.search(word);   //searching
        }
        
        if (option ==3) {
            String word = sc.next();
            obj.delete(word);  //deletion
        }
        
        if (option ==4) {
            break;  
        }
        
        }
    }
}

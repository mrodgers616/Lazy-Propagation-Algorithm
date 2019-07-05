import java.util.*;
import java.io.*;


public class solution3 {
    
    public static void main(String args[]) throws IOException{
        AVLTree tree = new AVLTree();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String inputString = input.readLine();
		
		int queries = Integer.parseInt(inputString);
        
        for(int q = 0; q < queries; q++) {
            
        	inputString = input.readLine();
			String[] data = inputString.split(" ");
			
            int t = Integer.parseInt(data[0]);
            
            if(t == 1) {
                tree.insert(data[1], Integer.parseInt(data[2]));
            }
            
            else if(t == 2) {
                String n1 = data[1];
                String n2 = data[2];
                int newRange = Integer.parseInt(data[3]);
                
                String name1 = "";
                String name2 = "";
                
                if(n1.compareToIgnoreCase(n2) < 0) {
                    name1 = n1;
                    name2 = n2;
                }
                else {
                    name1 = n2;
                    name2 = n1;
                }
                
                tree.rangeUpdate(name1, name2, tree.root, newRange);
            }
            
            else if (t == 3){
                String name = data[1];
                System.out.println(tree.find(name));
            }
        }
    }
}

class Node {
    
    public String key;
    public int value;
    public Node left;
    public Node right;
    public int height;
    public int carry = 0;
    public String min = " ";
    public String max = " ";
    Node(String key, int value){
        this.key=key;
        this.value=value;
        this.height=1;
    }
}

class AVLTree {
    
    public static Node root; 

    
    
    public static int getHeight(Node node){
    	if (node == null) 
            return 0; 
  
        return node.height; 
    }
    
    public static void rangeUpdate(String low, String high, Node node, int inc) {
        
    	if(node == null) {
    		return;
    	}
    	if(node.min.compareToIgnoreCase(low) >= 0 && node.max.compareToIgnoreCase(high) <= 0) {
        	node.carry += inc;
        }
    	
    	else if(node.key.compareToIgnoreCase(low) < 0) {
    		if(node.key.compareToIgnoreCase(low) >= 0 && node.key.compareToIgnoreCase(high) <= 0) {
        		node.value += inc;
        	}
    		rangeUpdate(low, high, node.right, inc);
        }
        else if (node.key.compareToIgnoreCase(high) > 0) {
        	if(node.key.compareToIgnoreCase(low) >= 0 && node.key.compareToIgnoreCase(high) <= 0) {
        		node.value += inc;
        	}
        	rangeUpdate(low, high, node.left, inc);
        }
        
        else if(node.min.compareToIgnoreCase(low) < 0 && node.max.compareToIgnoreCase(high) > 0) {
        	if(node.key.compareToIgnoreCase(low) >= 0 && node.key.compareToIgnoreCase(high) <= 0) {
        		node.value += inc;
        	}
        	rangeUpdate(low, high, node.left, inc);
        	rangeUpdate(low, high, node.right, inc);
        	return;
        }
        
        else {
        	if(node.key.compareToIgnoreCase(low) >= 0 && node.key.compareToIgnoreCase(high) <= 0) {
        		node.value += inc;
        	}
        	
        	rangeUpdate(low, high, node.left, inc);
        	rangeUpdate(low, high, node.right, inc);
        }
    }
    
    public static void pushDown(Node node) {
    	
    		node.value += node.carry;
        	if(node.left != null) {
            	node.left.carry += node.carry;
        	}
        	if(node.right != null) {
        		node.right.carry += node.carry;
        	}
        	node.carry = 0;
    	
    	
    }
    
	
    public static void updateInfo(Node node){
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        Node r = node;
        Node l = node;
        while(r.right != null) {
        	r = r.right;
        }
        while(l.left != null) {
        	l = l.left;
        }
        
        node.max = r.key;
        node.min = l.key;
    }

    public static Node rightRotate(Node node) {
        Node nodel = node.left;
        Node nodelr = nodel.right;
        nodel.right = node;
        node.left = nodelr;
        updateInfo(node);
        updateInfo(nodel);
        return nodel;
    }

    public static Node leftRotate(Node node) {
        Node noder = node.right;
        Node noderl = noder.left;
        noder.left = node;
        node.right = noderl;
        updateInfo(node);
        updateInfo(noder);
        return noder;
    }

    public static int getBalance(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public static Node doInsert(Node node, String key, int value) {
    	//pushDown(node);
    	if (node == null)
            return new Node(key, value);
    	else {
    		pushDown(node);
    	}
    	
        if (key.compareToIgnoreCase(node.key) < 0) {
            node.left = doInsert(node.left, key, value);
        }
        else if (key.compareToIgnoreCase(node.key) > 0) {
            node.right = doInsert(node.right, key, value);
        }
        else {
            node.value = value;
            return node;
        }
        updateInfo(node);
        int balance = getBalance(node);
        if (balance > 1 && key.compareToIgnoreCase(node.left.key) < 0)
            return rightRotate(node);
        if (balance < -1 && key.compareToIgnoreCase(node.right.key) > 0)
            return leftRotate(node);
        if (balance > 1 && key.compareToIgnoreCase(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareToIgnoreCase(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public static void insert(String key, int value) { 
        root = doInsert(root, key, value); 
    }

    public static int find(String key) { return doFind(root, key, 0); }

    public static int doFind(Node node, String key, int carry) {
        
    	if (node == null)
            return -1;
    	
    	carry += node.carry;
    	
        
        if (key.equals(node.key)) {
            System.out.println(" -- " + carry);
            return (node.value + carry);
        }
        else if (key.compareToIgnoreCase(node.key) < 0) {
            return doFind(node.left, key, carry);
        }
        else {
        	return doFind(node.right, key, carry);
        }

        
    }

    AVLTree(){
        root = null;
    }
}

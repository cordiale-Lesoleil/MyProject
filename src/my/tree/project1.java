package my.tree;  

import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List;

import javax.xml.soap.Node;  

public class project1<T> {  
    private final int DEFAULT_SIZE = 2;  
    private int size;  //树的size
    private int count;  //节点个数
    private Object[] nodes;  

    public project1() {  
        this.size = this.DEFAULT_SIZE;  
        this.nodes = new Object[this.size];  
        this.count = 0;  
    }  

    public project1(Node root) {  
        this();  
        this.count = 1;  
        this.nodes[0] = root;  
    }  

    public project1(Node root, int size) {  
        this.size = size;  
        this.nodes = new Object[this.size];  
        this.count = 1;  
        this.nodes[0] = root;  
    }  

    //添加一个节点  
    public void add(Node node) {  
        for (int i = 0; i < this.size; i++) {  
            if (this.nodes[i] == null) {  
                nodes[i] = node;  
                break;  
            }  
        }  
        this.count++;  
    }  

    public void check(){  
        if(this.count >= this.size){  
            this.enlarge();  
        }  
    }  
    //添加一个节点，并指明父节点  
    public void add(Node node, Node parent) {  
        this.check();  
        ((Object) node).setParent(this.position(parent));  
        this.add(node);  
    }  

    //获取节点在数组的存储位置  
    public int position(Node node) {  
        for (int i = 0; i < this.size; i++) {  
            if (nodes[i] == node) {  
                return i;  
            }  
        }  
        return -1;  
    }  

    //获取整棵树有多少节点  
    public int getSize(){  
        return this.count;  
    }  

    //获取根节点  
    @SuppressWarnings("unchecked")  //忽略unchecked警告
    public Node getRoot(){  
        return (Node) this.nodes[0];  
    }  

    //获取所以节点，以List形式返回  
    @SuppressWarnings("unchecked")  
    public List<Node> getAllNodes(){  
        List<Node> list = new ArrayList<Node<T>>();  
        for(int i=0;i<this.size;i++){  
            if(this.nodes[i] != null){  
                list.add((Node)nodes[i]);  
            }  
        }  
        return list;  
    }  

    //获取树的深度，只有根节点时为1  
    @SuppressWarnings("unchecked")  
    public int getDepth(){  

        int max = 1;  
        if(this.nodes[0] == null){  
            return 0;  
        }  

        for(int i=0;i<this.count;i++){  
            int deep = 1;  
            int location = ((Node)(this.nodes[i])).getParent();  
            while(location != -1 && this.nodes[location] != null){  
                location = ((Node)(this.nodes[location])).getParent();  
                deep++;  
            }  
            if(max < deep){  
                max = deep;  
            }  
        }  
        return max;  
    }  

    public void enlarge(){  
        this.size = this.size + this.DEFAULT_SIZE;  
        Object[] newNodes = new Object[this.size];  
        newNodes = Arrays.copyOf(nodes, this.size);  //把nodes拷贝到newNodes，并确定size
        Arrays.fill(nodes, null);  //清空nodes
        this.nodes = newNodes;  
        System.out.println("enlarge");  
    }  
}  
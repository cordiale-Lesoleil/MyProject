package my.tree;  

import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List;

import javax.xml.soap.Node;  

public class project1<T> {  
    private final int DEFAULT_SIZE = 2;  
    private int size;  //����size
    private int count;  //�ڵ����
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

    //���һ���ڵ�  
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
    //���һ���ڵ㣬��ָ�����ڵ�  
    public void add(Node node, Node parent) {  
        this.check();  
        ((Object) node).setParent(this.position(parent));  
        this.add(node);  
    }  

    //��ȡ�ڵ�������Ĵ洢λ��  
    public int position(Node node) {  
        for (int i = 0; i < this.size; i++) {  
            if (nodes[i] == node) {  
                return i;  
            }  
        }  
        return -1;  
    }  

    //��ȡ�������ж��ٽڵ�  
    public int getSize(){  
        return this.count;  
    }  

    //��ȡ���ڵ�  
    @SuppressWarnings("unchecked")  //����unchecked����
    public Node getRoot(){  
        return (Node) this.nodes[0];  
    }  

    //��ȡ���Խڵ㣬��List��ʽ����  
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

    //��ȡ������ȣ�ֻ�и��ڵ�ʱΪ1  
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
        newNodes = Arrays.copyOf(nodes, this.size);  //��nodes������newNodes����ȷ��size
        Arrays.fill(nodes, null);  //���nodes
        this.nodes = newNodes;  
        System.out.println("enlarge");  
    }  
}  
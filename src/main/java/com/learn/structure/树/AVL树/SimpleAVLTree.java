package com.learn.structure.树.AVL树;

/**
 * 平衡二叉树首先是查找二叉树， 每个节点的左右子树高度差不能超过1 
 * 为了保持平衡，插入和删除的时候需要旋转对于旋转情况分为 左左、左右、右左、右右
 * 
 * @author 王斌
 *
 */
public class SimpleAVLTree {
    
    private AVLTreeNode root;
    
    class AVLTreeNode{
        Integer key;
        int height=0;
        private AVLTreeNode leftNode;
        private AVLTreeNode rightNode;
        
        AVLTreeNode(Integer key,AVLTreeNode leftNode,AVLTreeNode rightNode){
            this.key = key;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.height = 0;
        }
        
        AVLTreeNode(){
            
        }
        
        @Override
        public String toString() {
            return "AVLTreeNode{" +
                    "key=" + key +
                    ", height=" + height +
                    ", leftNode=" + leftNode +
                    ", rightNode=" + rightNode +
                    '}';
        }
    }
    
    private int  height(AVLTreeNode tree) {
    	if(tree != null) {
    		return tree.height;
    	}
    	return 0;
    }
    
    private int max(int a,int b) {
    	return a>b?a:b;
    }
    /**
     * 左左,右旋
     * @param tree
     * @return
     */
    public AVLTreeNode leftLeftRotation(AVLTreeNode tree) {
    	AVLTreeNode leftChild = tree.leftNode;
    	tree.leftNode = leftChild.rightNode;
    	leftChild.rightNode = tree;
    	return leftChild;
    }
    /**
     * 左右，先左旋，再右旋
     * @param tree
     * @return
     */
    public AVLTreeNode leftRightRotation(AVLTreeNode tree) {
    	tree = this.rightRightRotation(tree);
    	tree = this.leftLeftRotation(tree);
    	return tree;
    }
    
    /**
     * 右左，先右旋，再左旋
     * @param tree
     * @return
     */
    public AVLTreeNode rightLeftRotation(AVLTreeNode tree) {
    	tree = this.leftLeftRotation(tree);
    	tree = this.rightRightRotation(tree);
    	return tree;
    }
    
    /**
     * 进行左旋
     * @param tree
     * @return
     */
    public AVLTreeNode rightRightRotation(AVLTreeNode tree) {
    	AVLTreeNode rightChild = tree.rightNode;
    	tree.rightNode = rightChild.leftNode;
    	rightChild.leftNode = tree;
    	return rightChild;
    }
    public AVLTreeNode insert(AVLTreeNode tree,Integer key) {
        if(tree == null) {
            //新节点创建
            tree = new AVLTreeNode(key,null,null);
        }else {
            if(key < tree.key) {
                tree.leftNode = insert(tree.leftNode,key);
                if(height(tree.leftNode) - height(tree.rightNode) == 2 ) {//左右子树高度相差超过了1
                	if(key < tree.leftNode.key) {//左左，需要旋转一次直接进行右旋
                		tree = leftLeftRotation(tree);
                	}else {//左右，需要旋转两次，先左旋后右旋
                		tree = leftRightRotation(tree);
                	}
                }
            }else if(key > tree.key) {
            	tree.rightNode = insert(tree.rightNode,key);
            	if(height(tree.rightNode) - height(tree.leftNode) == 2 ) {
            		if(key > tree.rightNode.key) {//右右
            			tree = rightRightRotation(tree);
            		}else {//右左
            			tree = rightLeftRotation(tree);
            		}
            	}
            }else {
                throw new RuntimeException("已经存在!");
            }
            
            tree.height = max(height(tree.leftNode),height(tree.rightNode))+1;
        }
        return tree;
    }
    
    public boolean delete(Integer data) {
        return true;
    }
    
    public void insert(Integer key) {
    	root = insert(root,key);
    }
    
   public static void main(String[] args) {
	   SimpleAVLTree simpleAVLTree = new SimpleAVLTree();
	   simpleAVLTree.insert(10);
	   simpleAVLTree.insert(5);
	   simpleAVLTree.insert(12);
	   simpleAVLTree.insert(3);
	   simpleAVLTree.insert(8);
	   simpleAVLTree.insert(2);
	   System.out.println(simpleAVLTree.root.toString());
   }
    
   
}

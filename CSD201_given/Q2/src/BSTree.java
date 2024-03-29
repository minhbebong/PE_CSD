/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

class BSTree
  {Node root;
   BSTree() {root=null;}
   boolean isEmpty()
      {return(root==null);
      }
   void clear()
      {root=null;
      }
   void fvisit(Node p, RandomAccessFile f) throws Exception
     {if(p != null) f.writeBytes(p.info + " ");

     }
   void preOrder(Node p, RandomAccessFile f) throws Exception
     {if(p==null) return;
      fvisit(p,f);
      preOrder(p.left,f);
      preOrder(p.right,f);
     }
   void inOrder(Node p, RandomAccessFile f) throws Exception
     {if(p==null) return;
      inOrder(p.left,f);
      fvisit(p,f);
      inOrder(p.right,f);
     }
   void postOrder(Node p, RandomAccessFile f) throws Exception
     {if(p==null) return;
      postOrder(p.left,f);
      postOrder(p.right,f);
      fvisit(p,f);
     }
  void breadth(Node p, RandomAccessFile f) throws Exception
    {if(p==null) return;
     Queue q = new Queue();
     q.enqueue(p);Node r;
     while(!q.isEmpty())
       {r = q.dequeue();
        fvisit(r,f);
        if(r.left!=null) q.enqueue(r.left);
        if(r.right!=null) q.enqueue(r.right);
       }
    }
   void loadData(int k)  //do not edit this function
     {String [] a = Lib.readLineToStrArray("data.txt", k);
      int [] b = Lib.readLineToIntArray("data.txt", k+1);
      int n = a.length;
      for(int i=0;i<n;i++) insert(a[i],b[i]);
     }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
  void insert(Car x)
    {Node q = new Node(x);
     if(root==null)
        {root = q;
         return; 
        }
     Node f,p;
     p=root;f=null;
     while(p!=null)
       {if(p.info.price == x.price) return;
        if(x.price < p.info.price)
          {f=p;p=p.left;}
           else
            {f=p;p=p.right;}
       }
     if(x.price < f.info.price)
        f.left=q;
         else
           f.right=q;
     }

  void insert(String xOwner, int xPrice)
     {//You should insert here statements to complete this function
      if(xOwner.charAt(0) == 'B' || xPrice>100) return;
       Car x = new Car(xOwner,xPrice);
      insert(x);
     }
  
  void f1() throws Exception
    {/* You do not need to edit this function. Your task is to complete insert  function
        above only.
     */
     clear();
     loadData(1);
     String fname = "f1.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     preOrder(root,f);
     f.writeBytes("\r\n");
     inOrder(root,f);
     f.writeBytes("\r\n");
     f.close();
    }  
  
//=============================================================
  void preOrder2(Node p,RandomAccessFile f) throws Exception
     {
         if(p==null) return;
      if(p.info.price >2 && p.info.price <6)
      fvisit(p,f);
      preOrder2(p.left, f);
      preOrder2(p.right,f);
     }
  
  void f2() throws Exception
    {clear();
     loadData(4);
     String fname = "f2.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     preOrder(root,f);
     f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        preOrder2(root, f);

    //------------------------------------------------------------------------------------
     f.writeBytes("\r\n");
     f.close();
    }  

// f.writeBytes(" k = " + k + "\r\n");
//=============================================================
  void f3() throws Exception
    {clear();
     loadData(7);
     String fname = "f3.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     breadth(root,f);
     f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        q123 = null;
        breadth2(root);
        if(q123 != null)
        deleByCopy(q123.info.price);

    //------------------------------------------------------------------------------------
     breadth(root,f);
     f.writeBytes("\r\n");
     f.close();
    }  

  Node q123 = null;
  void breadth2(Node p) {
       if(p==null) return;
       Queue q = new Queue();
       q.enqueue(p); Node r;
       while(!q.isEmpty()) {
         r=q.dequeue();
         
         if(r.left != null && r.right != null && r.info.price <= 7)
         { q123 = r; break;}
         
         if(r.left!=null) q.enqueue(r.left);
         if(r.right!=null) q.enqueue(r.right);
       }
    }
  void deleByCopy(int x) {
      Node f,p;
      f=null; p=root;
      while(p!=null) {
        if(p.info.price==x) break;
        f=p;
        if(x<p.info.price)
            p=p.left;
          else
            p=p.right;
      }
      if(p==null) return; // not found
      // p is a leaf node
      if(p.left==null && p.right==null) {
         if(f==null) { // p==root
           root=null;
           return;
         }
        if(p==f.left)
            f.left=null;
           else
            f.right=null;
      }
      // p has left son only
      if(p.left!=null && p.right==null) {
         if(f==null) { // p==root
           root=p.left;
           return;
         }
        if(p==f.left)
            f.left=p.left;
           else
            f.right=p.left;
      }
      // p has right son only
      if(p.left==null && p.right!=null) {
         if(f==null) { // p==root
           root=p.right;
           return;
         }
        if(p==f.left)
            f.left=p.right;
           else
            f.right=p.right;
      }
      // p has both 2 sons
      if(p.left!=null && p.right!=null) {
         Node q=p.left;
         // find the right-most node in the left sub-tree
         Node frp,rp;
         frp=null;rp=q;
         while(rp.right!=null) {
           frp=rp;
           rp=rp.right;
         }
         p.info=rp.info;
         if(frp==null) { // rp==q
            p.left=q.left;             
         }
         else {
           frp.right=rp.left;
         }
      }
      
   }
//=============================================================
  Node p123=null;
  void breadth3(Node p)
    {if(p==null) return;
     Queue q = new Queue();
     q.enqueue(p);Node r;
     while(!q.isEmpty())
       {r = q.dequeue();
        if(r.left!=null && r.info.price < 7) {p123=r;break;};
        if(r.left!=null) q.enqueue(r.left);
        if(r.right!=null) q.enqueue(r.right);
       }
    }
  
  Node father2(int xPrice) // return the father of the node q, where q.info.price = xPrice
    {Node f,p;
     f=null;p=root;
     while(p!=null)
       {if(p.info.price == xPrice) break;
         f=p;
         if(xPrice < p.info.price)  p=p.left; else p=p.right;
       }
     return(f);
    }
  
  Node  rotateRight(Node  p)
   {if(p == null || p.left==null)
     {System.out.println(" Cannot rotate right.");return(p);}
      Node  pl = p.left;
      p.left = pl.right;
      pl.right = p;
      return(pl);
   }
  
  void f4() throws Exception
    {clear();
     loadData(10);
     String fname = "f4.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     breadth(root,f);
     f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

     p123 = null; 
     breadth3(root);
     int xPrice = p123.info.price;
     Node r = rotateRight(p123);
     Node q = father2(xPrice);
     if(q==null)
        root = r;
         else
         {if(q.left==p123) q.left = r; else  q.right = r;
         }

    //------------------------------------------------------------------------------------
     breadth(root,f);
     f.writeBytes("\r\n");
     f.close();
    }  

 }

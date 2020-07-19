学习笔记
1.哈希表
2.树、二叉树、二叉搜索树
3.图

1.哈希碰撞
解决方法：
1.开放定址法
2.再哈希法
3.链地址法
时间复杂度分析: 查,插,删平均时间复杂度是O(1) 最坏时间复杂度是O(nlogn) 根据具体实现有所不同
put方法：
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    //hash表为null或者长度为0 初始化tab
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    //根据是否发生hash碰撞进行对应的处理
    if ((p = tab[i = (n - 1) & hash]) == null)
        //没有发生hash碰撞直接赋值给对应的下标
        tab[i] = newNode(hash, key, value, null);
    else {//发生了hash碰撞
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            //如果插入的值和所在的值地址完全一样 直接返回 不需要进行更新
            e = p;
        else if (p instanceof TreeNode)
           //碰撞的第一个节点是(红黑)树,那么改节点以后一直是树类型,将要插入的值插入到树中
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
           //如果是碰撞的节点是链表
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                  	//后继节点是为null直接插入
                    p.next = newNode(hash, key, value, null);
                  	//要判断链表长度是否大于树的阈值 就将链表转化为树
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
              	//和上面一样 如果完全一样
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
      	//e 是碰撞的元素
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            //onlyIfAbsent是方法的调用参数，表示是否替换已存在的值
            // 在默认的put方法中这个值是false，所以这里会用新值替换旧值
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    //操作计数器
    ++modCount;
    //如果下一个元素达到扩容的阈值进行扩容
    if (++size > threshold)
        resize();
  	//hashmap 是个空函数
    afterNodeInsertion(evict);
    return null;
}
---------------
get方法
final Node<K,V> getNode(int hash, Object key) {
  	//first 是链表的头节点
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
  	//tab不能为null,tab的长度不为0,hash的对应下标不为null
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        //hash碰撞 解决办法
      	//如果头节点和要查询的节点完全一样 直接返回
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
          	//如果是树,获取树的节点
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {//链表遍历查找
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
超过8个元素，其put和set方法底层都红黑树，其时间复杂度为O(logn)

2.二叉树遍历(背过)
前序
public void pre(Node root){
    if(root == null){
        return;
    }
    println(root.val);
    pre(root.left);
    pre(root.right);
}
中序
public void pre(Node root){
    if(root == null){
        return;
    }
    pre(root.left);
    println(root.val);
    pre(root.right);
}
后序
public void pre(Node root){
    if(root == null){
        return;
    }
    pre(root.left);
    pre(root.right);
    println(root.val);
}
3.图
遍历图是牢记增加set集合，记录遍历过的节点，防止大量重复遍历
一周学习总结

当了几年的码农了，一直对算法与数据结构一知半解，没有系统的学习和深究，最近下定决心好好学习一下算法知识

1.学习方法

不要死磕，如果有思路，可以先暴力求解，把自己比较笨的思路写下来，没有思路立刻写看解答

五毒神掌，一道题要做五遍，把题目吃透吃明白

善用脑图，把痛点难点知识点用脑图记录下来，如果没有太多时间五毒神掌，要用脑图加深印象


2.本周知识点

数组：O(1)访问，O(n)修改

链表：O(n)访问，O(1)修改

跳表：链表的优化，用空间换时间，增加的修改的复杂度，优化了查询，O(logn)访问

栈、队列、双端队列：取出插入操作O(1)

优先队列：offer和poll复杂度都是O(logn)

以下是priority queue的offer和poll方法，操作都会对数组进行O(logn)的整理

@SuppressWarnings("unchecked")
private void siftUpComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (key.compareTo((E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = key;
}

@SuppressWarnings("unchecked")
private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        if (key.compareTo((E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = key;
}

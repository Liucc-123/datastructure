package com.liucc.chapter_hashing;

import java.util.ArrayList;
import java.util.List;

// 链式地址哈希表
// 这里使用列表（动态数组）代替链表进行简单实现
class HashMapChaining {
    int size; // 键值对数量
    int capacity; // 哈希表容量
    double loadThreas; // 触发扩容的负载因子
    int extendRatio; // 扩容倍数
    List<List<Pair>> buckets; // 桶数组

    // 构造器
    public HashMapChaining() {
        capacity = 4; // 初始容量
        loadThreas = 2.0 / 3.0; // 扩容阈值
        extendRatio = 2; // 扩容2倍
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    // 哈希函数
    public int hashFunc(int key) {
        return key % capacity;
    }

    // 负载因子
    public double loadFactor() {
        return (double) size / capacity;
    }
    // 查询
    public String get(int key){
        int index = hashFunc(key);
        List<Pair> pairs = buckets.get(index);
        for (Pair pair : pairs) {
            if (pair.key == key) {
                return pair.val;
            }
        }
        return null; // 没有找到就返回null
    }
    // 新增/更新
    public void put(int key, String val){
        // 当负载因子达到阈值，进行扩容
        if (loadFactor() >= loadThreas) {
            // TODO 扩容
        }
        int index = hashFunc(key);
        List<Pair> pairs = buckets.get(index);
        for (Pair pair : pairs) {
            if (pair.key == key) { // buckets中已存在指定key，走更新
                pair.val = val;
                return;
            }
        }
        // 走新增
        pairs.add(new Pair(key, val));
        size++;
        return;
    }
    // 删除
    public void remove(int key){
        int index = hashFunc(key);
        List<Pair> pairs= buckets.get(index);
        for (Pair pair : pairs) {
            if (pair.key == key) {
                pairs.remove(pair);
                size--;
                break;
            }
        }
    }
    // 扩容
    public void extend(){
        // 备份原始buckets
        List<List<Pair>> bucketsTmp = buckets;
        // 对buckets进行扩容
        capacity = capacity * extendRatio;
        buckets = new ArrayList<>(capacity);
        // 将已有paris拷贝到buckets
        size = 0; // 重置size
        for (List<Pair> pairs : bucketsTmp) {
            for (Pair pair : pairs) {
                put(pair.key, pair.val);
            }
        }
    }
    // 打印
    void print() {
        for (List<Pair> bucket : buckets) {
            List<String> res = new ArrayList<>();
            for (Pair pair : bucket) {
                res.add(pair.key + " -> " + pair.val);
            }
            System.out.println(res);
        }
    }
}

public class hash_map_chaining {
    public static void main(String[] args) {
         /* 初始化哈希表 */
         HashMapChaining map = new HashMapChaining();
        
         /* 添加操作 */
        // 在哈希表中添加键值对 (key, value)
        map.put(12836, "小哈");
        map.put(15937, "小啰");
        map.put(16750, "小算");
        map.put(13276, "小法");
        map.put(10583, "小鸭");
        System.out.println("\n添加完成后，哈希表为\nKey -> Value");
        map.print();

        /* 查询操作 */
        // 向哈希表中输入键 key ，得到值 value
        String name = map.get(13276);
        System.out.println("\n输入学号 13276 ，查询到姓名 " + name);

        /* 删除操作 */
        // 在哈希表中删除键值对 (key, value)
        map.remove(12836);
        System.out.println("\n删除 12836 后，哈希表为\nKey -> Value");
        map.print();
    }
}

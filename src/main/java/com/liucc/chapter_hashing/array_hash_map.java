package com.liucc.chapter_hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * 键值对
 */
class Pair {
    public int key;
    public String val;

    public Pair(int key, String val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * 基于数组实现的Map
 */
class ArrayHashMap {
    private List<Pair> buckets;

    // 构造器初始化
    public ArrayHashMap() {
        buckets = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            buckets.add(null);
        }
    }

    // 哈希函数
    public int hashing(int key) {
        return key % 100;
    }

    // 查询操作
    public String get(int key) {
        int index = hashing(key);
        Pair pair = buckets.get(index);
        return pair == null ? null : pair.val;
    }
    // 添加/修改操作
    public void put(int key, String val){
        Pair pair = new Pair(key, val);
        int index = hashing(key);
        buckets.set(index, pair);
    }
    // 删除操作
    public void remove(int key){
        int index = hashing(key);
        buckets.set(index, null);
    } 
    // 获取所有键值对
    public List<Pair> pairSet(){
        List<Pair> resuList = new ArrayList<>();
        buckets.forEach(item -> {
            if (item != null) {
                resuList.add(item);
            }
        });
        return resuList;
    }
    // 获取所有key
    public List<Integer> keySet(){
        List<Integer> resultList = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                resultList.add(pair.key);
            }
        }
        return resultList;
    } 
    // 获取所有value
    public List<String> valus(){
        List<String> resultList = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                resultList.add(pair.val);
            }
        }
        return resultList;
    }
    // 打印
    public void print() {
        for (Pair kv : pairSet()) {
            System.out.println(kv.key + " -> " + kv.val);
        }
    }
}

public class array_hash_map {
    public static void main(String[] args) {
        /* 初始化哈希表 */
        ArrayHashMap map = new ArrayHashMap();

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
        String name = map.get(15937);
        System.out.println("\n输入学号 15937 ，查询到姓名 " + name);

        /* 删除操作 */
        // 在哈希表中删除键值对 (key, value)
        map.remove(10583);
        System.out.println("\n删除 10583 后，哈希表为\nKey -> Value");
        map.print();

        /* 遍历哈希表 */
        System.out.println("\n遍历键值对 Key->Value");
        for (Pair kv : map.pairSet()) {
            System.out.println(kv.key + " -> " + kv.val);
        }
        System.out.println("\n单独遍历键 Key");
        for (int key : map.keySet()) {
            System.out.println(key);
        }
        System.out.println("\n单独遍历值 Value");
        for (String val : map.valus()) {
            System.out.println(val);
        }
    }
}

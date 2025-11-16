package com.home.ms4j.core.base.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不可变类演示类
 * 
 * 本类展示了不可变类和可变类的区别，以及不可变类的优势
 */
public class ImmutableClassDemo {
    
    public static void main(String[] args) {
        System.out.println("========== 不可变类演示 ==========\n");
        BigDecimal bigDecimal = new BigDecimal(1);
        
        demonstrateImmutableClass();
        System.out.println("\n");
        
        demonstrateMutableClass();
        System.out.println("\n");
        
        demonstrateThreadSafety();
        System.out.println("\n");
        
        demonstrateHashMapKey();
    }
    
    /**
     * 演示不可变类的特性
     */
    private static void demonstrateImmutableClass() {
        System.out.println("--- 演示1: 不可变类的特性 ---");
        
        // 创建不可变对象
        List<String> initialHobbies = new ArrayList<>(Arrays.asList("读书", "游泳"));
        ImmutablePerson person1 = new ImmutablePerson("张三", 25, initialHobbies);
        
        System.out.println("创建对象: " + person1);
        System.out.println("初始爱好: " + person1.getHobbies());
        
        // 修改原始列表
        initialHobbies.add("编程");
        System.out.println("修改原始列表后的对象: " + person1);
        System.out.println("说明: 原始列表的修改不会影响不可变对象（因为构造时进行了防御性拷贝）");
        
        // 尝试通过 getter 修改（会被阻止）
        List<String> hobbies = person1.getHobbies();
        System.out.println("通过getter获取的爱好列表类型: " + hobbies.getClass().getSimpleName());
        try {
            hobbies.add("新爱好");  // 会抛出 UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("✓ 捕获异常: 无法修改不可变对象的内容");
        }
        
        // 使用 with 方法创建新对象
        ImmutablePerson person2 = person1.withName("李四");
        ImmutablePerson person3 = person1.addHobby("编程");
        
        System.out.println("原对象: " + person1);
        System.out.println("新对象（修改姓名）: " + person2);
        System.out.println("新对象（添加爱好）: " + person3);
        System.out.println("说明: 修改操作返回新对象，原对象保持不变");
    }
    
    /**
     * 演示可变类的问题
     */
    private static void demonstrateMutableClass() {
        System.out.println("--- 演示2: 可变类的问题 ---");
        
        MutablePerson person = new MutablePerson("王五", 30, new ArrayList<>(Arrays.asList("电影", "音乐")));
        System.out.println("创建对象: " + person);
        
        // 可以随意修改状态
        person.setName("赵六");
        person.setAge(31);
        System.out.println("修改后的对象: " + person);
        
        // 通过getter返回的引用可以修改内部状态
        List<String> hobbies = person.getHobbies();
        hobbies.add("游戏");
        System.out.println("通过引用修改后的对象: " + person);
        System.out.println("说明: 可变对象的状态可能被意外修改");
    }
    
    /**
     * 演示不可变类的线程安全性
     */
    private static void demonstrateThreadSafety() {
        System.out.println("--- 演示3: 线程安全性 ---");
        
        List<String> hobbies = new ArrayList<>(Arrays.asList("阅读", "旅行"));
        ImmutablePerson person = new ImmutablePerson("线程安全测试", 25, hobbies);
        
        // 多个线程同时访问同一个不可变对象
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                // 可以安全地读取，无需同步
                String name = person.getName();
                int age = person.getAge();
                List<String> h = person.getHobbies();
            }
        };
        
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        
        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("✓ 多线程环境下，不可变对象无需同步即可安全访问");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 演示不可变对象作为HashMap的key
     */
    private static void demonstrateHashMapKey() {
        System.out.println("--- 演示4: 作为HashMap的key ---");
        
        Map<ImmutablePerson, String> map = new HashMap<>();
        
        List<String> hobbies1 = new ArrayList<>(Arrays.asList("读书", "游泳"));
        ImmutablePerson key1 = new ImmutablePerson("张三", 25, hobbies1);
        
        map.put(key1, "员工信息1");
        System.out.println("添加key: " + key1);
        System.out.println("获取值: " + map.get(key1));
        
        // 创建相同的key（但不同对象）
        List<String> hobbies2 = new ArrayList<>(Arrays.asList("读书", "游泳"));
        ImmutablePerson key2 = new ImmutablePerson("张三", 25, hobbies2);
        
        System.out.println("创建相同内容的key: " + key2);
        System.out.println("key1.equals(key2): " + key1.equals(key2));
        System.out.println("获取值: " + map.get(key2));
        System.out.println("说明: 不可变对象适合作为HashMap的key，因为hashCode不会改变");
        
        // 对比：可变对象作为key的问题
        System.out.println("\n--- 可变对象作为key的问题 ---");
        Map<MutablePerson, String> mutableMap = new HashMap<>();
        MutablePerson mutableKey = new MutablePerson("李四", 30, new ArrayList<>(Arrays.asList("电影")));
        mutableMap.put(mutableKey, "员工信息2");
        System.out.println("添加key: " + mutableKey);
        System.out.println("获取值: " + mutableMap.get(mutableKey));
        
        // 修改key的状态
        mutableKey.setAge(31);
        System.out.println("修改key后: " + mutableKey);
        System.out.println("获取值: " + mutableMap.get(mutableKey));
        System.out.println("⚠ 警告: 可变对象作为key后，修改其状态会导致无法从Map中获取值！");
    }
}


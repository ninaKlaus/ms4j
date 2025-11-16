package com.home.ms4j.core.base.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 可变类（Mutable Class）示例 - 用于对比
 * 
 * 可变类的特点：
 * 1. 字段可以是 private 但不是 final
 * 2. 提供 setter 方法允许修改状态
 * 3. 状态可以在对象创建后改变
 * 
 * 问题：
 * - 多线程环境下需要同步
 * - 不适合作为 HashMap 的 key（hashCode可能改变）
 * - 状态可能被意外修改
 */
public class MutablePerson {
    
    // 字段不是 final，可以修改
    private String name;
    private int age;
    private List<String> hobbies;
    
    // 无参构造函数
    public MutablePerson() {
        this.hobbies = new ArrayList<>();
    }
    
    // 有参构造函数
    public MutablePerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
    }
    
    // Getter 方法
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public List<String> getHobbies() {
        return hobbies;  // 直接返回引用，外部可以修改
    }
    
    // Setter 方法 - 允许修改状态
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
    
    public void addHobby(String hobby) {
        if (this.hobbies == null) {
            this.hobbies = new ArrayList<>();
        }
        this.hobbies.add(hobby);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutablePerson that = (MutablePerson) o;
        return age == that.age &&
               Objects.equals(name, that.name) &&
               Objects.equals(hobbies, that.hobbies);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age, hobbies);
    }
    
    @Override
    public String toString() {
        return "MutablePerson{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", hobbies=" + hobbies +
               '}';
    }
}


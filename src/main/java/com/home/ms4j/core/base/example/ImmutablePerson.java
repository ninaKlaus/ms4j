package com.home.ms4j.core.base.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 不可变类（Immutable Class）示例
 * 
 * 不可变类的特点：
 * 1. 类声明为 final，防止被继承（最佳实践，但不是唯一方式）
 *    注意：即使类不是 final，通过正确设计也可以实现不可变性
 *    但 final 可以防止子类破坏不可变性，所以是强烈推荐的做法
 * 2. 所有字段都是 private final 的
 * 3. 不提供 setter 方法
 * 4. 如果字段是可变对象（如List、Date等），需要：
 *    - 在构造时进行深拷贝或防御性拷贝
 *    - 在getter中返回不可修改的视图或副本
 * 5. 构造函数初始化所有字段
 * 
 * 补充说明：
 * - BigDecimal 实际上是被 final 修饰的（JDK源码：public final class BigDecimal）
 * - 不可变性的核心是：对象状态创建后不能被修改
 * - final 修饰类主要是防止子类破坏不可变性，这是最佳实践
 * 
 * 优点：
 * - 线程安全（天然线程安全，无需同步）
 * - 可以作为HashMap的key（hashCode不会改变）
 * - 避免意外的状态修改
 * - 更容易推理和测试
 */
public final class ImmutablePerson {
    
    // 所有字段都是 private final
    private final String name;
    private final int age;
    private final List<String> hobbies;  // 可变对象字段
    
    /**
     * 构造函数：初始化所有字段
     * 对于可变对象（List），进行防御性拷贝
     */
    public ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = Objects.requireNonNull(name, "姓名不能为空");
        this.age = age;
        // 防御性拷贝：创建新的ArrayList，避免外部修改影响内部状态
        this.hobbies = new ArrayList<>(Objects.requireNonNull(hobbies, "爱好列表不能为空"));
    }
    
    // 只有 getter 方法，没有 setter 方法
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    /**
     * 返回不可修改的视图，防止外部修改内部状态
     * 或者也可以返回一个新的副本：return new ArrayList<>(hobbies);
     */
    public List<String> getHobbies() {
        // 使用 Collections.unmodifiableList 返回不可修改的视图
        return Collections.unmodifiableList(hobbies);
    }
    
    /**
     * 如果需要修改，返回新的不可变对象
     * 这是不可变类的典型模式：修改操作返回新对象
     */
    public ImmutablePerson withName(String newName) {
        return new ImmutablePerson(newName, this.age, this.hobbies);
    }
    
    public ImmutablePerson withAge(int newAge) {
        return new ImmutablePerson(this.name, newAge, this.hobbies);
    }
    
    public ImmutablePerson addHobby(String hobby) {
        List<String> newHobbies = new ArrayList<>(this.hobbies);
        newHobbies.add(hobby);
        return new ImmutablePerson(this.name, this.age, newHobbies);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutablePerson that = (ImmutablePerson) o;
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
        return "ImmutablePerson{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", hobbies=" + hobbies +
               '}';
    }
}


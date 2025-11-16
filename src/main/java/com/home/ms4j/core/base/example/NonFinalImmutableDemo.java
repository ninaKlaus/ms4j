package com.home.ms4j.core.base.example;

/**
 * 演示：即使类不是 final，也可以实现不可变性
 * 
 * 重要概念澄清：
 * 1. BigDecimal 类实际上是被 final 修饰的（查看JDK源码可以看到：public final class BigDecimal）
 * 2. 但即使类不是 final，通过正确的设计，也可以实现不可变性
 * 3. final 修饰类主要是为了防止子类破坏不可变性，这是最佳实践，但不是唯一方式
 */
public class NonFinalImmutableDemo {
    
    /**
     * 示例：非 final 类的不可变实现
     * 
     * 注意：这不是最佳实践，仅用于演示说明
     * 更好的做法是将类声明为 final
     */
    public static class NonFinalImmutableValue {
        private final int value;
        
        public NonFinalImmutableValue(int value) {
            this.value = value;
        }
        
        // 没有 setter 方法
        public int getValue() {
            return value;
        }
        
        // 修改操作返回新对象
        public NonFinalImmutableValue add(int other) {
            return new NonFinalImmutableValue(this.value + other);
        }
        
        @Override
        public String toString() {
            return "NonFinalImmutableValue{value=" + value + "}";
        }
    }
    
    /**
     * 演示非 final 类可能存在的问题：子类可能破坏不可变性
     */
    public static class MutableSubclass extends NonFinalImmutableValue {
        private int mutableValue;  // 子类添加可变字段
        
        public MutableSubclass(int value) {
            super(value);
            this.mutableValue = value;
        }
        
        // 子类提供 setter，破坏了不可变性
        public void setMutableValue(int value) {
            this.mutableValue = value;
        }
        
        public int getMutableValue() {
            return mutableValue;
        }
        
        @Override
        public String toString() {
            return "MutableSubclass{mutableValue=" + mutableValue + ", super=" + super.toString() + "}";
        }
    }
    
    public static void main(String[] args) {
        System.out.println("========== 非 final 类的不可变性演示 ==========\n");
        
        // 1. 演示非 final 类的不可变性
        System.out.println("--- 演示1: 非 final 类可以是不变的 ---");
        NonFinalImmutableValue v1 = new NonFinalImmutableValue(10);
        System.out.println("创建对象: " + v1);
        
        NonFinalImmutableValue v2 = v1.add(5);
        System.out.println("调用 add(5): " + v2);
        System.out.println("原对象: " + v1);
        System.out.println("说明: 非 final 类通过正确设计也可以实现不可变性");
        
        // 2. 演示非 final 类的问题：子类可能破坏不可变性
        System.out.println("\n--- 演示2: 非 final 类的问题 ---");
        MutableSubclass m1 = new MutableSubclass(10);
        System.out.println("创建可变子类: " + m1);
        m1.setMutableValue(20);
        System.out.println("修改后: " + m1);
        System.out.println("⚠ 警告: 非 final 类允许子类破坏不可变性！");
        
        // 3. 说明 BigDecimal 的情况
        System.out.println("\n--- 关于 BigDecimal 的说明 ---");
        System.out.println("BigDecimal 在 JDK 源码中的定义：");
        System.out.println("  public final class BigDecimal extends Number implements Comparable<BigDecimal>");
        System.out.println("结论: BigDecimal 是 final 类，因此是标准的不可变类实现");
        
        // 4. 验证 BigDecimal 的不可变性
        System.out.println("\n--- 演示3: BigDecimal 的不可变性 ---");
        java.math.BigDecimal bd1 = new java.math.BigDecimal("100.50");
        System.out.println("创建 BigDecimal: " + bd1);
        
        java.math.BigDecimal bd2 = bd1.add(new java.math.BigDecimal("50.25"));
        System.out.println("调用 add(50.25): " + bd2);
        System.out.println("原对象: " + bd1);
        System.out.println("说明: BigDecimal 的操作返回新对象，原对象不变");
        
        System.out.println("\n--- 总结 ---");
        System.out.println("1. 不可变性的核心：对象创建后状态不能被修改");
        System.out.println("2. final 修饰类：防止子类破坏不可变性（最佳实践）");
        System.out.println("3. 即使类不是 final，通过正确设计也可以实现不可变性");
        System.out.println("4. 但最佳实践是：实现不可变类时，将类声明为 final");
    }
}


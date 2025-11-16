package com.home.ms4j.core.base.example;

/**
 * Java 为什么不支持多继承？
 * 
 * 本示例展示多继承可能带来的问题，以及Java如何通过接口来解决这些问题
 */
public class MultipleInheritanceDemo {
    
    // ========== 问题1: 菱形继承问题（Diamond Problem） ==========
    
    /**
     * 假设Java支持多继承，会出现这样的问题：
     * 
     * class A {
     *     void method() { System.out.println("A"); }
     * }
     * 
     * class B extends A {
     *     void method() { System.out.println("B"); }
     * }
     * 
     * class C extends A {
     *     void method() { System.out.println("C"); }
     * }
     * 
     * class D extends B, C {  // 如果支持多继承
     *     // 问题：D继承自B和C，B和C都继承自A，都重写了method()
     *     // 那么 D.method() 应该调用哪个？B的还是C的？
     * }
     * 
     * 这就是著名的"菱形继承问题"或"钻石问题"
     */
    
    // ========== 问题2: 方法调用歧义 ==========
    
    interface Animal {
        default void eat() {
            System.out.println("动物在吃东西");
        }
    }
    
    interface Mammal {
        default void eat() {
            System.out.println("哺乳动物在吃东西");
        }
    }
    
    /**
     * 即使使用接口默认方法，也会出现歧义
     * 但Java要求在实现类中必须明确指定使用哪个方法
     */
    static class Dog implements Animal, Mammal {
        // 必须重写方法来解决歧义
        @Override
        public void eat() {
            // 可以调用父接口的方法
            Animal.super.eat();  // 或者 Mammal.super.eat()
            System.out.println("狗在吃东西");
        }
    }
    
    // ========== Java的解决方案：接口 ==========
    
    /**
     * Java 通过接口来避免多继承的问题
     * 接口可以多实现，但只能定义：
     * 1. 抽象方法（Java 8之前）
     * 2. 默认方法（default method，Java 8+）
     * 3. 静态方法（static method，Java 8+）
     * 4. 常量（public static final）
     * 
     * 接口不能有实例字段（Java 9+可以有private static字段）
     * 这避免了状态继承的复杂性
     */
    
    interface Flyable {
        void fly();  // 抽象方法
        
        default void takeOff() {
            System.out.println("起飞");
        }
    }
    
    interface Swimmable {
        void swim();  // 抽象方法
        
        default void dive() {
            System.out.println("潜水");
        }
    }
    
    /**
     * 可以同时实现多个接口，这是Java的多实现机制
     * 这避免了类多继承的问题，因为接口：
     * 1. 没有实例字段（状态），避免了状态继承的复杂性
     * 2. 有默认方法冲突时，必须明确解决
     */
    static class Duck implements Flyable, Swimmable {
        @Override
        public void fly() {
            System.out.println("鸭子飞");
        }
        
        @Override
        public void swim() {
            System.out.println("鸭子游");
        }
    }
    
    // ========== 问题3: 构造器调用顺序问题 ==========
    
    /**
     * 如果支持多继承，构造器调用顺序会变得复杂：
     * 
     * class A {
     *     A() { System.out.println("A"); }
     * }
     * 
     * class B {
     *     B() { System.out.println("B"); }
     * }
     * 
     * class C extends A, B {
     *     C() {
     *         // 问题：应该先调用A的构造器还是B的构造器？
     *         // 如果A和B的构造器有依赖关系怎么办？
     *     }
     * }
     */
    
    // ========== 问题4: 成员变量冲突 ==========
    
    /**
     * 如果支持多继承，成员变量冲突问题：
     * 
     * class A {
     *     int value = 10;
     * }
     * 
     * class B {
     *     int value = 20;
     * }
     * 
     * class C extends A, B {
     *     // 问题：C继承了两个value，应该用哪个？
     * }
     */
    
    // ========== 演示 ==========
    
    public static void main(String[] args) {
        System.out.println("========== Java 为什么不支持多继承？ ==========\n");
        
        System.out.println("--- 问题1: 菱形继承问题 ---");
        System.out.println("如果类A被类B和C继承，类D又同时继承B和C，");
        System.out.println("当D调用B和C都重写的方法时，会产生歧义。\n");
        
        System.out.println("--- 问题2: 接口默认方法歧义（已解决） ---");
        Dog dog = new Dog();
        dog.eat();
        System.out.println("说明：即使接口有默认方法冲突，Java也要求明确解决\n");
        
        System.out.println("--- Java的解决方案：接口多实现 ---");
        Duck duck = new Duck();
        duck.fly();
        duck.swim();
        duck.takeOff();
        duck.dive();
        System.out.println("说明：接口可以多实现，避免了类多继承的问题\n");
        
        System.out.println("--- 总结 ---");
        System.out.println("Java不支持类多继承的原因：");
        System.out.println("1. 避免菱形继承问题");
        System.out.println("2. 避免方法调用歧义");
        System.out.println("3. 避免构造器调用顺序复杂性");
        System.out.println("4. 避免成员变量冲突");
        System.out.println("5. 简化继承模型，提高代码可维护性");
        System.out.println("\nJava的替代方案：");
        System.out.println("1. 类单继承 + 接口多实现");
        System.out.println("2. 组合优于继承的设计原则");
        System.out.println("3. 接口默认方法（Java 8+）提供部分实现");
    }
}


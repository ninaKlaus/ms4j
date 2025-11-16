package com.home.ms4j.core.base.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口多实现示例
 * 
 * 演示如何使用接口多实现来实现类的多种能力
 */
public class InterfaceMultipleImplementationDemo {


    
    // ========== 定义多个接口 ==========
    
    /**
     * 接口1：可飞行的能力
     */
    interface Flyable {
        void fly();
        
        default void takeOff() {
            System.out.println("准备起飞");
        }
        
        default void land() {
            System.out.println("准备降落");
        }
        
        // 获取飞行速度
        default int getFlySpeed() {
            return 50;  // 默认飞行速度
        }
    }
    
    /**
     * 接口2：可游泳的能力
     */
    interface Swimmable {
        void swim();
        
        default void dive() {
            System.out.println("开始潜水");
        }
        
        default void surface() {
            System.out.println("浮出水面");
        }
        
        default int getSwimSpeed() {
            return 10;  // 默认游泳速度
        }
    }
    
    /**
     * 接口3：可奔跑的能力
     */
    interface Runnable {
        void run();
        
        default void startRunning() {
            System.out.println("开始奔跑");
        }
        
        default void stopRunning() {
            System.out.println("停止奔跑");
        }
        
        default int getRunSpeed() {
            return 20;  // 默认奔跑速度
        }
    }
    
    /**
     * 接口4：可跳跃的能力
     */
    interface Jumpable {
        void jump();
        
        default void highJump() {
            System.out.println("高跳");
        }
    }
    
    /**
     * 接口5：可发声的能力
     */
    interface Speakable {
        void speak();
        
        default void makeSound() {
            System.out.println("发出声音");
        }
    }
    
    // ========== 实现多个接口的类 ==========
    
    /**
     * 鸭子：既会飞，又会游泳，还会叫
     */
    static class Duck implements Flyable, Swimmable, Speakable {
        private String name;
        
        public Duck(String name) {
            this.name = name;
        }
        
        @Override
        public void fly() {
            System.out.println(name + " 在天空中飞翔");
        }
        
        @Override
        public void swim() {
            System.out.println(name + " 在水中游泳");
        }
        
        @Override
        public void speak() {
            System.out.println(name + " 嘎嘎叫");
        }
        
        @Override
        public int getFlySpeed() {
            return 30;  // 鸭子飞得比较慢
        }
        
        @Override
        public int getSwimSpeed() {
            return 15;  // 鸭子游泳还不错
        }
    }
    
    /**
     * 狗：会跑，会跳，会叫
     */
    static class Dog implements Runnable, Jumpable, Speakable {
        private String name;
        
        public Dog(String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            System.out.println(name + " 在地上奔跑");
        }
        
        @Override
        public void jump() {
            System.out.println(name + " 跳跃");
        }
        
        @Override
        public void speak() {
            System.out.println(name + " 汪汪叫");
        }
        
        @Override
        public int getRunSpeed() {
            return 40;  // 狗跑得比较快
        }
    }
    
    /**
     * 天鹅：会飞，会游泳，会跑，会跳，会叫（全能选手）
     */
    static class Swan implements Flyable, Swimmable, Runnable, Jumpable, Speakable {
        private String name;
        
        public Swan(String name) {
            this.name = name;
        }
        
        @Override
        public void fly() {
            System.out.println(name + " 优雅地飞翔");
        }
        
        @Override
        public void swim() {
            System.out.println(name + " 优雅地游泳");
        }
        
        @Override
        public void run() {
            System.out.println(name + " 在地面上奔跑");
        }
        
        @Override
        public void jump() {
            System.out.println(name + " 跳跃");
        }
        
        @Override
        public void speak() {
            System.out.println(name + " 发出优雅的叫声");
        }
        
        @Override
        public int getFlySpeed() {
            return 60;  // 天鹅飞得比较快
        }
    }
    
    /**
     * 鱼：只会游泳
     */
    static class Fish implements Swimmable {
        private String name;
        
        public Fish(String name) {
            this.name = name;
        }
        
        @Override
        public void swim() {
            System.out.println(name + " 在水中游来游去");
        }
        
        @Override
        public int getSwimSpeed() {
            return 25;  // 鱼游得很快
        }
    }
    
    // ========== 接口多实现的优势演示 ==========
    
    /**
     * 多态性的应用：可以统一处理实现了同一接口的对象
     */
    static class Zoo {
        private List<Flyable> flyableAnimals = new ArrayList<>();
        private List<Swimmable> swimmableAnimals = new ArrayList<>();
        private List<Speakable> speakableAnimals = new ArrayList<>();
        
        public void addFlyableAnimal(Flyable animal) {
            flyableAnimals.add(animal);
        }
        
        public void addSwimmableAnimal(Swimmable animal) {
            swimmableAnimals.add(animal);
        }
        
        public void addSpeakableAnimal(Speakable animal) {
            speakableAnimals.add(animal);
        }
        
        public void letAllFly() {
            System.out.println("--- 所有能飞的动物 ---");
            for (Flyable animal : flyableAnimals) {
                animal.fly();
            }
        }
        
        public void letAllSwim() {
            System.out.println("--- 所有能游泳的动物 ---");
            for (Swimmable animal : swimmableAnimals) {
                animal.swim();
            }
        }
        
        public void letAllSpeak() {
            System.out.println("--- 所有能发声的动物 ---");
            for (Speakable animal : speakableAnimals) {
                animal.speak();
            }
        }
    }
    
    // ========== 接口默认方法冲突的解决 ==========
    
    interface InterfaceA {
        default void method() {
            System.out.println("InterfaceA 的方法");
        }
        
        default void commonMethod() {
            System.out.println("InterfaceA 的通用方法");
        }
    }
    
    interface InterfaceB {
        default void method() {
            System.out.println("InterfaceB 的方法");
        }
        
        default void commonMethod() {
            System.out.println("InterfaceB 的通用方法");
        }
    }
    
    /**
     * 当多个接口有相同的默认方法时，实现类必须重写来解决冲突
     */
    static class ConflictResolver implements InterfaceA, InterfaceB {
        // 必须重写 method() 来解决冲突
        
        @Override
        public void method() {
            // 方式1：调用其中一个接口的方法
            InterfaceA.super.method();
            
            // 方式2：实现自己的逻辑
            System.out.println("ConflictResolver 自己实现的方法");
        }
        
        @Override
        public void commonMethod() {
            // 可以同时调用两个接口的方法
            InterfaceA.super.commonMethod();
            InterfaceB.super.commonMethod();
        }
    }
    
    // ========== 接口标记（Marker Interface）和功能性接口的混合 ==========
    
    /**
     * 标记接口：没有方法的接口，只是标记某种能力
     */
    interface Pet {
        // 标记接口，表示是宠物
    }
    
    /**
     * 类可以实现多个不同类型的接口
     */
    static class PetDuck extends Duck implements Pet {
        public PetDuck(String name) {
            super(name);
        }
    }
    
    // ========== 演示方法 ==========
    
    public static void main(String[] args) {
        StringBuilder

        System.out.println("========== 接口多实现示例 ==========\n");
        
        // 1. 基本的多实现
        System.out.println("--- 演示1: 基本的多实现 ---");
        Duck duck = new Duck("唐老鸭");
        System.out.println("鸭子具有的能力：");
        duck.fly();
        duck.swim();
        duck.speak();
        System.out.println("飞行速度: " + duck.getFlySpeed());
        System.out.println("游泳速度: " + duck.getSwimSpeed());
        System.out.println();
        
        // 2. 不同的实现组合
        System.out.println("--- 演示2: 不同的实现组合 ---");
        Dog dog = new Dog("旺财");
        System.out.println("狗具有的能力：");
        dog.run();
        dog.jump();
        dog.speak();
        System.out.println("奔跑速度: " + dog.getRunSpeed());
        System.out.println();
        
        // 3. 实现多个接口
        System.out.println("--- 演示3: 实现多个接口 ---");
        Swan swan = new Swan("白天鹅");
        System.out.println("天鹅具有的能力：");
        swan.fly();
        swan.swim();
        swan.run();
        swan.jump();
        swan.speak();
        System.out.println();
        
        // 4. 多态性的应用
        System.out.println("--- 演示4: 多态性的应用 ---");
        Zoo zoo = new Zoo();
        
        Duck duck1 = new Duck("鸭子1");
        Swan swan1 = new Swan("天鹅1");
        
        // 可以按接口类型添加到不同的集合
        zoo.addFlyableAnimal(duck1);
        zoo.addFlyableAnimal(swan1);
        zoo.addSwimmableAnimal(duck1);
        zoo.addSwimmableAnimal(swan1);
        zoo.addSwimmableAnimal(new Fish("金鱼"));
        zoo.addSpeakableAnimal(duck1);
        zoo.addSpeakableAnimal(swan1);
        zoo.addSpeakableAnimal(new Dog("小狗"));
        
        zoo.letAllFly();
        System.out.println();
        zoo.letAllSwim();
        System.out.println();
        zoo.letAllSpeak();
        System.out.println();
        
        // 5. 接口默认方法冲突的解决
        System.out.println("--- 演示5: 接口默认方法冲突的解决 ---");
        ConflictResolver resolver = new ConflictResolver();
        resolver.method();
        resolver.commonMethod();
        System.out.println();
        
        // 6. 接口的多态性
        System.out.println("--- 演示6: 接口的多态性 ---");
        // 可以用接口类型引用实现类
        Flyable flyable = new Duck("会飞的鸭子");
        Swimmable swimmable = new Duck("会游泳的鸭子");
        Speakable speakable = new Duck("会叫的鸭子");
        
        flyable.fly();
        swimmable.swim();
        speakable.speak();
        System.out.println();
        
        // 7. 类型检查
        System.out.println("--- 演示7: instanceof 类型检查 ---");
        Duck myDuck = new Duck("测试鸭");
        System.out.println("myDuck instanceof Flyable: " + (myDuck instanceof Flyable));
        System.out.println("myDuck instanceof Swimmable: " + (myDuck instanceof Swimmable));
        System.out.println("myDuck instanceof Speakable: " + (myDuck instanceof Speakable));
        System.out.println("myDuck instanceof Runnable: " + (myDuck instanceof Runnable));
        System.out.println();
        
        // 8. 接口的组合使用
        System.out.println("--- 演示8: 接口的组合使用 ---");
        demonstrateInterfaceCombination();
        
        System.out.println("\n--- 总结 ---");
        System.out.println("接口多实现的优势：");
        System.out.println("1. 一个类可以实现多个接口，获得多种能力");
        System.out.println("2. 实现了代码的灵活性和可扩展性");
        System.out.println("3. 支持多态性，可以按接口类型统一处理");
        System.out.println("4. 避免了类多继承的复杂性");
        System.out.println("5. 接口隔离原则：按需实现，不强制实现不需要的方法");
    }
    
    /**
     * 演示接口的组合使用
     */
    private static void demonstrateInterfaceCombination() {
        System.out.println("创建一个既会飞又会游泳的天鹅：");
        Swan swan = new Swan("组合示例天鹅");
        
        // 可以作为不同的接口类型使用
        Flyable asFlyable = swan;
        Swimmable asSwimmable = swan;
        Runnable asRunnable = swan;
        
        System.out.println("作为 Flyable 使用：");
        asFlyable.fly();
        asFlyable.takeOff();
        asFlyable.land();
        
        System.out.println("\n作为 Swimmable 使用：");
        asSwimmable.swim();
        asSwimmable.dive();
        asSwimmable.surface();
        
        System.out.println("\n作为 Runnable 使用：");
        asRunnable.run();
        asRunnable.startRunning();
        asRunnable.stopRunning();
    }
}


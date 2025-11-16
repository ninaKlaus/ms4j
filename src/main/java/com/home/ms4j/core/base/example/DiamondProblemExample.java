package com.home.ms4j.core.base.example;

/**
 * 菱形继承问题（Diamond Problem）的详细示例
 * 
 * 这个示例用代码模拟了如果Java支持多继承会遇到的典型问题
 */
public class DiamondProblemExample {
    
    // ========== 模拟多继承的层级结构 ==========
    
    /**
     * 祖父类：动物
     */
    static class Animal {
        protected String name;
        
        public Animal(String name) {
            this.name = name;
            System.out.println("Animal 构造器: " + name);
        }
        
        public void breathe() {
            System.out.println(name + " 在呼吸");
        }
        
        public void makeSound() {
            System.out.println(name + " 发出声音");
        }
        
        public String getInfo() {
            return "我是动物: " + name;
        }
    }
    
    /**
     * 父类1：鸟类（继承自动物）
     */
    static class Bird extends Animal {
        public Bird(String name) {
            super(name);
            System.out.println("Bird 构造器: " + name);
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " 在鸣叫");
        }
        
        @Override
        public String getInfo() {
            return "我是鸟类: " + name;
        }
        
        public void fly() {
            System.out.println(name + " 在飞翔");
        }
    }
    
    /**
     * 父类2：哺乳动物类（继承自动物）
     */
    static class Mammal extends Animal {
        public Mammal(String name) {
            super(name);
            System.out.println("Mammal 构造器: " + name);
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " 在叫");
        }
        
        @Override
        public String getInfo() {
            return "我是哺乳动物: " + name;
        }
        
        public void feedMilk() {
            System.out.println(name + " 在哺乳");
        }
    }
    
    /**
     * 假设Java支持多继承，蝙蝠既像鸟又像哺乳动物
     * 问题：Bat 应该继承 Bird 和 Mammal，但两者都继承自 Animal
     * 
     * 如果允许这样做，会出现以下问题：
     * 
     * 问题1: 构造器调用顺序
     *   - Bat 需要调用 Bird 和 Mammal 的构造器
     *   - Bird 和 Mammal 都需要调用 Animal 的构造器
     *   - Animal 的构造器会被调用两次？还是一次？
     * 
     * 问题2: 方法调用歧义
     *   - Bat 调用 makeSound() 时，应该调用 Bird 的还是 Mammal 的？
     *   - Bat 调用 getInfo() 时，应该返回什么？
     * 
     * 问题3: 成员变量访问
     *   - Bird 和 Mammal 都有 name 字段（继承自 Animal）
     *   - Bat 访问 name 时，是哪个 name？
     */
    
    // ========== Java的实际解决方案 ==========
    
    /**
     * 使用组合 + 接口来解决多继承的需求
     * 
     * 这是Java推荐的"组合优于继承"的设计原则
     */
    interface Flyable {
        void fly();
    }
    
    interface MilkFeeder {
        void feedMilk();
    }
    
    /**
     * 蝙蝠类：使用组合和接口来实现多继承的功能
     * 这样避免了多继承的问题，同时获得了所需的功能
     */
    static class Bat {
        private Animal animal;      // 组合：包含动物
        private Flyable flyable;    // 组合：包含飞行能力
        private MilkFeeder feeder;  // 组合：包含哺乳能力
        
        public Bat(String name) {
            this.animal = new Animal(name);
            this.flyable = new Flyable() {
                @Override
                public void fly() {
                    System.out.println(animal.name + " 在飞翔（蝙蝠）");
                }
            };
            this.feeder = new MilkFeeder() {
                @Override
                public void feedMilk() {
                    System.out.println(animal.name + " 在哺乳（蝙蝠）");
                }
            };
        }
        
        // 委托方法
        public void breathe() {
            animal.breathe();
        }
        
        public void makeSound() {
            System.out.println(animal.name + " 发出超声波（蝙蝠）");
        }
        
        public void fly() {
            flyable.fly();
        }
        
        public void feedMilk() {
            feeder.feedMilk();
        }
        
        public String getInfo() {
            return "我是蝙蝠: " + animal.name;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("========== 菱形继承问题演示 ==========\n");
        
        System.out.println("--- 单继承的构造器调用 ---");
        System.out.println("创建 Bird:");
        Bird bird = new Bird("麻雀");
        bird.makeSound();
        System.out.println();
        
        System.out.println("创建 Mammal:");
        Mammal mammal = new Mammal("狗");
        mammal.makeSound();
        System.out.println();
        
        System.out.println("--- 假设的多继承问题 ---");
        System.out.println("如果 Bat 可以同时继承 Bird 和 Mammal：");
        System.out.println("1. 构造器调用：Animal 会被调用几次？");
        System.out.println("2. 方法调用：makeSound() 应该调用 Bird 的还是 Mammal 的？");
        System.out.println("3. 成员变量：name 字段有几个？应该访问哪个？");
        System.out.println();
        
        System.out.println("--- Java的解决方案：组合 + 接口 ---");
        System.out.println("创建 Bat（使用组合）:");
        Bat bat = new Bat("蝙蝠");
        bat.breathe();
        bat.makeSound();
        bat.fly();
        bat.feedMilk();
        System.out.println(bat.getInfo());
        System.out.println();
        
        System.out.println("--- 总结 ---");
        System.out.println("Java不支持多继承，通过以下方式解决需求：");
        System.out.println("1. 单继承：保持简单的继承链");
        System.out.println("2. 接口多实现：实现多个接口来获得多种能力");
        System.out.println("3. 组合优于继承：通过组合对象来复用功能");
        System.out.println("4. 明确的方法解析：避免歧义");
    }
}


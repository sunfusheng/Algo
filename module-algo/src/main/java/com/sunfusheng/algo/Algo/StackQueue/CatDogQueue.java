package com.sunfusheng.algo.Algo.StackQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 【题目】
 * 猫狗队列
 * <p>
 * 宠物、狗和猫的类如下：
 * 实现一种狗猫队列的结构，要求如下：
 * ● 用户可以调用add方法将cat类或dog类的实例放入队列中；
 * ● 用户可以调用pollAll方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
 * ● 用户可以调用pollCat方法，将队列中cat类的实例按照进队列的先后顺序依次弹出；
 * ● 用户可以调用pollDog方法，将队列中dog类的实例按照进队列的先后顺序依次弹出；
 * ● 用户可以调用isEmpty方法，检查队列中是否还有dog或cat的实例；
 * ● 用户可以调用isCatEmpty方法，检查队列中是否有cat类的实例。
 * ● 用户可以调用isDogEmpty方法，检查队列中是否有dog类的实例；
 *
 * @author sunfusheng
 * @since 2020/3/9
 */
public class CatDogQueue {

    // 宠物类
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    // 猫类
    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    // 狗类
    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    // 猫狗宠物类
    public static class CatDogPet {
        private Pet pet;
        private long timestamp;

        public CatDogPet(Pet pet, long timestamp) {
            this.pet = pet;
            this.timestamp = timestamp;
        }

        public Pet getPet() {
            return pet;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public String getPetType() {
            return pet.getType();
        }
    }

    private Queue<CatDogPet> catQ;
    private Queue<CatDogPet> dogQ;
    private int index; // 模拟进入队列的时间

    public CatDogQueue() {
        this.catQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
    }

    public void add(Pet pet) {
        if (pet.getType().equals("cat")) {
            catQ.add(new CatDogPet(pet, index++));
        } else if (pet.getType().equals("dog")) {
            dogQ.add(new CatDogPet(pet, index++));
        } else {
            throw new RuntimeException("err, not cat or dog!");
        }
    }

    public Pet pollAll() {
        if (!catQ.isEmpty() && !dogQ.isEmpty()) {
            if (catQ.peek().getTimestamp() < dogQ.peek().getTimestamp()) {
                return catQ.poll().getPet();
            } else {
                return dogQ.poll().getPet();
            }
        } else if (!catQ.isEmpty()) {
            return catQ.poll().getPet();
        } else if (!dogQ.isEmpty()) {
            return dogQ.poll().getPet();
        } else {
            throw new RuntimeException("Cat dog queue is empty!");
        }
    }

    public Pet pollCat() {
        if (!catQ.isEmpty()) {
            return catQ.poll().getPet();
        }
        throw new RuntimeException("Cat queue is empty!");
    }

    public Pet pollDog() {
        if (!dogQ.isEmpty()) {
            return dogQ.poll().getPet();
        }
        throw new RuntimeException("Dog queue is empty!");
    }

    public boolean isEmpty() {
        return catQ.isEmpty() && dogQ.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQ.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogQ.isEmpty();
    }
}

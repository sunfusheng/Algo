package com.sunfusheng.algo.common.model;

/**
 * @author sunfusheng
 * @since 2020-02-25
 */
public class Node {
    public int value;
    public Node next;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}
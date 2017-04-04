package com.chirag.test.accolite;

public class thread {
	private int value = 4;

    public int getValue() {
        return value;
    }

    public void changeVal(int value) {
        this.value = value;
    }

    public static void main(String args[]) {
        int a = 1;
        thread c = new thread();
        c.changeVal(a);
        System.out.print(c.getValue());
    }
}

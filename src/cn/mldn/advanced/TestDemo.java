package cn.mldn.advanced;

import java.util.Arrays;

class B {

    private String title;
    private Double price;

    public B(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

public class TestDemo {
    public static void main(String[] args) {
        String str = Arrays.toString(new int[] {1,2});
        System.out.println(str);

        for (int i = 0; i < 10; i++) {

        }

    }
}

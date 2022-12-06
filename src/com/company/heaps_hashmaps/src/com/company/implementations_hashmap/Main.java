package com.company.implementations_hashmap;

public class Main {
    public static void main(String[] args) {
        OpenAddressingQuadraticProbing<String, Integer> map = new OpenAddressingQuadraticProbing<>();


        System.out.println("Insertion....");
        map.insert("Divya", 99);
        map.insert("Elon", 92);
        map.insert("Kunal", 22);
        map.insert("Bill", 43);
        map.insert("Abc", 234);

        System.out.println(map);

        System.out.println("After deletion...");
        map.remove("Abc");
        System.out.println(map);
        System.out.println("Search...");
        System.out.println(map.get("Divya"));
    }
}

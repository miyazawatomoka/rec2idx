package cc.honoka;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args[0] == null || args[1] == null) {
            System.out.println("Need two params");
            System.out.println("First is rec file path, Second is target idx file path");
            return;
        }
        IdxGenerator idxg = new IdxGenerator(args[0], args[1]);
        idxg.generateIdxFile();
        System.out.println("Successful generate idx file at " + args[1]);
    }
}

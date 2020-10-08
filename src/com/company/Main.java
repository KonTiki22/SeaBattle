package com.company;

import java.util.Random;

public class Main {

    public static boolean placeShip(byte[][] field, int y, int x, int len) {
        byte l = 0, r = 0, u = 0, d = 0;
        for (int i = 0; i < len; i++) {
            if(x-len >= 0) l += field[y][x-i];
            else l = 1;
            if(x+len < field[0].length) r += field[y][x+i];
            else r = 1;
            if(y-len >= 0) u += field[y-i][x];
            else u = 1;
            if(y+len < field.length) d += field[y+i][x];
            else d = 1;
        }
        if(l>0 && r>0 && u>0 && d>0) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if(l == 0) {
                field[y][x-i] = 1;
                placeMargin(field, y, x-i);
            }
            else if(r == 0) {
                field[y][x+i] = 1;
                placeMargin(field, y, x+i);
            }
            else if(u == 0) {
                field[y-i][x] = 1;
                placeMargin(field, y-i, x);
            }
            else if(d == 0) {
                field[y+i][x] = 1;
                placeMargin(field, y+i, x);
            }
        }
        return true;
    }

    public static void placeMargin(byte[][] field, int y, int x) {
        if(x-1 >= 0 && field[y][x-1] == 0) field[y][x-1] = 2;
        if(x+1 < field[0].length && field[y][x+1] == 0) field[y][x+1] = 2;
        if(y-1 >= 0 && field[y-1][x] == 0) field[y-1][x] = 2;
        if(y+1 < field.length && field[y+1][x] == 0) field[y+1][x] = 2;
        if(x-1 >= 0 && y-1 >= 0 && field[y-1][x-1] == 0) field[y-1][x-1] = 2;
        if(x+1 < field[0].length && y-1 >= 0 && field[y-1][x+1] == 0) field[y-1][x+1] = 2;
        if(x+1 < field[0].length && y+1 < field.length && field[y+1][x+1] == 0) field[y+1][x+1] = 2;
        if(x-1 >= 0 && y+1 < field.length && field[y+1][x-1] == 0) field[y+1][x-1] = 2;
    }

    public static void main(String[] args) {
        byte[][] field = new byte[10][10];
        Random r = new Random();
        for(int i=4;i>=0;i--){
            for (int k = 0; k <= i; k++) {
                int y = r.nextInt(10);
                int x = r.nextInt(10);
                if(!placeShip(field, y, x, 4-i)) {
                    k--;
                    continue;
                }
            }
        }
        for (int i = 0; i < field.length; i++) {
            System.out.print("|");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print((field[i][j] == 1? "██" : "__")+"|");
            }
            System.out.print("\n");
        }
    }
}

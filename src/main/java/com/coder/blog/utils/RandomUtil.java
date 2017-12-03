package com.coder.blog.utils;

import java.util.Random;

public class RandomUtil {
	public static int creat(int min,int max) {
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
}

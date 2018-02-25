package com.adithya.bookstore.util;

import java.util.Random;

public class IsbnGenerator implements NumberGenerator {
    @Override
    public String generateNumber() {
        return "14-677-" + Math.abs(new Random().nextInt());
    }
}

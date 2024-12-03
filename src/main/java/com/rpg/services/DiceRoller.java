package com.rpg.services;

import java.util.Random;

public class DiceRoller {
    private static final Random random = new Random();

    public static int rollDice(int sides) {
        if (sides <= 0) {
            throw new IllegalArgumentException("Número de lados deve ser maior que 0.");
        }
        return random.nextInt(sides) + 1;
    }
}

package com.exam.cripto.algorithm.diffiehellman;

import com.exam.cripto.algorithm.math.CMath;

public class DiffieHellmanKeyProtocol {

    private final StringBuilder report;


    public DiffieHellmanKeyProtocol() throws Exception {
        report = new StringBuilder();
        report.append("Алгоритм Диффи — Хеллмана между двумя учасниками.\n");
        report.append("Предположим у нас есть два актёра Ак1 и Ак2.\n\n");
        report.append("Оба актёра знают некоторые числа g и p, доступные каждому:\n");
        long p = CMath.randomPrime(100, 1000);
        report.append("p - открытое простое число. p = ").append(p).append("\n");
        long g = CMath.firstPrimitiveRoot(p);
        report.append("g - первообразный корень по модулю р. g = ").append(g).append("\n\n");
        report.append("У каждого актёра есть свой секретный ключ:\n");
        long a = CMath.randomPrime(1000, 10000);
        report.append("a - секретный ключ Ак1. a = ").append(a).append("\n");
        long b = CMath.randomPrime(1000, 10000);
        report.append("b = секретный ключ Ак2. b = ").append(b).append("\n\n");
        report.append("Каждый актёр на основе своего секретного ключа генерирует открытый ключ по формуле:\n");
        long openA = CMath.modulo(g, a, p);
        report.append("A - открытый ключ Ак1. A = g ^ a mod p = ").append(openA).append("\n");
        long openB = CMath.modulo(g, b, p);
        report.append("B - открытый ключ Ак2. B = g ^ a mod p = ").append(openB).append("\n").append("\n");
        report.append("Актёры деляется открытими ключами.\n");
        long k1 = CMath.modulo(openB, a, p);
        report.append("Актёр Ак1 на основе имеющегося у него 'a' и " +
                        "полученного по сети ключа B актёра Ак2 вычисляет значение: k = B^a mod p = g^ab mod p: ")
                .append(k1).append("\n");
        long k2 = CMath.modulo(openA, b, p);
        report.append("Актёр Ак2 на основе имеющегося у него 'b' и " +
                        "полученного по сети ключа A актёра Ак1 вычисляет значение: k = A^b mod p = g^ab mod p: ")
                .append(k2).append("\n");
        report.append("Теперь актёры Ак1 и Ак2 могут использовать k в качестве секретного ключа.");
    }

    public String getReport() {
        return report.toString();
    }
}

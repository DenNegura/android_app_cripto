package com.exam.cripto.algorithm.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CMath {

    public static boolean isPrime(long x) {
        long num = (long) Math.sqrt(x);
        if (x == 1) {
            return false;
        }

        if (x == 2) {
            return true;
        }

        for (int i = 2; i <= num; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long randomPrime(long from, long to) throws Exception {
        if (from > to) {
            throw new Exception(String.format("Верхняя граница (%d) меньше нижней (%d)", to, from));
        }
        long randNum = randomRange(from, to);
        boolean isUp = randNum > to - from;

        if (isUp) {
            for (long i = randNum; i <= to; i++) {
                if (isPrime(i)) {
                    return i;
                }
            }
            for (long i = randNum; i >= from; i--) {
                if (isPrime(i)) {
                    return i;
                }
            }
        } else {
            for (long i = randNum; i >= from; i--) {
                if (isPrime(i)) {
                    return i;
                }
            }
            for (long i = randNum; i <= to; i++) {
                if (isPrime(i)) {
                    return i;
                }
            }
        }
        throw new Exception(String.format("Нет простого числа между [%d, %d]", from, to));
    }

    public static boolean isCoprime(long a, long b) {
        long num = Math.min(a, b);
        for (int i = 2; i <= num; i++) {
            if (a % i == 0 && b % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareToModulo(long a, long b, long mod) {
        long modA = (a % mod + mod) % mod;
        long modB = (b % mod + mod) % mod;
        return modA == modB;
    }

    public static long firstPrimitiveRoot(long x) {
        if (!isPrime(x)) {
            return -1;
        }
        long phi = x - 1;
        List<Long> factors = factorize(phi);
        for (long g = 2; g <= x; g++) {
            boolean isPrimitiveRoot = true;
            for (long factor : factors) {
                if (modulo(g, phi / factor, x) == 1) {
                    isPrimitiveRoot = false;
                    break;
                }
            }
            if (isPrimitiveRoot) {
                return g;
            }
        }
        return -1;
    }

    public static List<Long> factorize(long n) {
        List<Long> factors = new ArrayList<>();
        for (long i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }

    public static long randomRange(long from, long to) throws Exception {
        if (from > to) {
            throw new Exception(String.format("Верхняя граница (%d) меньше нижней (%d)", to, from));
        }
        return Math.abs(new Random().nextLong()) % (to - from) + from;
    }

    public static long modulo(long a, long b, long m) {
        if (m == 1) {
            return 0;
        }
        long result = 1;
        a = a % m;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % m;
            }
            b = b >> 1;
            a = (a * a) % m;
        }
        return result;
    }

    public static BigInteger nextPrime(long i) {
        i++;
        while (!isPrime(i)) {
            i++;
        }
        return BigInteger.valueOf(i);
    }

//    public static Long modularMultiplicativeInverse(long number, long modulo) {
//        Long nextNumber = modulo / number;
//        while (true) {
//            nextNumber++;
//            if (nextNumber.equals(number)) {
//                continue;
//            }
//            if ((nextNumber * number) % modulo == 1) {
//                return nextNumber;
//            }
//        }
//    }

    public static Long modularMultiplicativeInverse(long num, long mod) {
        long a = mod;
        long b = num;
        long x = 0;
        long y = 1;
        long lastX = 1;
        long lastY = 0;
        long temp;
        while (b != 0) {
            long q = a / b;
            long r = a % b;

            a = b;
            b = r;

            temp = x;
            x = lastX - q * x;
            lastX = temp;

            temp = y;
            y = lastY - q * y;
            lastY = temp;
        }
        if (lastY < 0) {
            lastY += mod;
        }
        return lastY;
    }
}

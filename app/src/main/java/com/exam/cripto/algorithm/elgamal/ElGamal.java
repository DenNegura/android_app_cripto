package com.exam.cripto.algorithm.elgamal;

import android.annotation.SuppressLint;

import com.exam.cripto.algorithm.math.CMath;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class ElGamal {

    private List<Long> openKey;

    private Long privateKey;

    private StringBuilder report;

    public ElGamal() {
        report = new StringBuilder("Схема Эль-Гамаля.\n");
    }

    public ElGamal(int message, int numFrom, int numTo) throws Exception {
        boolean totoEBene = false;
        CMath.randomRange(numFrom, numTo);
        while (!totoEBene) {
            try {
                report = new StringBuilder("Схема Эль-Гамаля.\n");
                generateKeys(numFrom, numTo);
                List<Long> codingMessage = coding(message);
                decoding(codingMessage);
                List<Long> singMessage = sign((long) message);
                totoEBene = checkSing((long) message, singMessage);
            } catch (Exception ignored) {
            }
        }
    }

    @SuppressLint("DefaultLocale")
    public void generateKeys(int numFrom, int numTo) throws Exception {
        report.append("\nГенерация ключей:\n");
        long p = CMath.randomPrime(numFrom, numTo);
        report.append(String.format("1) Генерируется случайное простое число p = %d, от %d до %d.\n", p, numFrom, numTo));
        long g = CMath.firstPrimitiveRoot(p);
        report.append(String.format("2) Выбирается целое число g — первообразный корень p (%d). g = %d\n", p, g));
        long x = CMath.randomRange(1, p - 2);
        report.append(String.format("3) Выбирается случайное целое число x такое, что (1 < x < p − 1 (%d)). x = %d\n", p - 1, x));
        long y = CMath.modulo(g, x, p);
        report.append(String.format("4) Вычисляется y = g ^ x mod p, y = %d ^ %d mod %d = %d\n", g, x, p, y));
        openKey = new ArrayList<>();
        openKey.add(p);
        openKey.add(g);
        openKey.add(y);
        report.append(String.format("5) Открытым ключом является (y, g, p) = (%d, %d, %d), закрытым ключом — число x (%d).\n", y, g, p, x));
        privateKey = x;
    }

    public List<Long> coding(long message) throws Exception {
        return coding(message, openKey);
    }

    @SuppressLint("DefaultLocale")
    public List<Long> coding(long message, List<Long> openKey) throws Exception {
        report.append(String.format("\nШифрование:\nСообщение m должно быть меньше числа p. m = %d < %d\n", message, openKey.get(0)));
        BigInteger p = BigInteger.valueOf(openKey.get(0));
        BigInteger g = BigInteger.valueOf(openKey.get(1));
        BigInteger y = BigInteger.valueOf(openKey.get(2));
        if (message < 1 || message > p.intValue()) {
            throw new Exception(String.format("Сообщение (%d) больше чем открытый ключ (%d)", message, p));
        }
        long k = CMath.randomRange(1, p.intValue() - 2);
        report.append(String.format("1) Выбирается сессионный ключ — случайное целое число, " +
                "взаимно простое с (p − 1), k k такое, что 1 < k < p − 1 (%d). k = %d\n", p.intValue() - 1, k));
        long a = CMath.modulo(g.longValue(), k, p.longValue());
        long b = y.pow((int) k).multiply(BigInteger.valueOf(message)).remainder(p).longValue();
        report.append(String.format("2) Вычисляются числа a = g ^ k mod p (%d ^ %d mod %d) = %d\n", g, k, p, a))
                .append(String.format("\tи b = y ^ k * M mod p (%d ^ %d * %d mod %d) = %d\n", y, k, message, p, b))
                .append(String.format("3) Пара чисел (a, b) (%d, %d) является шифротекстом.\n", a, b));
        List<Long> encryptedMessage = new ArrayList<>();
        encryptedMessage.add(a);
        encryptedMessage.add(b);
        return encryptedMessage;
    }

    public long decoding(List<Long> message) {
        return decoding(message, openKey, privateKey);
    }

    @SuppressLint("DefaultLocale")
    public long decoding(List<Long> message, List<Long> openKey, long privateKey) {
        report.append("\nРасшифрование:\n");
        BigInteger a = new BigInteger(String.valueOf(message.get(0)));
        BigInteger b = new BigInteger(String.valueOf(message.get(1)));
        BigInteger p = new BigInteger(String.valueOf(openKey.get(0)));
        BigInteger c = p.subtract(new BigInteger(String.valueOf(1 + privateKey)));
        BigInteger d = a.pow(c.intValue());
        long decodingMessage = b.multiply(d).remainder(p).longValue();
        report.append(String.format("Расшифрование по формуле: m = " +
                        "b * (a ^ x) ^ -1 mod p = %d * (%d ^ %d) ^ -1 mod %d = %d - исходное сообщение.",
                b.longValue(), a.longValue(), privateKey, p.longValue(), decodingMessage));
        return decodingMessage;
    }

    public List<Long> sign(Long message) throws Exception {
        long p = openKey.get(0);
        long g = openKey.get(1);
        long y = openKey.get(2);
        report.append("\nПодпись сообщения:\n");
        report.append("Имеем открытый ключ из пункта выше (p, g, y): (")
                .append(p).append(", ").append(g).append(", ").append(y).append(") и приватный ключ x = ")
                .append(privateKey).append("\n");
        report.append("1) Вычисляется дайджест сообщения M: m = h(M). Где h - любая хеш функция.\n");
        long digest = message.hashCode() % p;
        report.append("дайджест сообщения \"").append(message)
                .append("\" : m = h(M) = ").append(digest).append("\n");
        long k = CMath.randomRange(1, p);
        report.append("2) Выберем случайное целое число k такое, " +
                "что выполняется условие 1 < k < p − 1. Пусть k = ").append(k).append("\n");
        long r = CMath.modulo(g, k, p);
        report.append("и число r = g ^ k mod p = ").append(g).append(" ^ ")
                .append(k).append(" mod ").append(p).append(" = ").append(r).append("\n");
        long kInverse = CMath.modularMultiplicativeInverse(k, p - 1);
        report.append("3) Находим k^-1 - это" +
                " мультипликативное обратное k по модулю p - 1: k^-1 mod p = ").append(kInverse).append("\n");
        long s = (((digest - privateKey * r) * kInverse) % (p - 1) + (p - 1)) % (p - 1);
        report.append("4) Находим число s = (m − xr) k^−1 (mod p − 1) = (")
                .append(digest).append(" - ").append(privateKey).append(" * ")
                .append(r).append(") * ").append(kInverse).append(" (mod ").append(p).append(") = ").append(s).append("\n");
        report.append("5) Подписью сообщения M является пара (r, s) = (").append(r).append(", ").append(s).append(")\n");
        List<Long> keys = new ArrayList<>();
        keys.add(r);
        keys.add(s);
        return keys;
    }

    @SuppressLint("DefaultLocale")
    public boolean checkSing(Long message, List<Long> sign) {
        long p = openKey.get(0);
        long g = openKey.get(1);
        long y = openKey.get(2);
        long r = sign.get(0);
        long s = sign.get(1);
        report.append("\nПроверка подписи:\n");
        System.out.println(message.hashCode());
        long digest = message.hashCode() % p;
        report.append("1) Вычисляем хеш-функцию: h ( M ) = h (")
                .append(message).append(") = m = ").append(digest).append("\n");
        report.append("2) Проверяем равенство уровнения: y^r * r^s mod p = g^m mod p\n");
        long part1 = (CMath.modulo(y, r, p) * CMath.modulo(r, s, p)) % p;
        long part2 = CMath.modulo(g, digest, p);
        report.append(String.format("2.1) Вычислим левую часть: y^r * r^s mod p = %d^%d * %d^%d mod %d = %d\n",
                y, r, r, s, p, part1));
        report.append(String.format("2.2) Вычислим правую часть: g^m mod p = %d^%d mod %d = %d\n", g, digest, p, part2));
        if(part1 == part2) {
            report.append("Так как правая и левая части равны, то это означает что подпись верна.\n");
        } else {
            throw new RuntimeException("Непредвиденная ошибка, попробуйте ещё много много раз)");
        }
        return true;
    }

    public List<Long> getOpenKey() {
        return openKey;
    }

    public Long getPrivateKey() {
        return privateKey;
    }

    public StringBuilder getReport() {
        return report;
    }
}

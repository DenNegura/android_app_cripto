package com.exam.cripto.algorithm.rc4;

import android.annotation.SuppressLint;

public class RC4 {

    private byte[] S;

    private int x;

    private int y;

    private final static int BYTE_COUNT = 128;

    private final StringBuilder report;

    public RC4(String key, String message) {
        this(key);
        report.append("Потоковый шифр RC4.\n    Ключ: \"")
                .append(key).append("\" - от 8 до 2048 бит,\n    Сообщение: \"")
                .append(message).append("\" - ").append(toHex(message.getBytes())).append("\n\n");
        report.append("Инициализация RC4 состоит из двух частей:\n" +
                "инициализация S-блока;\n" +
                "генерация псевдослучайного слова K.\n" +

                "Для инициализации блока S применяется алгоритм:\n" +
                "Key-Scheduling Algorithm:\n" +
                "for i from 0 to 255:\n" +
                " S[i] = i\n" +
                "j = 0\n" +
                "for i from 0 to 255:\n" +
                "j = (j + S[i] + key[i % key.length]) % 256\n" +
                "поменять местами S[i] и S[j]\n" +

                "Генератор ключевого потока RC4 переставляет значения, хранящиеся в S.\n" +
                "В одном цикле RC4 определяется одно n-битное слово Ki из ключевого потока:\n" +
                "Pseudo-Random Generation Algorithm:\n" +
                "x = (x + 1) % 256\n" +
                "y = (y + S[x]) % 256\n" +
                "перемещение S[x] и S[y]\n" +
                "Возвращаемое значение S[(S[x] + S[y]) % 256]\n\n" +

                "Шифрование:\n" +
                "Затем последовательность K битов посредством операции xor объединяется с открытым текстом m.\n" +
                "В результате получается шифрограмма c:\n");

        StringBuilder encodingMessage = new StringBuilder();
        for (byte b : encodeString(message)) {
            encodingMessage.append((char) b);
        }
        report.append("Зашифрованное сообщение: ").append(toHex(encodingMessage.toString().getBytes()));
        init(key.getBytes());
        report.append("\n\n").append("Расшифровывание:\n" +
                "1. Повторно создаётся (регенерируется) поток битов ключа Ki\n" +
                "2. Поток битов ключа складывается с шифрограммой c операцией «xor».\n" +
                "На выходе получается исходный текст m:\n");
        String decodingMessage = decode(encodingMessage.toString().getBytes());
        report.append("Расшифрованное сообщение: ").append(decodingMessage);
    }

    public RC4(String key) {
        this(key.getBytes());
    }

    public RC4(byte[] key) {
        report = new StringBuilder();
        init(key);
    }

    private void init(byte[] key) {
        S = new byte[BYTE_COUNT];
        x = 0;
        y = 0;
        int keyLen = key.length;

        for (int i = 0; i < BYTE_COUNT; i++) {
            S[i] = (byte) i;
        }

        int j = 0;
        for (int i = 0; i < BYTE_COUNT; i++) {
            System.out.println("j = " + j + "S[i] = " + S[i] + " key[i % keyLen] = " + key[i % keyLen]);
            j = (j + S[i] + key[i % keyLen]) % BYTE_COUNT;
            System.out.println("i = " + i + " j = " + j);
            swap(i, j);
        }
    }

    private void swap(int i1, int i2) {
        byte el = S[i1];
        S[i1] = S[i2];
        S[i2] = el;
    }

    private byte keyItem() {
        x = (x + 1) % BYTE_COUNT;
        y = (y + S[x]) % BYTE_COUNT;

        swap(x, y);

        return S[(S[x] + S[y]) % BYTE_COUNT];
    }

    public byte[] encodeString(String message) {
        return encodeByte(message.getBytes());
    }

    @SuppressLint("DefaultLocale")
    public byte[] encodeByte(byte[] message) {
        byte[] data = message.clone();

        byte[] cipher = new byte[data.length];

        for (int m = 0; m < data.length; m++) {
            byte item = keyItem();
            cipher[m] = (byte) (data[m] ^ item);
            report.append(String.format("C[%d] = K[%d] ^ m[%d] : %s = %s ^ %s\n",
                    m, m, m, toHex(cipher[m]), toHex(data[m]), toHex(item)));
        }

        return cipher;
    }

    public String decode(byte[] message) {
        StringBuilder messageEncode = new StringBuilder();
        for (byte b : decodeToByte(message)) {
            messageEncode.append((char) b);
        }
        return messageEncode.toString();
    }

    public byte[] decodeToByte(byte[] message) {
        return encodeByte(message);
    }

    public String getReport() {
        return report.toString();
    }


    private String toHex(byte b) {
        return "0x" + Integer.toHexString(b);
    }

    private String toHex(byte[] b) {
        StringBuilder bites = new StringBuilder();
        for(byte i: b) {
            bites.append(toHex(i)).append(" ");
        }
        return bites.toString();
    }

}
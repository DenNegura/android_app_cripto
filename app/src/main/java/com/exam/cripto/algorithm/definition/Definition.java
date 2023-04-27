package com.exam.cripto.algorithm.definition;

public class Definition {

    private final StringBuilder symmetricCripting;
    private final StringBuilder asymmetricCripting;
    private final StringBuilder blockCripting;
    private final StringBuilder electronicSign;
    private final StringBuilder fluidCripting;
    private final StringBuilder hashCripting;

    public Definition() {
        hashCripting = new StringBuilder("Криптографическая хеш-система - это алгоритм, который преобразует  произвольный входной текст (сообщение) фиксированной длины в выходную  последовательность битов фиксированной длины, которая называется  хеш-значением. Хеш-функции используются для многих криптографических  задач, таких как контроль целостности данных, создание электронных  подписей, проверка паролей, аутентификация и т.д.");
        fluidCripting = new StringBuilder("Гибкие системы шифрования - это системы шифрования, которые могут  изменять свои параметры и ключи в зависимости от контекста или  требований безопасности данных. Они обеспечивают более высокий уровень  безопасности, поскольку атакующий не может заранее знать параметры  шифрования и ключи, используемые для защиты данных.");
        electronicSign = new StringBuilder("Электронная подпись (digital signature) - это криптографический метод,  который позволяет аутентифицировать и проверить целостность документа  или сообщения в электронном формате. Она используется для подтверждения  того, что документ был создан конкретным автором, и что он не был  изменен после создания.");
        blockCripting = new StringBuilder("Системы шифрования на основе блоков (Block ciphers) - это тип  криптографических алгоритмов, которые шифруют информацию блоками  фиксированного размера. Каждый блок данных обрабатывается независимо от  других блоков, и результат шифрования каждого блока зависит от ключа  шифрования и предыдущих шагов шифрования");
        asymmetricCripting = new StringBuilder("Асимметричная система шифрования (или криптография с открытым ключом) -  это система шифрования, которая использует пару ключей: открытый и  закрытый. Открытый ключ известен всем, кто хочет отправить зашифрованные  сообщения, а закрытый ключ является секретным и известен только  получателю сообщения");
        symmetricCripting = new StringBuilder("Системы симметричного шифрования - это системы шифрования, где один и  тот же секретный ключ используется для зашифрования и расшифрования  сообщений. В отличие от асимметричных систем шифрования, где  используются открытый и закрытый ключи, симметричные системы являются  более быстрыми и эффективными для шифрования больших объемов данных.");
    }

    public StringBuilder getSymmetricCripting() {
        return symmetricCripting;
    }

    public StringBuilder getAsymmetricCripting() {
        return asymmetricCripting;
    }

    public StringBuilder getBlockCripting() {
        return blockCripting;
    }

    public StringBuilder getElectronicSign() {
        return electronicSign;
    }

    public StringBuilder getFluidCripting() {
        return fluidCripting;
    }

    public StringBuilder getHashCripting() {
        return hashCripting;
    }
}

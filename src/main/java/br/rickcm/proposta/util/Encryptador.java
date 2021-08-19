package br.rickcm.proposta.util;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class Encryptador {

    TextEncryptor encryptor = Encryptors.text("s3cr3t", "5c0744940b5c369b");

    public String encrypt(String textopuro) {
        return encryptor.encrypt(textopuro);
    }

    public String decrypt(String dado) {
        return encryptor.decrypt(dado);
    }
}

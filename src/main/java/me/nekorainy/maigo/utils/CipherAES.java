package me.nekorainy.maigo.utils;
import me.nekorainy.maigo.MaiGoAPI;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CipherAES {
    private static final int BLOCK_SIZE = 128; // bits

    private static final byte[] AES_KEY;
    private static final byte[] AES_IV;

    static {
        String aesKey = MaiGoAPI.instance.getAes_key();
        String aesIv = MaiGoAPI.instance.getAes_iv();

        AES_KEY = parseKeyOrIv(aesKey);
        AES_IV = parseKeyOrIv(aesIv);
    }

    /**
     * 判断字符串是否是十六进制
     */
    private static boolean isHexString(String str) {
        return str.matches("^[0-9a-fA-F]+$");
    }

    /**
     * 转换配置中的 key/iv
     */
    private static byte[] parseKeyOrIv(String value) {
        if (isHexString(value)) {
            // hex 形式
            return hexStringToBytes(value);
        } else {
            // 普通字符串，先转 hex 再 decode
            String hex = bytesToHex(value.getBytes(StandardCharsets.UTF_8));
            return hexStringToBytes(hex);
        }
    }

    /**
     * AES CBC PKCS#7 加密
     */
    public static byte[] encrypt(byte[] plaintext) throws Exception {
        byte[] padded = pad(plaintext);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(AES_KEY, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(AES_IV);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(padded);
    }

    /**
     * AES CBC PKCS#7 解密
     */
    public static byte[] decrypt(byte[] ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(AES_KEY, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(AES_IV);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decrypted = cipher.doFinal(ciphertext);
        return unpad(decrypted);
    }

    /**
     * PKCS#7 padding
     */
    private static byte[] pad(byte[] data) {
        int blockSize = BLOCK_SIZE / 8;
        int paddingLength = blockSize - (data.length % blockSize);
        byte[] padded = Arrays.copyOf(data, data.length + paddingLength);
        Arrays.fill(padded, data.length, padded.length, (byte) paddingLength);
        return padded;
    }

    /**
     * 移除 PKCS#7 padding
     */
    private static byte[] unpad(byte[] paddedData) {
        int padChar = paddedData[paddedData.length - 1] & 0xFF;
        if (padChar < 1 || padChar > BLOCK_SIZE / 8) {
            throw new IllegalArgumentException("Invalid padding");
        }
        return Arrays.copyOf(paddedData, paddedData.length - padChar);
    }

    /**
     * hex -> byte[]
     */
    private static byte[] hexStringToBytes(String hex) {
        int len = hex.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Invalid hex string length");
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * byte[] -> hex
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
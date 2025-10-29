package me.nekorainy.maigo.network;

import me.nekorainy.maigo.MaiGoAPI;
import me.nekorainy.maigo.utils.CipherAES;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ShitPost {
    private static String titleServerUri = MaiGoAPI.instance.getTitle_server_url();

    public static String useApi(String data, String useApi, long userId) throws Exception {
        String api = useApi;
        if (!api.endsWith("MaimaiChn")) {
            api += "MaimaiChn";
        }

        String hashApi = APIObfuscator(api);

        byte[] plainBytes = data.getBytes(StandardCharsets.UTF_8);
        byte[] compressed = zlibCompress(plainBytes);
        byte[] encrypted = CipherAES.encrypt(compressed);

        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", hashApi + "#" + userId);
        headers.put("Content-Type", "application/json");
        headers.put("Mai-Encoding", "1.50");
        headers.put("Accept-Encoding", "");
        headers.put("Charset", "UTF-8");
        headers.put("Content-Encoding", "deflate");
        headers.put("Expect", "100-continue");

        String url = titleServerUri + hashApi;

        HttpClient.HttpResult httpResult = HttpClient.post(url, headers, encrypted, 2);

        if (httpResult.getStatusCode() != 200) {
            String bodyText = httpResult.getBody() != null ? new String(httpResult.getBody(), StandardCharsets.UTF_8) : "";
            throw new Exception("Response error: " + httpResult.getStatusCode() + "\n" + bodyText);
        }

        byte[] respBytes = httpResult.getBody();
        if (respBytes == null || respBytes.length == 0) {
            throw new Exception("Empty response body");
        }

        byte[] decrypted;
        try {
            decrypted = CipherAES.decrypt(respBytes);
        } catch (Exception e) {
            throw new Exception("AES decrypt failed: " + e.getMessage(), e);
        }

        byte[] decompressed;
        try {
            decompressed = zlibDecompress(decrypted);
        } catch (Exception e) {
            throw new Exception("Zlib decompression failed: " + e.getMessage(), e);
        }

        return new String(decompressed, StandardCharsets.UTF_8);
    }

    private static String APIObfuscator(String api) {
        try {
            String combined = api + MaiGoAPI.instance.getPacket_obfuscation_key();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(combined.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xFF));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }

    private static byte[] zlibCompress(byte[] input) {
        Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();

        byte[] buffer = new byte[1024];
        List<byte[]> chunks = new ArrayList<>();
        int totalLen = 0;

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            chunks.add(Arrays.copyOf(buffer, count));
            totalLen += count;
        }

        byte[] output = new byte[totalLen];
        int offset = 0;
        for (byte[] chunk : chunks) {
            System.arraycopy(chunk, 0, output, offset, chunk.length);
            offset += chunk.length;
        }
        return output;
    }

    private static byte[] zlibDecompress(byte[] input) throws IOException {
        Inflater inflater = new Inflater();
        inflater.setInput(input);

        byte[] buffer = new byte[1024];
        List<byte[]> chunks = new ArrayList<>();
        int totalLen = 0;

        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                if (count == 0 && inflater.needsInput()) break;
                chunks.add(Arrays.copyOf(buffer, count));
                totalLen += count;
            }
        } catch (Exception e) {
            throw new IOException("ZLIB 解压失败", e);
        }

        byte[] output = new byte[totalLen];
        int offset = 0;
        for (byte[] chunk : chunks) {
            System.arraycopy(chunk, 0, output, offset, chunk.length);
            offset += chunk.length;
        }
        return output;
    }
}

package me.nekorainy.maigo.network;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient {

    public static class HttpResult {
        private int statusCode;
        private Map<String, String> headers;
        private byte[] body;

        public HttpResult(int statusCode, Map<String, String> headers, byte[] body) {
            this.statusCode = statusCode;
            this.headers = headers;
            this.body = body;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public byte[] getBody() {
            return body;
        }
    }

    /**
     * 简单的 POST 方法
     *
     * @param urlStr   请求 URL
     * @param headers  请求头
     * @param body     请求体 (byte[])
     * @param timeout  超时时间 (秒)
     */
    public static HttpResult post(String urlStr,
                                  Map<String, String> headers,
                                  byte[] body,
                                  double timeout) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout((int) (timeout * 1000));
            conn.setReadTimeout((int) (timeout * 1000));
            conn.setDoOutput(true);

            // 设置请求头
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // 发送数据
            if (body != null && body.length > 0) {
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(body);
                }
            }

            int statusCode = conn.getResponseCode();

            // 读取响应头
            Map<String, String> responseHeaders = new HashMap<>();
            for (Map.Entry<String, List<String>> entry : conn.getHeaderFields().entrySet()) {
                if (entry.getKey() != null && !entry.getValue().isEmpty()) {
                    responseHeaders.put(entry.getKey(), entry.getValue().get(0));
                }
            }

            // 读取响应体
            InputStream inputStream;
            if (statusCode >= 200 && statusCode < 400) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }

            byte[] responseBody = new byte[0];
            if (inputStream != null) {
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                    }
                    responseBody = baos.toByteArray();
                }
            }

            return new HttpResult(statusCode, responseHeaders, responseBody);

        } catch (Exception e) {
            e.printStackTrace();
            return new HttpResult(500, new HashMap<>(), new byte[0]);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}

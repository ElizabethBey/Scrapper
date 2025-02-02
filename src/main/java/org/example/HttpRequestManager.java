package org.example;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class HttpRequestManager {
    public String sendRequest(String url) throws IOException {
        try {
            Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .timeout(5000)
                    .execute();

            if (response.contentType().contains("html")) {
                return response.body();
            } else {
                String errorDescription = "Получен ответ не HTML: " + response.contentType();
                ErrorLogger.logError(url, errorDescription);
                throw new IOException(errorDescription);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при обработке URL: " + url);
            ErrorLogger.logError(url, e.getMessage());
            throw new IOException("Ошибка при обработке URL: " + url, e);
        }
    }
}

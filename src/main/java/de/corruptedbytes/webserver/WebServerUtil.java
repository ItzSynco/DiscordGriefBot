package de.corruptedbytes.webserver;

import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

public class WebServerUtil {

	public static void sendFile(HttpExchange exchange, int statusCode, File file) throws IOException {
		sendResponse(exchange, statusCode, Files.readAllBytes(file.toPath()));
	}

	public static void sendResponse(HttpExchange exchange, int statusCode, byte[] bytes) throws IOException {
		exchange.sendResponseHeaders(statusCode, 0L);
		OutputStream responseBody = exchange.getResponseBody();
		responseBody.write(bytes);
		responseBody.close();
		exchange.close();
	}

	public static void redirect(HttpExchange exchange, String location) throws IOException {
		exchange.getResponseHeaders().add("Location", location);
		exchange.sendResponseHeaders(302, 0L);
		exchange.close();
	}

	public static String getQueryParam(HttpExchange httpExchange, String queryParam) {
		String query = httpExchange.getRequestURI().getQuery();
		if (query != null) {
			for (String part : query.split("&")) {
				String[] keyValue = part.split("=");
				if (keyValue.length > 1 && keyValue[0].equals(queryParam)) {
					return keyValue[1];
				}
			}
		}
		return null;
	}

	public static String getCookie(HttpExchange exchange, String key) {
		if (exchange.getRequestHeaders().get("Cookie") != null) {
			List<String> cookies = exchange.getRequestHeaders().get("Cookie");
			for (String cookie : cookies) {
				if (cookie.startsWith(key)) {
					return cookie.split("\\=")[1];
				}
			}
		}
		return "";
	}
}

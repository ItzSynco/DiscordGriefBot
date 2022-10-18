package de.corruptedbytes.webserver.webserverindexes.requests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.sun.net.httpserver.HttpServer;

import de.corruptedbytes.GriefBot;
import de.corruptedbytes.webserver.WebServerIndex;
import de.corruptedbytes.webserver.WebServerUtil;
import net.dv8tion.jda.api.entities.TextChannel;

public class RequestSend implements WebServerIndex {

	@Override
	public void index(HttpServer httpServer) throws IOException {
		httpServer.createContext("/requests/send", exchange -> {
			if (!exchange.getRequestMethod().equals("GET")) {
				exchange.close();
				return;
			}
			
			if (WebServerUtil.getQueryParam(exchange, "id") != null && WebServerUtil.getQueryParam(exchange, "message") != null) {
				String channelID = new String(Base64.getDecoder().decode(WebServerUtil.getQueryParam(exchange, "id")), StandardCharsets.UTF_8);
				String message = new String(Base64.getDecoder().decode(WebServerUtil.getQueryParam(exchange, "message")), StandardCharsets.UTF_8);
				
				TextChannel channel = GriefBot.getInstance().getBotManager().getTextChannelById(channelID);
				channel.sendMessage(message).queue();
				WebServerUtil.sendResponse(exchange, 200, ("OK").getBytes());
			}
		});
	}

}

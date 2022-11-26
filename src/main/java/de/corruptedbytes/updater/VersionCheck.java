package de.corruptedbytes.updater;

import java.io.IOException;

import org.json.JSONException;

import de.corruptedbytes.GriefBot;

public class VersionCheck extends GitHubAPI {
	
	public static void checkVersion() throws JSONException, IOException {
		VersionCheck versionCheck = new VersionCheck();
		
		if (versionCheck.isUpdateAviable()) {
			
			System.out.println("");
			System.out.println("!!! NEW VERSION AVIABLE !!!");
			System.out.println("Please restart!");
			System.out.println("");
			System.exit(0);
		}
	}
	
	public boolean isUpdateAviable() throws JSONException, IOException {
		return !GriefBot.getInstance().getVersion().equals(getLatestVersion());
	}
	
}

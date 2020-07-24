package com.namelessmc.bot.listeners;

import com.namelessmc.bot.Queries;
import com.namelessmc.bot.Main;
import com.namelessmc.bot.Utils;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildJoinHandler extends ListenerAdapter {

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Main.log("Joined guild: " + event.getGuild().getName());

        // If we dont have an API url for this guild, DM the owner
        String api_url = Queries.getGuildApiUrl(event.getGuild().getId());
        if (api_url == null) {
            if (Queries.newGuild(event.getGuild().getId(), event.getGuild().retrieveOwner().complete().getId())) {
                Utils.messageGuildOwner(event.getGuild().getIdLong(), "Hello! Thank you for using the NamelessMC Bot. To get started, reply with your API URL found in `StaffCP -> Configuration -> API`");
                Main.log("Sent new join message to " + event.getGuild().retrieveOwner().complete().getEffectiveName() + " for guild " + event.getGuild().getName());
            } else {
                Utils.messageGuildOwner(event.getGuild().getIdLong(), "Hello! Thank you for using the NamelessMC Bot. I tried to setup your guild, but failed. Please contact aberdeener#0001");
                Main.log("Could not set new guild " + event.getGuild().getId());
            }
        }
        // Else DM the owner that we do
        else {
            if (Utils.getApiFromString(api_url) == null) {
                // Error with their stored url. Make them update the url
                Utils.messageGuildOwner(event.getGuild().getIdLong(), "Hello! Thank you for using the NamelessMC Bot. It looks like you have already set your API URL, but it is no longer functional. Please reply with your API URL found in `StaffCP -> Configuration -> API`");
                Main.log("Sent update api url message to " + event.getGuild().retrieveOwner().complete().getEffectiveName() + " for guild " + event.getGuild().getName());
            } else {
                // Good to go
                Utils.messageGuildOwner(event.getGuild().getIdLong(), "Hello! Thank you for using the NamelessMC Bot. It looks like you have already set your API URL. Welcome back.");
                Main.log("Sent already complete message to " + event.getGuild().retrieveOwner().complete().getEffectiveName() + " for guild " + event.getGuild().getName());
            }
        }
    }

}

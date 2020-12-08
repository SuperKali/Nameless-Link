package com.namelessmc.bot.connections;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import com.namelessmc.java_api.NamelessAPI;

public abstract class ConnectionManager {

	public abstract Optional<NamelessAPI> getApi(long guildId) throws BackendStorageException;

	public abstract void newConnection(long guildId, URL apiUrl) throws BackendStorageException;

	public abstract boolean updateConnection(long guildId, URL apiUrl) throws BackendStorageException;

	public abstract boolean removeConnection(long guildId) throws BackendStorageException;

	public abstract List<URL> listConnections() throws BackendStorageException;

	public abstract Optional<Long> getLastUsed(long guildId) throws BackendStorageException;

}
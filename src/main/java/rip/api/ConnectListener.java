package rip.api;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.event.*;
import net.md_5.bungee.api.event.*;

public class ConnectListener implements Listener
{
    private SafeCore plugin;

    public ConnectListener(final SafeCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAccept(final ServerConnectedEvent event) {
        final ProxiedPlayer player = event.getPlayer();
        final String name = player.getName();
        final Server from = player.getServer();
        final Server to = event.getServer();
        if (from == null) {
            this.plugin.send("Connect", name, null, to.getInfo().getName(), to);
        }
        else {
            this.plugin.send("Switch", name, from.getInfo().getName(), to.getInfo().getName(), to);
        }
    }

    @EventHandler
    public void onLeave(final PlayerDisconnectEvent event) {
        final ProxiedPlayer player = event.getPlayer();
        final Server server = player.getServer();
        if (server == null) {
            return;
        }
        this.plugin.send("Disconnect", player.getName(), player.getServer().getInfo().getName(), null, server);
    }
}


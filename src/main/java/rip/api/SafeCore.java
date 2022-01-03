package rip.api;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.connection.*;
import java.io.*;

public class SafeCore extends Plugin
{
    public void onEnable() {
        this.getProxy().registerChannel("SafeCore");
        this.getProxy().getPluginManager().registerListener((Plugin)this, (Listener)new ConnectListener(this));
    }

    public void log(final String message) {
        this.getProxy().getConsole().sendMessage(message);
    }

    public void send(final String type, final String name, final String from, final String to, final Server server) {
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(data);
        try {
            out.writeUTF(type);
            out.writeUTF(name);
            if (from != null) {
                out.writeUTF(from);
            }
            if (to != null) {
                out.writeUTF(to);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        server.sendData("SafeCore", data.toByteArray());
    }
}

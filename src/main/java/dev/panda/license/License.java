package dev.panda.license;

import dev.panda.chat.ChatUtil;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Getter
public class License {

    private final String license;
    private final String ip;
    private final Plugin pluginClass;
    private final String apiKey;
    private final String server;
    private ErrorType errorType;

    private String buyer;
    private String generateDate;

    private boolean valid = false;
    private final boolean debug = false;

    public License(String license, String ip, Plugin pluginClass) {
        this.server = "http://license.pandacommunity.org:9999";
        this.license = license;
        this.ip = ip;
        this.pluginClass = pluginClass;
        this.apiKey = "TOPssjRDkpFMyuHYBFCfYtOczILxDgWZIIUttDGCPphMPZqnoZ";
    }

    public void correct() {
        ChatUtil.log(ChatUtil.NORMAL_LINE);
        ChatUtil.log("&aChecking license...");
        ChatUtil.log("&aValid License.");
        ChatUtil.log(" ");
        ChatUtil.log("&aThanks for purchase in Panda Community.");
        ChatUtil.log("&ahttps://discord.pandacommunity.org");
    }

    public void wrong() {
        ChatUtil.log(ChatUtil.NORMAL_LINE);
        ChatUtil.log("&aChecking license...");
        ChatUtil.log("&cInvalid License.");
        ChatUtil.log(" ");
        ChatUtil.log("&cJoin our discord server for support.");
        ChatUtil.log("&ahttps://discord.pandacommunity.org");
        ChatUtil.log(ChatUtil.NORMAL_LINE);
    }

    public void check() {
        try {
            String pluginName = pluginClass.getDescription().getName();
            URL url;
            try {
                url = new URL(server + "/api/check/request/licenses?keyAPI=" + apiKey + "&license=" + license + "&plugin=" + pluginName + "&ip=" + ip);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                valid = false;
                return;
            }
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }

            String response = builder.toString();

            if (response.equalsIgnoreCase("API_KEY_NOT_VALID")) {
                errorType = ErrorType.API_KEY_NOT_VALID;
            }
            else if (response.equalsIgnoreCase("INVALID_LICENSE")) {
                errorType = ErrorType.INVALID_LICENSE;
            }
            else if (response.equalsIgnoreCase("INVALID_PLUGIN_NAME")) {
                errorType = ErrorType.INVALID_PLUGIN_NAME;
            }
            else if (response.equalsIgnoreCase("INVALID_IP")) {
                errorType = ErrorType.INVALID_IP;
            }
            else if (response.equalsIgnoreCase("EXPIRED")) {
                errorType = ErrorType.EXPIRED;
            }
            else if (response.startsWith("VALID")) {
                errorType = ErrorType.VALID;
                valid = true;
                String[] split = response.split(";");
                this.buyer = split[1];
                this.generateDate = split[3];
            }
            else {
                errorType = ErrorType.PAGE_ERROR;
            }
        }
        catch (IOException e) {
            if (debug) {
                e.printStackTrace();
            }
            valid = false;
            errorType = ErrorType.PAGE_ERROR;
        }
    }

    public enum ErrorType {
        PAGE_ERROR,
        API_KEY_NOT_VALID,
        INVALID_LICENSE,
        INVALID_PLUGIN_NAME,
        INVALID_IP,
        EXPIRED,
        VALID
    }
}

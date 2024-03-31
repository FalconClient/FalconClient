package net.optifine;

import ir.albino.client.AlbinoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final boolean logDetail = System.getProperty("log.detail", "false").equals("true");

    public static void detail(String s)
    {
        if (logDetail && AlbinoClient.instance.debug)
        {
            LOGGER.info("[OptiFine] " + s);
        }
    }

    public static void dbg(String s)
    {
        if(AlbinoClient.instance.debug)
            LOGGER.info("[OptiFine] " + s);
    }

    public static void warn(String s)
    {
        if(AlbinoClient.instance.debug)
            LOGGER.warn("[OptiFine] " + s);
    }

    public static void warn(String s, Throwable t)
    {
        if(AlbinoClient.instance.debug)
            LOGGER.warn("[OptiFine] " + s, t);
    }

    public static void error(String s)
    {
        if(AlbinoClient.instance.debug)
            LOGGER.error("[OptiFine] " + s);
    }

    public static void error(String s, Throwable t)
    {
        if(AlbinoClient.instance.debug)
            LOGGER.error("[OptiFine] " + s, t);
    }

    public static void log(String s)
    {
        dbg(s);
    }
}

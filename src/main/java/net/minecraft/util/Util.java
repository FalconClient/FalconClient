package net.minecraft.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.logging.log4j.Logger;

public class Util
{
    public static Util.EnumOS getOSType()
    {
        final String s = System.getProperty("os.name").toLowerCase();
        return s.contains("win") ?
                Util.EnumOS.WINDOWS :
                (
                        s.contains("mac") ?
                        Util.EnumOS.OSX :
                                (
                                        s.contains("solaris") ?
                                                Util.EnumOS.SOLARIS :
                                                (
                                                        s.contains("sunos") ?
                                                        Util.EnumOS.SOLARIS :
                                                        (
                                                                s.contains("linux") ?
                                                                        Util.EnumOS.LINUX :
                                                                        (
                                                                                s.contains("unix") ?
                                                                                        Util.EnumOS.LINUX :
                                                                                        Util.EnumOS.UNKNOWN)))));
    }

    public static <V> void runTask(final FutureTask<V> task, final Logger logger)
    {
        try
        {
            task.run();
            task.get();
        }
        catch (final Exception e)
        {
            logger.fatal("Error executing task", e);

            if (e.getCause() instanceof OutOfMemoryError)
            {
                throw (OutOfMemoryError)e.getCause();
            }
        }
    }

    public enum EnumOS
    {
        LINUX,
        SOLARIS,
        WINDOWS,
        OSX,
        UNKNOWN;
    }
}

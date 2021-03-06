/*-
 * ========================LICENSE_START========================
 * StickyPiston
 * %%
 * Copyright (C) 2017 - 2018 Laxio
 * %%
 * This file is part of Piston, licensed under the MIT License (MIT).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * ========================LICENSE_END========================
 */
package org.laxio.piston.sticky.logging;

import org.fusesource.jansi.Ansi;
import org.laxio.piston.piston.versioning.PistonModule;
import org.laxio.piston.piston.versioning.PistonModuleType;
import org.laxio.piston.piston.versioning.Version;

import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.fusesource.jansi.Ansi.Attribute;
import static org.laxio.piston.piston.chat.ChatColor.*;

public class LogUtil {

    private static final Logger logger;
    private static final PrintStream out;

    static {
        BLACK.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLACK).boldOff().toString());
        DARK_BLUE.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLUE).boldOff().toString());
        DARK_GREEN.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.GREEN).boldOff().toString());
        DARK_AQUA.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.CYAN).boldOff().toString());
        DARK_RED.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.RED).boldOff().toString());
        DARK_PURPLE.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.MAGENTA).boldOff().toString());
        GOLD.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.YELLOW).boldOff().toString());
        GRAY.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.WHITE).boldOff().toString());
        DARK_GRAY.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLACK).boldOff().toString());
        BLUE.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.BLUE).boldOff().toString());
        GREEN.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.GREEN).boldOff().toString());
        AQUA.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.CYAN).boldOff().toString());
        RED.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.RED).boldOff().toString());
        LIGHT_PURPLE.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.MAGENTA).boldOff().toString());
        YELLOW.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.YELLOW).boldOff().toString());
        WHITE.setConsole(Ansi.ansi().a(Attribute.RESET).fg(Ansi.Color.WHITE).boldOff().toString());
        MAGIC.setConsole(Ansi.ansi().a(Attribute.BLINK_SLOW).toString());
        BOLD.setConsole(Ansi.ansi().bold().toString());
        STRIKETHROUGH.setConsole(Ansi.ansi().a(Attribute.STRIKETHROUGH_ON).toString());
        UNDERLINE.setConsole(Ansi.ansi().a(Attribute.UNDERLINE).toString());
        ITALIC.setConsole(Ansi.ansi().a(Attribute.ITALIC).toString());
        RESET.setConsole(Ansi.ansi().a(Attribute.RESET).toString());

        logger = Logger.getGlobal();

        out = System.out;
    }

    public static PrintStream getOut() {
        return out;
    }

    public static void init(Handler handle) {
        // set console reader

        // logger.addHandler(new ConsoleHandler());
        System.setOut(new PrintStream(new LoggerOutputStream(logger, Level.INFO), true));
        System.setErr(new PrintStream(new LoggerOutputStream(logger, Level.WARNING), true));

        logger.setUseParentHandlers(false);
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        logger.addHandler(handle);

        for (PistonModuleType type : PistonModuleType.values()) {
            PistonModule module = type.getModule();
            Version version = module.getVersion();

            String name = version.toString();
            if (version.getPrefix() != null && version.getPrefix().equalsIgnoreCase("git")) {
                name = version.toString(false);
                if (version.getSuffix() != null) {
                    name = name + "-" + version.getSuffix();
                }

                name = name + " (git)";
            }

            logger.info("Loaded " + module.getTitle() + " v" + name);
        }
    }

}

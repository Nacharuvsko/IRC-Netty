package xyz.winston.irc.server;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.server.core.Server;

/**
 * Server owo uwu >w<
 * @author winston
 */
public final class Bootstrap {

    /** Entry Point */
    public static void main(final String @NotNull [] args) {
        val srv = new Server(1337, "127.0.0.1", "zal00pa");
        srv.loadAndBind();
    }

}

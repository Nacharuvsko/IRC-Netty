package xyz.winston.irc.client.gui.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author winston 
 */
@RequiredArgsConstructor
@Getter
public final class PasswordContainer {

    private final String token;
    private final boolean using;

}

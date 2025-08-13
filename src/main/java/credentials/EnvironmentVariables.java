package credentials;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;

public class EnvironmentVariables {
    public static final Long connectTimeout = Long.valueOf(Objects.requireNonNull(Dotenv.load().get("CONNECT_TIMEOUT")));

}

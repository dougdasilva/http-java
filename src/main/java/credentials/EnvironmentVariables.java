package credentials;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;

public class EnvironmentVariables {

    private static Dotenv env;

    public static Long getConnectTimeout() {
        return Long.valueOf(Objects.requireNonNull(env.get("CONNECT_TIMEOUT")));
    }

}

package ua.core.data;

/*

HostSettings

Fields:

	host		String
	port		int
	user		String
	password	String

	// topic	String

options:

	immutable
	builder

*/

/**
 * Created with CodeCrank.io
 */
public class HostSettings {

    public static class Builder {

        private String host = "";
        private int port = 0;
        private String user = "";
        private String password = "";

        public Builder host(String host) {
            this.host = host; return this;
        }

        public Builder port(int port) {
            this.port = port; return this;
        }

        public Builder user(String user) {
            this.user = user; return this;
        }

        public Builder password(String password) {
            this.password = password; return this;
        }

        public HostSettings build() {
            return new HostSettings(this);
        }
    }

    public static Builder Builder() {
        return new Builder();
    }

    private final String host;
    private final int port;
    private final String user;
    private final String password;

    public HostSettings(String host, int port, String user, String password) {

        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    private HostSettings(Builder builder) {

        this.host = builder.host;
        this.port = builder.port;
        this.user = builder.user;
        this.password = builder.password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "HostSettings [" +
            "host=" + ((host != null) ? "\"" + host + "\"" : "null") +
            ", port=" + port +
            ", user=" + ((user != null) ? "\"" + user + "\"" : "null") +
            ", password=" + ((password != null) ? "\"" + password + "\"" : "null") +
            "]";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + port;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HostSettings other = (HostSettings) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (port != other.port)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
    
}
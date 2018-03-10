package ua.core.data;
import java.util.Objects;

import ua.core.utils.CollectionUtils;
import ua.core.utils.StringUtils;
/**
 * Created with CodeCrank.io
 */
public class Version implements ValueObject, Comparable<Version> {
	
	
	public static Version valueOf (String stringValue) {
		
		String versionItems[];
		Version version = null;

		if (StringUtils.isNotEmpty (stringValue)) {
			
			versionItems = stringValue.split ("\\.");
			
			if (CollectionUtils.size (versionItems) >= 3) {
				
				version = new Version (
						Integer.parseInt (versionItems[0]),
						Integer.parseInt (versionItems[1]),
						Integer.parseInt (versionItems[2])
						);
			}
		}
		
		return version;
	}
	

    private final int major;
    private final int minor;
    private final int revision;

    public Version(int major, int minor, int revision) {

        this.major = major;
        this.minor = minor;
        this.revision = revision;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getRevision() {
        return revision;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof Version)) return false;
        Version item = (Version)object;
        return major == item.major &&
                minor == item.minor &&
                revision == item.revision;
    }

    @Override
    public int hashCode() {
        return Objects.hash (major, minor, revision);
    }

    @Override
    public String toString() {
        return "Version [" +
                "major=" + major + ", " +
                "minor=" + minor + ", " +
                "revision=" + revision +
                "]";
    }

	@Override
	public int compareTo (Version item) {
		
		if ((major > item.major) ||
				(major == item.major && minor > item.minor) ||
				(major == item.major && minor == item.minor && revision > item.revision)) {
			
			return 1;
		}
		else if ((major < item.major) ||
				(major == item.major && minor < item.minor) ||
				(major == item.major && minor == item.minor && revision < item.revision)) {
		
			return -1;
		}
		else {
			
			return 0;
		}

	}

	@Override
	public String asString() {
		return major + "." + minor + "." + revision;
	}

}
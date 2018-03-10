package ua.core.data;

import java.time.LocalDate;

import ua.core.utils.ValidateUtils;

/**
 * Created with CodeCrank.io
 */
public class AppInfo {

    public static class Builder {

        private String name = null;
        private Version version = null;
        private LocalDate createdDate = null;
        private LocalDate buildDate = null;
        private int buildNumber = 0;
        private String commitName = null;
        private String branchName = null;

        public Builder name(String name) {
            this.name = name; return this;
        }

        public Builder version(Version version) {
            this.version = version; return this;
        }

        public Builder createdDate(LocalDate createdDate) {
            this.createdDate = createdDate; return this;
        }

        public Builder buildDate(LocalDate buildDate) {
            this.buildDate = buildDate; return this;
        }

        public Builder buildNumber(int buildNumber) {
            this.buildNumber = buildNumber; return this;
        }

        public Builder commitName(String commitName) {
            this.commitName = commitName; return this;
        }

        public Builder branchName(String branchName) {
            this.branchName = branchName; return this;
        }

        public AppInfo build() {
            return new AppInfo(this);
        }
    }

    public static Builder Builder() {
        return new Builder();
    }

    private final String name;
    private final Version version;
    private final LocalDate createdDate;
    private final LocalDate buildDate;
    private final int buildNumber;
    private final String commitName;
    private final String branchName;

    public AppInfo(String name, Version version, LocalDate createdDate, LocalDate buildDate, int buildNumber, String commitName, String branchName) {

		ValidateUtils.notNull (name, "name");
		ValidateUtils.notNull (version, "version");
		ValidateUtils.notNull (createdDate, "createdDate");
		ValidateUtils.notNull (buildDate, "buildDate");

        this.name = name;
        this.version = version;
        this.createdDate = createdDate;
        this.buildDate = buildDate;
        this.buildNumber = buildNumber;
        this.commitName = commitName;
        this.branchName = branchName;
    }

    private AppInfo(Builder builder) {

        this.name = builder.name;
        this.version = builder.version;
        this.createdDate = builder.createdDate;
        this.buildDate = builder.buildDate;
        this.buildNumber = builder.buildNumber;
        this.commitName = builder.commitName;
        this.branchName = builder.branchName;

		ValidateUtils.notNull (name, "name");
		ValidateUtils.notNull (version, "version");
		ValidateUtils.notNull (createdDate, "createdDate");
		ValidateUtils.notNull (buildDate, "buildDate");
    }

    public String getName() {
        return name;
    }

    public Version getVersion() {
        return version;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getBuildDate() {
        return buildDate;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public String getCommitName() {
        return commitName;
    }

    public String getBranchName() {
        return branchName;
    }

    @Override
    public String toString() {
        return "AppInfo [" +
                "name=" + ((name != null) ? "\"" + name + "\"" : "null") + ", " +
                "createdDate=" + ((createdDate != null) ? createdDate.toString() : "null") + ", " +
                "buildDate=" + ((buildDate != null) ? buildDate.toString() : "null") + ", " +
                "buildNumber=" + buildNumber + ", " +
                "commitName=" + ((commitName != null) ? "\"" + commitName + "\"" : "null") + ", " +
                "branchName=" + ((branchName != null) ? "\"" + branchName + "\"" : "null") +
                "]";
    }
}
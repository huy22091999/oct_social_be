package vn.oceantech.mita.dto;

public class VersionDto {
    String versionName;

    public VersionDto() {
    }

    public VersionDto(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}

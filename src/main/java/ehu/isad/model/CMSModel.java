package ehu.isad.model;

public class CMSModel {

    private String URL;
    private String version;
    private String md5;

    public CMSModel(String URL, String version, String md5) {
        this.URL=URL;
        this.version=version;
        this.md5=md5;
    }

    public String getURL() {
        return URL;
    }

    public String getVersion() {
        return version;
    }

    public String getMd5() {
        return md5;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}

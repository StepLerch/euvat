package enums;

public enum SiteMap {
    VAT_JSONVAT_COM("http://jsonvat.com/");

    SiteMap(String url){
        this.url = url;
    }
    private String url;

    public String getUrl() {
        return url;
    }
}

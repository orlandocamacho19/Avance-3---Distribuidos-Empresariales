package mainPackage;

public class datos {

    static datos singletonInstance;
    private String json;

    private datos() {
    }

    public static datos getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new datos();
        }

        return singletonInstance;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }
}

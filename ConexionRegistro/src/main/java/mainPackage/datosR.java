package mainPackage;

public class datosR {

    static datosR singletonInstance;
    private String json;

    private datosR() {
    }

    public static datosR getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new datosR();
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

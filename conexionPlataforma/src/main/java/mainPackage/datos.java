/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainPackage;

/**
 *
 * @author orlandocamacho
 */
public class datos {
    static datos singletonInstance;
    private String json;

    private datos() {
    }
    
    public static datos getInstance(){
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

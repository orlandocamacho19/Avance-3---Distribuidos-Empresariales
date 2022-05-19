/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.mongodb.*;
import mainPackage.datos;
import mainPackage.iDatos;

/**
 *
 * @author orlandocamacho
 */
public class DatosDAO implements iDatos{
    
    @Override
    public String enviaDatos() {
        return datos.getInstance().getJson();
    }   
    
    public void insertarDatos() {
        MongoClient mongo = new MongoClient("LocalHost", 27017);
        String datos = enviaDatos();
        DB db = mongo.getDB("EscuelaServicio");
        subirColeccion(db, datos, "Datos");
    }
    
    public void subirColeccion(DB db, String datos, String coleccion) {
        
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject doc = new BasicDBObject();
        doc.put("Materia", datos);
        colec.drop();
        colec.insert(doc);
    }

}

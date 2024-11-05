package com.project;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class DaoSoftware implements Dao<ObjSoftware> {
    private void writeList(ArrayList<ObjSoftware> llista) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (ObjSoftware Software : llista) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", Software.getId());
                jsonObject.put("nom", Software.getNom());
                jsonObject.put("any", Software.getAny());
                JSONArray jsonLlenguatges = new JSONArray(Software.getLlenguatges());
                jsonObject.put("llenguatges", jsonLlenguatges);
                jsonArray.put(jsonObject);
            }
            PrintWriter out = new PrintWriter(Main.softwarePath);
            out.write(jsonArray.toString(4)); // 4 es l'espaiat
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int getPosition (int id) {
        int result = -1;
        ArrayList<ObjSoftware> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            ObjSoftware Software = llista.get(cnt);
            if (Software.getId() == id) {
                result = cnt;
                break;
            }
        }
        return result;
    }

    @Override
    public void add(ObjSoftware Software){
        ArrayList<ObjSoftware> llista = getAll();
        ObjSoftware item = get(Software.getId());
        if (item == null) {
            llista.add(Software);
            writeList(llista);
        }


    }


    @Override
    public ObjSoftware get(int id) {
        ObjSoftware result = null;
        ArrayList<ObjSoftware> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            result = llista.get(pos);
        }
        return result;
    }

    @Override
    public ArrayList<ObjSoftware> getAll() {
        ArrayList<ObjSoftware> result = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(Main.softwarePath)));
            
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String nom = jsonObject.getString("nom");
                int any = jsonObject.getInt("any");
                JSONArray jsonLlenguatges = jsonObject.getJSONArray("llenguatges");
                ArrayList<Integer> llenguatges = new ArrayList<>();
                for (int j = 0; j < jsonLlenguatges.length(); j++) {
                    llenguatges.add(jsonLlenguatges.getInt(j));
                }
                ObjSoftware Software = new ObjSoftware(id, nom, any, llenguatges);
                result.add(Software);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    


    @Override
    public void setAny(int id,int any){
        ArrayList<ObjSoftware> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            ObjSoftware Software = llista.get(pos);
            Software.setAny(any);
            writeList(llista);
        }
    }

    @Override
    public void setNom(int id,String nom){
        ArrayList<ObjSoftware> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            ObjSoftware Software = llista.get(pos);
            Software.setNom(nom);
            writeList(llista);
        }
    }

    public void setLlenguatgesAdd(int id,int idLlenguatge){
        ArrayList<ObjSoftware> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            ObjSoftware Software = llista.get(pos);
            ArrayList<Integer> llenguatges = Software.getLlenguatges();
            llenguatges.add(idLlenguatge);
            Software.setLlenguatges(llenguatges);
            writeList(llista);
        }

    }

    public void setLlenguatgesDelete(int id,int idLlenguatge){
        ArrayList<ObjSoftware> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            ObjSoftware Software = llista.get(pos);
            ArrayList<Integer> llenguatges = Software.getLlenguatges();
            llenguatges.remove(Integer.valueOf(idLlenguatge));
            Software.setLlenguatges(llenguatges);
            writeList(llista);
        }

    }




    @Override
    public void update(int id, ObjSoftware Software) {
        ArrayList<ObjSoftware> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.set(pos, Software);
            writeList(llista);
        }
    }

    @Override
    public void delete(int id) {
        ArrayList<ObjSoftware> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.remove(pos);
            writeList(llista);
        }
    }

    @Override
    public void print () {
        ArrayList<ObjSoftware> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            System.out.println("    " + llista.get(cnt));
        }
    }
}
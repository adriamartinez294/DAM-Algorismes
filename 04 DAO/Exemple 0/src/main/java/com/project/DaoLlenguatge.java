package com.project;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DaoLlenguatge implements Dao<ObjSoftware> {

    private void writeList(ArrayList<ObjSoftware> llista) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (ObjSoftware curs : llista) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", curs.getId());
                jsonObject.put("nom", curs.toString());
                jsonArray.put(jsonObject);
            }

            PrintWriter out = new PrintWriter(MainDao.cursosPath);
            out.write(jsonArray.toString(4));  // 4 es l'espaiat
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getPosition(int id) {
        int result = -1;
        ArrayList<ObjSoftware> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            ObjSoftware curs = llista.get(cnt);
            if (curs.getId() == id) {
                result = cnt;
                break;
            }
        }
        return result;
    }

    @Override
    public void add(ObjSoftware curs) {
        ArrayList<ObjSoftware> llista = getAll();
        ObjSoftware item = get(curs.getId());
        if (item == null) {
            llista.add(curs);
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
            String content = new String(Files.readAllBytes(Paths.get(MainDao.cursosPath)));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String nom = jsonObject.getString("nom");
                result.add(new ObjSoftware(id, nom));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(int id, ObjSoftware curs) {
        ArrayList<ObjSoftware> llista = getAll();
        int pos = getPosition(id);
        if (pos != -1) {
            llista.set(pos, curs);
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
    public void print() {
        ArrayList<ObjSoftware> llista = getAll();
        for (int cnt = 0; cnt < llista.size(); cnt = cnt + 1) {
            System.out.println("    " + llista.get(cnt));
        }
    }
}

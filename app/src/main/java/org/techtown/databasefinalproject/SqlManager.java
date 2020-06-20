
package org.techtown.databasefinalproject;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.techtown.databasefinalproject.Model.Animal;
import org.techtown.databasefinalproject.Model.Location;
import org.techtown.databasefinalproject.Model.Plant;
import org.techtown.databasefinalproject.Model.Query;
import org.techtown.databasefinalproject.Model.Tool;
import org.techtown.databasefinalproject.Model.Vacation;

import java.util.ArrayList;

public class SqlManager {
    private static SQLiteDatabase database = null;
    private final String TABLE_VACATION = "vacation";
    private final String DATABASE_NAME = "MyDatabase.db";
    private Context context;

    public void createDatabase(Context context) {
        if(database == null) database = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
    }

    public void createTable() {
        if(database == null) {
            Log.d("SQLiteError", "plz make database first");
            return;
        }

        database.execSQL(Query.tableCreateQuery);

    }


    public ArrayList<Plant> executeQueryForPlant(String query) {
        ArrayList<Plant> plants = new ArrayList<>();
        Plant plant;

        Cursor cursor = database.rawQuery(query, null);

        int recordCount = cursor.getCount();

        for(int i =0; i < recordCount; i++) {
            cursor.moveToNext();
            plant = new Plant();
            plant.setName(cursor.getString(0));
            plant.setSpecies(cursor.getString(1));
            plant.setDescription(cursor.getString(2));

            plants.add(plant);
        }

        return plants;
    }

    public ArrayList<Animal> executeQueryForAnimal(String query) {
        ArrayList<Animal> animals = new ArrayList<>();
        Animal animal;

        Cursor cursor = database.rawQuery(query, null);

        int recordCount = cursor.getCount();

        for(int i =0; i < recordCount; i++) {
            cursor.moveToNext();
            animal = new Animal();
            animal.setName(cursor.getString(0));
            animal.setSpecies(cursor.getString(1));
            animal.setDescription(cursor.getString(2));

            animals.add(animal);
        }

        return animals;
    }

    public ArrayList<Vacation> executeQueryForVacation(String query) {
        ArrayList<Vacation> vacations = new ArrayList<>();
        Vacation vacation;

        Cursor cursor = database.rawQuery(query, null);

        int recordCount = cursor.getCount();

        for(int i =0; i < recordCount; i++) {
            cursor.moveToNext();
            vacation = new Vacation();
            vacation.setSpotName(cursor.getString(0));
            vacation.setPhoneNumber(cursor.getString(1));
            vacation.setDescription(cursor.getString(2));

            vacations.add(vacation);
        }

        return vacations;
    }

    public ArrayList<Location> executeQueryForLocation(String query) {
        ArrayList<Location> locations = new ArrayList<>();
        Location location;

        Cursor cursor = database.rawQuery(query, null);

        int recordCount = cursor.getCount();

        for(int i =0; i < recordCount; i++) {
            cursor.moveToNext();
            location = new Location();
            location.setProvince(cursor.getString(1));
            location.setTown(cursor.getString(2));
            location.setCity(cursor.getString(3));

            locations.add(location);
        }

        return locations;
    }

    public ArrayList<Tool> executeQueryForTool(String query) {
        ArrayList<Tool> tools = new ArrayList<>();
        Tool tool;

        Cursor cursor = database.rawQuery(query, null);

        int recordCount = cursor.getCount();

        for(int i =0; i < recordCount; i++) {
            cursor.moveToNext();
            tool = new Tool();
            tool.setToolName(cursor.getString(0));
            tool.setDescription(cursor.getString(1));

            tools.add(tool);
        }

        return tools;
    }
}


package org.techtown.databasefinalproject;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.techtown.databasefinalproject.Model.Plant;
import org.techtown.databasefinalproject.Model.Query;

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


    public ArrayList<Plant> executeQuery(String query) {
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

}

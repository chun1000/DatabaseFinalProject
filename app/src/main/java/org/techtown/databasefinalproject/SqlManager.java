
package org.techtown.databasefinalproject;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.techtown.databasefinalproject.Model.Query;

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



    /*

    public ArrayList<ChatRoomModel> executeQuery() {
        ArrayList<ChatRoomModel> chatListDataModels = new ArrayList<>();
        ChatRoomModel chatListDataModel;

        Cursor cursor = database.rawQuery(
                "select * from " + CHAT_LIST_TABLE_NAME
                , null
        );

        int recordCount = cursor.getCount();

        for(int i =0; i < recordCount; i++) {
            cursor.moveToNext();
            chatListDataModel = new ChatRoomModel();
            chatListDataModel.opponentId = cursor.getString(0);
            chatListDataModel.opponentName = cursor.getString(1);
            chatListDataModel.lastDate = cursor.getString(2);
            chatListDataModel.lastChat = cursor.getString(3);
            chatListDataModel.pictureUrl = cursor.getString(4);
            chatListDataModel.myId = cursor.getString(5);

            chatListDataModels.add(chatListDataModel);
        }

        return chatListDataModels;
    }

    public void makeDummyChatList(Context context) {
        ChatListSqlManager chatListSqlManager = new ChatListSqlManager();
        chatListSqlManager.createDatabase(context);
        chatListSqlManager.createTable();
        ChatRoomModel chatRoomModel = new ChatRoomModel();
        chatListSqlManager.insertRecord(chatRoomModel);
    }
    */
}

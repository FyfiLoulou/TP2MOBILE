package naji.ecole.TP23.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnexionBD {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_ADRESSE, MySQLiteHelper.COLUMN_PHONE };

    public ConnexionBD(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Info createInfo(String adresse, String phone) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ADRESSE, adresse);
        values.put(MySQLiteHelper.COLUMN_PHONE, phone);
        long insertId = database.insert(MySQLiteHelper.TABLE_INFO, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_INFO, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Info newInfo = cursorToInfo(cursor);
        cursor.close();
        return newInfo;
    }

    public void deleteInfo(Info info) {
        long id = info.getId();
        database.delete(MySQLiteHelper.TABLE_INFO, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Info> getAllInfos() {
        List<Info> infos = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_INFO, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            infos.add(cursorToInfo(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return infos;
    }

    public void clear() {
        getAllInfos().forEach(this::deleteInfo);
    }

    private Info cursorToInfo(Cursor cursor) {
        Info info = new Info();
        info.setId(cursor.getLong(0));
        info.setAdresse(cursor.getString(1));
        info.setPhone(cursor.getString(2));
        return info;
    }
}

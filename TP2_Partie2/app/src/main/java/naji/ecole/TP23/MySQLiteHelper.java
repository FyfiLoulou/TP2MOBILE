package naji.ecole.TP23;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_INFO = "info";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_ADRESSE = "adresse";
    public static final String COLUMN_CODEPOSTAL = "codepostal";
    public static final String COLUMN_TELEPHONE = "telephone";

    private static final String DATABASE_NAME = "bd.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_INFO + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOM + " TEXT NOT NULL, "
            + COLUMN_PRENOM + " TEXT NOT NULL, "
            + COLUMN_ADRESSE + " TEXT NOT NULL, "
            + COLUMN_CODEPOSTAL + " TEXT NOT NULL, "
            + COLUMN_TELEPHONE + " TEXT NOT NULL"
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), oldVersion+"->"+newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);
        onCreate(db);
    }
}

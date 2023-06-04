package quannkph29999.fpoly.danh_ba_content_provider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "APP_DANH_BA", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String bangdanhba = "CREATE TABLE danhba(id_DB INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten_DB text NOT NULL,sdt_DB text NOT NULL UNIQUE )";
        db.execSQL(bangdanhba);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package quannkph29999.fpoly.danh_ba_content_provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAO {
    private DBHelper dbHelper;


    public  DAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<DanhBa> GetDB() {
        ArrayList<DanhBa> listdb = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM danhba", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                DanhBa danhBa = new DanhBa();
                danhBa.setId(Integer.parseInt(cursor.getString(0)));
                danhBa.setTen_DB(cursor.getString(1));
                danhBa.setSdt_DB(cursor.getString(2));
                listdb.add(danhBa);
            } while (cursor.moveToNext());
        }
        return listdb;
    }

   public long ThemDB(DanhBa danhBa){
       SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put("ten_DB",danhBa.getTen_DB());
       contentValues.put("sdt_DB",danhBa.getSdt_DB());

       return sqLiteDatabase.insert("danhba",null,contentValues);
   }
}

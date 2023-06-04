package quannkph29999.fpoly.danh_ba_content_provider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 1;
    DAO dao;
    RecyclerView recyclerView;
    AdapterDB adapterDB;
    ArrayList<DanhBa> listdb;
    Button dbdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyc);
        dbdb = findViewById(R.id.dong_bo_db);
        reloaddata();
        dbdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDbList();
            }
        });
    }

    public void GetDbList() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    PERMISSION_REQUEST_READ_CONTACTS);
        } else {
            listdb = new ArrayList<>();
            ContentResolver contentResolver = getContentResolver();
            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            String[] projection = {
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
            };
            Cursor cursor = contentResolver.query(uri, projection, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.
                            CommonDataKinds.Phone.DISPLAY_NAME));
                    @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.
                            CommonDataKinds.Phone.NUMBER));
                    dao = new DAO(getApplicationContext());
                    DanhBa danhBa = new DanhBa(name, phone);
                    if(dao.ThemDB(danhBa) > 0){
                        reloaddata();
                        Toast.makeText(this, "Đồng Bộ Thành Công", Toast.LENGTH_SHORT).show();
                    }

                }

                cursor.close();

            }
        }
    }

    public void reloaddata() {
        dao = new DAO(getApplication());
        listdb = dao.GetDB();
        adapterDB = new AdapterDB(dao, MainActivity.this, listdb);
        recyclerView.setAdapter(adapterDB);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
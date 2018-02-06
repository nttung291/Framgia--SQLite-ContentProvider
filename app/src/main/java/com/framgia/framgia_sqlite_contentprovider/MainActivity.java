package com.framgia.framgia_sqlite_contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<UserModel> userModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requsestPermission();
    }

    public void loadData(){
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                null, null, null);
        int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        cursor.moveToFirst();
        do{
            String contactName = cursor.getString(nameIndex);
            String contactNumber = cursor.getString(numberIndex);
            UserModel userModel = new UserModel(contactName,contactNumber);
            userModels.add(userModel);
        }while(cursor.moveToNext());
        cursor.close();
    }

    private void setUI() {
        RecyclerView recyclerView = findViewById(R.id.rv_contact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ContactAdapter adapter = new ContactAdapter(this,userModels);
        recyclerView.setAdapter(adapter);
    }

    public void requsestPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadData();
                    setUI();
                } else {

                }
                return;
            }

        }
    }
}

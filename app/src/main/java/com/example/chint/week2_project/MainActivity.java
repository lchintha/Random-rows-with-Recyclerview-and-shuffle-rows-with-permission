package com.example.chint.week2_project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MenuItem shuffle;
    private final int CAMERA_REQUEST_CODE = 1;
    private ShareActionProvider sa;
    private View mv;
    RecyclerView rviewm;
    MyAdapter adapter;
    int[] p = {R.drawable.carrick, R.drawable.costa, R.drawable.costa, R.drawable.carrick, R.drawable.costa,
            R.drawable.degea, R.drawable.oscar, R.drawable.herera, R.drawable.herera, R.drawable.carrick};
    String[] a = {"Title1", "Title2", "Title3", "Title4", "Title5", "Title6", "Title7", "Title8", "Title9", "Title10"};
    String[] b = {"Description1", "Desciption2", "Desciption3", "Desciption4", "Desciption5", "Desciption6",
            "Desciption7", "Desciption8", "Desciption9", "Desciption10"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rviewm = (RecyclerView)findViewById(R.id.rview);
        // rviewm.setHasFixedSize(true);
        rviewm.setLayoutManager(new LinearLayoutManager(this));
        // rviewm.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyAdapter(this, a, b, p);
        rviewm.setAdapter(adapter);
        //LinearLayout ll = (LinearLayout)findViewById(R.id.mlayout);
        mv = findViewById(R.id.mlayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_items, menu);
        shuffle = (MenuItem)findViewById(R.id.shuffle);
        sa = (ShareActionProvider) MenuItemCompat.getActionProvider(shuffle);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();
        askPermission(Manifest.permission.CAMERA, CAMERA_REQUEST_CODE);
        return super.onOptionsItemSelected(item);
    }

    private void askPermission(final String permission, final int requestCode) {
        if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){

            //ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            if(!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
                Toast.makeText(this, "Please go to Setting & Change Permissions", Toast.LENGTH_LONG).show();
            }
            else if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
                Snackbar.make(mv, "Please Give Permission...", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
                    }
                }).show();
            }
        }else{
            //Toast.makeText(this, "Permission has already granted", Toast.LENGTH_LONG).show();
            int[] rimage = new int[10];
            String[] rtitle = new String[10];
            String[] rdescription = new String[10];
            Random r = new Random();

            for(int i = 0; i<10; i++){
                int n = r.nextInt(10);
                rimage[i] = p[n];
                rtitle[i] = a[n];
                rdescription[i] = b[n];
            }
            adapter = new MyAdapter(this, rtitle, rdescription, rimage);
            rviewm.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
        }//else
            //Snackbar.make(mv,"Permission Denied",Snackbar.LENGTH_SHORT).show();
    }
}

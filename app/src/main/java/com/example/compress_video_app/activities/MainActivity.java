package com.example.compress_video_app.activities;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.compress_video_app.R;
import com.example.compress_video_app.adapters.VideoFoldersAdapter;
import com.example.compress_video_app.models.NormalVideo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<String> ExternalFolders = new ArrayList<>();
    private RecyclerView recyclerView;
    private VideoFoldersAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<NormalVideo> normalVideos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.folders_rv);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_folders);

        showExternalFolders();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showExternalFolders();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void showExternalFolders() {
        normalVideos = fetchNormalVideo();
        adapter = new VideoFoldersAdapter(normalVideos, ExternalFolders, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false));
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("Range")
    public ArrayList<NormalVideo> fetchNormalVideo() {
        ArrayList<NormalVideo> normalVideoArrayList = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = getContentResolver().query(uri, null,
                null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            do {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media._ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                @SuppressLint("Range") String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                @SuppressLint("Range") String size = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
                @SuppressLint("Range") String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                @SuppressLint("Range") String dateAdded = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATE_ADDED));
                @SuppressLint("Range") String bitrate = "";
                try {
                    bitrate = "" + cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.BITRATE));
                } catch (Exception e) {
                    bitrate = "";
                }
                NormalVideo normalVideo = new NormalVideo(id, title, displayName, size, duration, path,
                        dateAdded, bitrate);

                int index = path.lastIndexOf("/");
                String subString = path.substring(0, index);
                if (!ExternalFolders.contains(subString)) {
                    ExternalFolders.add(subString);
                }
                normalVideoArrayList.add(normalVideo);
            } while (cursor.moveToNext());
        }
        return normalVideoArrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.folder_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh_folders) {
            finish();
            startActivity(getIntent());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
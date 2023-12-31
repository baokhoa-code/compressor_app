package com.example.compress_video_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.compress_video_app.R;
import com.example.compress_video_app.activities.VideoFilesActivity;
import com.example.compress_video_app.models.NormalVideo;

import java.util.ArrayList;

public class VideoFoldersAdapter extends RecyclerView.Adapter<VideoFoldersAdapter.ViewHolder> {
    private final ArrayList<NormalVideo> mediaFiles;
    private final ArrayList<String> folderPath;
    private final Context context;

    public VideoFoldersAdapter(ArrayList<NormalVideo> mediaFiles, ArrayList<String> folderPath, Context context) {
        this.mediaFiles = mediaFiles;
        this.folderPath = folderPath;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.folder_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int indexPath = folderPath.get(position).lastIndexOf("/");
        String nameOFFolder = folderPath.get(position).substring(indexPath + 1);
        holder.folderName.setText(nameOFFolder);
        holder.folder_path.setText(folderPath.get(position));
//        holder.noOfFiles.setText(noOfFoles(folderPath.get(position)) + " Videos");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoFilesActivity.class);
                intent.putExtra("folderName", nameOFFolder);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return folderPath.size();
    }

    int noOfFoles(String folder_name) {
        int files_no = 0;
        for (NormalVideo normalVideo : mediaFiles) {
            if (normalVideo.getPath().substring(0, normalVideo.getPath().lastIndexOf("/"))
                    .endsWith(folder_name)) {
                files_no++;
            }
        }
        return files_no;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView folderName, folder_path;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folderName = itemView.findViewById(R.id.folderName);
            folder_path = itemView.findViewById(R.id.folderPath);

        }
    }
}
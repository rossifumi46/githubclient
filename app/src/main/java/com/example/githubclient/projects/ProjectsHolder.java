package com.example.githubclient.projects;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.githubclient.R;
import com.example.githubclient.data.model.project.Project;


public class ProjectsHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mLanguage;

    public ProjectsHolder(View itemView) {
        super(itemView);;
        mName = itemView.findViewById(R.id.tv_name);
        mLanguage = itemView.findViewById(R.id.tv_language);
    }

    public void bind(Project item, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mName.setText(item.getName());
        mLanguage.setText(item.getLanguage());

        if (onItemClickListener != null) {
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(
                    item.getFullname()
            ));
        }
    }
}

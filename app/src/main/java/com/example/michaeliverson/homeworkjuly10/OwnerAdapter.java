package com.example.michaeliverson.homeworkjuly10;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by michaeliverson on 7/10/17.
 */

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.OwnerViewHolder>
{
    private ArrayList<GithubRepo> listGit;
    private Context context;

    public OwnerAdapter(Context context,ArrayList<GithubRepo> list)
    {
        this.listGit = list;
        this.context = context;
    }

    @Override
    public OwnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gitlist, parent, false);
        OwnerViewHolder ownerView = new OwnerViewHolder(view);
        return ownerView;
    }

    @Override
    public void onBindViewHolder(OwnerViewHolder holder, int position) {
        ImageView image = holder.ivView;
        TextView name = holder.tvName;
        TextView fullName = holder.tvfulName;
        TextView url = holder.tvURL;
        TextView gitUrl = holder.tvGITURL;

        name.setText(this.listGit.get(position).getName());
        fullName.setText(this.listGit.get(position).getFullName());
        url.setText(this.listGit.get(position).getUrl());
        gitUrl.setText(this.listGit.get(position).getGitUrl());
        if (image == null)
        {
            new ImageLoadTask(this.listGit.get(position).getOwner().getAvatarUrl(), image).execute();
        }
    }

    @Override
    public int getItemCount() {
        return this.listGit.size();
    }

    public class OwnerViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivView;
        private TextView tvName;
        private TextView tvfulName;
        private TextView tvURL;
        private TextView tvGITURL;

        public OwnerViewHolder(View itemView) {
            super(itemView);
            this.ivView = (ImageView)itemView.findViewById(R.id.ivView1);
            this.tvName = (TextView)itemView.findViewById(R.id.tvName1);
            this.tvfulName = (TextView)itemView.findViewById(R.id.tvFullName1);
            this.tvURL = (TextView)itemView.findViewById(R.id.tvURL1);
            this.tvGITURL = (TextView)itemView.findViewById(R.id.tvGitURL1);
        }
    }
}

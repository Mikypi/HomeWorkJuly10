package com.example.michaeliverson.homeworkjuly10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListOfRepositories extends AppCompatActivity {

    private OwnerAdapter adaptor;
    private RecyclerView recycle;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_repositories);

        this.recycle = (RecyclerView)findViewById(R.id.rvList);
        this.recycle.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        this.recycle.setLayoutManager(layoutManager);
        this.recycle.setItemAnimator(new DefaultItemAnimator());

        DataWrapper wrap = (DataWrapper)getIntent().getSerializableExtra("KEYDATARRAY");
        this.adaptor = new OwnerAdapter(this,wrap.getParliaments());
        this.recycle.setAdapter(this.adaptor);
    }
}

package com.example.dillichalise.jt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

public class HomeActivity extends Fragment {
    DatabaseHelperForm databaseHelperForm;
    JobListAdapter jobListAdapter;
    List<Form> formContents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        databaseHelperForm=new DatabaseHelperForm(getActivity());
         formContents = databaseHelperForm.getFormContents();

        Log.e("size of forms/",formContents.size()+"");
         jobListAdapter=new JobListAdapter(formContents);
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.joblist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(jobListAdapter);

//        recyclerview.setAdapter(jobLKistAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        formContents.clear();
        formContents.addAll(databaseHelperForm.getFormContents()) ;
        Log.e("onresume",formContents.size()+"");
        jobListAdapter.notifyDataSetChanged();
    }

    
}

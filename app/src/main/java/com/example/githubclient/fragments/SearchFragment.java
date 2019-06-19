package com.example.githubclient.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.githubclient.R;
import com.example.githubclient.projects.ProjectsFragment;

public class SearchFragment extends Fragment{

    private EditText mQuery;
    private EditText mLanguage;
    private Spinner mSort;
    private Button mSearchBtn;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mQuery = view.findViewById(R.id.query);
        mLanguage = view.findViewById(R.id.language);
//        mSort = view.findViewById();
        mSearchBtn = view.findViewById(R.id.srcbtn);

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("Query", mQuery.getText().toString());
                if (!TextUtils.isEmpty(mLanguage.getText()))
                    args.putString("Language", mLanguage.getText().toString());
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, ProjectsFragment.newInstance(args))
                        .addToBackStack(null)
                        .commit();
            }
        });



    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

}

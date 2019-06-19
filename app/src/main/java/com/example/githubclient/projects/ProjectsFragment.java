package com.example.githubclient.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.githubclient.R;
import com.example.githubclient.common.RefreshOwner;
import com.example.githubclient.common.Refreshable;
import com.example.githubclient.data.model.project.Project;
import com.example.githubclient.project.ProjectActivity;
import com.example.githubclient.project.ProjectFragment;
import com.example.githubclient.utils.ApiUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ProjectsFragment extends Fragment implements Refreshable, ProjectsAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private ProjectsAdapter mProjectsAdapter;
    private Disposable mDisposable;
    private String mQuery;
    private String mLanguage;

    public static ProjectsFragment newInstance(Bundle args) {
        ProjectsFragment fragment = new ProjectsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof RefreshOwner) {
            mRefreshOwner = ((RefreshOwner) context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mErrorView = view.findViewById(R.id.errorView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            getActivity().setTitle(R.string.projects);
        }


        mQuery = getArguments().getString("Query");
        mLanguage = getArguments().getString("Language");
        if (mLanguage != null)
            mQuery = mQuery + "+language:" + mLanguage;
        mProjectsAdapter = new ProjectsAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mProjectsAdapter);

        onRefreshData();
    }

    @Override
    public void onItemClick(String fullname) {
        Intent intent = new Intent(getActivity(), ProjectActivity.class);
        Bundle args = new Bundle();
        args.putString(ProjectFragment.PROJECT_KEY, fullname);
        intent.putExtra(ProjectActivity.USERNAME_KEY, args);
        startActivity(intent);
    }

    @Override
    public void onDetach() {
        mRefreshOwner = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        super.onDetach();
    }

    @Override
    public void onRefreshData() {
        getProjects();
    }

    private void getProjects() {
        mDisposable = ApiUtils.getApiService().getProjects(mQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mRefreshOwner.setRefreshState(false))
                .subscribe(
                        response -> {
                            mErrorView.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                            List<Project> projectList =  response.getProjects();
                            mProjectsAdapter.addData(response.getProjects(), true);
                        },
                        throwable -> {
                            mErrorView.setVisibility(View.VISIBLE);
                            mRecyclerView.setVisibility(View.GONE);
                        });
    }


}

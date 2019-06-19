package com.example.githubclient.project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.githubclient.R;
import com.example.githubclient.common.RefreshOwner;
import com.example.githubclient.common.Refreshable;
import com.example.githubclient.data.model.project.Project;
import com.example.githubclient.utils.ApiUtils;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ProjectFragment extends Fragment implements Refreshable {

    public static final String PROJECT_KEY = "PROJECT_KEY";

    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    private View mProfileView;
    private String mFullname;
    private Disposable mDisposable;

    private TextView mProjectName;
    private TextView mProjectOwner;
    private TextView mProjectLanguage;

    public static ProjectFragment newInstance(Bundle args) {
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mRefreshOwner = context instanceof RefreshOwner ? (RefreshOwner) context : null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_project, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mErrorView = view.findViewById(R.id.errorView);
        mProfileView = view.findViewById(R.id.view_profile);

//        mProfileImage = view.findViewById(R.id.iv_profile);
        mProjectName = view.findViewById(R.id.tv_display_name_details);
        mProjectOwner = view.findViewById(R.id.tv_created_on_details);
        mProjectLanguage = view.findViewById(R.id.tv_location_details);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mFullname = getArguments().getString(PROJECT_KEY);
        }

        if (getActivity() != null) {
            getActivity().setTitle(mFullname);
        }

        mProfileView.setVisibility(View.VISIBLE);

        onRefreshData();
    }

    @Override
    public void onRefreshData() {
        getProfile();
    }

    private void getProfile() {
        mDisposable = ApiUtils.getApiService().getProject(mFullname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mRefreshOwner.setRefreshState(false))
                .subscribe(
                        response -> {
                            mErrorView.setVisibility(View.GONE);
                            mProfileView.setVisibility(View.VISIBLE);
                            bind(response);
                        },
                        throwable -> {
                            mErrorView.setVisibility(View.VISIBLE);
                            mProfileView.setVisibility(View.GONE);
                        });
    }

    private void bind(Project project) {
        mProjectName.setText(project.getName());
        mProjectOwner.setText(project.getOwner().getLogin());
        mProjectLanguage.setText(project.getLanguage());
    }

    @Override
    public void onDetach() {
        mRefreshOwner = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        super.onDetach();
    }
}

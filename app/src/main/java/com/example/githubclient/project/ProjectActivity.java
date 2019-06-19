package com.example.githubclient.project;

import android.support.v4.app.Fragment;

import com.example.githubclient.common.SingleFragmentActivity;



public class ProjectActivity extends SingleFragmentActivity {

    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    protected Fragment getFragment() {
        if (getIntent() != null) {
            return ProjectFragment.newInstance(getIntent().getBundleExtra(USERNAME_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");
    }
}


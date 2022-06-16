package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
    }
}


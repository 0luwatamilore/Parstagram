package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    private Post post;
    private TextView tvProfileImage;
    private ImageView ivimage;
    private TextView tvProfileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvProfileImage = findViewById(R.id.tvProfileName);
        ivimage = findViewById(R.id.ivimage);
        tvProfileImage = findViewById(R.id.tvProfileName);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        ParseFile image = post.getImage();
        tvProfileImage.setText(post.getUser().getUsername());
        Glide.with(this).load(image.getUrl()).into(ivimage);

    }
}


package com.example.parstagram;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.parstagram.fragments.PostsFragment;
import com.example.parstagram.fragments.ProfileFragment;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private Context context;
    private List<Post> posts;


    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvCreatedAt;
        private ImageView ivProfile;
        private ImageButton btnLike;
        private TextView tvLikesCount;
        List<String> likedBy = new ArrayList<>();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            btnLike = itemView.findViewById(R.id.btnLike);
            tvLikesCount = itemView.findViewById(R.id.tvLikesCount);

        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvCreatedAt.setText((post.getCreatedAt().toString()));

            tvLikesCount.setText(String.valueOf(post.getLike().size()));

            ivImage.setOnClickListener(v -> {
                Intent i = new Intent(context,PostDetailsActivity.class);
                i.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                context.startActivity(i);
            });

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> getLikedBy = post.getLike();
                    ParseUser currentUser = ParseUser.getCurrentUser();

                    if(!getLikedBy.contains(currentUser.getObjectId())) {
                        getLikedBy.remove(currentUser.getObjectId());
                        post.setUserLike(getLikedBy);

                        tvLikesCount.setText(String.valueOf(post.getLike().size() + 1));
                    } else{
                        likedBy.add(currentUser.getObjectId());
                        post.setUserLike(likedBy);
                        tvLikesCount.setText(String.valueOf(post.getLike().size() - 1));
                    }
                    post.saveInBackground();
                    //tvLikesCount.setText(String.valueOf(post.getLike().size()));
                }

            });

                ParseFile profileImg = post.getUser().getParseFile("profilePic");

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);

            }
            if (profileImg!= null){
                Glide.with(context).load(profileImg.getUrl())
                        .transform(new CenterCrop(),new RoundedCorners(14))
                        .into(ivProfile);
            }
        }
        }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

}

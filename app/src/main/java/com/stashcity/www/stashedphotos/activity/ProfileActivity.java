package com.stashcity.www.stashedphotos.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.stashcity.www.stashedphotos.R;
import com.stashcity.www.stashedphotos.util.ImageLoadTask;

public class ProfileActivity extends AppCompatActivity {
ImageView profileImageView;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImageView =(ImageView) findViewById(R.id.profilePic);
        name = (TextView)findViewById(R.id.nameProfileActivity);
        Profile profile = Profile.getCurrentProfile();
        new ImageLoadTask(profile.getProfilePictureUri(1000,1000).toString()
                , profileImageView).execute();
        name.setText(profile.getName());

    }
}

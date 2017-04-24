package com.stashcity.www.stashedphotos.adapter;

/**
 * Created by sravan on 15/10/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.stashcity.www.stashedphotos.ApplicationController;
import com.stashcity.www.stashedphotos.FeedImageView;
import com.stashcity.www.stashedphotos.R;
import com.stashcity.www.stashedphotos.model.FeedItem;
import com.stashcity.www.stashedphotos.volley.VolleyNetworkCalls;

import java.util.List;

public class FeedListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    private Context context;
    TextView likeCount;
    ImageLoader imageLoader = ApplicationController.getInstance().getImageLoader();
    VolleyNetworkCalls VNC;

    public FeedListAdapter(Activity activity, List<FeedItem> feedItems,Context context) {
        this.activity = activity;
        this.feedItems = feedItems;
        this.context = context;
    }



    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feed_item, null);

        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.timestamp);
        TextView statusMsg = (TextView) convertView
                .findViewById(R.id.txtStatusMsg);
        //TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
        NetworkImageView profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        FeedImageView feedImageView = (FeedImageView) convertView
                .findViewById(R.id.feedImage1);


        final FeedItem item = feedItems.get(position);

        name.setText(item.getName());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getStatus())) {
            statusMsg.setText(item.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }

        // user profile pic
        profilePic.setImageUrl(item.getProfilePic(), imageLoader);

        // Feed image
         if (item.getProfilePic() != null) {

            feedImageView.setImageUrl(item.getProfilePic(), imageLoader);
            feedImageView.setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                            Log.e("Image Error","Error Loading Image");
                        }

                        @Override
                        public void onSuccess() {
                            Log.e("Image Success","Loading Image successful");
                        }
                    });
        }

        likeCount=(TextView)convertView.findViewById(R.id.likeCount);
        likeCount.setText(": "+item.getLikes());
        Button likeButton = (Button) convertView.findViewById(R.id.likeBtn);
        likeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("loveBTN",":"+item.getId());
                likeCount.setText(": "+(item.getLikes()+1));
                VNC =new VolleyNetworkCalls();
                VNC.LikePost(item.getId());

            }
        });
        AppCompatButton shareBtn = (AppCompatButton) convertView.findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, item.getUrl());
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this photo!");
                context.startActivity(Intent.createChooser(intent, "Share"));

            }
        });


        return convertView;
    }

}

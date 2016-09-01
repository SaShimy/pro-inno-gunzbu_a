package com.etna.gunzbu_a.freshdj;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SuChi on 01/09/2016.
 */
public class AddVideoActivity extends MainActivity{

    public static final String API_KEY = "AIzaSyCqiRYh13_-Fjy6qCMO9zRP1reaG4S2K6w";
    public static final String KEY_PLID = "playlistId";
    public static final String KEY_MUSICID = "musicId";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMGURL = "imgUrl";
    public static String VIDEOID = "toto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvideo);

        final String title = getIntent().getExtras().getString("title");
        final String channelTitle = getIntent().getExtras().getString("channelTitle");
        final String thumbnailUrl = getIntent().getExtras().getString("thumbnailUrl");
        VIDEOID = getIntent().getExtras().getString("videoId");
        final String id = getIntent().getExtras().getString("Id");

        TextView tv_Channel = (TextView) findViewById(R.id.tV_channel);
        TextView tv_Title = (TextView) findViewById(R.id.tV_title);

        tv_Title.setText(title);
        tv_Channel.setText("Channel : " + channelTitle);

        Button RequestAddBtn = (Button) findViewById(R.id.addtoplaylist);

        RequestAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(AddVideoActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://apifreshdj.cloudapp.net/playlist/api/addMusic",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(AddVideoActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                Log.v("ERR", error.toString());
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put(KEY_NAME, title);
                        params.put(KEY_IMGURL, thumbnailUrl);
                        params.put(KEY_MUSICID, VIDEOID);
                        params.put(KEY_PLID, "1"/*id*/);
                        Log.v("PAR", params.toString());
                        return params;
                    }

                    public Map<String, String> getHeaders() {
                        Map<String, String> header = new HashMap<String, String>();
                        header.put("Authorization", "Bearer " + "NzZiZDljNWM0YjA4MzU5YmE3YzBkNmQ1MzlhMDIwNmNhMTMxOWFlODQ3YzYzN2I3MDRjZTgzMGNmNTM2Nzg1Yg");
                        return header;
                    }
                };
                queue.add(stringRequest);
            }
        });

        /** Initializing YouTube player view **/
        /*YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);*/
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!wasRestored) {
            player.cueVideo(VIDEOID);
        }
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };
}

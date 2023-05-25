package com.fact.factsapp;

import static com.fact.factsapp.FirstFragment.CATEGORY;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondFragment extends Fragment {

    private Button notificationButton;
    private static String NOTIFICATION_CHANNEL_ID = "Notif channel";
    private View view;
    private VideoView videoView;

    public SecondFragment() {
        super(R.layout.fragment_second);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        createNotificationChannel();
        getFact();
        VideoView videoView = view.findViewById(R.id.videoView);

        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("android.resource://" + requireActivity().getPackageName() + "/" + R.raw.catdoorbell);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(1.0f, 1.0f);
                videoView.start();
            }
        });

        notificationButton = view.findViewById(R.id.btn);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFact();
            }
        });
    }


    private void getFact() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            CategoryModel model = bundle.getParcelable(CATEGORY);
            String Url = "";
            if (Objects.equals(model.getName(), "Cats")) {
                Url = "https://catfact.ninja/";
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CatFactService service = retrofit.create(CatFactService.class);
                CatFactTask task = new CatFactTask(service, new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String catFact = response.body();
                            System.out.println(catFact);
                            ((TextView) view.findViewById(R.id.title)).setText(catFact);
                            addNotification(catFact);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                task.execute();
            }
            if (Objects.equals(model.getName(), "Dogs")) {
                Url = "https://dogapi.dog/api/v2/";
            }
            if (Objects.equals(model.getName(), "Numbers")) {
                Url = "http://numbersapi.com/random/trivia/";
            }
            if (Objects.equals(model.getName(), "Random")) {
                Url = "https://uselessfacts.jsph.pl/api/v2/facts/random/";
            }
        }
    }

    private void addNotification(String fact) {
        Intent intent = new Intent(getContext(), CategoriesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        int id = 1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), NOTIFICATION_CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("FactsApp").setContentText(fact).setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent).setSound(null);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.notify(id, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "channel name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("channel description");

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity().getApplicationContext());
            notificationManagerCompat.createNotificationChannel(channel);
        }
    }


}

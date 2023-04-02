package com.fact.factsapp;

import static com.fact.factsapp.FirstFragment.CATEGORY;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

public class SecondFragment extends Fragment {

    private Button notificationButton;
    private static String NOTIFICATION_CHANNEL_ID = "Notif channel";

    public SecondFragment() {
        super(R.layout.fragment_second);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createNotificationChannel();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            CategoryModel model = bundle.getParcelable(CATEGORY);

            ((TextView) view.findViewById(R.id.title)).setText(model.getName());
        }

        notificationButton = view.findViewById(R.id.btn);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });
    }

    private void addNotification() {
        Intent intent = new Intent(getContext(), CategoriesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        int id = 1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), NOTIFICATION_CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("FactsApp").setContentText("Get your daily dose of knowledge!").setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent).setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

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

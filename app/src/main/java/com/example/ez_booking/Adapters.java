package com.example.ez_booking;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

class AdapterUtils {
    static String capitalize(String word) {
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }

    static String formatScheduleId(String scheduleId) {
        String[] scheduleIdParts = scheduleId.split("[_]");
        int hour = Integer.parseInt(scheduleIdParts[0].substring(0, 2));
        String minute = scheduleIdParts[0].substring(2);
        String amPm = hour < 12 ? "AM" : "PM";

        scheduleIdParts[1] = AdapterUtils.capitalize(scheduleIdParts[1]);
        scheduleIdParts[2] = AdapterUtils.capitalize(scheduleIdParts[2]);

        return hour + ":" + minute + " " + amPm + " from " + scheduleIdParts[1] + " to " + scheduleIdParts[2];
    }
}

class DocumentSnapshotAdapter extends ArrayAdapter<DocumentSnapshot> {
    public DocumentSnapshotAdapter(@NonNull Context context, int resource, @NonNull List<DocumentSnapshot> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setText(AdapterUtils.formatScheduleId(getItem(position).getId()));
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setText(AdapterUtils.formatScheduleId(getItem(position).getId()));
        return textView;
    }
}

class DocumentReferenceAdapter extends ArrayAdapter<DocumentReference> {
    public DocumentReferenceAdapter(@NonNull Context context, int resource, @NonNull List<DocumentReference> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setText(AdapterUtils.capitalize(getItem(position).getId()));
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setText(AdapterUtils.capitalize(getItem(position).getId()));
        return textView;
    }
}

package com.f1x.mtcdtools.adapters;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.f1x.mtcdtools.R;

/**
 * Created by COMPUTER on 2016-08-08.
 */
public class ApplicationEntryArrayAdapter extends ArrayAdapter<ApplicationEntry> {
    public ApplicationEntryArrayAdapter(Context context) {
        super(context, R.layout.application_row, R.id.applicationName);
        PackageManager packageManager = context.getPackageManager();

        for(ApplicationInfo applicationInfo : packageManager.getInstalledApplications(0)) {
            try {
                Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo.packageName);
                add(new ApplicationEntry(applicationIcon, packageManager.getApplicationLabel(applicationInfo).toString(), applicationInfo.packageName));
            } catch(PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.application_row, null);
        }

        ApplicationEntry applicationEntry = getItem(position);
        if(applicationEntry != null) {
            ImageView applicationIconImageView = (ImageView) view.findViewById(R.id.applicationIcon);
            applicationIconImageView.setImageDrawable(applicationEntry.getIcon());

            TextView applicationNameTextView = (TextView) view.findViewById(R.id.applicationName);
            applicationNameTextView.setText(applicationEntry.getApplicationName());
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
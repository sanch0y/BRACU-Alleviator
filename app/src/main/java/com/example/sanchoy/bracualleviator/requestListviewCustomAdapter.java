package com.example.sanchoy.bracualleviator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


    public class requestListviewCustomAdapter extends ArrayAdapter<ParseObject> {
    protected Context requestContext;
    protected List<ParseObject> studentRequests;

    public requestListviewCustomAdapter(Context reqContext, List<ParseObject> requests) {
        super(reqContext, R.layout.student_request_custom_layout, requests);
        requestContext = reqContext;
        studentRequests = requests;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(requestContext).inflate(
                    R.layout.student_request_custom_layout, null);
            holder = new ViewHolder();
            holder.studentID = (TextView) convertView
                    .findViewById(R.id.student_id);
            holder.requestTime = (TextView) convertView
                    .findViewById(R.id.request_time);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        ParseObject requestObject = studentRequests.get(position);
        // id
        String stdID = requestObject.getString("student_ID");
        holder.studentID.setText(stdID);

        // time
        Date reqTime = requestObject.getCreatedAt();
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy, HH:mm:ss");
        holder.requestTime.setText(df.format(reqTime));

        return convertView;
    }

    public static class ViewHolder {
        TextView studentID;
        TextView requestTime;
    }

}

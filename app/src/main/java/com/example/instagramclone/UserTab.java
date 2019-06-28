package com.example.instagramclone;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserTab extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;
    public UserTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_user_tab, container, false);

        listView=view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        arrayAdapter =new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);

        listView.setOnItemClickListener(UserTab.this);
        listView.setOnItemLongClickListener(UserTab.this);

        final TextView txtLoadUsers= view.findViewById(R.id.LoadUsers);

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e==null){
                    if (users.size() > 0){
                        for (ParseUser user : users){
                            arrayList.add(user.getUsername());
                        }

                        listView.setAdapter(arrayAdapter);
                        txtLoadUsers.animate().alpha(0).setDuration(500);
                        listView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(),UsersPosts.class);
        intent.putExtra("username", arrayList.get(position));
        startActivity(intent);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        final ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereEqualTo("username", arrayList.get(position));
        parseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseQuery != null && e == null){
                    final PrettyDialog prettyDialog = new PrettyDialog(getContext());

                    prettyDialog.setTitle(parseUser.getUsername() + " 's Info").setMessage(parseUser.get("profileBio")+
                            "\n" +parseUser.get("profileProfession") + "\n" + parseUser.get("profileHobbies") + "\n"
                    + parseUser.get("profileFavSport")).setIcon(R.drawable.user).addButton("ok", R.color.colorAccent,
                            R.color.colorPrimary, new PrettyDialogCallback() {
                                @Override
                                public void onClick() {
                                    prettyDialog.dismiss();
                                }
                            }).show();
                }
            }
        });

        return true;
    }
}

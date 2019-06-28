package com.example.instagramclone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtName,edtBio,edtProfession,edtHobbies,edtFavSport;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtName = view.findViewById(R.id.profileName);
        edtBio = view.findViewById(R.id.userBio);
        edtProfession = view.findViewById(R.id.userProfession);
        edtHobbies = view.findViewById(R.id.userHobbies);
        edtFavSport = view.findViewById(R.id.userFavSport);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName")==null){
            edtName.setText("");
        }else {
            edtName.setText(parseUser.get("profileName")+"");
        }
        if (parseUser.get("profileBio")==null){
            edtBio.setText("");
        }else {
            edtBio.setText(parseUser.get("profileBio")+"");
        }
        if (parseUser.get("profileProfession")==null)
        {
            edtProfession.setText("");
        }else {
            edtProfession.setText(parseUser.get("profileProfession")+"");
        }
        if (parseUser.get("profileHobbies")==null){
            edtHobbies.setText("");
        }else {
            edtHobbies.setText(parseUser.get("profileHobbies")+"");
        }
        if (parseUser.get("profileFavSport")==null){
            edtFavSport.setText("");
        }else {
            edtFavSport.setText(parseUser.get("profileFavSport")+"");
        }

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName", edtName.getText().toString());
                parseUser.put("profileBio", edtBio.getText().toString());
                parseUser.put("profileProfession", edtProfession.getText().toString());
                parseUser.put("profileHobbies", edtHobbies.getText().toString());
                parseUser.put("profileFavSport", edtFavSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null)
                        {
                            Toast.makeText(getContext(), "Information Updated", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return view;
    }


}

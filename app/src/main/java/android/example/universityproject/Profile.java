package android.example.universityproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Profile extends Fragment {

    TextView user_name, user_email;
    ImageView profile_image;

    GoogleSignInAccount account;
    Button btn_log_out;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_profile, container, false);

        profile_image = rootView.findViewById(R.id.profile_image);
        user_name = rootView.findViewById(R.id.user_name);
        user_email = rootView.findViewById(R.id.user_email);
        btn_log_out = rootView.findViewById(R.id.btn_log_out);

        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                build();


        account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (account != null) {
            String personName = account.getDisplayName();
            user_name.setText(personName);
//            String personGivenName = account.getGivenName();
            String personEmail = account.getEmail();
            user_email.setText(personEmail);
//            String personId = account.getId();

            if(account.getPhotoUrl() != null){
                String personPhoto = account.getPhotoUrl().toString();
                //            profile_image.setImageBitmap(personPhoto);
                Glide.with(this).load(personPhoto).into(profile_image);
            } else{
                profile_image.setImageResource(R.drawable.google);
            }

        }

        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInClient googleSignInClient=GoogleSignIn.getClient(getActivity(),gso);
                googleSignInClient.signOut();
                Intent next = new Intent(getActivity(), CreateAccountActivity.class);
                startActivity(next);
            }
        });


        return rootView;
    }
}
package com.kisanthapa.connect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kisanthapa.connect.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersActivity extends AppCompatActivity {

    private Toolbar mUsersToolbar;
    private RecyclerView mUsersList;

    //Firebase Database Reference
    private DatabaseReference mUsersDatabase;
    //private FirebaseAuth mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        //Toolbar
        mUsersToolbar = (Toolbar)findViewById(R.id.users_appbar);
        setSupportActionBar(mUsersToolbar);
        getSupportActionBar().setTitle("All Users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Firebase
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        //mUsersDatabase.keepSynced(true);
        //mCurrentUser = FirebaseAuth.getInstance();

        //Recycler view
        mUsersList = (RecyclerView)findViewById(R.id.users_list);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        //mUsersDatabase.child(mCurrentUser.getCurrentUser().getUid()).child("online").setValue(true);

//        FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
//                Users.class,
//                R.layout.users_single_layout,
//                UsersViewHolder.class,
//                mUsersDatabase
//        ) {
//            @NonNull
//            @Override
//            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull Users model) {
//
//            }
//
//            @Override
//            protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position) {
//
//                viewHolder.setDisplayName(model.getName());
//                viewHolder.setStatus(model.getStatus());
//                viewHolder.setUserImage(model.getThumb_image(), getApplicationContext());
//
//                //Getting key of the user
//                final String user_id = getRef(position).getKey();
//
//                //When clicks on users
//                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent usersProfileIntent = new Intent(UsersActivity.this, UsersProfileActivity.class);
//                        usersProfileIntent.putExtra("user_id", user_id);
//                        startActivity(usersProfileIntent);
//                    }
//                });
//
//            }
//        };

//        mUsersList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class UsersViewHolder extends RecyclerView.ViewHolder{

        View mview;
        public UsersViewHolder(View itemView) {
            super(itemView);

            mview = itemView;
        }

        public void setDisplayName(String name){
            TextView usersName = (TextView)mview.findViewById(R.id.user_single_name);
            usersName.setText(name);
        }
        public void setStatus(String status){
            TextView usersStatus = (TextView)mview.findViewById(R.id.user_single_status);
            usersStatus.setText(status);
        }
        public void setUserImage(String thumb_image, Context context){
            CircleImageView usersImageView = (CircleImageView)mview.findViewById(R.id.user_single_image);

//            Picasso.with(context).load(thumb_image).placeholder(R.drawable.default_avatar).into(usersImageView);
            Picasso.get().load(thumb_image).into(usersImageView);
        }

    }
}

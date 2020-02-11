package com.my.kissandost;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FindKey {
    static String FRID = "";

    public void getKey() {
        DatabaseReference root1 = FirebaseDatabase.getInstance().getReference("Kissan Dost" + Login.type);
        root1.addListenerForSingleValueEvent(get_id_key);
    }

    ValueEventListener get_id_key = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren() ) {
                User user = dataSnapshot1.getValue(User.class);

                if(user.getEmailId().equals(Login.ID)) {
                    FRID = dataSnapshot1.getKey();
                    Log.i("FID",FRID);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}

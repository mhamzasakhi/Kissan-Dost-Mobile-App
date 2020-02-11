package com.my.kissandost;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FarmersInfo extends AppCompatActivity {

    static ArrayList<String> checkedList;
    RecyclerView myrv;

    // Array list for the farmer Products to show the Retailers...

    List<FarmerProductDetails> lstFarmerDetails = new ArrayList<FarmerProductDetails>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmers_info);
        FarmerProductDetails obj=new FarmerProductDetails("12far434","Patato","1234","Muhammad Hamza","120-KG","200-KG","Vegetables");

        lstFarmerDetails.add(obj);

        myrv = findViewById(R.id.recycleRetailer);
        Intent intent =  getIntent();
        checkedList = intent.getStringArrayListExtra("CheckList");

        // Get the product details of the specific farmer that id coming from the previous page...

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Table Name Here");
        databaseReference.addListenerForSingleValueEvent(showListToRetailer);
    }

    ValueEventListener showListToRetailer = new ValueEventListener() {
        private DatabaseError databaseError;

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String name = "Farmer";
            int count = 1;


            for(DataSnapshot formerKey : dataSnapshot.getChildren()) {

                for(DataSnapshot productKey : formerKey.getChildren()) {

                    ProductDetails productDetails = productKey.getValue(ProductDetails.class);

                    if(checkedList.contains(productDetails.ProductCategory)) {


                        lstFarmerDetails.add(new FarmerProductDetails(productKey.getKey(),
                                                                        productDetails.ProductName,
                                                                        productDetails.ProductCategory,
                                                                        formerKey.getKey(),
                                                                name + count ,
                                                                        productDetails.ProductBasePrice,
                                                                        productDetails.ProdQuantity)
                                            );

                        FarmerDetailsAdapter myAdapter = new FarmerDetailsAdapter(FarmersInfo.this, lstFarmerDetails);
                        myrv.setLayoutManager(new GridLayoutManager(FarmersInfo.this,1));
                        myrv.setAdapter(myAdapter);
                        Log.i("Value1",productDetails.ProdQuantity + productDetails.ProductCategory + productDetails.ProductBasePrice + productDetails.ProductName);
                    }
                }
                count++;
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }


    };
}

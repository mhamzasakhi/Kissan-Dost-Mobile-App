package com.my.kissandost;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class bidding extends AppCompatActivity {

    private String name, quantity, price, productID, formerID, retailerID;
    private EditText biddingPrice;
    private TextView ProductName, Quantity, BasePrice;
    ArrayAdapter<String> productDetails;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);
        getSupportActionBar().hide();

        final Intent intent = getIntent();
        final ArrayList<String> productDetails = intent.getStringArrayListExtra("ProductDetails");

        name = productDetails.get(2);
        productID = productDetails.get(1);
        quantity = productDetails.get(5);
        formerID = productDetails.get(4);
        price = productDetails.get(6);

        //retailerID = FindTheKey.FRID;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Start Bidding");

        // Inflate the bidding XML here...
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.bidding, null);

        // Store the bidding XML attributes in corresponding objects
        ProductName = view.findViewById(R.id.ProductName);
        Quantity = view.findViewById(R.id.Quantity);
        BasePrice = view.findViewById(R.id.Price);
        biddingPrice = view.findViewById(R.id.BiddingPrice);

        // Set original Values of the product
        ProductName.setText(name);
        Quantity.setText(quantity);
        BasePrice.setText(price);

        builder.setView(view);

        builder.setPositiveButton("START", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Log.i("Value","BiddingTransaction/".concat(formerID).concat("/").concat(productID).replaceAll(" ",""));

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("EFarmer/BiddingTransaction/" +formerID+ "/"+productID);
                String string = databaseReference.push().getKey();
                Log.i("Retailer Id",string);



                if(Integer.parseInt(biddingPrice.getText().toString()) < Integer.parseInt(price)) {
                    Toast.makeText(bidding.this,"Please Enter a Value Greater than the price",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(bidding.this,bidding.class);
                    intent1.putExtra("ProductDetails",productDetails);
                    startActivity(intent1);
                }


                TransactionDetails transactionDetails = new TransactionDetails(retailerID, biddingPrice.getText().toString());
                databaseReference.child(string).setValue(transactionDetails);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent1 = new Intent(getApplicationContext(), retailer.class);
                startActivity(intent1);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

package com.my.kissandost;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class retailer extends AppCompatActivity {

    String[] vegetables = {"Tomato","Broccoli","Brussels sprouts","Cabbage","Lettuce","Spinach","Carrot","Beetroot","Pea","Turnip","Cauliflower","Drumstick","Beans","Round Beans","Okra","Garlic","Onion","Potato","Ginger","radish","Sweet Potato"};
    String[] fruits = {"Kiwi","Orange","Banana","Apple","Pineapple","Pomogranate","Blackberry","Strawberry","Avocado","Bluberry"};
    String[] flowers = {"Alstroemeria","Amaranthus","Amaryllis","Calla","Chrysanthemum","Daffodil","Dahlia","Red Rose","Hybiscus","Lily","Sunflower","Tulip"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retailer);

        final ListView retailerListView = findViewById(R.id.retialerCheckList);

        final ArrayList<String> checkList= new ArrayList<String>();
        checkList.add("Vegetables");
        checkList.add("Fruits");
        checkList.add("Flowers");

        final ArrayAdapter<String> retailerList= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,checkList);
        retailerListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        retailerListView.setAdapter(retailerList);


        final ArrayList<String> checkedArrayList = new ArrayList<String>();
        Button submit = findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checked  = retailerListView.getCheckedItemPositions();
                checkedArrayList.clear();

                for(int i=0; i<checkList.size(); i++) {
                    if(checked.get(i))
                        checkedArrayList.add(checkList.get(i));

                }


                Intent intent3 = new Intent(getApplicationContext(),FarmersInfo.class);
                intent3.putExtra("CheckList",checkedArrayList);
                startActivity(intent3);
            }
        });

    }
}

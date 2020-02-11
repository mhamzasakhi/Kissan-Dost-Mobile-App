package com.my.kissandost;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FarmerDetailsAdapter extends RecyclerView.Adapter<FarmerDetailsAdapter.MyViewHolder>{
    private Context context;
    private List<FarmerProductDetails> mData;

    public FarmerDetailsAdapter(Context context, List<FarmerProductDetails> mData) {
        this.context = context;
        this.mData = mData;
    }


    @Override
    public FarmerDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.showfarmerbidding,parent,false);


        return new FarmerDetailsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FarmerDetailsAdapter.MyViewHolder holder, int position) {
        holder.tv_pName.setText("Product Name :  " + mData.get(position).getpName());
        holder.tv_basePrice.setText("Base Price : " + mData.get(position).getbPrice());
        holder.tv_farmerName.setText("Farmer :  " + mData.get(position).getFarmerName());
        holder.tv_qty.setText("Product Quantity :  " + mData.get(position).getQuantity());
        holder.tv_farmerId.setText("Farmer Id :  " + mData.get(position).getFarmerId());
        holder.tv_prodCategory.setText("Product Category :  " + mData.get(position).getPcategory());
        holder.tv_productId.setText("Product Id :  " + mData.get(position).getProductId());

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String tempProdName = holder.tv_pName.getText().toString();
                int productNameIndex = tempProdName.lastIndexOf(" ");
                String productName = tempProdName.substring(productNameIndex);

                String tempProdId = holder.tv_productId.getText().toString();
                int productIdIndex = tempProdId.lastIndexOf(" ");
                String productId = tempProdId.substring(productIdIndex);

                String tempFarmerName = holder.tv_farmerName.getText().toString();
                int farmerNameIndex = tempFarmerName.lastIndexOf(" ");
                String farmerName= tempFarmerName.substring(farmerNameIndex);

                String tempFarmerId = holder.tv_farmerId.getText().toString();
                int farmerIdIndex = tempFarmerId.lastIndexOf(" ");
                String farmerId = tempFarmerId.substring(farmerIdIndex);

                String tempProdCategory = holder.tv_prodCategory.getText().toString();
                int productCategoryIndex = tempProdCategory.lastIndexOf(" ");
                String productCategory = tempProdCategory.substring(productCategoryIndex);

                String tempProdQty = holder.tv_qty.getText().toString();
                int productQtyIndex = tempProdQty.lastIndexOf(" ");
                String productQuantity= tempProdQty.substring(productQtyIndex);

                String tempBasePrice = holder.tv_basePrice.getText().toString();
                int basePriceIndex = tempBasePrice.lastIndexOf(" ");
                String basePrice = tempBasePrice.substring(basePriceIndex);

                ArrayList<String> productDetails= new ArrayList<String>();
                productDetails.add(productCategory);
                productDetails.add(productId);
                productDetails.add(productName);
                productDetails.add(farmerName);
                productDetails.add(farmerId);
                productDetails.add(productQuantity.replaceAll("[^0-9]", ""));
                productDetails.add(basePrice.replaceAll("[^0-9]", ""));

                Intent intent = new Intent(context,bidding.class);
                intent.putExtra("ProductDetails",productDetails);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_pName;
        TextView tv_farmerName,tv_productId;
        TextView tv_farmerId,tv_basePrice,tv_qty,tv_prodCategory;
        CardView cardView;

        public MyViewHolder(View itemView){

            super(itemView);
            tv_pName = itemView.findViewById(R.id.productName);
            tv_farmerName = itemView.findViewById(R.id.farmerName);
            tv_basePrice = itemView.findViewById(R.id.BasePrice);
            tv_qty = itemView.findViewById(R.id.farmerProductQuantity);
            tv_farmerId = itemView.findViewById(R.id.farmerID);
            tv_productId = itemView.findViewById(R.id.productId);
            tv_prodCategory = itemView.findViewById(R.id.productCategory);
            cardView = itemView.findViewById(R.id.cardview_id2);

        }
    }
}

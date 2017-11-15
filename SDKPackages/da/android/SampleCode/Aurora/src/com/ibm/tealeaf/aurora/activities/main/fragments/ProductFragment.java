/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora.activities.main.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibm.tealeaf.aurora.AuroraHelper;
import com.ibm.tealeaf.aurora.AuroraListItem;
import com.ibm.tealeaf.aurora.R;
import com.ibm.tealeaf.aurora.tagging.TagProductView;
import com.ibm.tealeaf.aurora.tagging.TagShopAction5;

/**
 * Product fragment
 * 
 * @author cyang
 * 
 */
public class ProductFragment extends Fragment {

    private AuroraListItem mProduct;

    private TextView mTitle;
    private TextView mPrice;
    private TextView mDescription;
    private ImageView mImage;
    private Button mAddToCartButton;

    private ProgressDialog mProgressDialog;

    public ProductFragment(AuroraListItem product) {
        mProduct = product;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        mTitle = (TextView) view.findViewById(R.id.product_titleTextView);
        mTitle.setText(mProduct.getTitle());

        mPrice = (TextView) view.findViewById(R.id.product_priceTextView);
        mPrice.setText(mProduct.getPrice());

        mDescription = (TextView) view.findViewById(R.id.product_descriptionTextView);
        mDescription.setText(mProduct.getFullDescription());

        mImage = (ImageView) view.findViewById(R.id.product_mainImageView);
        AuroraHelper.requestProductImage(mProduct.getImageUrl(), this);

        mAddToCartButton = (Button) view.findViewById(R.id.product_add_button);
        if (mAddToCartButton != null) {
            mAddToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mProgressDialog = ProgressDialog.show(ProductFragment.this.getActivity(), null,
                            "Adding Item To Cart", true);
                    AuroraHelper.requestAddToCart(mProduct.getId(), ProductFragment.this);
                    
                    //TAGGING: Tag a ShopAction5 for this Product
                    final TagShopAction5 tag = new TagShopAction5(mProduct);
                    tag.executeTag();
                }

            });
        }
        
        
        //Tagging: Tag this ProductView
        final TagProductView tag = new TagProductView(this, mProduct);
        tag.executeTag();

        return view;
    }

    // Called by AuroraHelper
    public void setProductImage(final Bitmap bitmap) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mImage.setImageBitmap(bitmap);
            }
        });
    }

    // Called by AuroraHelper
    public void addToCardCompleted(final String message) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();

                new AlertDialog.Builder(ProductFragment.this.getActivity()).setMessage(message)
                        .setNegativeButton("View Cart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == DialogInterface.BUTTON_NEGATIVE) {
                                    // Go to cart fragment tag
                                    ((ActionBarActivity) getActivity()).getSupportActionBar()
                                            .setSelectedNavigationItem(1);
                                }
                            }
                        }).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                            }
                        }).show();
            }
        });
    }
}

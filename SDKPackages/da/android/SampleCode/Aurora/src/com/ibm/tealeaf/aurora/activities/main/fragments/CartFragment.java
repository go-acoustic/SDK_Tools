/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
package com.ibm.tealeaf.aurora.activities.main.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ibm.tealeaf.adapter.ShopAdapter;
import com.ibm.tealeaf.aurora.AuroraHelper;
import com.ibm.tealeaf.aurora.AuroraListItem;
import com.ibm.tealeaf.aurora.R;
import com.ibm.tealeaf.aurora.Utils;
import com.ibm.tealeaf.aurora.activity.CheckoutActivity;
import com.ibm.tealeaf.aurora.tagging.TagSession;

public class CartFragment extends ListFragment implements FragmentRefreshListener {
    private ShopAdapter mListAdapter;

    private Button mCheckoutButton;
    private TextView mGrandTotalTextView;
    private TextView mDiscountTotalTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mCheckoutButton = (Button) view.findViewById(R.id.cart_checkout_Button);
        if (mCheckoutButton != null) {
            mCheckoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // mProgressDialog = ProgressDialog.show(CartFragment.this.getActivity(), null,
                    // "Submitting Your Order", true);
                    // AuroraHelper.requestCheckout(CartFragment.this);
                    startActivity(new Intent(getActivity(), CheckoutActivity.class));
                }

            });
        }
        mGrandTotalTextView = (TextView) view.findViewById(R.id.cart_price);
        mDiscountTotalTextView = (TextView) view.findViewById(R.id.cart_discount);
        return view;
    }

    public void setPrice(final String price) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mGrandTotalTextView.setText(Utils.toCurrency(price));
            }
        });
        
        //TAGGING: Record the subtotal
        TagSession.getInstance().setOrderSubtotal(price);
    }

    public void setDiscount(final String discount) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDiscountTotalTextView.setText(Utils.toCurrency(discount));
            }
        });
    }

    public void showCart(final boolean show) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (show) {
                    CartFragment.this.getView().findViewById(R.id.cart_checkout_Button).setEnabled(true);
                    CartFragment.this.getView().findViewById(R.id.cart_emptyTextView).setVisibility(View.GONE);
                    CartFragment.this.getView().findViewById(R.id.cart_contentsLayout).setVisibility(View.VISIBLE);
                } else {
                    CartFragment.this.getView().findViewById(R.id.cart_checkout_Button).setEnabled(false);
                    CartFragment.this.getView().findViewById(R.id.cart_emptyTextView).setVisibility(View.VISIBLE);
                    CartFragment.this.getView().findViewById(R.id.cart_contentsLayout).setVisibility(View.GONE);
                }
            }
        });
    }

    public void setCartListAdapter(final ArrayList<AuroraListItem> shopEntryList) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListAdapter = new ShopAdapter(getActivity(), shopEntryList);
                setListAdapter(mListAdapter);
            }
        });
        
        //TAGGING: Record the cartItems
        TagSession.getInstance().setCartItems(shopEntryList);
    }

    @Override
    public void fragmentIsVisible() {
        if (mListAdapter != null) {
            mListAdapter.clear();
        }
        if (mGrandTotalTextView != null) {
            mGrandTotalTextView.setText("");
        }
        if (mDiscountTotalTextView != null) {
            mDiscountTotalTextView.setText("");
        }
        AuroraHelper.requestCart(this);
    }
}

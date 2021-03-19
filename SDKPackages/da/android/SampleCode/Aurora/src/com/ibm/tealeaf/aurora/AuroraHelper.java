/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
package com.ibm.tealeaf.aurora;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.ibm.tealeaf.aurora.activities.login.LoginActivity;
import com.ibm.tealeaf.aurora.activities.login.LoginFragment;
import com.ibm.tealeaf.aurora.activities.main.MainActivity;
import com.ibm.tealeaf.aurora.activities.main.fragments.CartFragment;
import com.ibm.tealeaf.aurora.activities.main.fragments.OrdersFragment;
import com.ibm.tealeaf.aurora.activities.main.fragments.ProductFragment;
import com.ibm.tealeaf.aurora.activities.main.fragments.ShopListFragment;

/**
 * This controller class helps to maintain the relation between the view and the. Aurora site
 * 
 * @author JustinManeri
 * 
 */
public class AuroraHelper {
    public final static String TAG = "AuroraHelper";

    // These hold authentication tokens for Aurora transactions
    private static String sHeader_WCToken;
    private static String sHeader_WCTrustedToken;

    // Check if session objects are initialized
    private static boolean isLoggedIn() {
        if (sHeader_WCToken == null || sHeader_WCTrustedToken == null) {
            return false;
        }
        return true;
    }

    // Clear any session data
    public static void clearToken() {
        sHeader_WCToken = null;
        sHeader_WCTrustedToken = null;
    }

    // Log out
    public static void logOut(final Activity activity) {
        new AlertDialog.Builder(activity).setMessage(R.string.logout_warning)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(activity, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(i);

                        // TODO Clear AuroraHelper Cookies
                        AuroraHelper.clearToken();
                        // Stop back button from returning after activity transition
                        activity.finish();
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User cancelled the dialog
                    }
                }).show();
    }

    public static void requestLogin(final String username, final String password, final LoginFragment viewFragment) {
        new Thread() {
            @Override
            public void run() {
                String alertMessage = null;
                String requestJson = "{\"logonId\": \"" + username + "\",\"logonPassword\": \"" + password + "\"}";

                AuroraNetworkRequest arn = new AuroraNetworkRequest(Consts.API_LOGIN, HttpRequestType.POST, true);

                // Check to see if the server logged the application in successfully
                // if it did, store the session header tokens
                try {

                    arn.setHttpRequestBody(requestJson);
                    HttpResponse httpResponse = arn.execute();

                    if (httpResponse == null) {
                        alertMessage = "Network Error";
                    } else {
                        JSONObject response = new JSONObject(arn.getHttpResponseMessage());
                        if (response.has("WCToken") && response.has("WCTrustedToken")) {
                            AuroraHelper.sHeader_WCToken = response.getString("WCToken");
                            AuroraHelper.sHeader_WCTrustedToken = response.getString("WCTrustedToken");
                        } else {
                            alertMessage = response.getJSONArray("errors").getJSONObject(0).getString("errorMessage");
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                }

                // Set Alert Message
                if (isLoggedIn()) {
                    alertMessage = "Login Successful"; // TODO reference string
                } else {
                    if (alertMessage == null) {
                        // TODO reference string
                        alertMessage = "Reason Unknown - Status Code " + arn.getHttpResponseStatusCode();
                    }
                    alertMessage = "Login Failed - " + alertMessage; // TODO reference string
                }

                // Tell the view what to do
                // If the fragment has not been removed (back button)
                if (viewFragment.isAdded()) {
                    viewFragment.login(isLoggedIn(), alertMessage);
                }
            }
        }.start();
    }

    public static void requestParentCategory(final ShopListFragment viewFragment) {
        requestCategory(null, viewFragment);
    }

    public static void requestCategory(final String uniqueId, final ShopListFragment viewFragment) {
        new Thread() {
            @Override
            public void run() {
                String url = (uniqueId == null) ? Consts.API_CATEGORY_PARENT : Consts.API_CATEGORY + uniqueId;
                AuroraNetworkRequest arn = new AuroraNetworkRequest(url);

                arn.execute();

                // Make sure the request was successful before allocating memory
                if (arn.getHttpResponseStatusCode() == HttpStatus.SC_OK) {
                    try {
                        JSONArray catalog = new JSONObject(arn.getHttpResponseMessage())
                                .getJSONArray("CatalogGroupView");

                        JSONObject catalogItem;

                        ArrayList<AuroraListItem> itemList = new ArrayList<AuroraListItem>();
                        AuroraListItem item;
                        String thumbnailUrl;

                        for (int i = 0; i < catalog.length(); i++) {
                            catalogItem = catalog.getJSONObject(i);

                            item = new AuroraListItem(catalogItem.getString("uniqueID"));
                            item.setTitle(catalogItem.getString("name"));

                            thumbnailUrl = "http://" + Consts.CATALOG_THUMBNAIL_DIR
                                    + catalogItem.getString("thumbnail").replace("\\", "");
                            item.setThumbnail(requestImage(thumbnailUrl));

                            itemList.add(item);
                        }

                        // If the fragment has not been removed (back button)
                        if (viewFragment.isAdded()) {
                            viewFragment.setShopListAdapter(itemList); // TODO Make a different function name
                        }

                    } catch (JSONException e) {
                        // TODO Error handle when response is 200 but does not contain correct JSON
                        Log.e(TAG, e.getMessage());
                    }
                } else if (arn.getHttpResponseStatusCode() == HttpStatus.SC_NOT_FOUND) {
                    // If a 404 is returned, there is a chance you were at the
                    // end of the catalog and need to request a product
                    requestProductList(uniqueId, viewFragment);
                } else {
                    // TODO Error handle when network call fails (non 200 or 404 status code)
                    Log.e(TAG, "Network Error:  " + arn.getHttpResponseStatusCode());
                }

            }
        }.start();
    }

    public static void requestProductList(final String uniqueId, final ShopListFragment viewFragment) {
        new Thread() {
            @Override
            public void run() {
                String url = Consts.API_PRODUCT + uniqueId;
                AuroraNetworkRequest arn = new AuroraNetworkRequest(url);

                arn.execute();

                if (arn.getHttpResponseStatusCode() == HttpStatus.SC_OK) {
                    try {
                        JSONArray catalog = new JSONObject(arn.getHttpResponseMessage())
                                .getJSONArray("CatalogEntryView");

                        JSONObject catalogItem;

                        ArrayList<AuroraListItem> productList = new ArrayList<AuroraListItem>();
                        AuroraListItem product;
                        String imageUrl;
                        String thumbnailUrl;

                        for (int i = 0; i < catalog.length(); i++) {
                            catalogItem = catalog.getJSONObject(i);
                            product = new AuroraListItem(catalogItem.getString("uniqueID"));
                            product.setTitle(catalogItem.getString("name"));
                            product.setSubtitle(catalogItem.getString("shortDescription"));
                            product.setmFullDescription(catalogItem.getString("longDescription"));
                            product.setPrice(catalogItem.getJSONArray("Price").getJSONObject(0).getString("priceValue"));

                            thumbnailUrl = "http://" + Consts.HOSTNAME
                                    + catalogItem.getString("thumbnail").replace("\\", "");
                            imageUrl = Consts.HOSTNAME + (catalogItem.getString("fullImage").replace("\\", ""));
                            product.setThumbnail(requestImage(thumbnailUrl));
                            product.setImageUrl(imageUrl);

                            productList.add(product);
                        }

                        // If the fragment has not been removed (back button)
                        if (viewFragment.isAdded()) {
                            viewFragment.setShopListAdapter(productList, true); // TODO Make a different function name
                        }
                    } catch (JSONException e) {
                        // TODO Error handle when response is 200 but does not contain correct JSON
                        Log.e(TAG, "Incorrect JSON response.");
                    }
                } else {
                    // TODO Error handle when network call fails (non 200 status code)
                    Log.e(TAG, "Network Error:  " + arn.getHttpResponseStatusCode());
                }
            }
        }.start();
    }

    public static void requestAddToCart(final String uniqueId, final ProductFragment viewFragment) {
        new Thread() {
            @Override
            public void run() {
                String url = Consts.API_ADD_TO_CART;
                AuroraNetworkRequest arn = new AuroraNetworkRequest(url, HttpRequestType.POST, true);

                String requestBody = "{\"orderItem\": [{\"quantity\": \"1\",\"productId\": \""
                        + requestFirstSku(uniqueId) + "\"}]}";
                arn.setHttpRequestBody(requestBody);

                // Add authentication headers
                setAuthentication(arn);

                arn.execute();

                String alertMessage;
                if (arn.getHttpResponseStatusCode() == HttpStatus.SC_CREATED) {
                    alertMessage = "Item successfully added to your cart.";
                } else {
                    alertMessage = "Item could not be added to your cart.";
                }

                // If the fragment has not been removed (back button)
                if (viewFragment.isAdded()) {
                    viewFragment.addToCardCompleted(alertMessage);
                }
            }
        }.start();
    }

    public static void requestProductImage(final String imageUrl, final ProductFragment viewFragment) {
        new Thread() {
            @Override
            public void run() {
                AuroraNetworkRequest arn = new AuroraNetworkRequest("http://" + imageUrl);
                arn.execute();

                // If the fragment has not been removed (back button)
                if (viewFragment.isAdded()) {
                    viewFragment.setProductImage(arn.getHttpResponseImage());
                }
            }
        }.start();
    }

    public static void requestCart(final CartFragment viewFragment) {
        new Thread() {
            @Override
            public void run() {
                AuroraNetworkRequest arn = new AuroraNetworkRequest(Consts.API_CART, HttpRequestType.GET, true);
                setAuthentication(arn);

                arn.execute();

                if (arn.getHttpResponseStatusCode() == HttpStatus.SC_OK) {
                    if (viewFragment.isAdded()) {
                        viewFragment.showCart(true);
                    }
                    try {
                        JSONObject json = new JSONObject(arn.getHttpResponseMessage());
                        JSONArray jsonItems = json.getJSONArray("orderItem");

                        String cartTotalPrice = json.getString("grandTotal");

                        String cartDiscount = json.getJSONArray("adjustment").getJSONObject(0).getString("amount");

                        final ArrayList<AuroraListItem> productList = new ArrayList<AuroraListItem>();
                        for (int i = 0; i < jsonItems.length(); i++) {
                            String productId = jsonItems.getJSONObject(i).getString("productId");
                            productList.add(requestCartProduct(productId));
                        }

                        // If the fragment has not been removed (back button)
                        if (viewFragment.isAdded()) {
                            viewFragment.setPrice(cartTotalPrice);
                            viewFragment.setDiscount(cartDiscount);
                            viewFragment.setCartListAdapter(productList);
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    if (viewFragment.isAdded()) {
                        viewFragment.showCart(false);
                    }
                }
            }
        }.start();
    }

    public static void requestOrders(final OrdersFragment viewFragment) {
        new Thread() {
            @Override
            public void run() {
                AuroraNetworkRequest arn = new AuroraNetworkRequest(Consts.API_ORDERS, HttpRequestType.GET, true);
                setAuthentication(arn);
                arn.execute();

                if (arn.getHttpResponseStatusCode() == HttpStatus.SC_OK) {
                    try {
                        JSONArray ordersJson = new JSONObject(new String(arn.getHttpResponseMessage()))
                                .getJSONArray("Order");
                        ArrayList<AuroraListItem> orderList = new ArrayList<AuroraListItem>();
                        AuroraListItem item;

                        for (int i = 0; i < ordersJson.length(); i++) {
                            JSONObject orderJson = ordersJson.getJSONObject(i);

                            item = new AuroraListItem(orderJson.getString("orderId"));

                            /* Parse date string, handle zulu time format and display user's preferred locale/timezone */
                            final DateFormat outputFormat = new SimpleDateFormat(Consts.INPUT_DATE_PATTERN,
                                    Locale.getDefault());
                            final Date tempDate = outputFormat.parse(orderJson.getString("placedDate").replaceAll("Z$",
                                    "+0700"));
                            final String dateStr = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,
                                    Locale.getDefault()).format(tempDate);

                            String title = "Order " + item.getId() + ": " + dateStr;
                            String subtitle = "Total: " + Utils.toCurrency(orderJson.getString("totalProductPrice"))
                                    + " " + orderJson.getString("totalProductPriceCurrency");

                            item.setTitle(title);
                            item.setSubtitle(subtitle);

                            orderList.add(item);
                        }

                        if (viewFragment.isAdded()) {
                            viewFragment.setCartListAdapter(orderList);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }.start();
    }

    public final static void requestCheckout(final FragmentActivity activity, final ProgressDialog dialog) {
        new Thread() {
            @Override
            public void run() {
                AuroraNetworkRequest arn = new AuroraNetworkRequest(Consts.API_PRECHECKOUT, HttpRequestType.PUT, true);
                setAuthentication(arn);
                arn.execute();

                if (arn.getHttpResponseStatusCode() == HttpStatus.SC_OK) {
                    arn = new AuroraNetworkRequest(Consts.API_CHECKOUT, HttpRequestType.POST, true);
                    setAuthentication(arn);
                    arn.execute();

                    final boolean successful = (arn.getHttpResponseStatusCode() == HttpStatus.SC_CREATED);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (dialog != null) {
                                dialog.dismiss();
                            }

                            String message;
                            if (successful) {
                                message = "Order Processed Successfully";
                            } else {
                                message = "Order Failed to Process";
                            }
                            new AlertDialog.Builder(activity).setMessage(message).setCancelable(false)
                                    .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // If submit order was successful, transition to MainActivity
                                            if (successful) {
                                                Intent intent = new Intent(activity, MainActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.putExtra("type", "submit");
                                                activity.startActivity(intent);
                                                activity.finish();
                                            } else {
                                                dialog.cancel();
                                            }
                                        }
                                    }).show();
                        }
                    });
                }
            }
        }.start();
    }

    // Add authentication to an AuroraNetworkRequest
    private static void setAuthentication(AuroraNetworkRequest arn) {
        arn.addHttpRequestHeader("WCToken", sHeader_WCToken);
        arn.addHttpRequestHeader("WCTrustedToken", sHeader_WCTrustedToken);
    }

    // Cannot be called on main thread
    private static AuroraListItem requestCartProduct(String uniqueId) {
        String url = Consts.API_PRODUCT_BY_ID + uniqueId;
        AuroraNetworkRequest arn = new AuroraNetworkRequest(url);
        arn.execute();

        if (arn.getHttpResponseStatusCode() != HttpStatus.SC_OK) {
            Log.e(TAG, "Http Error: " + arn.getHttpResponseStatusCode());
            return null; // Error with network request
        }

        try {
            JSONObject catalogItem = new JSONObject(arn.getHttpResponseMessage()).getJSONArray("CatalogEntryView")
                    .getJSONObject(0);
            AuroraListItem product = new AuroraListItem(catalogItem.getString("uniqueID"));
            product.setTitle(catalogItem.getString("name"));
            product.setSubtitle(catalogItem.getString("shortDescription"));
            product.setmFullDescription(catalogItem.getString("longDescription"));
            product.setPrice(catalogItem.getJSONArray("Price").getJSONObject(0).getString("priceValue"));

            String thumbnailUrl = "http://" + Consts.HOSTNAME + catalogItem.getString("thumbnail").replace("\\", "");
            product.setThumbnail(requestImage(thumbnailUrl));

            return product;

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    // Cannot be called on main thread
    private static String requestFirstSku(String uniqueId) {
        String url = Consts.API_PRODUCT_BY_ID + uniqueId;
        AuroraNetworkRequest arn = new AuroraNetworkRequest(url);

        arn.execute();

        if (arn.getHttpResponseStatusCode() != HttpStatus.SC_OK) {
            return null;
        }

        String productFirstSku = null;
        try {
            productFirstSku = new JSONObject(arn.getHttpResponseMessage()).getJSONArray("CatalogEntryView")
                    .getJSONObject(0).getJSONArray("SKUs").getJSONObject(0).getString("SKUUniqueID");
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

        return productFirstSku;
    }

    // Cannot be called on main thread
    private static Bitmap requestImage(String url) {
        AuroraNetworkRequest arn = new AuroraNetworkRequest(url);
        arn.execute();

        return arn.getHttpResponseImage();
    }
}

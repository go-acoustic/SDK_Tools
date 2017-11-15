/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora;

import android.graphics.Bitmap;

public class AuroraListItem {
    private Bitmap mThumbnail;
    private String mTitle;
    private String mSubtitle;
    private final String mId;

    // When ListItem is a Product
    private String mPrice;
    private String mImageUrl;
    private String mFullDescription;

    public AuroraListItem(final String id) {
        mId = id;
    }

    public final Bitmap getThumbnail() {
        return mThumbnail;
    }

    public final void setThumbnail(final Bitmap thumbnail) {
        this.mThumbnail = thumbnail;
    }

    public final String getTitle() {
        return mTitle;
    }

    public final void setTitle(final String title) {
        this.mTitle = title;
    }

    public final String getSubtitle() {
        return mSubtitle;
    }

    public final void setSubtitle(final String subtitle) {
        this.mSubtitle = subtitle;
    }

    public final String getPrice() {
        return mPrice;
    }

    public final void setPrice(final String price) {
        mPrice = Utils.toCurrency(price);
    }

    public final String getImageUrl() {
        return mImageUrl;
    }

    public final void setImageUrl(final String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public final String getFullDescription() {
        return mFullDescription;
    }

    public final void setmFullDescription(final String fullDescription) {
        this.mFullDescription = fullDescription;
    }

    public final String getId() {
        return mId;
    }

}

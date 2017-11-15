package tl.hybridhtmlembedded;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ZoomButtonsController;

import com.ibm.eo.util.LogInternal;
import com.tl.uic.Tealeaf;

import java.lang.reflect.Method;

public class MainActivity extends Activity {
    private String logicalPageName = "HybridAppActivity";
    //private GestureDetectorCompat detector;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Initialize tealeaf with a reference to application
		@SuppressWarnings("unused")
        Tealeaf tealeaf = new Tealeaf(this.getApplication());
        Tealeaf.enable();
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        createWebView(R.id.myWebView1, "file:///android_asset/mobile_domcap/embeddedAppsMenu.html");
//        createWebView(R.id.myWebView2, "file:///android_asset/mobile_domcap/embeddedGesturesMenu.html");
        
//        Button button = (Button) findViewById(R.id.button1);
//        button.setOnClickListener(getOnClickListener());
//        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
//        checkBox.setOnClickListener(getOnClickListener());
        
        if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }

        Tealeaf.logScreenLayout(this, logicalPageName, 2000);
	}
	
	@SuppressLint("SetJavaScriptEnabled")
    private void createWebView(final int id, final String url) {
	    final MyWebView myWebView = (MyWebView) findViewById(id);
        myWebView.clearCache(true);
        
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        
        // Remove the zuto zoom
        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
          // Use the API 11+ calls to disable the controls
          // Use a separate class to obtain 1.6 compatibility
          new Runnable() {
            public void run() {
                myWebView.getSettings().setDisplayZoomControls(false);
            }
          }.run();
        } else {
            try {
                Method zoomMethod = myWebView.getClass().getMethod("getZoomButtonsController");
                if (zoomMethod != null) {
                    Object[] value = null;
                    final ZoomButtonsController zoom_controll = (ZoomButtonsController) zoomMethod.invoke(myWebView, value);
                    zoom_controll.getContainer().setVisibility(View.GONE);
                }
            } catch (Exception e) {
                LogInternal.logException("HybridHTMLEmbedded", e);
            }
        }
        
        myWebView.loadUrl(url);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
	}
	
	public static OnClickListener getOnClickListener() {
		OnClickListener onClickListener = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Tealeaf.logEvent(view);
			}
		};
		return onClickListener;
	}
	
	/**
     * {@inheritDoc}
     */
    protected void onPause() {
        Tealeaf.onPause(this, logicalPageName);
        super.onPause();
    }

    /**
     * {@inheritDoc}
     */
    protected void onResume() {
        Tealeaf.onResume(this, logicalPageName);
        //detector = new GestureDetectorCompat(this, new TLGestureDetector(this));
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    protected void onDestroy() {
        Tealeaf.onDestroy(this, logicalPageName);
        super.onDestroy();
    }
    
    /* Add touch event to collect gestures for Tealeaf.
     * 
     * (non-Javadoc)
     * @see android.app.Activity#dispatchTouchEvent(android.view.MotionEvent)
     */
    public boolean dispatchTouchEvent(MotionEvent e)
    {
        //detector.onTouchEvent(e);
        Tealeaf.dispatchTouchEvent(this, e);
        return super.dispatchTouchEvent(e);
    }
}

package pro.francisco.cordova.webp;

import android.backport.webp.WebPFactory;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Base64;
import android.util.Log;

public class WebPEncoderDecoder extends CordovaPlugin {
    
    private static enum Action {
        encode,
        decode
    }

    @Override
    public boolean execute(String actionAsString, JSONArray args, CallbackContext cbc) {

        Action action;
        try {
            action = Action.valueOf(actionAsString);
        } catch (IllegalArgumentException e) {
            // shouldn't ever happen
            Log.e(WebPEncoderDecoder.class.getSimpleName(), "unexpected error", e);
            return false;
        }

        try {
            return executeAndPossiblyThrow(action, args, cbc);
        } catch (JSONException e) {
            // TODO: signal JSON problem to JS
            Log.e(WebPEncoderDecoder.class.getSimpleName(), "unexpected error", e);
            return false;
        }
    }

    private boolean executeAndPossiblyThrow(Action action, JSONArray args, CallbackContext cbc)
            throws JSONException {

        boolean status = true;
        JSONObject o;

        switch (action) {

            case encode:
                o = args.getJSONObject(0);
                String data = o.getString("data");

                byte[] imageAsBytes = Base64.decode(data.getBytes(), Base64.DEFAULT);
                Bitmap selectedBitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                byte[] webpImageData = WebPFactory.nativeEncodeBitmap(selectedBitmap, 100);

                String encodedImage = Base64.encodeToString(webpImageData, Base64.DEFAULT);

                // Bitmap webpBitmap = WebPFactory.nativeDecodeByteArray(webpImageData, null);
                // TODO: if the db is open, must put request in the q to close & delete the db
                //status = this.deleteDatabase(dbname);
                // deleteDatabase() requires an async callback
                //if (status) {
                cbc.success(encodedImage);
                //} else {
                //    cbc.error("couldn't delete database");
                //}
                break;
        }
        
        return status;
    }

}

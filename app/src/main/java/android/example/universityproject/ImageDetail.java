package android.example.universityproject;

import android.net.Uri;

public class ImageDetail {
    String uri;
    ImageDetail(){

    }

    ImageDetail(String uri){
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

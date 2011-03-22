package br.com.jera;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayerActivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String url = (String) this.getIntent().getExtras().get("url");
		setContentView(R.layout.player);
		VideoView video =  (VideoView)findViewById(R.id.videoView);
		MediaController mc=new MediaController(this);
        mc.setEnabled(true);
        mc.show(0);
        video.setMediaController(mc); 
        video.setVideoURI(Uri.parse(url));
        video.requestFocus();
        video.showContextMenu();
        video.start();         
	}
}

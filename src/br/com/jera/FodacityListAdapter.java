package br.com.jera;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mcsoxford.rss.MediaThumbnail;
import org.mcsoxford.rss.RSSItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FodacityListAdapter extends ArrayAdapter<RSSItem> {

	private List<RSSItem> items;
	private Map<Integer, Drawable> cache = new HashMap<Integer, Drawable>();

	public FodacityListAdapter(Context context, List<RSSItem> items) {
		super(context, R.layout.item, R.id.title, items);
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout layout = (LinearLayout) super.getView(position, convertView, parent);
		ImageButton button = (ImageButton) layout.findViewById(R.id.thumb);
		TextView textView = (TextView) layout.findViewById(R.id.title);
		final RSSItem item = items.get(position);
		textView.setText(item.getTitle());

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink().toString())));
				Intent videoClient = new Intent(Intent.ACTION_VIEW);
				videoClient.setData(Uri.parse(item.getLink().toString()));
				videoClient.setClassName("com.google.android.youtube", "com.google.android.youtube.PlayerActivity");
				getContext().startActivity(videoClient);
				
				Intent i = new Intent(getContext(), PlayerActivity.class);
				i.putExtra("url", item.getLink().toString());
				getContext().startActivity(i);
			}
		});

		try {
			button.setBackgroundDrawable(loadThumb(items.get(position), position));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return layout;
	}

	private Drawable loadThumb(RSSItem item, Integer position) throws IOException {
		if (cache.containsKey(position)) {
			return cache.get(position);

		} else {
			MediaThumbnail thumb = item.getThumbnails().get(0);
			URL url = new URL(thumb.getUrl().toString());
			Drawable d = Drawable.createFromStream(((InputStream) url.getContent()), thumb.getUrl().getLastPathSegment());
			cache.put(position, d);
			return d;
		}
	}
}

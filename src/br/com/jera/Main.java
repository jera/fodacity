package br.com.jera;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Main extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		ListView listView = (ListView) findViewById(R.id.list);
		RSSReader reader  = new RSSReader();
		RSSFeed feed;
		try {
			feed = reader.load("http://labs.jera.com.br/fodacity/feed.xml");
			listView.setAdapter(new FodacityListAdapter(this, feed.getItems()));
		} catch (RSSReaderException e) {
			e.printStackTrace();
		}
	}
}

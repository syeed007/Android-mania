package mahbubul.syeed.mylistviewactionbarcontextmenu;

import java.util.List;

import mahbubul.syeed.mylistviewfunctionalitytest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {
	Context mContext;
	List<String> mContent;

	public CustomArrayAdapter(Context context, List<String> content) {
		super(context, R.layout.listitem_layout_custom, content);
		mContext = context;
		mContent = content;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TextView textView;
		ImageView imageView;
		View rowView;

		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			rowView = inflater.inflate(R.layout.listitem_layout_custom, parent,
					false);

			textView = (TextView) rowView.findViewById(R.id.secondLine);
			imageView = (ImageView) rowView.findViewById(R.id.icon);
		} else {
			rowView = convertView;

			textView = (TextView) rowView.findViewById(R.id.secondLine);
			imageView = (ImageView) rowView.findViewById(R.id.icon);
		}

		textView.setText(mContent.get(position));
		imageView.setImageResource(R.drawable.ic_launcher);
		// change the icon for Windows and iPhone
		/*
		 * String s = mContent[position]; if (s.startsWith("iPhone")) {
		 * imageView.setImageResource(R.drawable.no); } else {
		 * imageView.setImageResource(R.drawable.ok); }
		 */
		return rowView;
	}
} // end class

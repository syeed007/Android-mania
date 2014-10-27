package com.example.filereadwrite;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	EditText fname, fcontent, fnameread;
	Button write, read, delete, createDirectory;
	TextView filecon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fname = (EditText) findViewById(R.id.fname);
		fcontent = (EditText) findViewById(R.id.ftext);
		fnameread = (EditText) findViewById(R.id.fnameread);
		write = (Button) findViewById(R.id.btnwrite);
		read = (Button) findViewById(R.id.btnread);
		delete = (Button) findViewById(R.id.btndelete);
		createDirectory = (Button) findViewById(R.id.btndirectory);
		filecon = (TextView) findViewById(R.id.filecon);
		write.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String filename = fname.getText().toString();
				String filecontent = fcontent.getText().toString();
				FileOperations fop = new FileOperations();
				fop.write(filename, filecontent);
				if (fop.write(filename, filecontent)) {
					Toast.makeText(getApplicationContext(),
							filename + ".txt created", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(getApplicationContext(), "I/O error",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		read.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String readfilename = fnameread.getText().toString();
				FileOperations fop = new FileOperations();
				String text = fop.read(readfilename);
				String filePath = fop.getFilePath(readfilename);
				
				if (text != null) {
					filecon.setText(text + "\n" + filePath);
				} else {
					Toast.makeText(getApplicationContext(), "File not Found",
							Toast.LENGTH_SHORT).show();
					filecon.setText(null);
				}
			}
		});
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String deletefilename = fnameread.getText().toString();
				FileOperations fop = new FileOperations();
				boolean isDeleted = fop.delete(deletefilename);
				if (isDeleted) {
					Toast.makeText(getApplicationContext(), "Deleted successfully!",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "File not Found",
							Toast.LENGTH_SHORT).show();
					filecon.setText(null);
				}
			}
		});
		createDirectory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String directoryName = fnameread.getText().toString();
				FileOperations fop = new FileOperations();
				boolean isDirCreated = fop.createDirectory(directoryName);
				if (isDirCreated) {
					Toast.makeText(getApplicationContext(), "Directory created successfully!",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "Directory Creation failed",
							Toast.LENGTH_SHORT).show();
					filecon.setText(null);
				}
			}
		});
	}
}

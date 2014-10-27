package com.example.filereadwrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

public class FileOperations {
	public FileOperations() {
	}

	public Boolean createDirectory(String dirName){
		File mydir = new File(Environment.getExternalStorageDirectory() + dirName);
		if(!mydir.exists())	
		{
		    mydir.mkdirs();
		    return true;
		}
		else
		    return false;
	}
	
	public Boolean write(String fname, String fcontent) {
		try {
			String fpath = "/sdcard/" + fname + ".txt";
			File file = new File(fpath);
			// If file does not exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fcontent);
			bw.close();
			Log.d("Suceess", "Sucess");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String read(String fname) {
		BufferedReader br = null;
		String response = null;
		try {
			StringBuffer output = new StringBuffer();
			String fpath = "/sdcard/" + fname + ".txt";
			br = new BufferedReader(new FileReader(fpath));
			String line = "";
			while ((line = br.readLine()) != null) {
				output.append(line + "\n");
			}
			response = output.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}

	public String getFilePath(String fname){
		File file = new File(fname);
		return file.getAbsolutePath();
	}
	public boolean delete(String deletefilename) {
		// TODO Auto-generated method stub
		String fpath = "/sdcard/" + deletefilename + ".txt";
		File file = new File(fpath);
		return file.delete();
		
	}

}

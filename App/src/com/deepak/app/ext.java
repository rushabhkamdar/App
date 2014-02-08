package com.deepak.app;

import java.io.File;
import java.util.ArrayList;

import android.util.Log;

public class ext {

	ArrayList<String> fileList = new ArrayList<String>();
	ArrayList<String> fileList2 = new ArrayList<String>();
	ArrayList<String> extnlist;
	
	public ext()
	{
		fileList = new ArrayList<String>();
		fileList2 = new ArrayList<String>();
		extnlist = new ArrayList<String>();
		extnlist.add("JPG");
		extnlist.add("mp3");
		extnlist.add("mp4");
		extnlist.add("png");
		extnlist.add("pdf");
		extnlist.add("epub");
		extnlist.add("apk");
		extnlist.add("PDF");
		extnlist.add("log");
		extnlist.add("zip");
		extnlist.add("html");
		extnlist.add("doc");
		extnlist.add("mobi");
		extnlist.add("xlsx");
		extnlist.add("pptx");
		extnlist.add("docx");
		extnlist.add("MP3");
		extnlist.add("xls");
	}
	
	public ArrayList<String> getfile(File dir)//pass fileType as a music , video, etc.               
	{
	    File listFile[] = dir.listFiles();
	    if (listFile != null && listFile.length > 0) 
	    {
	        for (int i = 0; i < listFile.length; i++) 
	        {

	            if (listFile[i].isDirectory()) 
	            {
	                getfile(listFile[i]);

	            } 
	            else 
	            {
	            	String temp;
	            	boolean ret=true;
	            	temp=listFile[i].toString().substring((listFile[i].toString().lastIndexOf(".")+1), listFile[i].toString().length());
	            	for(int j=0;j<fileList.size();j++)
	            	{
	            		if(temp.equals(fileList.get(j)))
	            		{
	            			ret=false;
	            			break;
	            		}
	            	}
	            	if(listFile[i].isHidden() || temp.length()>4)
	            	{
	            		ret=false;
	            	}
	            	for(int j=0;j<extnlist.size();j++)
	            	{
	            		if(temp.equals(extnlist.get(j)) && ret)
	            		{
	    	            	fileList.add(temp);
	    	            	break;
	            		}
	            	}
	            	/*if(ret)
	            	{
		            	fileList.add(temp);
	            	}*/
	            }
	        }
	    }
	    return fileList;
	}
	
	public ArrayList<String> getextrun(File dir,String str)//pass fileType as a music , video, etc.               
	{
		fileList2.clear();
		Log.d("clear", "clear");
        return getext(dir,str);
	}
	
	public ArrayList<String> getext(File dir,String str)//pass fileType as a music , video, etc.               
	{
	    File listFile[] = dir.listFiles();
	    if (listFile != null && listFile.length > 0) 
	    {
	        for (int i = 0; i < listFile.length; i++) 
	        {

	            if (listFile[i].isDirectory()) 
	            {
	                getext(listFile[i],str);

	            } 
	            else 
	            {
	            	String temp;
	            	if(!listFile[i].isHidden()){
	            	temp=listFile[i].toString().substring((listFile[i].toString().lastIndexOf(".")+1), listFile[i].toString().length());
	            	if(temp.equals(str) && !listFile[i].isHidden())
	            	{
	            		fileList2.add(listFile[i].toString());
	            		Log.d("equals",listFile[i].toString() );
	            	}
	            	}
	            }
	        }
	    }
	    return fileList2;
	}

}

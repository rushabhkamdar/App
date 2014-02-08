package com.deepak.app;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private File path = new File(Environment.getExternalStorageDirectory() + "");
	final Context context = this;
    
 //   private File path2 = new File(Environment.getExternalStorageDirectory() + "");
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
    
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        //Log.d("final",listDataChild.toString());
        //Log.d("final",listDataHeader.toString());
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
        expListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					final int groupPosition, final int childPosition, long id) {
				Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                        listDataHeader.get(groupPosition)).get(
                                        childPosition), Toast.LENGTH_SHORT)
                        .show();
				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.prompt, null);
 
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
 
				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);
 
				final EditText userInput = (EditText) promptsView
						.findViewById(R.id.editTextDialogUserInput);
				
				userInput.setText(listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition));
				
				// set dialog message
				alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						// get user input and set it to result
						// edit text
						//result.setText(userInput.getText());
					    	boolean b1,b2;
					    	File sdcard = Environment.getExternalStorageDirectory();
					    	File from = new File(listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition));
					    	File to = new File(userInput.getText().toString());
					    	b1=from.exists();
					    	b2=from.renameTo(to);
					    	if(b2)
					    	{
					    		//code to change the display of rename/moved file
					    		List<String> data = new ArrayList<String>();
					        	data=listDataChild.get(listDataHeader.get(groupPosition));
					        	data.set(childPosition, userInput.getText().toString());
					        	//Log.d("here","here");
					        	listDataChild.put(listDataHeader.get(groupPosition), data);// Header, Child data
					        	listAdapter.notifyDataSetChanged();
					    	}
					    	Log.d("from",listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition));
					    	Log.d("to",userInput.getText().toString());
					    	Log.d("answer",b1+" "+b2);
					    }
					  })
					.setNegativeButton("Cancel",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					    }
					  });
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
                return false;
			}
		});
	}

	
	 /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        ext E=new ext();
        
        listDataHeader=E.getfile(path.getAbsoluteFile());
        //List<String>[] data = (List<String>[])new ArrayList[listDataHeader.size()];
        
        
        // Adding child data
        
        //List<List<String>> data = new ArrayList<List<String>>(10);
        //Log.d("size", " "+listDataHeader.size());
        
        for(int i=0;i<listDataHeader.size();i++){
        	List<String> data = new ArrayList<String>();
        	ext E2=new ext();
        	data=E2.getextrun(path.getAbsoluteFile(),listDataHeader.get(i));
        	//Log.d("here","here");
        	listDataChild.put(listDataHeader.get(i), data);// Header, Child data
        	Log.d("data",i+"\n"+ data.toString());
        	
        }
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

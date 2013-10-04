package com.example.contactlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity {

	//protected static final Uri SMS_INBOX_CONTENT_URI = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("Hello1" );
		final Button Retrieve = (Button) findViewById(R.id.button1);
        Retrieve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//System.out.println("Hello1" );
            	//Log.d("LOg_myapp","Here");
                Calendar c = Calendar.getInstance(); 
                int c_year = c.get(Calendar.YEAR);
                int c_month = c.get(Calendar.MONTH);
                c_month=c_month+1;
                int c_day = c.get(Calendar.DAY_OF_MONTH);
                int c_hour = c.get(Calendar.HOUR);
                int c_min = c.get(Calendar.MINUTE);
                int c_am_pm = c.get(Calendar.AM_PM);
//                int c_pm = c.get(Calendar.PM);
                String c_new= ""+c_year+"-"+c_month+"-"+c_day+"-"+c_hour+"-"+c_min;
                if(c_am_pm==0){
                	c_new += "AM";
                } else {
                	c_new += "PM";
                }
                File fileDir = fileLocation(c_new);
                String contact = "contact" + c_new + ".txt";
                String raw_contact = "raw_contact" + c_new + ".txt";
                String call = "call" + c_new + ".txt";
                String sms_inbox = "sms_inbox" + c_new + ".txt";
                String sms_sent = "sms_sent" + c_new + ".txt";
                String audio = "audio" + c_new + ".txt";
                String video = "video" + c_new + ".txt";
                String image = "image" + c_new + ".txt";
     			File lmFile_contact = new File(fileDir,contact);
     			File lmFile_rawcontact = new File(fileDir,raw_contact);
     			File lmFile_call = new File(fileDir,call);
     			File lmFile_sms_inbox = new File(fileDir,sms_inbox);
     			File lmFile_sms_sent = new File(fileDir,sms_sent);
     			File lmFile_audio = new File(fileDir,audio);
     			File lmFile_video = new File(fileDir,video);
     			File lmFile_image = new File(fileDir,image);
     			if(!(fileDir.exists() && fileDir.isDirectory()))
     				fileDir.mkdirs();
     			
		    	//if file does not exist, then create it			
				if(!lmFile_contact.exists()){
					try {
							//System.out.println(lmFile.toString());
							lmFile_contact.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				if(!lmFile_rawcontact.exists()){
					try {
							//System.out.println(lmFile.toString());
							lmFile_rawcontact.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				if(!lmFile_call.exists()){
					try {
							//System.out.println(lmFile.toString());
							lmFile_call.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				if(!lmFile_sms_inbox.exists()){
					try {
							//System.out.println(lmFile.toString());
							lmFile_sms_inbox.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				
				if(!lmFile_sms_sent.exists()){
					try {
							//System.out.println(lmFile.toString());
							lmFile_sms_sent.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				
				if(!lmFile_audio.exists()){
					try {
							//System.out.println(lmFile.toString());
							lmFile_audio.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				
				if(!lmFile_video.exists()){
					try {
							//System.out.println(lmFile.toString());
							lmFile_video.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				
				if(!lmFile_image.exists()){
					try {
							//System.out.println(lmFile.toString());
							lmFile_image.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				
				PrintWriter lmFout_contact = null;
				PrintWriter lmFout_rawcontact = null;
				PrintWriter lmFout_call = null;
				PrintWriter lmFout_sms_inbox = null;
				PrintWriter lmFout_sms_sent = null;
				PrintWriter lmFout_audio = null;
				PrintWriter lmFout_video = null;
				PrintWriter lmFout_image = null;
				
				try {
						lmFout_contact = new PrintWriter(new FileWriter(lmFile_contact));
					} catch (IOException e) {
						e.printStackTrace();
				}
				
				try {
					lmFout_rawcontact = new PrintWriter(new FileWriter(lmFile_rawcontact));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					lmFout_call = new PrintWriter(new FileWriter(lmFile_call));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					lmFout_sms_inbox = new PrintWriter(new FileWriter(lmFile_sms_inbox));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					lmFout_sms_sent = new PrintWriter(new FileWriter(lmFile_sms_sent));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					lmFout_audio = new PrintWriter(new FileWriter(lmFile_audio));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					lmFout_video = new PrintWriter(new FileWriter(lmFile_video));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					lmFout_image = new PrintWriter(new FileWriter(lmFile_image));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				ContentResolver cr = getContentResolver();
                Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
				if (cur.getCount() > 0) {
                	while (cur.moveToNext()) {
                		String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                		String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                		int tc = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.TIMES_CONTACTED)); 
                		int starred = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.STARRED));
                		//String status = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.CONTACT_STATUS_LABEL));
                		String newrow = null;
                		if (id!= null){
                			newrow += "id:"+id;
                		}
                		if (name!= null){
                			//newrow += ";nm:" + name.hashCode();
                			newrow += ";nm:" + name;
                		}
                		if (tc!= 0){
                			newrow += ";tc:" + tc;
                		}
                		
                		if (starred!= 0){
                			newrow += ";*:" + starred;
                		}
                		//String newrow = "id:"+id + " nm:" + name + " tc:" + tc + " *:" + starred;
                		Cursor cursorEmail = cr.query(
                		        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                		        null,
                		        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                		        new String[] { id },
                		        null);
             			if (cursorEmail != null) {
             		    	while (cursorEmail.moveToNext()) {
             		    		int type = cursorEmail.getInt(cursorEmail.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
             		    		String s = cursorEmail.getString(cursorEmail.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
             		    		if (s != null){
             		    			switch (type) {
             		    				case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
             		    					newrow += ";he:" + s;
             		    					break;
             		    				case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
             		    					newrow += ";we:" + s;
             		    					break;
             		    				case ContactsContract.CommonDataKinds.Email.TYPE_OTHER:
             		    					newrow += ";oe:" + s;
             		    					break;
             		    				case ContactsContract.CommonDataKinds.Email.TYPE_MOBILE:
             		    					newrow += ";me:" + s;
             		    					break;
             		    				case ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM:
             		    					newrow += ";ce:" + s;
             		    					break;
             		    			}
             		    		}
             		    	}
             		    	cursorEmail.close();
             			}
             			
             			String s1 = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
             		    if (Integer.parseInt(s1) > 0) {
             		    	Cursor cursorPhone = cr.query(
             		    			ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
             		    			null,
             		    			ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
             		    			new String[] { id },
             		    			null);
             		    	if (cursorPhone != null) {
             		    		while (cursorPhone.moveToNext()) {
             		    			int type = cursorPhone.getInt(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
             		    			s1 = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
             		    			if (s1 != null) {
             		    				switch (type) {
             		    					case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME:
             		    						newrow += ";fh:" + s1;
             		    						// s *** is private fax
             		    						break;
             		    					case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK:
             		    						// s *** is business fax
             		    						newrow += ";fw:" + s1;
             		    						break;
             		    					case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
             		    						// s *** is private phone
             		    						newrow += ";hm:" + s1;
             		    						break;
             		    					case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
             		    						// s *** is mobile phone
             		    						newrow += ";pm:" + s1;
             		    						break;
             		    					case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
             		    						// s *** is business phone
             		    						newrow += ";wm:" + s1;
             		    						break;
             		    					case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
             		    						// s *** is business mobile
             		    						newrow += ";bm:" + s1;
             		    						break;
             		    				}
             		    			}
             		    		}
             		    	}
             		    	cursorPhone.close();
             		    }
             	
             		    Cursor cursorOrganization = cr.query(
             		    		ContactsContract.Data.CONTENT_URI,
             		    		null,
             		    		ContactsContract.CommonDataKinds.Organization.CONTACT_ID + " = ?",
             		    		new String[] { id },
             		    		null);
             		    if (cursorOrganization != null) {
             		    	while (cursorOrganization.moveToNext()) {
             		    		String s2 = cursorOrganization.getString(cursorOrganization.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DATA));
             		    		if (s2 != null) {
             		    			newrow += ";on:" + s2;
             		    		}
             		    		// s *** Company name not working
             		    		
             		    		String s3 = cursorOrganization.getString(cursorOrganization.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE));
             		    		if (s3 != null) {
             		    			// s *** Function within company not working
             		    			newrow += ";jt:" + s3;
             		    		}
             		    	}
             		    	cursorOrganization.close();
             		    }
                        if ( lmFout_contact != null ){
                        	//lmFout.println(id + ":" + name + ":" + no);
                        	lmFout_contact.println(newrow);
                        	//System.out.println(name + " " + id);
                        }
                	}	
                	
				}
				
				Cursor raw_cur = cr.query(ContactsContract.RawContacts.CONTENT_URI, null, null, null, null);
				if (raw_cur.getCount() > 0) {
                	while (raw_cur.moveToNext()) {
                		String name = raw_cur.getString(raw_cur.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_NAME));
                		String type = raw_cur.getString(raw_cur.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE));
                		String tone = raw_cur.getString(raw_cur.getColumnIndex(ContactsContract.RawContacts.CUSTOM_RINGTONE));
                		String last = raw_cur.getString(raw_cur.getColumnIndex(ContactsContract.RawContacts.LAST_TIME_CONTACTED));
                		String newRow = null;
                		if (name != null){
                			newRow += "name:" + name;
                		}
                		if (type != null){
                			newRow += ";type:" + type;
                		}
                		if (tone != null){
                			newRow += ";tone:" + tone;
                		}
                		
                		if (last != null){
                			newRow += ";last:" + last;
                		}
                		
                		//String newRow = "name:" + name + " type:" + type + " tone:" + tone + " last:" + last;
                		//String newRow = "name:" + name + " type:" + type;
                		if ( lmFout_rawcontact != null ){
                        	lmFout_rawcontact.println(newRow);
                        	
                        }
                	}
				}
				
				String strOrder = android.provider.CallLog.Calls.DATE + " DESC"; 
				 
				Cursor mCallCursor = getContentResolver().query(
					android.provider.CallLog.Calls.CONTENT_URI,
				    null,
				    null,
				    null,
				    strOrder);
				
				if (mCallCursor.getCount() > 0) {
					while (mCallCursor.moveToNext()) {
						String num= mCallCursor.getString(mCallCursor.getColumnIndex(CallLog.Calls.NUMBER));// for  number
						String name= mCallCursor.getString(mCallCursor.getColumnIndex(CallLog.Calls.CACHED_NAME));// for name
						String duration = mCallCursor.getString(mCallCursor.getColumnIndex(CallLog.Calls.DURATION));// for duration
						String date = mCallCursor.getString(mCallCursor.getColumnIndex(CallLog.Calls.DATE));// for duration
						int type = Integer.parseInt(mCallCursor.getString(mCallCursor.getColumnIndex(CallLog.Calls.TYPE)));// for call type, Incoming or
						String newrow = null;
						if (num != null){
							newrow += "num:"+ num;
						}
						if (name != null){
							newrow += ";name:"+ name;
						}
						if (date != null){
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH/mm/ss");
							formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
							Calendar calendar = Calendar.getInstance();
							long l = Long.parseLong(date);
						    calendar.setTimeInMillis(l);
						    String formatted = formatter.format(calendar.getTime());
							newrow += ";date:"+ formatted;
						}
						if (num != null){
							newrow += ";num:"+ num;
						}
						if (duration != null){
							long l = Long.parseLong(duration);
							/*
							long hour = l/3600;
							long min = (l%3600)/60;
							long sec = ((l%3600)%60);
							newrow += " duration:";
							int flag=0;
							if (hour != 0){
								newrow += ""+hour+" hour";
								flag=1;
							}
							if (min != 0){
								newrow += ""+min+" min";
								flag=1;
							}
							if (sec != 0 || flag==0){
								newrow += ""+sec+" sec";
								flag=1;
							}*/
							newrow += ";duration:"+l;
						}
						newrow += ";callType:";
						if (type == 1){
							newrow += "Incoming";
						}
						if (type == 2){
							newrow += "Outgoing";
						}
						if (type == 3){
							newrow += "Missed";
						}
						//String newrow = "num:"+ num + " name:" + name + " date:" + date + " duration:" +duration + "type:" + type;
						if ( lmFout_call != null ){
							//lmFout.println(id + ":" + name + ":" + no);
							lmFout_call.println(newrow);
							//System.out.println(name + " " + id);
						}
					}
				}
				Uri SMSinbox = Uri.parse("content://sms/inbox");
				Cursor cursor_sms_inbox = cr.query(
                        //SMS_INBOX_CONTENT_URI,
						SMSinbox,
                        new String[] { "_id", "thread_id", "address", "person", "date", "body" },
                        null,
                        null,
                        null);
				//int count = 0;
				if (cursor_sms_inbox != null) {
					//count = cursor_sms.getCount();
     		    	while (cursor_sms_inbox.moveToNext()) {
     		    		long messageId = cursor_sms_inbox.getLong(0);
     		    	    //long threadId = cursor_sms.getLong(1);
     		    	    String address = cursor_sms_inbox.getString(2);
     		    	    long contactId = cursor_sms_inbox.getLong(3);
     		    	    //String contactId_string = String.valueOf(contactId);
     		    	    long timestamp = cursor_sms_inbox.getLong(4);
     		    	    String body = cursor_sms_inbox.getString(5);
     		    	    String newRow = "msgId:" + messageId +";contctId:" + contactId + ";body:" + body;
     		    	   if (timestamp != 0){
   		    			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH/mm/ss");
							formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
							Calendar calendar = Calendar.getInstance();
							calendar.setTimeInMillis(timestamp);
						    String formatted = formatter.format(calendar.getTime());
							newRow += ";time:"+ formatted;
   		    		}
     		    	    if (address != null){
     		    	    	newRow +=";address:" + address;
     		    	    }
     		    	   
     		    	    if ( lmFout_sms_inbox != null ){
							lmFout_sms_inbox.println(newRow);
						}
     		    		
     		    	}
				}
				
				Uri SMSsent = Uri.parse("content://sms/sent");
				Cursor cursor_sms_sent = cr.query(
                        //SMS_INBOX_CONTENT_URI,
						SMSsent,
                        new String[] { "_id", "thread_id", "address", "person", "date", "body" },
                        null,
                        null,
                        null);
				//int count = 0;
				if (cursor_sms_sent != null) {
					//count = cursor_sms.getCount();
     		    	while (cursor_sms_sent.moveToNext()) {
     		    		long messageId = cursor_sms_sent.getLong(0);
     		    	    //long threadId = cursor_sms.getLong(1);
     		    	    String name = cursor_sms_sent.getString(2);
     		    	    long contactId = cursor_sms_sent.getLong(3);
     		    	    //String contactId_string = String.valueOf(contactId);
     		    	    long timestamp = cursor_sms_sent.getLong(4);
     		    	    String body = cursor_sms_sent.getString(5);
     		    	    String newRow = "msgId:" + messageId +";contctId:" + contactId + ";body:" + body;
     		    	   if (timestamp != 0){
    		    			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH/mm/ss");
							formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
							Calendar calendar = Calendar.getInstance();
							calendar.setTimeInMillis(timestamp);
						    String formatted = formatter.format(calendar.getTime());
							newRow += ";time:"+ formatted;
    		    		}
    		    	    if (name != null){
    		    	    	newRow +=";name:" + name;
    		    	    }
     		    	    if ( lmFout_sms_sent != null ){
							lmFout_sms_sent.println(newRow);
						}
     		    		
     		    	}
				}
				
				Uri uriaudio = MediaStore.Audio.Media.getContentUri("external");
				String[] audio_proj = { MediaStore.Audio.Media._ID,
	                      MediaStore.Audio.Media.DATA,
	                      MediaStore.Audio.Media.DISPLAY_NAME,
	                      MediaStore.Audio.Artists.ARTIST,
	                      MediaStore.Audio.Media.DATE_ADDED,
	                      MediaStore.Audio.Media.TITLE,
	                      MediaStore.Audio.Albums.ALBUM,
	                      MediaStore.Audio.Media.DURATION,
	                      MediaStore.Audio.Media.IS_NOTIFICATION,
	                      MediaStore.Audio.Media.IS_ALARM,
	                      MediaStore.Audio.Media.IS_RINGTONE};
				
				Cursor cursor_audio = cr.query(
                      	uriaudio,
						audio_proj,
                        null,
                        null,
                        null);
				if (cursor_audio != null) {
					//count = cursor_sms.getCount();
     		    	while (cursor_audio.moveToNext()) {
     		    		String audio_id = cursor_audio.getString(0);
     		    		//String audio_data = cursor_audio.getString(1);
     		    		//String audio_name = cursor_audio.getString(2);
     		    		//String audio_artist = cursor_audio.getString(3);
     		    		String audio_date = cursor_audio.getString(4);
     		    		String audio_title = cursor_audio.getString(5);
     		    		//String audio_album = cursor_audio.getString(6);
     		    		String duration = cursor_audio.getString(7);
     		    		String notification = cursor_audio.getString(8);
     		    		String alarm = cursor_audio.getString(9);
     		    		String ring_tone = cursor_audio.getString(10);
     		    		String newRow = "audio_id:" + audio_id + " title:"+audio_title;
     		    		if (audio_date != null){
     		    			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH/mm/ss");
							formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
							Calendar calendar = Calendar.getInstance();
							long l = Long.parseLong(audio_date);
							l= l*1000;
						    calendar.setTimeInMillis(l);
						    String formatted = formatter.format(calendar.getTime());
							newRow += ";date_added:"+ formatted;
     		    		}
     		    		if (duration != null){
							long l = Long.parseLong(duration);
							/*l = l/1000;
							long hour = l/3600;
							long min = (l%3600)/60;
							long sec = ((l%3600)%60);
							newRow += " duration:";
							int flag=0;
							if (hour != 0){
								newRow += ""+hour+" hour";
								flag=1;
							}
							if (min != 0){
								newRow += ""+min+" min";
								flag=1;
							}
							if (sec != 0 || flag==0){
								newRow += ""+sec+" sec";
								flag=1;
							}*/
							newRow += ";duration:"+l;
							
						}
     		    		if (notification != null){
     		    			long l = Long.parseLong(notification);
     		    			if (l != 0){
     		    				newRow += ";notification: yes";
     		    			}
     		    			
     		    		}
     		    		if (alarm != null){
     		    			long l = Long.parseLong(alarm);
     		    			if (l != 0){
     		    				newRow += ";alarm: yes";
     		    			}
     		    			
     		    		}
     		    		
     		    		if (ring_tone != null){
     		    			long l = Long.parseLong(ring_tone);
     		    			if (l != 0){
     		    				newRow += ";ring_tone: yes";
     		    			}
     		    			
     		    		}
     		    		
     		    		if ( lmFout_audio != null ){
							lmFout_audio.println(newRow);
						}
     		    	}
     		    	
				}
				Uri videosUri = MediaStore.Video.Media.getContentUri("external");
				String[] video_proj = { MediaStore.Video.Media._ID,
						  MediaStore.Video.Media.ALBUM,
						  MediaStore.Video.Media.TITLE,
						  MediaStore.Video.Media.ARTIST,
						  MediaStore.Video.Media.DISPLAY_NAME,
						  MediaStore.Video.Media.DATE_ADDED,
						  MediaStore.Video.Media.DURATION,
						  MediaStore.Video.Media.TAGS};
				
				Cursor cursor_video = cr.query(
						videosUri,
						video_proj,
                        null,
                        null,
                        null);
				if (cursor_video != null) {
					//count = cursor_sms.getCount();
     		    	while (cursor_video.moveToNext()) {
     		    		String video_id = cursor_video.getString(0);
     		    		//String video_album = cursor_video.getString(1);
     		    		String video_title = cursor_video.getString(2);
     		    		//String video_artist = cursor_video.getString(3);
     		    		//String video_name = cursor_video.getString(4);
     		    		String video_date = cursor_video.getString(5);
     		    		String video_duration = cursor_video.getString(6);
     		    		//String video_tags = cursor_video.getString(7);
     		    		
     		    		String newRow = "video_id:" + video_id + ";video_title:" + video_title;
     		    		if (video_date != null){
     		    			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH/mm/ss");
							formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
							Calendar calendar = Calendar.getInstance();
							long l = Long.parseLong(video_date);
							l= l*1000;
						    calendar.setTimeInMillis(l);
						    String formatted = formatter.format(calendar.getTime());
							newRow += ";date_added:"+ formatted;
     		    		}
     		    		if (video_duration != null){
							long l = Long.parseLong(video_duration);
							l = l/1000;
							long hour = l/3600;
							long min = (l%3600)/60;
							long sec = ((l%3600)%60);
							newRow += ";duration:";
							int flag=0;
							if (hour != 0){
								newRow += ""+hour+" hour";
								flag=1;
							}
							if (min != 0){
								newRow += ""+min+" min";
								flag=1;
							}
							if (sec != 0 || flag==0){
								newRow += ""+sec+" sec";
								flag=1;
							}
							
						}
     		    		if ( lmFout_video != null ){
							lmFout_video.println(newRow);
						}
     		    	}
				}
				
				Uri uriimage = MediaStore.Images.Media.getContentUri("external");
				String[]  image_proj = { MediaStore.Images.Media._ID,
						  MediaStore.Images.Media.TITLE,
	                      MediaStore.Images.Media.DISPLAY_NAME,
	                      MediaStore.Images.Media.DATE_ADDED};
				
				Cursor cursor_image = cr.query(
						uriimage,
						image_proj,
                        null,
                        null,
                        null);
				
				if (cursor_image != null) {
					//count = cursor_sms.getCount();
     		    	while (cursor_image.moveToNext()) {
     		    		String image_id = cursor_image.getString(0);
     		    		String image_title = cursor_image.getString(1);
     		    		//String image_name = cursor_image.getString(2);
     		    		String image_date = cursor_image.getString(3);
     		    		String newRow = "image_id:" + image_id + ";image_title:" + image_title;
     		    		if (image_date != null){
     		    			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH/mm/ss");
							formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
							Calendar calendar = Calendar.getInstance();
							long l = Long.parseLong(image_date);
						    calendar.setTimeInMillis(l);
						    String formatted = formatter.format(calendar.getTime());
							newRow += ";date_added:"+ formatted;
     		    		}
     		    		
     		    		if ( lmFout_image != null ){
							lmFout_image.println(newRow);
						}
     		    	}
				}
				
				if ( null != lmFout_contact ) {
          			lmFout_contact.close();
				}
				if ( null != lmFout_rawcontact ) {
          			lmFout_rawcontact.close();
				}
				if ( null != lmFout_call ) {
          			lmFout_call.close();
				}
				if ( null != lmFout_sms_inbox ) {
          			lmFout_sms_inbox.close();
				}
				if ( null != lmFout_sms_sent ) {
          			lmFout_sms_sent.close();
				}
				if ( null != lmFout_audio ) {
          			lmFout_audio.close();
				}
				if ( null != lmFout_video ) {
          			lmFout_video.close();
				}
				if ( null != lmFout_image ) {
          			lmFout_image.close();
				}
			}

			//private void GregorianCalendar(int year, int month, int day,
				//	int hour, int min) {
				// TODO Auto-generated method stub
				
			//}
            
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
public File fileLocation(String timestamp) {
		
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
		    // We can read and write the media
		    mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		    // We can only read the media
		    mExternalStorageAvailable = true;
		    mExternalStorageWriteable = false;
		} else {
		    mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
		//Log.d(LOG_TAG,Environment.getExternalStorageState()+Boolean.toString(mExternalStorageAvailable) + Boolean.toString(mExternalStorageWriteable));
		if (mExternalStorageAvailable && mExternalStorageWriteable) {
			
			//int fNo = app_preferences.getInt("fileNo",1);
			return new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Contacts/"+timestamp);
			
		}
		else {
			//No external Storage;
			return null;
		}
	}

}

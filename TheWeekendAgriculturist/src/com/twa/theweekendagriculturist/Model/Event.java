package com.twa.theweekendagriculturist.Model;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Event")
public class Event extends ParseObject implements Parcelable{

	public Event()
	{
		
	}
	
	 public Event(Parcel parcel) {   
	        
	        setTitle(parcel.readString());
	        setDescription(parcel.readString());
	        setDetails(parcel.readString());
	        setPlace(parcel.readString());
	    }
	
	public String getTitle(){
		return getString("title");
	}
	
	public void setTitle(String title){
		put("title", title);
	}
	
	public String getDescription(){
		return getString("description");
	}
	
	public void setDescription(String description){
		put("description", description);
	}
	public String getDetails(){
		return getString("details");
	}
	
	public void setDetails(String details){
		put("details", details);
	}
	public Date getEventDate(){
		return getDate("details");
	}
	
	public void setEventDate(Date date){
		put("details", date);
	}
	public String getPlace(){
		return getString("place");
	}
	
	public void setPlace(String place){
		put("place", place);
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		 parcel.writeString(getTitle());
	     parcel.writeString(getDescription());
	     parcel.writeString(getDetails());
	   
	     parcel.writeString(getPlace());
	}
	
	  public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() { 
	        public Event createFromParcel(Parcel in) { 
	            return new Event(in); 
	            }
	        public Event[] newArray(int size) { 
	            return new Event[size]; 
	            } 
	        };
	
}

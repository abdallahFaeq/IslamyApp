package com.training.islamyapp.pojo.azkar;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AzkarItem implements Parcelable {

	private String reference, count, description, zekr, category;

	public AzkarItem(String reference, String count, String description, String zekr, String category){
		this.reference = reference;
		this.count = count;
		this.description = description;
		this.zekr = zekr;
		this.category = category;
	}

	protected AzkarItem(Parcel in){
		reference = in.readString();
		count = in.readString();
		description = in.readString();
		zekr = in.readString();
		category = in.readString();
	}

	@Override
	public void writeToParcel(@NonNull Parcel parcel, int i) {
		parcel.writeString(reference);
		parcel.writeString(count);
		parcel.writeString(description);
		parcel.writeString(zekr);
		parcel.writeString(category);
	}

	public static final Parcelable.Creator<AzkarItem> creator = new Parcelable.Creator<AzkarItem>(){
		@Override
		public AzkarItem createFromParcel(Parcel parcel) {
			return new AzkarItem(parcel);
		}

		@Override
		public AzkarItem[] newArray(int i) {
			return new AzkarItem[i];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	public String getReference(){
		return reference;
	}

	public String getCount(){
		return count;
	}

	public String getDescription(){
		return description;
	}

	public String getZekr(){
		return zekr;
	}

	public String getCategory(){
		return category;
	}
}

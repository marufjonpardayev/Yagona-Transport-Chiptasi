package uz.transport.yagonatransportchiptasi.model

import android.os.Parcel
import android.os.Parcelable

data class PassengerStatus(var placeNumber: Int, var upOrDown: String? = null, var status: String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(placeNumber)
        parcel.writeString(upOrDown)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PassengerStatus> {
        override fun createFromParcel(parcel: Parcel): PassengerStatus {
            return PassengerStatus(parcel)
        }

        override fun newArray(size: Int): Array<PassengerStatus?> {
            return arrayOfNulls(size)
        }
    }
}

package uz.transport.yagonatransportchiptasi.model

import android.os.Parcel
import android.os.Parcelable

data class PassengerDetail(
    var surname: String?,
    var name: String?,
    var middleName: String?,
    var dateOfBirth: String?,
    var gender: Char,
    var documentNumber: String?,
    var privilege: String? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt().toChar(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(surname)
        parcel.writeString(name)
        parcel.writeString(middleName)
        parcel.writeString(dateOfBirth)
        parcel.writeInt(gender.toInt())
        parcel.writeString(documentNumber)
        parcel.writeString(privilege)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PassengerDetail> {
        override fun createFromParcel(parcel: Parcel): PassengerDetail {
            return PassengerDetail(parcel)
        }

        override fun newArray(size: Int): Array<PassengerDetail?> {
            return arrayOfNulls(size)
        }
    }
}
import android.os.Parcel
import android.os.Parcelable

// Mendefinisikan kelas Game sebagai data class
data class Game(
    val title: String?,
    val description: String?,
    val photo: Int,
    val price: String?,
    val release: String?
) : Parcelable {
    // Konstruktor kedua untuk mengambil data dari Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    // Metode untuk menulis data ke Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(photo)
        parcel.writeString(price)
        parcel.writeString(release)
    }

    // Metode yang mengembalikan tipe konten Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // Companion object yang digunakan untuk membuat objek Game dari Parcel
    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        // Membuat array objek Game dari Parcel
        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}

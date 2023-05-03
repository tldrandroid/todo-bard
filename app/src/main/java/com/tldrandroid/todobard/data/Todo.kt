package com.tldrandroid.todobard.data

import android.os.Parcel
import android.os.Parcelable

class Todo : Parcelable {

    var id: Int = -1
    var title: String = ""
    var description: String = ""
    var completed: Boolean = false

    constructor() {
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()!!
        description = parcel.readString()!!
        completed = parcel.readInt() == 1
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(if (completed) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Todo> {

        override fun createFromParcel(parcel: Parcel): Todo {
            return Todo(parcel)
        }

        override fun newArray(size: Int): Array<Todo?> {
            return arrayOfNulls(size)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Todo) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (completed != other.completed) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + completed.hashCode()
        return result
    }
}

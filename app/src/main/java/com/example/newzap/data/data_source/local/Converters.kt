package com.example.newzap.data.data_source.local

import androidx.room.TypeConverter
import com.example.newzap.data.data_source.remote.dto.SourceDTO
import com.google.gson.Gson
import java.util.Date

class Converters {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

}
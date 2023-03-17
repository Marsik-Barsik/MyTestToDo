package com.example.mytesttodo.list

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.mytesttodo.R
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Entity(tableName = "Task_item_table")
class TaskItem (
    @ColumnInfo(name = "textTask") var textTask: String,
    @ColumnInfo(name = "dueTime") var dueTime: LocalTime?,
    @ColumnInfo(name = "completedDate")  var completedDate: LocalDate?,
    @ColumnInfo(name = "id")  var id:UUID = UUID.randomUUID() )
{
    fun isCompleted () = completedDate !=null
    fun imageResource( ): Int = if (isCompleted()) R.drawable.baseline_check_circle_outline_24
                                    else R.drawable.baseline_radio_button_unchecked_24
    fun imageColor(context: Context): Int = if (isCompleted()) green(context) else black(context)

        private fun green(context: Context) = ContextCompat.getColor(context,R.color.purple_200)
        private fun black(context: Context) = ContextCompat.getColor(context,R.color.black)


}

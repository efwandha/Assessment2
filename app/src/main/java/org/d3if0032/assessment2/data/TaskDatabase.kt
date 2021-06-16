package org.d3if0032.assessment2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.d3if0032.assessment2.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Assessment Mobpro", important = true))
                dao.insert(Task("Assessment SBD"))
                dao.insert(Task("Assessment Game", important = true))
                dao.insert(Task("Assessment Pancasila"))
                dao.insert(Task("Assessment Bahasa Indonesia"))
                dao.insert(Task("Assessment Kewirausahaan"))
                dao.insert(Task("Assessment PT2", completed = true))
                dao.insert(Task("Assessment Olahraga", completed = true))
            }
        }
    }
}
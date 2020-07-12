package com.example.app1

import android.os.Bundle
import android.os.Environment
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import org.apache.poi.hssf.usermodel.HSSFRichTextString
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createFileExcel()



        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home,R.id.scheduleActivities,R.id.activitiesExecuting,R.id.workTask,R.id.documents,R.id.materials2), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun createExcel(){


    }

    fun createFileExcel(){
        val workbook = HSSFWorkbook()
        val firstSheet = workbook.createSheet("Hoja 1")
        val secondSheet = workbook.createSheet("Hoja 2")
        val rowA = firstSheet.createRow(0)
        val cellA = rowA.createCell(0)
        cellA.setCellValue(HSSFRichTextString("Hola prueba hoja uno"))
        val rowB = secondSheet.createRow(0)
        val cellB = rowB.createCell(0)
        cellB.setCellValue(HSSFRichTextString("Hola prueba hoja dos"))

        var fos: FileOutputStream? = null
        try {
            val str_path: String = Environment.getExternalStorageDirectory().toString()
            val file: File
            file = File(str_path, "TEST.xls")
            fos = FileOutputStream(file)
            workbook.write(fos)
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

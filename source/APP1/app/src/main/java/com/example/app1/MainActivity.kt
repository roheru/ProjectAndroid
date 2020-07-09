package com.example.app1

import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.app1.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val columns = arrayOf("Name", "Email", "Date Of Birth", "Salary")
        val employees: List<User> = ArrayList()

        val workbook = HSSFWorkbook()
        val createHelper = workbook.creationHelper

        // Create a Sheet

        // Create a Sheet
        val sheet = workbook.createSheet("Employee")

        // Create a Font for styling header cells

        // Create a Font for styling header cells
        val headerFont = workbook.createFont()
        headerFont.setBold(true)
        headerFont.setFontHeightInPoints(14.toShort())
        headerFont.setColor(IndexedColors.RED.getIndex())

        // Create a CellStyle with the font

        // Create a CellStyle with the font
        val headerCellStyle = workbook.createCellStyle()
        headerCellStyle.setFont(headerFont)

        // Create a Row

        // Create a Row
        val headerRow = sheet.createRow(0)

        // Create cells

        // Create cells
        for (i in 0 until columns.size) {
            val cell: Cell = headerRow.createCell(i)
            cell.setCellValue(columns[i])
            cell.cellStyle = headerCellStyle
        }
        val dateCellStyle = workbook.createCellStyle()
        dateCellStyle.dataFormat = createHelper.createDataFormat().getFormat("dd-MM-yyyy")

        // Create Other rows and cells with employees data

        // Create Other rows and cells with employees data
        var rowNum = 1
        for (employee in employees) {
            val row = sheet.createRow(rowNum++)
            row.createCell(0)
                .setCellValue("Roger")
            row.createCell(1)
                .setCellValue("roheru87@gmail.com")
            val dateOfBirthCell: Cell = row.createCell(2)
            dateOfBirthCell.setCellValue("2020-10-01")
            dateOfBirthCell.cellStyle = dateCellStyle
            row.createCell(3)
                .setCellValue("1200222")
        }

        // Resize all columns to fit the content size

        // Resize all columns to fit the content size
        for (i in 0 until columns.size) {
            sheet.autoSizeColumn(i)
        }

        val fileOut = FileOutputStream("poi-generated-file.xls")
        workbook.write(fileOut)
        fileOut.close()

        // Closing the workbook

        // Closing the workbook
        workbook.close()

        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
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
}

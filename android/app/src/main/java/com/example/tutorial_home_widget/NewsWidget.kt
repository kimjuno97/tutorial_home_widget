package com.example.tutorial_home_widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import es.antonborri.home_widget.HomeWidgetPlugin
import java.io.File

/**
 * Implementation of App Widget functionality.
 */
class NewsWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for(appWidgetId in appWidgetIds) {
            // Get reference to SharedPreferences
            val widgetData = HomeWidgetPlugin.getData(context)
            val views = RemoteViews(context.packageName, R.layout.news_widget).apply {

                val title = widgetData.getString("headline_title", null)
                setTextViewText(R.id.headline_title, title ?:"No title set")

                val description = widgetData.getString("headline_description", null)
                setTextViewText(R.id.headline_description, description ?: "No description set")

                // New : Add the section below
                // Get chart image and put it in the widget, if exists
                val imageName = widgetData.getString("filename", null)
                println("android imageName => $imageName")
                if(imageName != null){
                    val imageFile = File(imageName)
                    val myBitmap: Bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
                    setImageViewBitmap(R.id.widget_image, myBitmap)
                } else {
                    println("imageName not found!")
                }
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


package com.dota2.music.binding

import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout

class DrawerBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["allowDrawerOpen"], requireAll = false)
        fun allowDrawerOpen(drawerLayout: DrawerLayout, allowDrawerOpen: Boolean) {
            drawerLayout.setDrawerLockMode(if (allowDrawerOpen)
                DrawerLayout.LOCK_MODE_UNLOCKED
            else
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }

        @JvmStatic
        @BindingAdapter(value = ["isOpenDrawer"], requireAll = false)
        fun openDraw(drawerLayout: DrawerLayout, openDraw: Boolean) {
            // openDraw为true表示需要打开
            if (openDraw && !drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }

}
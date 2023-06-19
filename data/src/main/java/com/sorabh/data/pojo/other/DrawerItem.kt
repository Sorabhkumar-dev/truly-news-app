package com.sorabh.data.pojo.other

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(val drawerIcon: ImageVector,@StringRes val drawerTitle: Int,val screen:String)
package com.deluxan.medicine.utils.helpers

import android.content.Context
import android.widget.Toast

/**
 * Created by Dilan M Deluxan on 31-Dec-20 AD at 9:50 AM
 */

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
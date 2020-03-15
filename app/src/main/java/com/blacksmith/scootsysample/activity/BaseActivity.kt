package com.blacksmith.scootsysample.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    protected fun showToast(mesg: String) {
        Toast.makeText(this, mesg, Toast.LENGTH_SHORT).show();
    }

}

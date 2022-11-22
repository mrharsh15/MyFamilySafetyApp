package com.codewithme.myfamilysafety

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.codewithme.myfamilysafety.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val permission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_CONTACTS
    )
    val permissionCode=15
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        askForPermission()

        binding.bottomBar.setOnItemSelectedListener {
            if(it.itemId == R.id.nav_guard){
                inflateFragment(GuardFragment.newInstance())
            }
            else if(it.itemId == R.id.nav_home){
                inflateFragment(HomeFragment.newInstance())
            }
            else if(it.itemId == R.id.nav_dashboard){
                inflateFragment(MapsFragment())
            }
            else if(it.itemId == R.id.nav_profile){
                inflateFragment(ProfileFragment.newInstance())
            }

            true
        }
        binding.bottomBar.selectedItemId = R.id.nav_home
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, permission, permissionCode)
    }

    private fun inflateFragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == permissionCode){
            if(allPermissionGranted()){

            }else{

            }
        }
    }

    private fun allPermissionGranted(): Boolean {
        for (item in permission){
            if (ContextCompat.checkSelfPermission(this, item) != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }
}
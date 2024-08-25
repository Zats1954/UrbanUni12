package ru.zatsoft.listview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import ru.zatsoft.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolBar: Toolbar
    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolBar = binding.toolbarMain
        setSupportActionBar(toolBar)
        title = " "

        listAdapter = ListAdapter(this, userModel.listUsers.value!!)
        userModel.listUsers.observe(this) {
            listAdapter = ListAdapter(this, it)
//        listAdapter.notifyDataSetInvalidated()     -------------- не нужно?
        }

        val inputKeyboard = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.listView.adapter = listAdapter
        binding.save.setOnClickListener {
            val user = User(binding.edName.text.toString(), binding.edAge.text.toString().toInt())
            userModel.add(user)
            binding.edName.text.clear()
            binding.edAge.text.clear()
            inputKeyboard.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exit)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
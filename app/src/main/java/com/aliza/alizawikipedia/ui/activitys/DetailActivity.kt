package com.aliza.alizawikipedia.ui.activitys

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.aliza.alizawikipedia.base.BaseActivity
import com.aliza.alizawikipedia.base.SEND_DATA_TO_DETAIL_ACTIVITY
import com.aliza.alizawikipedia.data.ItemPost
import com.aliza.alizawikipedia.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    override fun inflateBinding(): ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configToolBar()

        val dataPost = intent.getParcelableExtra(SEND_DATA_TO_DETAIL_ACTIVITY ,ItemPost::class.java)
        if (dataPost != null) {
            showData(dataPost)
        }
    }

    private fun configToolBar(){
        setSupportActionBar(binding.toolBarDetail)
        binding.collapsingDetail.setExpandedTitleColor(ContextCompat.getColor(this,android.R.color.transparent))
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun showData(itemPost: ItemPost) {
        Glide
            .with(this)
            .load( itemPost.imgUrl )
            .into( binding.imgDetail )
        binding.txtDetailTitle.text = itemPost.txtTitle
        binding.txtDetailSubtitle.text = itemPost.txtSubtitle
        binding.txtDetailText.text = itemPost.txtDetail

        binding.fabDetailGoToWiki.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(itemPost.wikiUrl))
            startActivity(intent)
        }
    }
}
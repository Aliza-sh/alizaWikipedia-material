package com.aliza.alizawikipedia.ui

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.aliza.alizawikipedia.base.BaseFragment
import com.aliza.alizawikipedia.databinding.FragmentProfileBinding

class FragmentProfile : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::inflate
) {

    private val imageContract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.imgProfileImgUser.setImageURI(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnProfileImgUserEdit.setOnClickListener {
            imageContract.launch("image/*")
        }
    }

}
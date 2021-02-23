package com.starwars.challenge.features.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.starwars.challenge.R
import com.starwars.challenge.databinding.ActivityDetailsBinding
import com.starwars.challenge.databinding.LayoutPlanetDetailsBinding
import com.starwars.challenge.databinding.LayoutProfileDetailsBinding
import com.starwars.challenge.databinding.LayoutSpecieDetailsBinding
import com.starwars.challenge.features.search.domain.model.CharacterDetailModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var profileBinding: LayoutProfileDetailsBinding
    private lateinit var planetBinding: LayoutPlanetDetailsBinding
    private lateinit var speciesBinding: LayoutSpecieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater).apply {
            profileBinding = LayoutProfileDetailsBinding.bind(root)
            planetBinding = LayoutPlanetDetailsBinding.bind(root)
            speciesBinding = LayoutSpecieDetailsBinding.bind(root)
            setContentView(root)
        }
        setupView()
    }

    private fun setupView() {
        val extras = intent?.extras ?: return
        val details = extras.getParcelable<CharacterDetailModel>(DETAILS_EXTRA) ?: return

        supportActionBar?.title = details.name

        profileBinding.detailsProfileName.text = getString(R.string.profile_name, details.name)
        profileBinding.detailsProfileBirth.text =
            getString(R.string.birth_profile, details.birthYear)
        profileBinding.detailsProfileHeight.text = getString(R.string.height_profile, details.height)

//        planetBinding.detailsPlanetName

//        speciesBinding.detailsSpeciesName
    }

    companion object {
        private const val DETAILS_EXTRA = "details_extra"

        fun getIntent(
            context: Context,
            characterDetails: CharacterDetailModel
        ) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtras(
                    bundleOf(
                        DETAILS_EXTRA to characterDetails
                    )
                )
            }
    }
}
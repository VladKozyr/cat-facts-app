package com.vlad.kozyr.catfacts.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vlad.kozyr.catfacts.core.toPx
import com.vlad.kozyr.catfacts.core.visibleOrInvisible
import com.vlad.kozyr.catfacts.databinding.ActivityMainBinding
import com.vlad.kozyr.catfacts.presentation.ui.CatFactsAdapter
import com.vlad.kozyr.catfacts.presentation.ui.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fabReload.setOnClickListener { vm.fetchRandomCatImagesWithFacts() }
        binding.rvData.addItemDecoration(
            MarginItemDecoration(
                verticalSize = 12.toPx(),
                horizontalSize = 16.toPx()
            )
        )

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect {
                    binding.cpLoading.visibleOrInvisible(it is MainState.Loading)
                    binding.rvData.visibleOrInvisible(it is MainState.Loaded)
                    binding.tvError.visibleOrInvisible(it is MainState.Error)

                    when (it) {
                        is MainState.Error -> binding.tvError.text = it.error
                        is MainState.Loaded -> binding.rvData.adapter = CatFactsAdapter(it.catFacts)
                        MainState.Loading -> Unit
                    }
                }
            }
        }
    }
}
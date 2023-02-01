package com.example.wwcrewmanagement.detailedInfoWorker

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.wwcrewmanagement.R
import com.example.wwcrewmanagement.databinding.DetailedInfoWorkerFragmentBinding
import com.example.wwcrewmanagement.model.Worker

class DetailedInfoWorkerFragment : Fragment() {

    private lateinit var binding: DetailedInfoWorkerFragmentBinding
    private lateinit var viewModel: DetailedInfoViewModel
    private lateinit var worker: Worker
    val args: DetailedInfoWorkerFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        worker = args.worker
        viewModel = ViewModelProvider(this)[DetailedInfoViewModel::class.java]
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detailed_info_worker_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        viewModel.setWorker(worker)
    }

    private fun setupData() {
        Glide.with(this)
            .load(worker.image)
            .into(binding.workerImage)

        context?.let {
            ContextCompat.getDrawable(it, R.drawable.ic_profile)
                ?.let { binding.fullnameTv.setImage(it) }
            ContextCompat.getDrawable(it, R.drawable.ic_age)?.let { binding.ageTv.setImage(it) }
            ContextCompat.getDrawable(it, R.drawable.ic_gender)
                ?.let { binding.genderTextView.setImage(it) }
            ContextCompat.getDrawable(it, R.drawable.ic_height)
                ?.let { binding.heightTextView.setImage(it) }
            ContextCompat.getDrawable(it, R.drawable.ic_email)
                ?.let { binding.emailTextView.setImage(it) }
            ContextCompat.getDrawable(it, R.drawable.ic_country)
                ?.let { binding.countryTextView.setImage(it) }
            ContextCompat.getDrawable(it, R.drawable.ic_color)?.let { binding.colorTv.setImage(it) }
            ContextCompat.getDrawable(it, R.drawable.ic_food)?.let { binding.foodTv.setImage(it) }
            ContextCompat.getDrawable(it, R.drawable.ic_random)
                ?.let { binding.randomTv.setImage(it) }
            ContextCompat.getDrawable(it, R.drawable.ic_song)?.let { binding.songTv.setImage(it) }
        }

        binding.nameTextView.text = worker.name
        binding.professionTextView.text = worker.profession
        binding.fullnameTv.setText("${worker.name} ${worker.surname}")
        binding.ageTv.setText(worker.age.toString())
        binding.genderTextView.setText(worker.gender)
        binding.heightTextView.setText("${worker.height.toString()} cm")
        binding.emailTextView.setText(worker.email)
        binding.countryTextView.setText(worker.country)
        binding.colorTv.setText(worker.favorite.color)
        binding.foodTv.setText(worker.favorite.food)
        binding.randomTv.setText(worker.favorite.randomString)
        binding.songTv.setText(worker.favorite.song)


    }
}
package com.example.wwcrewmanagement.presentation.workerList

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wwcrewmanagement.R
import com.example.wwcrewmanagement.databinding.WorkerListFragmentBinding
import com.example.wwcrewmanagement.model.Worker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WorkerListFragment : Fragment() {

    private lateinit var binding: WorkerListFragmentBinding
   // private lateinit var viewModel: WorkerListViewModel
     private val viewModel: WorkerListViewModel by viewModels()

    private lateinit var adapter: WorkerAdapter
    private lateinit var professionAdapter: ProfessionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      //  viewModel = ViewModelProvider(this).get(WorkerListViewModel::class.java)


        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.worker_list_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterMenuSetup()
        setupClickListener()

        adapter = WorkerAdapter() { worker ->
            navigateToDetail(worker)
        }
        binding.recyclerview.adapter = adapter

        viewModel.filteredWorkers.observe(viewLifecycleOwner) {
            viewModel.extractProfessions(it)
            adapter.workers = it
        }

        professionAdapter = ProfessionAdapter() {
            filterWorkers(it)
        }
        binding.professionRecycler.adapter = professionAdapter
        binding.professionRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)



        viewModel.professions.observe(viewLifecycleOwner) {
            professionAdapter.professionsToFilter = it
        }

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && dy != 0) {
                    viewModel.loadNextEvents()
                }
            }
        })
    }

    private fun filterWorkers(profession: String) {
        viewModel.setCurrentProfession(profession)
        viewModel.filterWorkersBy()
    }

    private fun filterMenuSetup() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.top_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                showFilterModal()
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun navigateToDetail(worker: Worker) {
        val action =
            WorkerListFragmentDirections.actionWorkerListFragmentToWorkerDetailsFragment(worker.id)
        findNavController().navigate(action)
    }

    private fun setupClickListener() {

        binding.femaleTv.setOnClickListener {
            viewModel.setCurrentGender("F")
            viewModel.filterWorkersBy()

            binding.femaleTv.isEnabled = false
            binding.maleTv.isEnabled = true
            binding.otherTv.isEnabled = true

        }

        binding.maleTv.setOnClickListener {
            viewModel.setCurrentGender("M")
            viewModel.filterWorkersBy()

            binding.maleTv.isEnabled = false
            binding.femaleTv.isEnabled = true
            binding.otherTv.isEnabled = true

        }

        binding.otherTv.setOnClickListener {
            viewModel.setCurrentGender("")
            viewModel.setCurrentProfession("")
            viewModel.filterWorkersBy()

            binding.otherTv.isEnabled = false
            binding.maleTv.isEnabled = true
            binding.femaleTv.isEnabled = true


        }

    }

    private fun showFilterModal() {
        if (binding.genderFilterContainer.isVisible) {
            binding.genderFilterContainer.visibility = View.GONE
            binding.professionFilterContainer.visibility = View.GONE
        } else {
            binding.genderFilterContainer.visibility = View.VISIBLE
            binding.professionFilterContainer.visibility = View.VISIBLE

        }
    }

}

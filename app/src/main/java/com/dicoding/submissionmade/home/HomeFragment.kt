package com.dicoding.submissionmade.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissionmade.MainActivity
import com.dicoding.submissionmade.R
import com.dicoding.submissionmade.core.data.Resource
import com.dicoding.submissionmade.core.domain.model.Movie
import com.dicoding.submissionmade.core.ui.MovieAdapter
import com.dicoding.submissionmade.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {
                val toDeTail = HomeFragmentDirections.actionNavHomeToDetailMovieActivity(it)
                findNavController().navigate(toDeTail)
            }

            homeViewModel.movie.observe(viewLifecycleOwner, {
                showData(it, "unsorted")
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun showData(movie: Resource<List<Movie>>, key: String) {
        when (movie) {
            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                when(key) {
                    "title" -> movieAdapter.setData(movie.data?.sortedBy { it.title })
                    "date" -> movieAdapter.setData(movie.data?.sortedBy { it.releaseDate })
                    "unsorted" -> movieAdapter.setData(movie.data)
                }
            }
            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.viewError.root.visibility = View.VISIBLE
                binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.option_menu, menu)
        val searchView = SearchView(((context as MainActivity).supportActionBar?.themedContext ?: context)!!)
        menu.findItem(R.id.search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                movieAdapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.sort_by_id -> {
                homeViewModel.movie.observe(viewLifecycleOwner, {
                    showData(it, "title")
                })
            }
            R.id.sort_by_date -> {
                homeViewModel.movie.observe(viewLifecycleOwner, {
                    showData(it, "date")
                })
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
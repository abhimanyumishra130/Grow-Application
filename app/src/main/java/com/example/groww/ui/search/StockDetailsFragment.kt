package com.example.groww.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.groww.R
import com.example.groww.adapter.StockDetailsFragmentAdapter
import com.example.groww.viewmodel.ViewModelGrow
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_stock_details.*

@AndroidEntryPoint
class StockDetailsFragment : Fragment(R.layout.fragment_stock_details) {

    private val viewModel: ViewModelGrow by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(requireView())
        val id = arguments?.getInt("id")

        setStockDetails(id)

        btnBuy.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id",id!!)
            navController.navigate(R.id.action_stockDetailsFragment_to_buyShareFragment,bundle)
        }

        ivBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id",id!!)
            navController.navigate(R.id.action_stockDetailsFragment_to_searchAllFragment,bundle)
        }

        ivShareWtsp.setOnClickListener {
        shareView()
        }
    }

    private fun shareView() {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "type/plain"
            val shareBody = "Share your Fav Stock"
            val shareSub = "Research More on this stock to get more Profit"
            intent.putExtra(Intent.EXTRA_SUBJECT,shareBody)
            intent.putExtra(Intent.EXTRA_TEXT,shareSub)
            startActivity(Intent.createChooser(intent,"Share you Stock details"))
    }

    private fun setStockDetails(id: Int?) {
        viewModel.getParticularStock(id!!).observe(viewLifecycleOwner, Observer {
            Glide.with(ivStockPic).load(it.companyImage).into(ivStockPic)
            tvStockName.text = it.companyName
            tvStockPrice.text = "₹ ${it.companyCurrentPrice}"
            tvStockPercentage.text = "${it.priceGain} (${it.percentGain}) 1D"
            StockDetailsOverviewData.currentOverviewStockPrice = it.companyCurrentPrice.toFloat()
            setupFragmentViewPager()
        })
    }

    private fun setupFragmentViewPager() {
        val stockDetailsFragmentAdapter = StockDetailsFragmentAdapter(childFragmentManager,lifecycle)
        stockDetailsViewPager.adapter =stockDetailsFragmentAdapter
            TabLayoutMediator(stockDetailsTabLayout,stockDetailsViewPager){ tab, position ->
                when(position){
                    0 -> tab.text = "Overview"
                    1 -> tab.text = "News"
                    2 -> tab.text = "Events"
                }
            }.attach()
    }

}
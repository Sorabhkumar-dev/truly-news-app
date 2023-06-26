package com.soarbh.trulynews.ui.screen.filtered_news

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.soarbh.trulynews.R
import com.soarbh.trulynews.TrulyApp
import com.soarbh.trulynews.ui.screen.paging_source.TopHeadlineSource
import com.soarbh.trulynews.utils.enums.NewsFilterType
import com.sorabh.data.network.KtorClientRepository
import com.sorabh.data.pojo.request.TopHeadlineRequest
import com.sorabh.data.pojo.response.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilteredNewsViewModel constructor(private val repository: KtorClientRepository) :
    ViewModel() {
    private val _filteredNewsFlow: MutableStateFlow<PagingData<Article>> =
        MutableStateFlow(PagingData.empty())
    val filteredNewsFlow = _filteredNewsFlow.asStateFlow()

    val countriesMap = hashMapOf<String, String>().apply {
        put("ae", "UAE")
        put("ar", "Argentina")
        put("be", "Belgium")
        put("bg", "Bulgaria")
        put("br", "Brazil")
        put("ca", "Canada")
        put("ch", "Switzerland")
        put("cn", "China")
        put("co", "Columbia")
        put("cu", "Cuba")
        put("cz", "Czech Republic")
        put("de", "Germany")
        put("eg", "Egypt")
        put("fr", "France")
        put("gb", "United Kingdom")
        put("gr", "Greece")
        put("hk", "Hong Kong")
        put("hu", "Hungry")
        put("id", "Indonesia")
        put("il", "Israel")
        put("ie", "Ireland")
        put("in", "India")
        put("it", "Italy")
        put("jp", "Japan")
        put("kr", "South Korea")
        put("lt", "Lithuania")
        put("lv", "Latvia")
        put("ma", "Morocco")
        put("mx", "Mexico")
        put("my", "Malaysia")
        put("ng", "Nigeria")
        put("nl", "Netherlands")
        put("no", "Norway")
        put("nz", "New Zealand")
        put("ph", "Philippines")
        put("pl", "Poland")
        put("pt", "Portugal")
        put("ro", "Romania")
        put("rs", "Serbia")
        put("ru", "Russia")
        put("sa", "Saudi Arabia")
        put("se", "Sweden")
        put("sg", "Singapore")
        put("si", "Sloveia")
        put("sk", "Slovakia")
        put("th", "Thailand")
        put("tr", "Turkey")
        put("tw", "Taiwan")
        put("ua", "Ukraine")
        put("us", "United State")
        put("ve", "Venezuela")
        put("za", "South Africa")
    }

    val sources = listOf(
        "NDTV News",
        "India Today",
        "The India Express",
        "The Hindu",
        "News18",
        "Firstpost",
        "Business Standard",
        "DNA",
        "Deccan Chronicale",
        "Storify News",
        "Hub News",
        "New York Times",
        "Wall Street Journal",
        "Huiff Post",
        "Washington Post",
        "Los Angles Times",
        "Reuters",
        "Bloomberg Business",
        "NBC News",
        "Guardian",
        "BBC News",
        "Star",
        "Forbes",
        "CNBC",
        "China Daily",
        "New York Post"
    )

    val categories =
        listOf("business", "entertainment", "general", "health", "science", "sports", "technology")

    val selectedCategory: MutableStateFlow<String?> = MutableStateFlow(null)

    val selectedCountry: MutableStateFlow<String?> = MutableStateFlow(null)

    val selectedSources = mutableStateListOf<String>()

    val selectedFilterType: MutableStateFlow<NewsFilterType> =
        MutableStateFlow(NewsFilterType.Category)

    fun onSelectedCountryChanged(key: String) {
        selectedCountry.value = key
    }

    fun onSelectedCategoryChanged(category: String) {
        selectedCategory.value = category
    }

    fun onSelectedSources(source: String) {
        if (selectedSources.contains(source))
            selectedSources.remove(source)
        else selectedSources.add(source)
    }

    fun onFilterTypeChanged(newsFilterType: NewsFilterType) {
        selectedFilterType.value = newsFilterType
    }

    fun getTopHeadLines() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = {
                    TopHeadlineSource(
                        repository = repository,
                        topHeadlineRequest = TopHeadlineRequest(
                            10,
                            10,
                            TrulyApp.R().getString(R.string.news_api_key),
                            selectedCountry.value,
                            selectedCategory.value,
                            if(selectedSources.isNotEmpty())selectedSources.reduce { acc, s -> acc.plus(",$s") } else null
                        )
                    )
                }
            ).flow.cachedIn(viewModelScope).collect { _filteredNewsFlow.value = it }
        }
    }
}
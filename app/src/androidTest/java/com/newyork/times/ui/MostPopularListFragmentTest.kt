package com.newyork.times.ui

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import com.newyork.times.R
import com.newyork.times.ui.main.adapter.MostPopularAdapter
import com.newyork.times.ui.main.mostpopular.MostPopularFragment
import com.newyork.times.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class MostPopularListFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Test
    fun launchArticleList_checkIfRecyclerView_isDisplayed() {
        launchArticleListFragment()
        onView(withId(R.id.rv_article_list)).check(matches(isDisplayed()))
    }

    @Test
    fun launchArticleList_scrollToPosition_isDisplayed() {
        launchArticleListFragment()
        runBlocking {
            delay(2000)
        }
        onView(withId(R.id.rv_article_list)).perform(
            scrollToPosition<MostPopularAdapter.MostPopularViewHolder>(
                10
            )
        )
    }

    @Test
    fun launchArticleList_clickOnItem() {
        launchArticleListFragment()
        runBlocking {
            delay(2000)
        }
        onView(withId(R.id.rv_article_list)).perform(
            actionOnItemAtPosition<MostPopularAdapter.MostPopularViewHolder>(
                1,
                click()
            )
        )
    }

    private fun launchArticleListFragment() {
        launchFragmentInHiltContainer<MostPopularFragment> {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.mostPopularFragment)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }
}
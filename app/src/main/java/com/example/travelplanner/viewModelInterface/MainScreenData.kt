package com.example.travelplanner.viewModelInterface

interface MainScreenNavigation {
    fun navigateToPlanCreate()
    fun navigateToPlanResult()
    fun navigateToPlanDelete()
}

class FakeMainScreenNavigation : MainScreenNavigation {
    override fun navigateToPlanCreate() {}
    override fun navigateToPlanResult() {}
    override fun navigateToPlanDelete() {}
}
package app.nameplaceholder.common

import app.nameplaceholder.common.SampleViewModel.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SampleViewModel : IViewModel<State> {

  data class State(val name: String = "")

  override val state: StateFlow<State> = MutableStateFlow(State())
  override fun dispose() {
  }
}

typealias SampleViewModelFactory = () -> SampleViewModel
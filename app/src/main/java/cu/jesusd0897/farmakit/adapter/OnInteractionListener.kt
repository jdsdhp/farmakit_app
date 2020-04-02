package cu.jesusd0897.farmakit.adapter

interface OnInteractionListener<M> {
    fun onClick(item: M)
    fun onLongClick(item: M)
}
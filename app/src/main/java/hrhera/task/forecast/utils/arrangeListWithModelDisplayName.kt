package appssquare.projects.HealloCare.utils.ext.arrayadapter

import android.widget.AutoCompleteTextView
import hrhera.task.forecast.utils.CustomArrayAdapter
import com.window.wndo.utils.arrayadapter.ModelDisplayName


fun arrangeListWithModelDisplayNameNormalView(
    mList: List<ModelDisplayName>,
    view: AutoCompleteTextView,
    selectFirst: Boolean = false,
    onSelect: (Int) -> Unit = {}
) {

    val adapter = CustomArrayAdapter(
        view.context, values = mList
    )
    view.setAdapter(adapter)

    if (selectFirst) {
        if (mList.isNotEmpty()) {
            view.setText(mList.first().name, false)
            onSelect(0)
        }
    }
    view.setOnItemClickListener { _, _, position, _ ->
        with(adapter.getItem(position)) {
            view.setText(this.name, false)
            view.setSelection(view.text.count())
            onSelect(position)
        }
    }
}





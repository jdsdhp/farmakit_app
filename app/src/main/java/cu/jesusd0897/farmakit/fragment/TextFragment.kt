package cu.jesusd0897.farmakit.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import cu.jesusd0897.farmakit.R

private const val ARG_TEXT_PARAM = "text_param"

class TextFragment : Fragment() {

    private lateinit var textView: AppCompatTextView
    private var text: String? = null

    companion object {
        fun newInstance(text: String?): TextFragment {
            val fragment = TextFragment()
            val args = Bundle()
            args.putString(ARG_TEXT_PARAM, text)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) text = arguments!!.getString(ARG_TEXT_PARAM)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_text, container, false)
        textView = view.findViewById(R.id.text)
        val placeholder = view.findViewById<View>(R.id.placeholder)
        if (!TextUtils.isEmpty(text)) {
            textView.visibility = View.VISIBLE
            textView.text = text
            placeholder.visibility = View.GONE
        } else {
            textView.visibility = View.GONE
            placeholder.visibility = View.VISIBLE
        }
        return view
    }

}
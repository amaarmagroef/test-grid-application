package evermos.test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import evermos.test.R
import evermos.test.databinding.BottomSheetDetailBinding
import evermos.test.model.ModelImage


/**
 * Created by siapaSAYA on 20/02/2020
 */

class BottomSheetDetail : BottomSheetDialogFragment() {
    private var mTitle : String? = ""
    private var mPath : String? = ""

    fun newInstance(model : ModelImage.Result): BottomSheetDetail {
        val args = Bundle()
        val fragment = BottomSheetDetail()
        args.putString("title", model.title)
        args.putString("paths", model.posterPath)
        mTitle = model.title
        mPath = model.posterPath
        fragment.arguments = args
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<BottomSheetDetailBinding>(
                inflater,
                R.layout.bottom_sheet_detail,
                container,
                false)
        val view : View = binding.root
        val data = ModelImage.Result(
            arguments!!.getString("paths"),
            arguments!!.getString("title"))
        binding.modeldata = data
        ViewCompat.setTransitionName(binding.imagess, data.posterPath)
        return view
    }


}
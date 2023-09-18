    package com.example.footballpazl.fragment

    import android.graphics.Bitmap
    import android.graphics.BitmapFactory
    import android.net.Uri
    import android.os.Bundle
    import android.util.Log
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import androidx.fragment.app.viewModels
    import androidx.lifecycle.lifecycleScope
    import com.example.footballpazl.R
    import com.example.footballpazl.databinding.FragmentMainBinding
    import com.example.footballpazl.viewmodel.PhotoViewModel
    import com.google.android.material.button.MaterialButton
    import dagger.hilt.android.AndroidEntryPoint
    import kotlinx.coroutines.flow.collect
    import kotlinx.coroutines.launch

    @AndroidEntryPoint
    class MainFragment : Fragment() {

        private var imgCountry: ImageView? = null
        val photoViewModel: PhotoViewModel by viewModels()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Инфлейт макета фрагмента
            val view = inflater.inflate(R.layout.fragment_main, container, false)

            // Находим элементы интерфейса через findViewById
            imgCountry = view.findViewById(R.id.img_country)

            return view
        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initBtn()


        }

        private fun initBtn() {
            val btnMoreInfo = view?.findViewById<MaterialButton>(R.id.btn_more_info)
            btnMoreInfo?.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fr_container, InfoFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

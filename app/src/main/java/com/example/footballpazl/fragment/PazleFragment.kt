    package com.example.footballpazl.fragment

    import android.annotation.SuppressLint
    import android.graphics.BitmapFactory
    import android.graphics.Rect
    import android.os.Bundle
    import android.util.Log
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.MotionEvent
    import android.view.View
    import android.view.ViewGroup
    import android.widget.GridLayout
    import android.widget.ImageView
    import android.widget.RelativeLayout
    import android.widget.Toast
    import androidx.constraintlayout.widget.ConstraintLayout
    import com.example.footballpazl.R
    import com.example.footballpazl.databinding.FragmentPazleBinding
    import com.example.utils.PuzzleImage
    import dagger.hilt.android.AndroidEntryPoint
    import java.lang.Math.abs
    import java.lang.Math.sqrt
    import kotlin.random.Random

    @AndroidEntryPoint
    class PazleFragment : Fragment() {

        private lateinit var binding: FragmentPazleBinding
        private var selectedImageView: ImageView? = null
        private var offsetX = 0f
        private var offsetY = 0f
        private val pieceImageViews = mutableListOf<ImageView>()
        private val mergedImageViews = mutableSetOf<ImageView>()
        private var mergedPieceCount = 0

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentPazleBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initImg()
        }

        @SuppressLint("ClickableViewAccessibility")
        private fun initImg() {
            val originalImage = BitmapFactory.decodeResource(resources, R.drawable.img_brazil)
            val numRows = 2 // Количество строк частей пазла
            val numCols = 2 // Количество столбцов частей пазла

            val puzzleImage = PuzzleImage(originalImage, numRows, numCols)
            val puzzlePieces = puzzleImage.splitImage()

            pieceImageViews.add(binding.piece1)
            pieceImageViews.add(binding.piece2)
            pieceImageViews.add(binding.piece3)
            pieceImageViews.add(binding.piece4)

            for (i in pieceImageViews.indices) {
                pieceImageViews[i].setImageBitmap(puzzlePieces[i])
                pieceImageViews[i].tag = i // Устанавливаем тег для каждой части
                Log.d("TagDebug", "Tag for piece $i: ${pieceImageViews[i].tag}")
                pieceImageViews[i].setOnTouchListener { _, event ->
                    handleTouch(pieceImageViews[i], event)
                    true
                }
            }
        }

        private fun handleTouch(imageView: ImageView, event: MotionEvent) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (imageView in mergedImageViews) {
                        // Если элемент уже объединен, игнорируем его
                        selectedImageView = null
                    } else {
                        selectedImageView = imageView
                        offsetX = event.rawX - imageView.x
                        offsetY = event.rawY - imageView.y
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    if (selectedImageView == imageView) {
                        imageView.x = event.rawX - offsetX
                        imageView.y = event.rawY - offsetY
                        checkProximity(imageView)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (selectedImageView == imageView) {
                        selectedImageView = null
                    }
                }
            }
        }

        private fun checkProximity(imageView: ImageView) {
            if (imageView in mergedImageViews) {
                // Если элемент уже объединен, не проверяем близость
                return
            }

            val imageViewRect = Rect()
            imageView.getHitRect(imageViewRect)

            for (piece in pieceImageViews) {
                if (piece != imageView) {
                    val pieceRect = Rect()
                    piece.getHitRect(pieceRect)

                    // Проверяем, стыкуются ли границы элемента imageView с границами элемента piece
                    if (areBordersTouching(imageViewRect, pieceRect)) {
                        Log.d("PazleDebug", "Attempting to merge pieces with tags ${imageView.tag} and ${piece.tag}")
                        tryMergeWithMatchingPiece(imageView, piece)
                    }
                }
            }
        }

        private fun areBordersTouching(rect1: Rect, rect2: Rect): Boolean {

            return rect1.top == rect2.bottom || rect1.bottom == rect2.top || rect1.left == rect2.right || rect1.right == rect2.left
        }


        private fun tryMergeWithMatchingPiece(view1: ImageView, view2: ImageView) {

                // Если теги совпадают, то элементы можно объединить
            mergedPieceCount++
                // Перемещаем view1 в центр view2
                val centerX2 = view2.x + view2.width / 2
                val centerY2 = view2.y + view2.height / 2

                view1.x = centerX2 - view1.width / 2
                view1.y = centerY2 - view1.height / 2

                Log.d("PazleDebug", "Merged pieces with tag ${view1.tag}")

                Log.d("PazleDebug", "Removed view2 with tag ${view2.tag}")

                // Добавляем view1 в список объединенных элементов
                mergedImageViews.add(view1)

                Log.d("PazleDebug", "Added view1 with tag ${view1.tag} to mergedImageViews")
            if (mergedPieceCount == pieceImageViews.size) {
                showToast("Все части пазла объединены!")
                binding.btnNext.visibility = View.VISIBLE
            }
            }
        private fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            binding.btnNext.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fr_container, MainFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }


    }
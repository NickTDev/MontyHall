package com.example.montyhallproblem

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.montyhallproblem.databinding.FragmentMontyHallBinding
import androidx.appcompat.content.res.AppCompatResources.getDrawable as getDrawable1


class MontyHallFragment : Fragment(){
    private var _binding: FragmentMontyHallBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private var timesClicked: Int = 0
    private var playerChoice: Int = 0
    private var correctChoice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Generates the correct choice
        correctChoice = generateCorrect()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentMontyHallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            doorOne.setOnClickListener {view: View ->
                if (timesClicked == 0) {
                    playerChoice = 1
                    removeIncorrect(playerChoice, correctChoice)
                    timesClicked++
                } else if (timesClicked == 1) {
                    playerChoice = 1
                    checkIfCorrect(playerChoice, correctChoice)
                }
            }

            doorTwo.setOnClickListener {view: View ->
                if (timesClicked == 0) {
                    playerChoice = 2
                    removeIncorrect(playerChoice, correctChoice)
                    timesClicked++
                } else if (timesClicked == 1) {
                    playerChoice = 2
                    checkIfCorrect(playerChoice, correctChoice)
                }
            }

            doorThree.setOnClickListener {view: View ->
                if (timesClicked == 0) {
                    playerChoice = 3
                    removeIncorrect(playerChoice, correctChoice)
                    timesClicked++
                } else if (timesClicked == 1) {
                    playerChoice = 3
                    checkIfCorrect(playerChoice, correctChoice)
                }
            }

            resetButton.setOnClickListener { view: View ->
                resetGame()
            }
        }
    }

    private fun generateCorrect(): Int {
        var choice = (1..3).random()
        return choice
    }

    private fun removeIncorrect(pChoice: Int, cChoice: Int) {
        var toRemove: Int = 1

        if (toRemove == pChoice || toRemove == cChoice) { //Checks if either player or correct is door 1
            toRemove = 2
            if (toRemove == pChoice || toRemove == cChoice) { //Checks if either player or correct is door 2
                toRemove = 3
            }
        }

        //Removes a door by switching the image to an X
        if (toRemove == 1) {
            binding.doorOne.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.wrong, null))
            //binding.doorOne.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.wrong))
            binding.doorOne.isEnabled = false
        } else if (toRemove == 2) {
            binding.doorTwo.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.wrong, null))
            //binding.doorTwo.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.wrong))
            binding.doorTwo.isEnabled = false
        } else if (toRemove == 3) {
            binding.doorThree.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.wrong, null))
            //binding.doorThree.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.wrong))
            binding.doorThree.isEnabled = false
        }

        //Updates the instructions
        binding.announceText.setText(getText(R.string.removed_door))
    }

    private fun checkIfCorrect(pChoice: Int, cChoice: Int) {
        if (pChoice == 1) {
            if (pChoice == cChoice) {
                binding.doorOne.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.medal, null))
                //binding.doorOne.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.medal))
                binding.announceText.setText(getText(R.string.win_text))
            } else {
                binding.doorOne.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.donkey, null))
                //binding.doorOne.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.donkey))
                binding.announceText.setText(getText(R.string.lose_text))
            }
        } else if (pChoice == 2) {
            if (pChoice == cChoice) {
                binding.doorTwo.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.medal, null))
                //binding.doorTwo.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.medal))
                binding.announceText.setText(getText(R.string.win_text))
            } else {
                binding.doorTwo.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.donkey, null))
                //binding.doorTwo.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.donkey))
                binding.announceText.setText(getText(R.string.lose_text))
            }
        } else if (pChoice == 3) {
            if (pChoice == cChoice) {
                binding.doorThree.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.medal, null))
                //binding.doorThree.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.medal))
                binding.announceText.setText(getText(R.string.win_text))
            } else {
                binding.doorThree.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.donkey, null))
                //binding.doorThree.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.donkey))
                binding.announceText.setText(getText(R.string.lose_text))
            }
        }

        binding.doorOne.isEnabled = false
        binding.doorTwo.isEnabled = false
        binding.doorThree.isEnabled = false
    }

    private fun resetGame() {
        timesClicked = 0
        playerChoice = 0
        correctChoice = generateCorrect()

        binding.doorOne.isEnabled = true
        binding.doorTwo.isEnabled = true
        binding.doorThree.isEnabled = true

        binding.doorOne.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.door, null))
        //binding.doorOne.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.door))
        binding.doorTwo.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.door, null))
        //binding.doorTwo.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.door))
        binding.doorThree.setImageDrawable(ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.door, null))
        //binding.doorThree.setImageDrawable(getDrawable(requireActivity().getApplicationContext(), R.drawable.door))

        binding.announceText.setText(getText(R.string.announce_text))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.ticktactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var  player1Wins = 0
    var player2Wins = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(view: View) {
        val buttonSelected = view as Button

        var cellID = 0
        when(buttonSelected.id){
            R.id.button1 -> cellID = 1
            R.id.button2 -> cellID = 2
            R.id.button3 -> cellID = 3
            R.id.button4 -> cellID = 4
            R.id.button5 -> cellID = 5
            R.id.button6-> cellID = 6
            R.id.button7 -> cellID =7
            R.id.button8 -> cellID = 8
            R.id.button9 -> cellID = 9
        }

       //Log.d("btnClick",buttonSelected.id.toString());
        //Log.d("btnClick: cellID",cellID.toString());
        playGame(cellID,buttonSelected);
    }

    var activePlayer = 1;
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var totalTurns = 0
    fun playGame(cellId:Int,buttonSelected:Button){

        if(winner)
            return
        totalTurns ++
        if(activePlayer ==1){
            buttonSelected.text = "X"
            activePlayer = 2
            player1.add(cellId)
            buttonSelected.setBackgroundResource(R.color.player1)
            if(checkWinner(player1)){
                player1Wins++
                player1Score.text = player1Wins.toString()
                Toast.makeText(this, "Player 1 wins",Toast.LENGTH_LONG).show()
            }
        else
            autoPlay()
        }
        else{
            activePlayer = 1
        player2.add(cellId)
        buttonSelected.text = "O"
        buttonSelected.setBackgroundResource(R.color.player2)
        if(checkWinner(player2)){
            player2Wins++
            player2Score.text = player2Wins.toString()
            Toast.makeText(this, "Player 2 wins",Toast.LENGTH_LONG).show()

        }
        }

        buttonSelected.isEnabled = false

        if(totalTurns == 9){
            Toast.makeText(this,"Game is a draw",Toast.LENGTH_LONG).show()
            menuRow.isVisible = true
        }

    }

        var winner = false
    fun checkWinner(player:ArrayList<Int>):Boolean{
        winner = false;
        //row 1
        if(player.contains(1) && player.contains(2) && player.contains(3))
          winner = true
        //row 2
        else if(player.contains(4) && player.contains(5) && player.contains(6))
            winner = true
        //row 3
        else if(player.contains(7) && player.contains(8) && player.contains(9))
         winner = true
        //col 1
        else if(player.contains(1) && player.contains(4) && player.contains(7))
            winner = true
        //col 2
        else if(player.contains(2) && player.contains(5) && player.contains(8))
            winner = true
        //col 3
        else if(player.contains(3) && player.contains(6) && player.contains(9))
            winner = true

        if(winner)
            menuRow.isVisible = true

    return winner
        }

    fun autoPlay(){

        var emptyCells = ArrayList<Int>()
        for (cellId:Int in 1..9)
        {
            if(!player1.contains(cellId) && !player2.contains(cellId)){
                emptyCells.add((cellId))
            }
        }
            if(emptyCells.count() == 0)
            return
            val randomIndex = Random.nextInt(emptyCells.size)
        val cellId = emptyCells[randomIndex]

        var selected:Button?
        selected = when (cellId){
            1-> button1
            2-> button2
            3-> button3
            4-> button4
            5-> button5
            6-> button6
            7-> button7
            8-> button8
            9-> button9
            else -> { button1}
        }

        playGame(cellId,selected)
    }

   private fun restartGame(){
        menuRow.isVisible = false
    totalTurns = 0;
        winner = false
        player1.clear()
        player2.clear()
        activePlayer =1

        for(cellId in 1..9){
          var btnselect:Button? = when(cellId){
              1-> button1
              2-> button2
              3-> button3
              4-> button4
              5-> button5
              6-> button6
              7-> button7
              8-> button8
              9-> button9
              else -> { button1}
          }
            btnselect!!.text = ""
            btnselect!!.setBackgroundResource(R.color.button)
            btnselect.isEnabled = true
        }
    }

    fun onbtnRestart(view: View) {

        restartGame()
    }


}

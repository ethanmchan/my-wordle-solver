# my-wordle-solver
Solves the daily wordle puzzle (https://www.nytimes.com/games/wordle/index.html) using optimal words

*****************************************************************************************************************************

How To Use Wordle Solver:
This Wordle Solver program solves the daily Wordle puzzle in the optimal number of moves using optimal words. 
***Note: If you don't know what Wordle is, refer to the link and instructions at the bottom of this page.

The Wordle Solver program starts by prompting the user to enter their first guess for the Wordle puzzle. 
For example, if the user's first guess for the Wordle puzzle is "plane", the user will also enter "plane" into
the Wordle Solver. After the user submits their first word into the Wordle game, the game will display a series 
of colors that the user will input into the Wordle Solver. For example, if the colors corresponding to "plane" are 
green, green, black, yellow, black, the user will input "ggbyb" into the Wordle Solver. The Wordle Solver
will then output the optimal word to guess for your next word in the game. This process will repeat until the
user guesses the mystery word in the Wordle game.

***Note: The Wordle Solver will fail only if the word for the day is not in this program's five-letter word bank. (five-letter-word.txt)
         In very, very unlikely cases, the Wordle Solver will also fail because of single-letter randomness (which is impossible to avoid)
           ex) canes --> bgggg         possible words: manes, banes, lanes, wanes, vanes, panes, janes, etc. > 6 

Thank You and Have Fun!

*****************************************************************************************************************************

The Rules and How To Play Wordle (https://www.nytimes.com/games/wordle/index.html):

1. You have six tries to guess the five-letter Wordle of the day.
2. Type in your guess and submit your word by hitting the “enter” key on the Wordle keyboard.
3. The color of the tiles will change after you submit your word. A yellow tile indicates that you picked the right
   letter but it’s in the wrong spot. The green tile indicates that you picked the right letter in the correct spot. 
   The gray tile indicates that the letter you picked is not included in the word at all.
4. Continue until you solve the Wordle or run out of guesses. Good luck!

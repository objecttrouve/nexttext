
[<img src="https://travis-ci.org/objecttrouve/nexttext.svg?branch=master" alt="Travis build"/>](https://travis-ci.org/objecttrouve/nexttext)

# Next Text

Experimental library for a simple text similarity measure.

## CRI Distance

The CRI distance is a measure for superficial text similarity. "CRI" stands for **c**haracter **r**epetition **i**ntervals. 
The idea behind it is to gauge a text by capturing the numbers of symbols it takes for each character to repeat. 

### Example

Given an alphabet consisting of the letters "a" and "b". Let's index them with 0 and 1.
Let's define an "interval" simply as the difference between the character indexes in the string. 
The length of the first interval is the position of the respective character. 
Because it takes that many symbols until the character "repeats" for the first time.

More formally: 

Let _s<sub>i<sub>_ be a unique symbol where _0 &le; i &le; n_ .

Let _A_ be an alphabet of _n_ symbols:<br>_A_ = {_s<sub>0<sub>_, ... _s<sub>n</sub>_}

Let _S_ be a sequence of length _l_ of symbols from _A_.

Let _j_,_k_ (_0&le;j<;k_, _k&le;m_) be the positions of two occurrences of _s<sub>i<sub>_ in _S_ where no other occurrence of _s<sub>i<sub>_ intervenes.

Then a CRI interval is just the difference between the positions: <br><i>CRI<sub>i,j,k</sub>  = (j..k] = k - j</i>

For the _first_ occurrence of _s<sub>i<sub>_ we define _j=0_ and set _k_ to the position of the first occurrence, thus allowing _j&le;k_.

So much for the interval.

Now, let's look at the string "abba". 
It takes 0 positions to increment for the "a" to appear for the first time and one for the "b". 
From the first "b" to the second "b", the position increments by one, so the interval is one. 
From the first "a" to the next, the position increments by 3.

Now, let's put this in a matrix where the first dimension is the alphabet indexes and the second are the frequencies (counts) of the respective repetition intervals (CRI): 

String "abba":
<table>
    <tr><td>Position</td><td><b>0</b></td><td><b>1</b></td><td><b>2</b></td><td><b>3</b></td></tr>
    <tr><td>Character</td><td>a</td><td>b</td><td>b</td><td>a</td></tr>
</table>

CRI counts of "abba":
<table>
    <tr>
        <td>&#x2b10; Symbol / CRI &#x2192;</td><td><b>0</b></td><td><b>1</b></td><td><b>2</b></td><td><b>3</b></td>
    </tr>
    <tr>
        <td><b>0</b> ("a")</td> <td>1</td> <td>0</td> <td>0</td> <td>1</td>
    </tr>
     <tr>
        <td><b>1</b> ("b")</td> <td>0</td> <td>2</td> <td>0</td> <td>0</td>
     </tr>
</table>

Let's compare this with the matrix we get for the string "baba":

String "baba":
<table>
    <tr><td><b>0</b></td><td><b>1</b></td><td><b>2</b></td><td><b>3</b></td></tr>
    <tr><td>b</td><td>a</td><td>b</td><td>a</td></tr>
</table>

CRI counts of "baba":
<table>
    <tr>
        <td>&#x2b10; Symbol / CRI &#x2192;</td><td><b>0</b></td><td><b>1</b></td><td><b>2</b></td><td><b>3</b></td>
    </tr>
    <tr>
        <td><b>0</b> ("a")</td> <td>0</td> <td>1</td> <td>1</td> <td>0</td>
    </tr>
     <tr>
        <td><b>1</b> ("b")</td> <td>1</td> <td>0</td> <td>1</td> <td>0</td>
     </tr>
</table>
<br>

Formally: 


Now, let's calculate a naïve distance between those two matrixes simply by adding up the absolute differences between the individual cell values.

The distance between "abba" and "baba" is 8.

Let's compare the strings "abbababa" and "babaabba" in the same way. The two strings have been concatenated and concatenated in reverse order.
The distance is still 8.

The algorithm honors identical subsequences that can appear in different orders and in different locations. 


## Disclaimer

I have no idea whether this algorithm has any academic discourse. I'm basically implementing it as an incentive to learn Kotlin.
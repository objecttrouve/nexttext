
[<img src="https://travis-ci.org/objecttrouve/nexttext.svg?branch=master" alt="Travis build"/>](https://travis-ci.org/objecttrouve/nexttext)

[<img src="https://maven-badges.herokuapp.com/maven-central/org.objecttrouve/nexttext/badge.svg" alt="Latest release in Maven Repository"/>](https://mvnrepository.com/artifact/org.objecttrouve/nexttext)


# Next Text

Experimental library for a simple text similarity measure.

## CRI Distance

The CRI distance is a measure for superficial text similarity. "CRI" stands for **c**haracter **r**epetition **i**ntervals. 
The idea behind it is to gauge a text by capturing the numbers of symbols it takes for each character to repeat. 

The normalized CRI distance of two texts is a value between 0 and 1, where 0 means the texts are identical and 0 means they have nothing in common at all. 

### Theory
#### By Example

Given an alphabet consisting of the letters "a" and "b". Let's index them with 0 and 1.
Let's define an "interval" simply as the difference between the character indexes in the string. 
The length of the first interval is the position of the respective character. 
Because it takes that many symbols until the character "repeats" for the first time.

So much for the interval.

Now, let's look at the string "abba". 
It takes 0 positions to increment for the "a" to appear for the first time and one for the "b". 
From the first "b" to the second "b", the position increments by one, so the interval is one. 
From the first "a" to the next, the position increments by 3.

Now, let's put this in a matrix where the first dimension is the alphabet indexes and the second are the frequencies (counts) of the respective repetition interval (CRI) lengths: 

String "abba":
<table>
    <tr><td>Position</td><td><b>0</b></td><td><b>1</b></td><td><b>2</b></td><td><b>3</b></td></tr>
    <tr><td>Character</td><td>a</td><td>b</td><td>b</td><td>a</td></tr>
</table>

CRI counts of "abba":
<table>
    <tr>
        <td>&#x2b10; Symbol / CRI length &#x2192;</td><td><b>0</b></td><td><b>1</b></td><td><b>2</b></td><td><b>3</b></td>
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
        <td>&#x2b10; Symbol / CRI length &#x2192;</td><td><b>0</b></td><td><b>1</b></td><td><b>2</b></td><td><b>3</b></td>
    </tr>
    <tr>
        <td><b>0</b> ("a")</td> <td>0</td> <td>1</td> <td>1</td> <td>0</td>
    </tr>
     <tr>
        <td><b>1</b> ("b")</td> <td>1</td> <td>0</td> <td>1</td> <td>0</td>
     </tr>
</table>
<br>

Now, let's calculate a naïve distance between those two matrixes simply by adding up the absolute differences between the individual cell values.

The distance between "abba" and "baba" is 8.

Let's compare the strings "abbababa" and "babaabba" in the same way. The two strings have been concatenated and concatenated in reverse order.
The distance is still 8.

The algorithm honors identical subsequences that can appear in different orders and in different locations. 

Finally, it would be nice to have a normalized value which is easier to interpret than the raw distance. 
We divide the distance by the sum of the two text's lengths and we get a result between 0 and 1, 0 meaning identical and 1 meaning nothing in common at all.

:warning: The metric can be tricky if the compared strings don't contain any repeating characters. It returns 1 when differences occur at the beginning.

#### More Formally

Let _s<sub>i<sub>_ be a unique symbol where _0 &le; i &le; n_ .

Let _A_ be an alphabet of _n_ symbols:<br>_A_ = {_s<sub>0<sub>_, ... _s<sub>n</sub>_}

Let _S_ be a sequence of length _L_ of symbols from _A_.

Let _j_,_k_ (_0&le;j<;k_, _k&lt;L_) be the positions of two occurrences of _s<sub>i<sub>_ in _S_ where no other occurrence of _s<sub>i<sub>_ intervenes.

Then a CRI interval is just the range of positions from _j_ to _k_, not including _k_: <br><i>CRI<sub>i,j,k</sub>  = [j..k)</i>

For the _first_ occurrence of _s<sub>i<sub>_ we define _j=0_ and set _k_ to the position of the first occurrence of _s<sub>i<sub>_ (_j&le;k_).

The length <i>l<sub>k,j</sub></i> of the _CRI_ is <i>k - j</i>.

The CRI count <i>c<sub>i,l</sub></i> is the count of all repetition intervals of <i>s<sub>i<sub></i> of length _l_.

Let <i>M<sub>1</sub><sup>i⨯l</sup></i> be a matrix of CRI counts _c_ of _S<sub>1<sub>_ by symbol index _i_ and CRI length _l_.

Let <i>M<sub>2</sub><sup>i⨯l</sup></i> be a matrix of CRI counts _c_ of _S<sub>2<sub>_ by symbol index _i_ and CRI length _l_.

Let <i>M<sub>diff</sub></i> be the subtraction of the two matrixes: <i>M<sub>diff</sub> = M<sub>1</sub> - M<sub>2</sub></i>

Then the absolute CRI distance _D_ is just the sum over the absolute values of _d_ of <i>M<sub>diff</sub></i>: <i>D = &#x2211;|d<sub>i,l</sub>|</i>

The normalized CRI distance is _D/(L<sub>1</sub>+L<sub>2</sub>)_.

### Disclaimer

I have no idea whether this algorithm has any academic or whatsoever discourse. 
Didn't find it among the top 10 on Google. (Or googled the wrong buzzwords.) And didn't bother to do any research on the field. 
(Actually, I was just thinking about a pragmatic solution for an imminent task.)
Giving the algorithm is so trivial, I'm sure it already exists. Or otherwise it's just crap.
I'm implementing it anyway as a nice playground to learn Kotlin.

So, forgive me and let me know if I (unintentionally) plagiarized.

:see_no_evil: And condone the amateurish Kotlin...

## Usage

I have a weakness for the builder pattern, so the API looks like this:

```
        val nextText = NextText.Builder()
                .withMinCodePoint(0)
                .withMaxCodePoint(127)
                .build()

        val criDistance = nextText.criDistance("text 1", "text 2")

        println("The normalized CRI distance is $criDistance.")
```

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author Alejandro Luna
 */

function shareOnFacebook() {
    var x = document.URL;
    var y = "https://www.facebook.com/sharer/sharer.php?u=";
    x =  y.concat(x);
    window.open(x);
}

function shareOnTwitter() {
    var x = document.URL;
    var y = "https://twitter.com/intent/tweet?button_hashtag=Skynet&text=";
    x =  y.concat(x);
    window.open(x);    
}



<html>
	<head>
		<meta name="layout" content="principal"/>
                <title>Pagina no encontrada</title>
        </head>
    <body >
    	<div>
    		<h1 class="not-found" >404
    		NYAN FOUND!</h1>
    	</div>
    	<audio id="player_intro" src="${createLinkTo(dir:'sounds',file:'nyan-cat-intro.ogg')}" type="audio/ogg" ></audio>
    	<audio id="player_song" src="${createLinkTo(dir:'sounds',file:'nyan-cat.ogg')}" type="audio/ogg" ></audio>
    	<div id="nyan-cat" class="nyan" >
    		<img src="${createLinkTo(dir:'images',file:'nyan-cat-animated.gif')}" alt="NYAN!" width="800" height="800" />
    	</div>
    	<script type="text/javascript" >
    	<!--
    		var playeri = document.getElementById("player_intro");
    		var players = document.getElementById("player_song");
    		players.addEventListener('ended', function() {
				this.currentTime = 0;
				this.play();
			}, false);
			playeri.play();
    		window.setTimeout(function() {
    			players.play();
    			$("#nyan-cat").show();
    		}, 3500);
    	-->
    	</script>
    </body>
</html>


<html>
    <head>
        <meta name="layout" content="principal"/>
        <title>Error</title>
	<script type="text/javascript">
            $(document).ready(function() {
                document.getElementById("player").play();
            });
	</script>
    </head>
    <body >
        <!-- audio id="player" src="/sounds/Game_Over.ogg" type="audio/ogg" ></audio -->
        <audio id="player" src="http://www.vgmpf.com/Wiki/images/d/d7/11_-_Legend_of_Zelda_-_NES_-_Game_Over.ogg" type="audio/ogg" ></audio>
    	<div>
            <img src="${createLinkTo(dir:'images', file:'i-am-error.gif')}" alt="You've met with a terrible fate, haven't you?" width="400" height="300" style="margin: 2%; margin-left: 12%;" />
                <h3 style="float: right; margin-top: 10%; margin-right: 15%;" > 
                 <g:if env="development">
                    <g:renderException exception="${exception}" />
                </g:if>
                <g:else>
                    Error m&eacute;todo no permitido
                </g:else>
                </h3>
    	</div>
    </body>
</html>


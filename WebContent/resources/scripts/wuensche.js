/**
 * 
 */

var getIndexOfActiveElement = function() {
	console.log("temp");
    document.getElementById("deleteLineForm:hiddenIndex").value = $('.clickable-row.active').index();
}

var onLoadFunction = function(){
	$("#wuenscheTabelle").on("click",".clickable-row", function(){
		  $(this).addClass('active').siblings().removeClass('active');
			document.getElementById("deleteLineForm:deleteLineButton").disabled = false;
			document.getElementById("deleteLineForm:deleteLineButton").classList.remove(".disabled");
		});

}



jQuery(document).ready(function($) {
	onLoadFunction();

})

$( window ).on( "load", function() { 
	document.getElementById("deleteLineForm:deleteLineButton").disabled = true; 
	document.getElementById("deleteLineForm:deleteLineButton").classList.add(".disabled");
})
/**
 * 
 */

var getIndexOfActiveElement = function() {
	console.log("temp");
    document.getElementById("deleteLineForm:hiddenIndex").value = $('.clickable-row.active').index();
}

jQuery(document).ready(function($) {

	$('.clickable-row').click( function(event) {
		  $(this).addClass('active').siblings().removeClass('active');
			document.getElementById("deleteLineForm:deleteLine").disabled = false;
			document.getElementById("deleteLineForm:deleteLine").classList.remove(".disabled");
		 
		});
})

$( window ).on( "load", function() { 
	document.getElementById("deleteLineForm:deleteLine").disabled = true; 
	document.getElementById("deleteLineForm:deleteLine").classList.add(".disabled");
})
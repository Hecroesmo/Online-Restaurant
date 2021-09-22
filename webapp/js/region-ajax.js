/**
 Ajax code to bring regions to select's tags'
 */

$.when().then(function () {	
	$("#province").on( "change", function() {
		var provinceId = $( "#province" ).val()
		var municipality = $( "#municipality" )
		getRegionsById(provinceId, municipality, "Municipality")
	});
	
	$("#municipality").on( "change", function() {
		var municipalityId = $( "#municipality" ).val()
		var commune = $( "#commune" )
		getRegionsById(municipalityId, commune, "Commune")
	});
	
	$("#commune").on( "change", function() {
		var communeId = $( "#commune" ).val()
		var neighborhood = $( "#neighborhood" )
		getRegionsById(communeId, neighborhood, "Neighborhood")
	});
});

function getRegionsById(id, region, typeRegion) {
	$( region ).empty().append( "<option>Selecione...</option" )
	// Using the core $.ajax() method
	$.ajax({
	 
	    // The URL for the request
	    url: "HandleRequest",
	 
	    // The data to send (will be converted to a query string)
	    data: {
	        id,
			typeRegion
	    },
	 
	    // Whether this is a POST or GET request
	    type: "GET",
	 
	    // The type of data we expect back
	    dataType : "json",
	})
	  // Code to run if the request succeeds (is done);
	  // The response is passed to the function
	  .done(function(json) {
		appendToSelect(json, region)
	  })
	  // Code to run if the request fails; the raw request and
	  // status codes are passed to the function
	  .fail(function( xhr, status, errorThrown ) {
	    alert( "Sorry, there was a problem!" );
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	  })
	  // Code to run regardless of success or failure;
	  .always(function( xhr, status ) {
		console.dir( "The request is complete!" );
	  });
}

function appendToSelect(json, region) {
	for (let i = 0; i < json.length; i++) {
			$( region ).append( "<option value=\""+ json[i].pkRegion +
			 "\">"+ json[i].name +" </option" )
	}
}
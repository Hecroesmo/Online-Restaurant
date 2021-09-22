/**
 Ajax code to bring categories to select's tags'
 */

$.when().then(function () {
	
	$("#fcategory").on( "change", function() {
		var firstCategoryId = $( "#fcategory" ).val()
		var secondCategory = $( "#scategory" )
		getRegionsById(firstCategoryId, secondCategory)
	});
	
	$("#scategory").on( "change", function() {
		var secondCategoryId = $( "#scategory" ).val()
		var thirdCategory = $( "#tcategory" )
		getRegionsById(secondCategoryId, thirdCategory)
	});
	
	$("#tcategory").on( "change", function() {
		var thirdCategoryId = $( "#tcategory" ).val()
		var product = $( "#product" )
		getRegionsById(thirdCategoryId, product)
	});
});

function getRegionsById(id, category) {
	$( category ).empty().append( "<option>Selecione...</option" )
	// Using the core $.ajax() method
	$.ajax({
	 
	    // The URL for the request
	    url: "HandleRequestCategory",
	 
	    // The data to send (will be converted to a query string)
	    data: {
	        id
	    },
	 
	    // Whether this is a POST or GET request
	    type: "GET",
	 
	    // The type of data we expect back
	    dataType : "json",
	})
	  // Code to run if the request succeeds (is done);
	  // The response is passed to the function
	  .done(function(json) {
		appendToSelect(json, category)
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

function appendToSelect(json, category) {
	for (let i = 0; i < json.length; i++) {
			$( category ).append( "<option value=\""+ json[i].pkCategory +
			 "\">"+ json[i].name +" </option" )
	}
}





$( window ).ready( () => {
	var firstCategory = $( "#fcategory" )
	var firstCategoryId = $( "#f-category" ).html()
	setDefaultOption(firstCategoryId, firstCategory);
	
	var secondCategory = $( "#scategory" )
	var secondCategoryId = $( "#s-category" ).html()
	setDefaultOption(secondCategoryId, firstCategory);
	
	var thirdCategory = $( "#tcategory" )
	var thirdCategoryId = $( "#t-category" ).html()
	setDefaultOption(thirdCategoryId, firstCategory);
});

function setDefaultOption(id, element) {
	$(element).val(id)
}	
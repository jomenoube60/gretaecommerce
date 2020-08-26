$(function(){
	console.log("dans jquery");
	let csrfHeaderMeta = $("meta[name=csrfHeaderName]");
	let csrfHeaderName = $(csrfHeaderMeta).attr("content");

	let csrfTokenMeta = $("meta[name=csrfToken]");
	let csrfToken = $(csrfTokenMeta).attr("content");

	console.log(csrfHeaderName);
	console.log(csrfToken);
	/* $(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(csrfHeaderName, csrfToken);
	});*/

	let post = function(url , successUrl) {
		$.post(url, function(data){
			console.log(data);
			if(data){
				alert("Categorie suprimmé !");
				window.location.href = successUrl;
//				window.location.replace(successUrl);
			}
			else{
				alert("opération échouée !");
			}
		});
	}

	$(".deleteCategorieBtn").on("click", function(evt){
		evt.preventDefault();
		let url = $(this).attr("href");
		let successUrl = $(this).attr("success-url");
		post(url, successUrl);
	});

	$(".deleteMenuBtn").on("click", function(evt){
		evt.preventDefault();
		let url = $(this).attr("href");
		let successUrl = $(this).attr("success-url");
		post(url, successUrl);
	});

});

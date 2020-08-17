$(function(){
	console.log("dans jquery");
	let csrfHeaderMeta = $("meta[name=csrfHeaderName]");
	let csrfHeaderName = $(csrfHeaderMeta).attr("content");
	
	let csrfTokenMeta = $("meta[name=csrfToken]");
	let csrfToken = $(csrfTokenMeta).attr("content");
	
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(csrfHeaderName, csrfToken);
	});
	
	$(".deleteCategorieBtn").on("click", function(evt){
		evt.preventDefault();
		alert("test deleteCategorieBtn");
		//récupérer Nom
		//créer la réquete POST AJAX
		let url = $(this).attr("href");
		let successUrl = $(this).attr("success-url");
		console.log(successUrl);
		console.log(url);
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
	});
});

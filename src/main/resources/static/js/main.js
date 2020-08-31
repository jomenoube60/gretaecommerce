

	$(function(){
//	console.log("dans jquery");
//	let csrfHeaderMeta = $("meta[name=csrfHeaderName]");
//	let csrfHeaderName = $(csrfHeaderMeta).attr("content");
//
//	let csrfTokenMeta = $("meta[name=csrfToken]");
//	let csrfToken = $(csrfTokenMeta).attr("content");
//	$(document).ajaxSend(function(e, xhr, options) {
//		xhr.setRequestHeader(csrfHeaderName, csrfToken);
//	});

		// Factorisation de la requete POST AJAX
	let post = function(url , successUrl) {
	//créer la réquete POST AJAX
		console.log("ajax");
		$.post(url, function(data){
			if(data){
				alert("Opération réussie!");
				window.location.href = successUrl;
//				window.location.replace(successUrl);
			}
			else{
				alert("opération échouée !");
			}
		}); 	
	}
	
	$(".activeCategorieBtn").on("click", function(evt){
		evt.preventDefault();
		alert("test activeCategorieBtn");
		//récupérer Nom
		let url = $(this).attr("href");
		let successUrl = $(this).attr("success-url");
		post(url, successUrl);
	});

	$(".desactiveCategorieBtn").on("click", function(evt){
		evt.preventDefault();
		alert("test desactiveCategorieBtn");
		//récupérer Nom
		let url = $(this).attr("href");
		let successUrl = $(this).attr("success-url");
		post(url, successUrl);
	});

//	Produit boutons activer/désactiver
	$(".activerProdBtn").on("click", function(evt){
		evt.preventDefault();
		alert("test activerProdBtn");
		//récupérer Nom
		let url = $(this).attr("href");
		let successUrl = $(this).attr("success-url");
		post(url, successUrl);
	});

	$(".desactiverProdBtn").on("click", function(evt){
		evt.preventDefault();
		alert("test desactiverProdBtn");
		//récupérer Nom
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

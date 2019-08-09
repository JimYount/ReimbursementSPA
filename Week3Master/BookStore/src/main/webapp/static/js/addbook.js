var book={};
book.authors=[];
book.genres=[];
var baseURL="/BookStore/";

window.onload=function(){
	
	getAuthors();
	getGenres();
	
	//event listeners
	document.getElementById("b_authors").onchange=addAuthor;
	document.getElementById("b_genres").onchange=addGenre;
	
	document.onkeydown = checkEnter;
	document.getElementById("submitbtn").onclick=submit;
	document.getElementById("backbtn").onclick=backtobooks;
	let simple = document.getElementsByClassName("simple-book");
	for(let i = 0; i<simple.length; i++){
		simple[i].onchange=updateField;
		simple[i].keydown=checkEnter;
	}
	//add our navbar
	addNavBar();
}

function populateForm(){
	document.getElementById("b_title").value=book.title;
	document.getElementById("b_cover").value = book.cover;
	document.getElementById("b_isbn10").value = book.isbn10;
	document.getElementById("b_isbn13").value = book.isbn13;
	document.getElementById("b_stock").value = book.stock;
	document.getElementById("b_price").value = book.price;
}
function getAuthors(){
	console.log("getAuthors()");
	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=addAuthorsToList;
	xhttp.open("GET", baseURL+"authors");
	xhttp.send();
	function addAuthorsToList(){
		if(xhttp.readyState===4 && xhttp.status===200){
			//console.log(xhttp.responseText);
			let authors=JSON.parse(xhttp.responseText);
			let authorSelect = document.getElementById("b_authors");
			for(let i = 0; i<authors.length; i++){
				if(book.authors.length==0 || book.authors.find(author=>author.id != authors[i].id)){
					let option = document.createElement("option");
					option.id="aopt_"+authors[i].id;
					option.value=JSON.stringify(authors[i]);
					option.innerHTML=authors[i].first+" "+authors[i].last;
					authorSelect.appendChild(option);
				} else{
					let li = document.createElement("li");
					li.id="ali_"+authors[i].id;
					li.innerHTML=authors[i].first+" "+authors[i].last;
					li.addEventListener("click", removeAuthor);
					document.getElementById("selectedAuthors").appendChild(li);
				}
			}
		}
	}
}
function removeAuthor(){
	let li = event.target;
	let id = li.id.substring("ali_".length);
	
	//remove it from the array
	let author = book.authors.find(author=>author.id==id);
	book.authors.splice(book.authors.indexOf(author),1);
	
	//remove from the list
	document.getElementById("selectedAuthors").removeChild(li);
	
	// add to select
	let option = document.createElement("option");
	option.id="aopt_"+author.id;
	option.value=JSON.stringify(author);
	option.innerHTML=author.first+" "+author.last;
	document.getElementById("b_authors").appendChild(option);
}
function addAuthor(){
	var author = JSON.parse(document.getElementById("b_authors").value);
	// add to the book object so our model is correct
	book.authors.push(author);
	
	// update our view
	
	// add to our list
	let li = document.createElement("li");
	li.id="ali_"+author.id;
	li.innerHTML=author.first+" "+author.last;
	li.addEventListener("click", removeAuthor);
	document.getElementById("selectedAuthors").appendChild(li);
	
	//remove from the options
	var option = document.getElementById("aopt_"+author.id);
	var select = document.getElementById("b_authors");
	select.removeChild(option);
}
function getGenres(){
	console.log("getGenres()")
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = addGenresToList;
    xhttp.open("GET", baseURL+"genres");
	xhttp.send();
	
	function addGenresToList() {
		if(xhttp.readyState===4 && xhttp.status===200) {
			console.log(xhttp.responseText);
			let genres = JSON.parse(xhttp.responseText);
			let genreSelect= document.getElementById("b_genres");
			for(let i = 0; i<genres.length; i++) {
				if(book.genres.length==0 || book.genres.find(g => g.id != genres[i].id)) {
					let option = document.createElement("option");
					option.id="gopt_"+genres[i].id;
					option.value=JSON.stringify(genres[i]);
					option.innerHTML=genres[i].genre;
					genreSelect.appendChild(option);
				} else {
					let li = document.createElement("li");
					li.id="gli_"+genres[i].id;
					li.innerHTML=genres[i].genre;
					li.addEventListener("click", removeGenre);
					document.getElementById("selectedGenres").appendChild(li);
				}
			}
		}
	}
}
function addGenre() {
	var genre = JSON.parse(document.getElementById("b_genres").value);
	book.genres.push(genre);
	var option = document.getElementById("gopt_"+genre.id);
	var select = document.getElementById("b_genres");
	select.removeChild(option);
	let li = document.createElement("li");
	li.id="gli_"+genre.id;
	li.innerHTML=genre.genre;
	li.addEventListener("click", removeGenre);
	document.getElementById("selectedGenres").appendChild(li);
}
function removeGenre(event){
	let li = event.target;
	let id = li.id.substring("gli_".length);
	
	// remove from array
	let genre = book.genres.find(g => g.id == id);
	book.genres.splice(book.genres.indexOf(genre),1);
	
	// remove from list
	document.getElementById("selectedGenres").removeChild(li);
	
	// add author back into select
	let option = document.createElement("option");
	option.id="gopt_"+genre.id;
	option.value=JSON.stringify(genre);
	option.innerHTML=genre.genre;
	document.getElementById("b_genres").appendChild(option);
	
}
function checkEnter(){
	event.stopPropagation();
	if(event.which===13){
		if(event.target.id.startsWith("b_")){
			console.log("enter with target");
			updateField();
			submit();
		} else {
			console.log("enter with no target");
			submit();
		}
	}
}
function updateField(){
	console.log(book);
	let id = event.target.id.substring("b_".length);
	book[id]=event.target.value;
	console.log(book);
}

function submit(){
	document.getElementById("submitbtn").disabled=true;
	document.getElementById("backbtn").disabled=true;
	console.log("submit");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=moveToBooks;
	xhttp.open("POST", baseURL+"books/");
	xhttp.send(JSON.stringify(book));

	function moveToBooks() {
		if(xhttp.readyState===4)
			backtobooks();
	}
}
function backtobooks(){
	window.location.href=baseURL+"getBooks";
}
window.onload = function(){
    //console.log(bulba);
    bulba = JSON.parse(bulba);
    console.log(bulba);
    populateDex(bulba);
    let button = document.getElementById("pokemonSubmit");
    button.onclick = getPokemon;
}
function getPokemon(){
    var id = document.getElementById("pokemonId").value;
    // AJAX - Asynchronous JS and XML
    
    // Step 1: Create a new XML Http Request object
    let xhttp = new XMLHttpRequest();
    // Step 2: Set a callback function to onreadystatechange
    xhttp.onreadystatechange=parsePokemon;
    // Step 3: Open the request
    xhttp.open("GET", "https://pokeapi.co/api/v2/pokemon/"+id);
    // Step 4: Send the request
    xhttp.send();


    function parsePokemon(){
        // we want our callback function to have access to the xhttp object
        
        /*
        READY STATE = The state of the request
        0: UNSENT: open() has not been called
        1: OPENED: open() has been called
        2: HEADERS_RECEIVED: send() has been called.
            Response headers and the status are now available
        3: LOADING: Downloading the response. responseText is available
        4: DONE: Operation complete
        */
       // We don't want to do anything until it is done
       if(xhttp.readyState===4 && xhttp.status === 200) {
           // the response is ready and was successful
           let response = xhttp.responseText;
           response = JSON.parse(response);
           populateDex(response);
       }
    }
}
function populateDex(pokemon){
    console.log(pokemon);
    var image = document.getElementById("pokemonImg");
    var name = document.getElementById("pokemonName");
    var stats = document.getElementById("stats");
    var types= document.getElementById("types");

    name.innerHTML=pokemon.name;
    image.src = pokemon.sprites.front_default;
    createStatsTable(stats, pokemon.stats);
    addTypes(types, pokemon.types);
}
function createStatsTable(parent, statArray) {
    parent.innerHTML='';
    var table = document.createElement("table");
    var tableHeaders = document.createElement("tr");
    var tableData = document.createElement("tr");
    table.appendChild(tableHeaders);
    table.appendChild(tableData);
    parent.appendChild(table);

    for(let i = statArray.length-1; i>=0; i--){
        let th = document.createElement("th");
        let td = document.createElement("td");
        th.innerHTML = statArray[i].stat.name;
        td.innerHTML = statArray[i].base_stat;

        tableHeaders.appendChild(th);
        tableData.appendChild(td);
    }
}
function addTypes(parent, typeArray) {
    parent.innerHTML='';
    var primaryType = typeArray.find((type)=>type.slot===1);
    console.log(primaryType);
    var h2 = document.createElement("h2");
    h2.innerHTML=primaryType.type.name;
    parent.appendChild(h2);
    if(typeArray.length>1){
        var secondaryType = typeArray.find((type)=>type.slot===2);
        var h3 = document.createElement("h3");
        h3.innerHTML=secondaryType.type.name;
        parent.appendChild(h3);
    }
}
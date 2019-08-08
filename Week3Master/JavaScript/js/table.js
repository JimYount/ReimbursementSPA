var arr = [
    {
        name: 'Richard Orr',
        color: 'Purple',
        cheese: 'Bleu'
    },
    {
        name: 'Jonathan Soncrant',
        color: 'Teal',
        cheese: 'provolone'
    },
    {
        name: 'Alex Lohse',
        color: 'Green',
        cheese: 'Pepper-jack'
    },
    {
        name: 'Josh Ponti',
        color: 'Green',
        cheese: 'Kraft Singles'
    },
    {
        name: 'Jon Hess',
        color: 'Green',
        cheese: 'Colby Jack'
    },
    {
        name: 'Hans Kiessler',
        color:'black',
        cheese: 'Pepper-jack'
    },
    {
        name: 'Kerwin Chu',
        color: 'Blue',
        cheese: 'Mozzarella'
     },
     {
        name: 'Tammy',
        color: 'Aquamarine',
        cheese: 'Brie'
    },
    {
        name: 'William Thompson',
        color: 'Blue',
        cheese: 'American'
    },
    {
        name: 'Luis Valencia',
        color: 'Navy',
        cheese: 'Mozarella'
    },
    {
        name: 'Jim Yount',
        color: 'Red',
        cheese: 'Provolone'
    },
    {
        name: 'Adam Hemmelgarn',
        color: 'Red',
        cheese: 'American'
    },
    {
        name: 'Dan Zonca',
        color: 'Orange',
        cheese: 'Provolone'
    },
    {
        name: 'Andrew Weber',
        color: 'blue',
        cheese: 'n/a'
    }
];
window.onload=populateTable(arr);
function populateTable(arr){
    var table = document.getElementById("table");
    arr.forEach( (item) => {
        let bob = document.createElement("tr");
        table.appendChild(bob);

        let td = document.createElement('td');
        bob.appendChild(td);
        td.innerHTML=item.name;

        td = document.createElement('td');
        bob.appendChild(td);
        td.innerHTML=item.color;
        td.style.backgroundColor = item.color;

        td = document.createElement('td');
        bob.appendChild(td);
        td.innerHTML=item.cheese;
        
    });
}
/**
 * Create the table
 * 
 */

const address = "http://176.181.167.238:10000/";
let NL; // number of lines
let NC; //number of columns
const promise = fetch(address + "getDim");
promise.then(function(res){
    return res.text();
}).then(function(res){
    console.log(res)
    const dim = JSON.parse(res);
    NL=dim[0];
    NC=dim[1];
    console.log(`tableau de ${NL} par ${NC}`);
    
    let table = document.createElement('table');
    for (let i = 0 ; i<NL ; i++){
        let line = document.createElement('tr');
        line.setAttribute('class',`line${i}`);
        for (let j = 0 ; j<NC ; j++){
            let box = document.createElement('td');
            box.setAttribute('class',`column${j}`);
            box.setAttribute('onclick', 'changeColor(this)')
            line.appendChild(box);
        }
        table.appendChild(line)
    }
    
    document.getElementsByClassName('myTableDiv')[0].appendChild(table);
    
    init();
}).catch(function(){
    console.log("server is down");
    NL = 5;
    NC = 4;
});

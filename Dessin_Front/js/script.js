

function changeColor(obj){
    
    //console.log(obj);
    let colorRGBString = document.getElementsByClassName("jscolor")[0].style.backgroundColor;

    obj.style.backgroundColor = colorRGBString; 

    let str = colorRGBString.split(",")
    console.log(str);
    let r = parseInt(str[0].substring(4));
    let g = parseInt(str[1]);
    let b = parseInt(str[2].substring(0,str[2].length - 1 ));
    console.log(r);
    console.log(g);
    console.log(b);
    const row =obj.parentElement.className.substring(4);
    const column = obj.className.substring(6);
    const promise = putColorInBack(obj.parentElement.className.substring(4), obj.className.substring(6), rgb2hex(r,g,b));
    promise.then(function(result){
        console.log(result);
    });
    
}


const putColorInBack = function(row, column, color){
    let box  = {};
    box.line = row;
    box.column = column;
    box.color = color;   
    console.log(JSON.stringify(box));
    
    return fetch(address + "color",{
        method: "PUT",
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
          'Content-Type': 'application/json'
          // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(box)
    });
    
}
const getColor = function (row, column){
    const request = address + `color/${row}|${column}`;
    //console.log(request);
    const promise = fetch(request,{
        method: "GET",
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        //credentials: 'same-origin', // include, *same-origin, omit
        headers: {
          'Content-Type': 'application/json'
          // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        
    });
    return  promise.then(function(resultat){
        return resultat.text()
    });
}

const init = function (){
    let color;
    for (let i = 0 ; i < NL ; i++){
        for (let j = 0 ; j < NC ; j++){
            let promise  = getColor(i,j);
            promise.then(function(resultat){
                //console.log(typeof resultat);
                let line = document.getElementsByClassName(`line${i}`)[0];
                
                
                let cas = line.getElementsByClassName(`column${j}`)[0];
                //console.log(cas);
                cas.style.backgroundColor = `#${resultat}`;
            });
        }
    }
}
const rgb2hex = function(r ,g, b){
    let rs = r.toString(16)
    let gs = g.toString(16)
    let bs = b.toString(16)
    rs = "0".repeat(2 - rs.length) + rs;
    gs = "0".repeat(2 - gs.length) + gs;
    bs = "0".repeat(2 - bs.length) + bs;
    console.log(`${rs}${gs}${bs}`);
    return `${rs}${gs}${bs}`;
}
console.log('qui est le premier');

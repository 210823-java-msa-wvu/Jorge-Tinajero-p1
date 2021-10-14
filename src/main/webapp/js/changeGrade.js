let gradeUrl = 'http://localhost:8080/p1/gradechange';
let baseUrl = 'http://localhost:8080/p1/employeereimbursement?';
function updateGrade(){
    


    

    // console.log('Button clicked');

    //let userInput = document.getElementById('dataInput').value;
    //console.log('User has input: ' + userInput);
    // let url = baseUrl;// + userInput;
    
    // 4 Steps to building an AJAX call

    // STEP 1: Create our XMLHttpRequest Object
    let xhttp = new XMLHttpRequest();

    // STEP 2: Set a callback function for the readystatechange event
    xhttp.onreadystatechange = receiveData;

    // STEP 3: Open the request
    xhttp.open('GET', baseUrl, true); // true is the default argument - meaning that this is async

    // STEP 4: Send the request
    xhttp.send();
    function receiveData(){
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let r = xhttp.responseText;
            // console.log(r);

            r = JSON.parse(r);
            // console.log(r);
            //might require for loop
            for(var i=0; i<r.length; i++){
                updating(r,i);
            }

        }
    }   


    function updating(res, index){

        console.log('updating grades inside updating');

        let respArray = res[index];
    
        // console.log(respArray.pass);
        // STEP 1: Create our XMLHttpRequest Object

            let xhttpsecond = new XMLHttpRequest();
            
            // respArray.
            console.log(respArray);
            var myGrades = document.getElementById("gradingType");
            let pass = {letter: myGrades.options[myGrades.selectedIndex].text};
            //let grade = JSON.stringify();
            console.log(pass.letter);

            respArray.pass = pass.letter;
            console.log(respArray.pass + " the value");


            // STEP 2: Set a callback function for the readystatechange event
            //xhttp.onreadystatechange = receiveData;
            // STEP 3: Open the request
            passString = JSON.stringify(respArray);
            xhttpsecond.open('POST', gradeUrl, true); // true is the default argument - meaning that this is async

            // STEP 4: Send the request
            //window.location.assign("/p1/welcomeEmp.html");
            xhttpsecond.send(passString);
            
        
        
    }
}
